package ku.cs.services;

import ku.cs.models.User;
import ku.cs.models.AccountList;

public class UserListHardCodeDataSource {
    private AccountList userList;

    public UserListHardCodeDataSource() {
        userList = new AccountList();
        readData();
    }

    public void readData() {
        userList.addAccount(new User("dujrawee", "dujduj"));
        userList.addAccount(new User("kan", "shokun"));
        userList.addAccount(new User("thanaphat", "zonzon"));
    }


    public AccountList getUserList() {
        return userList;
    }
}
