package ku.cs.models.accounts;

import ku.cs.services.datasource.DataSource;
import ku.cs.services.datasource.accounts.AccountListFileDataSource;
import ku.cs.services.filter.AccountUsernameFilter;
import ku.cs.util.Data;

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
        if(account == null) return null;
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
    public ArrayList<Moderator> getAllMod(){
        ArrayList<Moderator> mods = new ArrayList<>();
        for(Account a : accounts) if(a instanceof Moderator) mods.add((Moderator) a);
        return mods;
    }
}
