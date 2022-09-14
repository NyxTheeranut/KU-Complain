package ku.cs.services.accounts;

import ku.cs.models.accounts.*;
import ku.cs.services.DataSource;

import java.io.*;

public class AccountListFileDataSource implements DataSource<AccountList> {
    private final String directoryName = "src/main/resources/ku/cs/data/";
    private final String fileName = "account_list.csv";

    public AccountList readData() {
        AccountList accountList = new AccountList();
        BufferedReader buffer = null;
        Account account = null;

        try {
            InputStream inputStream = getClass().getResourceAsStream("/ku/cs/data/account_list.csv");
            buffer = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while((line = buffer.readLine()) != null){
                String[] data = line.split(",");
                Boolean isBanned;
                if (data[7].equals("0")) isBanned = false;
                else isBanned = true;
                if (data[0].equals("admin")){
                    account = new Admin(data[1], data[2], data[3], data[4], data[5], data[6], isBanned);
                }
                else if (data[0].equals("mod")){
                    account = new Moderator(data[1], data[2], data[3], data[4], data[5], data[6], isBanned);
                }
                else if (data[0].equals("user")){
                    account = new User(data[1], data[2], data[3], data[4], data[5], data[6], isBanned);
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
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return accountList;
    }

    public void writeData(AccountList accountList) {
        try {
            OutputStream outputStream = new FileOutputStream("/ku/cs/data/account_list.csv");
        } catch (FileNotFoundException e) {
            System.err.println("Invalid file path");
        }

        for(Account account : accountList.getAllAccount()) {
            //role,name,password,imagepath
            String line = account.getRole() + ","
                    + account.getName() + ","
                    + account.getPassword() + ","
                    + account.getImagePath();
        }
    }
}
