package ku.cs.services.filter;

import ku.cs.models.accounts.Account;
import ku.cs.models.accounts.Moderator;

public class AccountUnitFilter implements Filterer<Account> {

    @Override
    public boolean found(Account obj, String filter) {
        if (!(obj instanceof Moderator)) return false;
        return ((Moderator) obj).getUnit().equals(filter);
    }

    @Override
    public String getName() {
        return "unit";
    }
}
