package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import ku.cs.services.datasource.DataSource;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ku.cs.models.reports.Report;
import ku.cs.models.reports.ReportList;
import ku.cs.services.reports.ReportListFileDataSource;
import ku.cs.util.ObjectStorage;
import ku.cs.util.Util;

import javax.swing.*;
import java.util.UUID;
import com.github.saacsos.FXRouter;

public class ReportPageController {

    Report report;
    @FXML
    Button reportButton;
    @FXML
    Button backButton;
    @FXML
    TextField topicTextField;
    @FXML
    TextField detailTextField;
    @FXML RadioButton ComplaintReportButton;
    @FXML RadioButton AccountReportButton;


    public void handleReportButton() {
        String topic = topicTextField.getText();
        String detail = detailTextField.getText();
        UUID id;
        String type;

        if(ComplaintReportButton.isSelected()) {
            type = "Complaint";
            id = ((ObjectStorage) FXRouter.getData()).getComplaint().getId();
        }
        else {
            type = "Account";
            //id = Util.complaint.getAuthor().getId();
            id = ((ObjectStorage) FXRouter.getData()).getComplaint().getAuthor().getId();
        }
        report(type, id, topic, detail);

        Stage stage = (Stage) reportButton.getScene().getWindow();
        stage.close();
    }

    //user,1,topic,detail

    private void report(String type,UUID id, String topic, String detail) {
        DataSource<ReportList> dataSource = new ReportListFileDataSource();
        ReportList reportList = dataSource.readData();
        Report report = new Report(type,id,topic, detail);
        reportList.addReport(report);
        dataSource.writeData(reportList);
    }
    @FXML public void handleBackButton(ActionEvent actionEvent){
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }

}
