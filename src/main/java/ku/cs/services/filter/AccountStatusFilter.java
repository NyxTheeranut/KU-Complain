package ku.cs.services.filter;

import ku.cs.models.accounts.Account;

public class AccountStatusFilter implements Filterer<Account> {
    @Override
    public boolean found(Account obj, String filter) {
        if (((Boolean) obj.isBanned()).toString().equals(filter)) return true;
        return false;
    }

    @Override
    public String getName() {
        return "role";
    }
}
