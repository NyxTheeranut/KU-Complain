package ku.cs.services.filter;

import ku.cs.models.accounts.Account;

public class AccountIdFilter implements Filterer<Account> {

    @Override
    public boolean found(Account obj, String filter) {
        if (obj.getId().equals(filter)) return true;
        return false;
    }

    @Override
    public String getName() {
        return "id";
    }
}
