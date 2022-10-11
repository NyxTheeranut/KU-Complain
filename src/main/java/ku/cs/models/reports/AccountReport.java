package ku.cs.models.reports;

import ku.cs.models.accounts.Account;

import java.util.UUID;

public class AccountReport extends Report{
    public AccountReport(String type, UUID authorId, String topic, String description) {
        super("Account", authorId, topic, description);

    }
}
