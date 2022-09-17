package ku.cs.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import ku.cs.models.complaints.Complaint;
import ku.cs.models.complaints.ComplaintList;
import ku.cs.objectcollector.DataBank;
import ku.cs.services.complaints.ComplaintListFileDataSource;
import ku.cs.services.DataSource;
import com.github.saacsos.FXRouter;

import java.io.IOException;

public class ComplaintListPageController {
    @FXML private ListView<Complaint> complaintListView;

    //private ComplaintListHardCodeDataSource dataSource;
    private DataSource<ComplaintList> dataSource;
    private ComplaintList complaintList;

    public void initialize(){ //get data from dataSource
        dataSource = new ComplaintListFileDataSource();
        complaintList = dataSource.readData();
        showListView();
        handleSelectedComplaint();
    }

    private void showListView(){
        complaintListView.getItems().addAll(complaintList.getAllComplaints());
        complaintListView.refresh();
    }

    private void handleSelectedComplaint() {
        complaintListView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Complaint>() {
                    @Override
                    public void changed(ObservableValue<? extends Complaint> observable,
                                        Complaint oldValue, Complaint newValue) {
                        System.out.println(newValue + " is selected");
                        showSelectedComplaint(newValue);

                    }
                });
    }

    private void showSelectedComplaint(Complaint complaint) {
        DataBank.complaint = complaint;
        try {
            FXRouter.goTo("complaint");
        } catch (IOException e) {
            System.err.println("Error loading complaint page");
        }
    }
}
