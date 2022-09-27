package ku.cs.services.searcher;

import ku.cs.models.complaints.Complaint;

public class SearchComplaintByName implements Searcher<Complaint>{
    @Override
    public boolean found(Complaint obj, String name) {
        if (obj.getId().equals(name)) return true;
        return false;
    }
}
