package ku.cs.services.filter;

import ku.cs.models.complaints.Complaint;

import java.util.UUID;

public class ComplaintIdFilter implements Filterer<Complaint> {

    @Override
    public boolean found(Complaint obj, String filter) {
        if (obj.getId().equals(UUID.fromString(filter))) return true;
        return false;
    }

    @Override
    public String getName() {
        return "id";
    }
}
