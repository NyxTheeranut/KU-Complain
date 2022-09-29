package ku.cs.services.filter;

import ku.cs.models.complaints.Complaint;

public class ComplaintIdFilter implements Filterer<Complaint> {

    @Override
    public boolean found(Complaint obj, String filter) {
        if (obj.getId().equals(filter)) return true;
        return false;
    }

    @Override
    public String getName() {
        return "id";
    }
}
