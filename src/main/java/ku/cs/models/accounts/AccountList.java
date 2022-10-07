package ku.cs.models.accounts;

import ku.cs.util.Util;
import ku.cs.services.filter.AccountUsernameFilter;

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

        Account account = Util.search(username, getAllAccount(), new AccountUsernameFilter());
        if (account.getPassword().equals(password)) {
            return account;
        }
        return null;
    }

    public Boolean checkRegister(String username) {
        if (Util.search(username, accounts, new AccountUsernameFilter()) == null) {
            return true;
        }
        return false;
    }
    
    public void changePassword(Account account, String password){
        for(Account i : accounts){
            if (i.getName().equals(account.getName())){
                i.setPassword(password);
                return;
            }

        }

    }

    public void changeName(Account account, String name){
        for(Account i : accounts){
            if (i.getName().equals(account.getName())){
                i.setName(name);
                return;
            }

        }

    }
    public void changeSurname(Account account, String surname){
        for(Account i : accounts){
            if (i.getName().equals(account.getName())){
                i.setSurname(surname);
                return;
            }

        }

    }
    public void changePicture(Account account, String picture){
        for(Account i : accounts){
            if (i.getName().equals(account.getName())){
                i.setImagePath(picture);
                return;
            }

        }

}}
