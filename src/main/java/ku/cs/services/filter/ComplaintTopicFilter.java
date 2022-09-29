package ku.cs.services.filter;

import ku.cs.models.complaints.Complaint;

public class ComplaintTopicFilter implements Filterer<Complaint> {
    @Override
    public boolean found(Complaint obj, String filter) {
        if (obj.getTopic().contains(filter)) return true;
        return false;
    }

    @Override
    public String getName() {
        return "topic";
    }
}
