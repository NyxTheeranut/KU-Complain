package ku.cs.services.searcher;

import ku.cs.models.complaints.Complaint;

public class SearchComplaintById implements Searcher<Complaint> {
    @Override
    public boolean found(Complaint obj, String id) {
        if (obj.getId().equals(id)) return true;
        return false;
    }
}
