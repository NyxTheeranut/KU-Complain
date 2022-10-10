package ku.cs.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import ku.cs.models.accounts.Account;
import ku.cs.models.accounts.Moderator;
import ku.cs.models.complaints.Complaint;
import ku.cs.models.complaints.ComplaintList;
import ku.cs.models.complaints.Status;
import ku.cs.services.datasource.DataSource;
import ku.cs.services.datasource.complaints.ComplaintListFileDataSource;
import ku.cs.services.filter.ComplaintIdFilter;
import ku.cs.util.Data;
import ku.cs.util.ObjectStorage;

public class ManageComplaintController {
    @FXML private ComboBox statusComboBox;
    @FXML private TextArea solvingDetailTextArea;
    @FXML private Label errorLabel;

    public void initialize() {
        setupStatusComboBox();
        errorLabel.setText("");
        solvingDetailTextArea.setWrapText(true);
    }

    private void setupStatusComboBox() {
        statusComboBox.setItems(FXCollections.observableArrayList(
                "NOTSTARTED",
                "INPROGRESS",
                "DONE"
        ));
    }

    public void handleSaveButton() {
        Status status;
        try {
            status = Status.valueOf((String) statusComboBox.getValue());
        }catch (NullPointerException e){
            errorLabel.setTextFill(Color.RED);
            errorLabel.setText("กรุณาใส่สถานะการแก้ไข");
            return;
        }

        String solvingDetail = solvingDetailTextArea.getText();

        ObjectStorage objectStorage = (ObjectStorage) com.github.saacsos.FXRouter.getData();
        Account account = objectStorage.getAccount();
        Complaint complaint = objectStorage.getComplaint();

        complaint.setModerator((Moderator) account);
        complaint.setStatus(status);
        complaint.setSolvingDetail(solvingDetail);

        DataSource<ComplaintList> dataSource = new ComplaintListFileDataSource();
        ComplaintList complaintList = dataSource.readData();

        Complaint complaint1 = Data.search(complaint.getId().toString(), complaintList.getAllComplaints(), new ComplaintIdFilter());

        complaint1.setModerator((Moderator) account);
        complaint1.setStatus(status);
        complaint1.setSolvingDetail(solvingDetail);

        if (status == Status.NOTSTARTED) {
            complaint1.setModerator(null);
            complaint1.setSolvingDetail("");
        }

        dataSource.writeData(complaintList);

        errorLabel.setTextFill(Color.GREEN);
        errorLabel.setText("อัปเดตสถานะเรื่องร้องเรียนสำเร็จ");
    }
}
