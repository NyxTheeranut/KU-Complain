package ku.cs.services.filter;

import ku.cs.models.accounts.Account;

import java.util.UUID;

public class AccountRoleFilter implements Filterer<Account>{
    @Override
    public boolean found(Account obj, String filter) {
        if (filter.equals("")) return false;
        if (obj.getRole().equals(filter)) return true;
        return false;
    }

    @Override
    public String getName() {
        return "role";
    }
}
