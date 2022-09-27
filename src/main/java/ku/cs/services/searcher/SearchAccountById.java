package ku.cs.services.searcher;

import ku.cs.models.accounts.Account;

public class SearchAccountById implements Searcher<Account> {

    @Override
    public boolean found(Account obj, String id) {
        if (obj.getId().equals(id)) return true;
        return false;
    }
}
