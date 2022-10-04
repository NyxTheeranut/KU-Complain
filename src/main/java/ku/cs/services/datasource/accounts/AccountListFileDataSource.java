package ku.cs.services.datasource.accounts;

import ku.cs.models.accounts.*;
import ku.cs.services.datasource.DataSource;

import java.io.*;
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
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);
            String line = "";
            while((line = buffer.readLine()) != null){
                String[] data = line.split(",");
                Boolean isBanned;
                if (data[7].equals("0")) isBanned = false;
                else isBanned = true;
                if (data[0].equals("admin")){
                    account = new Admin(UUID.fromString(data[1]), data[2], data[3], data[4], data[5], data[6], isBanned);
                }
                else if (data[0].equals("mod")){
                    account = new Moderator(UUID.fromString(data[1]), data[2], data[3], data[4], data[5], data[6], isBanned);
                }
                else if (data[0].equals("user")){
                    account = new User(UUID.fromString(data[1]), data[2], data[3], data[4], data[5], data[6], isBanned);
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
                reader.close();
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
            writer = new FileWriter(file);
            buffer = new BufferedWriter(writer);
            for(Account account : accountList.getAllAccount()) {
                String isBanned;
                if (account.getIsBanned())  isBanned = "1";
                else isBanned = "0";
                //role,name,password,imagepath
                String line = account.getRole() + ","
                        + account.getId().toString() + ","
                        + account.getUsername() + ","
                        + account.getPassword() + ","
                        + account.getName() + ","
                        + account.getSurname() + ","
                        + account.getImagePath() + ","
                        + isBanned;
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
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }
}