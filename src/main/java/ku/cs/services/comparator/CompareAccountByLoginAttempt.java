package ku.cs.services.comparator;

import ku.cs.models.accounts.Account;
import ku.cs.models.accounts.User;

import java.util.Comparator;

public class CompareAccountByLoginAttempt implements Comparator<Account> {

    @Override
    public int compare(Account o1, Account o2) {
        if (o1 instanceof User && o2 instanceof User) {
            return ((User)o2).getLoginAttempt() - ((User)o1).getLoginAttempt();
        }
        return 0;
    }

    @Override
    public Comparator<Account> reversed() {
        return Comparator.super.reversed();
    }
}
