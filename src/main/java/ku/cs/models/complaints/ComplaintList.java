package ku.cs.models.complaints;

import java.util.ArrayList;

public class ComplaintList {
    private ArrayList<Complaint> complaints;

    public ComplaintList(){
        complaints = new ArrayList<>();
    }
    public void addComplaint(Complaint complaint){
        complaints.add(complaint);
    }
    public void setComplaints(ArrayList<Complaint> complaints) {
        this.complaints = complaints;
    }
    public ArrayList<Complaint> getAllComplaints(){
        return complaints;
    }
}
