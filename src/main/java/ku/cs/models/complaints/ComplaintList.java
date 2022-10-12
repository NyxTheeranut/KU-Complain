package ku.cs.models.complaints;

import ku.cs.models.accounts.Account;
import ku.cs.services.filter.ComplaintIdFilter;
import ku.cs.util.Data;

import java.util.ArrayList;
import java.util.UUID;

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
    public void removeComplaint(UUID id){
        complaints.remove(Data.search(id.toString(),complaints,new ComplaintIdFilter()));
    }
    public ArrayList<Complaint> getAllComplaints(){
        return complaints;
    }


}
