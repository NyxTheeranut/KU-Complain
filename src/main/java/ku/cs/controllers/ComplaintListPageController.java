package ku.cs.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import ku.cs.models.Complaint;
import ku.cs.models.ComplaintList;
import ku.cs.services.ComplaintListHardCodeDataSource;

public class ComplaintListPageController {
    @FXML private ListView<Complaint> complaintListView;

    private ComplaintListHardCodeDataSource dataSource;
    private ComplaintList complaintList;

    public void initialize(){
        dataSource = new ComplaintListHardCodeDataSource();
        complaintList = dataSource.getComplaintList();
        showListView();
    }

    private void showListView(){
        complaintListView.getItems().addAll(complaintList.getAllComplaints());
        complaintListView.refresh();
    }
}
