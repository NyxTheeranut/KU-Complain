package ku.cs.services.datasource.accounts;

import ku.cs.models.accounts.*;
import ku.cs.services.datasource.DataSource;
import ku.cs.util.Spliter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class AccountListFileDataSource implements DataSource<AccountList> {
    private final String directoryName = "data";
    private final String fileName = "account_list.csv";

    public AccountList readData() {
        AccountList accountList = new AccountList();
        File file = new File(directoryName +File.separator+ fileName);
        FileReader reader = null;
        BufferedReader buffer = null;
        Account account = null;
        try {
            buffer = new BufferedReader(new InputStreamReader(
                    new FileInputStream(file), "UTF-8"));
            String line = "";
            while((line = buffer.readLine()) != null){
                // 0    1     2       3       4     5         6        7        8            9            10     11
                //role,id,username,password,name,surname,default.jpg,lastLogin,isBan,unban request,loginAttempt,unit
                //user,id,username,password,name,surname,default.jpg,lastLogin,isBan,unban request,loginAttempt,unit
                //admin,id,username,password,name,surname,default.jpg,lastLogin
                //mod,id,username,password,name,surname,default.jpg,lastLogin,unit

                String[] data = Spliter.split(line,",");

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime lastLogin = LocalDateTime.parse(data[7], formatter);
                if (data[0].equals("admin")){
                    account = new Admin(UUID.fromString(data[1]), data[2], data[3], data[4], data[5], data[6], lastLogin);
                }
                else if (data[0].equals("mod")){
                    account = new Moderator(UUID.fromString(data[1]), data[2], data[3], data[4], data[5], data[6], lastLogin, data[11]);
                }
                else if (data[0].equals("user")){
                    account = new User(UUID.fromString(data[1]), data[2], data[3], data[4], data[5], data[6], lastLogin, data[8].equals("true"), data[9], Integer.parseInt(data[10]));
                }
                accountList.addAccount(account); //add account to account list
            }
        }catch (FileNotFoundException e){
            throw new RuntimeException(e);
        }catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                buffer.close();
                //reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return accountList;
    }

    public void writeData(AccountList accountList) {
        File file = new File(directoryName + File.separator + fileName);
        FileWriter writer = null;
        BufferedWriter buffer = null;
        try {
//            writer = new FileWriter(file);
//            buffer = new BufferedWriter(writer);
            buffer = new BufferedWriter(new FileWriter(file, StandardCharsets.UTF_8));

            for(Account account : accountList.getAllAccount()) {

                String line;

                if (account instanceof User) {
                    line = ((User) account).format();
                }
                else if (account instanceof Moderator) {
                    line = ((Moderator) account).format();
                }
                else if (account instanceof Admin) {
                    line = ((Admin) account).format();
                }
                else continue;

                buffer.append(line);
                buffer.newLine();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Invalid file path");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                buffer.close();
                //writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }
}
