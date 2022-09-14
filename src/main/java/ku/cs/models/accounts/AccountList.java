package ku.cs.models.accounts;

import ku.cs.objectcollector.DataSource;

import java.io.IOException;
import java.util.ArrayList;

public class AccountList {
    private ArrayList<Account> accounts;

    public AccountList() {
        accounts = new ArrayList<>();
    }

    public void addAccount(Account account){
        accounts.add(account);
    }

    public ArrayList<Account> getAllAccount() {
        return accounts;
    }

    public Account checkLogin(String username, String password) {
        for (Account account : getAllAccount()) {
            if (account.getName().equals(username)) {
                if (account.getPassword().equals(password)) {
                    return account;
                }
                break;
            }
        }
        return null;
    }

    public Account getAccount(String data) {
        Account account = null;
        account = getAccountByUsername(data);
        if (account != null) return account;
        account = getAccountById(data);
        if (account != null) return account;
        return null;
    }

    public Account getAccountByUsername(String username) {
        for (Account account:accounts) {
            if (account.getName().equals(username.strip())) {
                return account;
            }
        }
        return null;
    }

    public Account getAccountById(String id) {
        for (Account account:accounts) {
            if (account.getId().equals(id.strip())) {
                return account;
            }
        }
        return null;
    }


}
