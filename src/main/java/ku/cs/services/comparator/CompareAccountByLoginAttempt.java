package ku.cs.services.comparator;

import ku.cs.models.accounts.Account;

import java.util.Comparator;

public class CompareAccountByLoginAttempt implements Comparator<Account> {

    @Override
    public int compare(Account o1, Account o2) {
        return o2.getLoginAttempt() - o1.getLoginAttempt();
    }

    @Override
    public Comparator<Account> reversed() {
        return Comparator.super.reversed();
    }
}
