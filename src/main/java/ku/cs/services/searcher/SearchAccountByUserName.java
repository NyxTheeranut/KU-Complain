package ku.cs.services.searcher;

import ku.cs.models.accounts.Account;

public class SearchAccountByUserName implements Searcher<Account> {

    @Override
    public boolean found(Account obj, String userName) {
        if (obj.getUsername().equals(userName)) return true;
        return false;
    }
}
