package ku.cs.services.comparator;

import ku.cs.models.complaints.Complaint;

import java.util.Comparator;

public class CompareComplaintById implements Comparator<Complaint> {
    @Override
    public int compare(Complaint o1, Complaint o2) {
        return Integer.parseInt(o1.getId())-Integer.parseInt(o2.getId());
    }

    @Override
    public Comparator<Complaint> reversed() {
        return Comparator.super.reversed();
    }
}
