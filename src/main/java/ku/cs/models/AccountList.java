package ku.cs.models;

import java.util.ArrayList;

public class AccountList {
    private ArrayList<Account> accounts;

    public AccountList() {
        accounts = new ArrayList<>();
    }

    public void addAccount(Account account){
        accounts.add(account);
    }

    public ArrayList<Account> getAllUser() {
        return accounts;
    }

    public Account getAccount(String name) {

        for (Account account:accounts) {
            if (account.getName().equals(name.strip())) {
                return account;
            }
        }
        return null;
    }


}
