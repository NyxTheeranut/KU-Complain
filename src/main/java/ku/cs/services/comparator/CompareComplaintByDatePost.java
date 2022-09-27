package ku.cs.services.comparator;

import ku.cs.models.complaints.Complaint;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;

public class CompareComplaintByDatePost implements Comparator<Complaint> {
    @Override
    public int compare(Complaint o1, Complaint o2) {
        if (o1.getDatePosted().isBefore(o2.getDatePosted())) return -1;
        else if (o1.getDatePosted().isEqual(o2.getDatePosted())) return 0;
        return -1;
    }

    @Override
    public Comparator<Complaint> reversed() {
        return Comparator.super.reversed();
    }
}
