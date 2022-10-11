package ku.cs.models.reports;

import java.util.UUID;

public class ComplaintReport extends Report{

    public ComplaintReport(String type, String postId, String topic, String description) {
        super("Complaint", postId, topic, description);

    }
}
