package ku.cs.services.comparator;

import ku.cs.models.complaints.Complaint;

import java.util.Comparator;

public class CompareComplaintByName implements Comparator<Complaint> {

    @Override
    public int compare(Complaint o1, Complaint o2) {
        return o1.getTopic().compareTo(o2.getTopic());
    }

    @Override
    public Comparator<Complaint> reversed() {
        return Comparator.super.reversed();
    }
}
