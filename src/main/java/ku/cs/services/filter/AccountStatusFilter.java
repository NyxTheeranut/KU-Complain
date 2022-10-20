package ku.cs.services.filter;

import ku.cs.models.accounts.Account;
import ku.cs.models.accounts.User;

public class AccountStatusFilter implements Filterer<Account> {
    @Override
    public boolean found(Account obj, String filter) {
        if (obj instanceof User) {
            if (((Boolean) ((User)obj).isBanned()).toString().equals(filter)) return true;
        }
        return false;
    }

    @Override
    public String getName() {
        return "role";
    }
}
