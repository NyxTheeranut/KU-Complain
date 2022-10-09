package ku.cs.models.accounts;

import ku.cs.services.datasource.DataSource;
import ku.cs.services.datasource.accounts.AccountListFileDataSource;
import ku.cs.util.Data;
import ku.cs.util.Util;
import ku.cs.services.filter.AccountUsernameFilter;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class AccountList {
    private ArrayList<Account> accounts;

    public AccountList() {
        accounts = new ArrayList<>();
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public ArrayList<Account> getAllAccount() {
        return accounts;
    }

    public Account checkLogin(String username, String password) {

        Account account = Data.search(username, getAllAccount(), new AccountUsernameFilter());
        if (account.getPassword().equals(password)) {
            account.setLastLogin(LocalDateTime.now());
            DataSource<AccountList> dataSource = new AccountListFileDataSource();
            dataSource.writeData(this);
            return account;
        }
        return null;
    }

    public Boolean checkRegister(String username) {
        if (Data.search(username, accounts, new AccountUsernameFilter()) == null) {
            return true;
        }
        return false;
    }

    public void changePassword(Account account, String password) {
        for (Account i : accounts) {
            if (i.getName().equals(account.getName())) {
                i.setPassword(password);
                return;
            }

        }

    }

    public void changePicture(Account account, String picture) {
        for (Account i : accounts) {
            if (i.getName().equals(account.getName())) {
                i.setImagePath(picture);
                return;
            }

        }
    }

    public void getBaned(Account account, Boolean ban){
        for (Account i : accounts) {
            if (i.getName().equals(account.getName())) {
                i.setBanned(ban);
                return;
            }
        }
    }
}