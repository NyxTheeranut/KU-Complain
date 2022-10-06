package ku.cs.services.filter;

import ku.cs.models.complaints.Complaint;

public class ComplaintVoteFilter implements Filterer<Complaint> {
    @Override
    public boolean found(Complaint obj, String filter) {
        String[] t = filter.split(",");
        int from, to;
        try {
            from = Integer.parseInt(t[0]);
        } catch (NumberFormatException e) {
            from = 0;
        } catch (ArrayIndexOutOfBoundsException e) {
            from = 0;
        }
        try {
            to = Integer.parseInt(t[1]);
        } catch (NumberFormatException e) {
            to = Integer.MAX_VALUE;
        } catch (ArrayIndexOutOfBoundsException e) {
            to = Integer.MAX_VALUE;
        }

        if (obj.getVote() >= from && obj.getVote() <= to) return true;
        return false;
    }

    @Override
    public String getName() {
        return "vote";
    }
}
