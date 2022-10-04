package ku.cs.services.comparator;

import ku.cs.models.complaints.Complaint;

import java.util.Comparator;

public class CompareComplaintByDownVote implements Comparator<Complaint> {
    @Override
    public int compare(Complaint o1, Complaint o2) {
        return o2.getDownVote() - o1.getDownVote();
    }

    @Override
    public Comparator<Complaint> reversed() {
        return Comparator.super.reversed();
    }
}
