package ku.cs.services.filter;

import ku.cs.models.accounts.Account;

public class AccountUsernameFilter implements Filterer<Account> {

    @Override
    public boolean found(Account obj, String filter) {
        if (obj.getUsername().equals(filter)) return true;
        return false;
    }

    @Override
    public String getName() {
        return "username";
    }
}
