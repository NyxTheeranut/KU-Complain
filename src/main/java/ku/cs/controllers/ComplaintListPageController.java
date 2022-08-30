package ku.cs.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import ku.cs.models.Complaint;
import ku.cs.models.ComplaintList;
import ku.cs.services.ComplaintListFileDataSource;
import ku.cs.services.ComplaintListHardCodeDataSource;
import ku.cs.services.DataSource;

public class ComplaintListPageController {
    @FXML private ListView<Complaint> complaintListView;

    //private ComplaintListHardCodeDataSource dataSource;
    private DataSource<ComplaintList> dataSource;
    private ComplaintList complaintList;

    public void initialize(){ //get data from dataSource
        dataSource = new ComplaintListFileDataSource();
        complaintList = dataSource.readData();
        showListView();
    }

    private void showListView(){
        complaintListView.getItems().addAll(complaintList.getAllComplaints());
        complaintListView.refresh();
    }
}
