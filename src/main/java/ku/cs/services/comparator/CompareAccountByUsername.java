package ku.cs.services.comparator;

import ku.cs.models.accounts.Account;

import java.util.Comparator;

public class CompareAccountByUsername implements Comparator<Account> {

    @Override
    public int compare(Account o1, Account o2) {
        return o1.getUsername().compareTo(o2.getUsername());
    }

    @Override
    public Comparator<Account> reversed() {
        return Comparator.super.reversed();
    }
}
