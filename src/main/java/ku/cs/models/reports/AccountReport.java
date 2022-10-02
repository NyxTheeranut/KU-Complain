package ku.cs.models.reports;

import ku.cs.models.accounts.Account;

public class AccountReport extends Report{
    public AccountReport(String type, String authorId, String topic, String description) {
        super("Account", authorId, topic, description);

    }
}
