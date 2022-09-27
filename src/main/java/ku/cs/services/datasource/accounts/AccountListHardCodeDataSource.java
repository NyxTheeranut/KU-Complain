package ku.cs.services.datasource.accounts;

import ku.cs.models.accounts.AccountList;

public class AccountListHardCodeDataSource {
    private AccountList userList;

    public AccountListHardCodeDataSource() {
        userList = new AccountList();
        readData();
    }

    public void readData() {
        /*userList.addAccount(new User("dujrawee", "dujduj"));
        userList.addAccount(new User("kan", "shokun"));
        userList.addAccount(new User("thanaphat", "zonzon"));*/
    }


    public AccountList getUserList() {
        return userList;
    }
}
