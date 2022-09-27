package ku.cs.services.datasource.complaints;

import ku.cs.models.complaints.ComplaintList;

public class ComplaintListHardCodeDataSource {
    private ComplaintList complaintList;

    public ComplaintListHardCodeDataSource(){
        complaintList = new ComplaintList();
        readData();
    }

    public void readData(){
        //complaintList.addComplaint(new Complaint("แบะๆๆ","บาบาบิบุ"));
        //complaintList.addComplaint(new Complaint("แบะๆๆๆ","บุบาบิเบอ"));
        //complaintList.addComplaint(new Complaint("แบะๆๆๆๆ","บิบุบาเบ"));
        //complaintList.addComplaint(new Complaint("แบะๆๆๆๆๆ","บาบิบุบอ"));
        //complaintList.addComplaint(new Complaint("แบะๆๆๆๆๆๆ","บอบาบุบิ"));
    }

    public ComplaintList getComplaintList(){
        return complaintList;
    }
}
