package ku.cs.models.accounts;

import ku.cs.util.Util;
import ku.cs.services.searcher.SearchAccountByUserName;

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

        Account account = Util.search(username, getAllAccount(), new SearchAccountByUserName());
        if (account.getPassword().equals(password)) {
            return account;
        }
        return null;
    }

    public Boolean checkRegister(String username) {
        if (Util.search(username, accounts, new SearchAccountByUserName()) != null) {
            return true;
        }
        return false;
    }


}
