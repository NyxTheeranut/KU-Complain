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

import javax.swing.*;


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

    @FXML public void handleReportButton(ActionEvent actionEvent) {
        String topic = topicTextField.getText();
        String detail = detailTextField.getText();
        report(topic, detail);

    }

    @FXML public void radioButtonSelected(ActionEvent actionEvent) {
        if (ComplaintReportButton.isSelected()) {
            report.setType("Complaint");
        }
        if (AccountReportButton.isSelected()) {
            report.setType("Account");
        }
    }

    private void report(String topic, String detail) {
        DataSource<ReportList> dataSource = new ReportListFileDataSource();
        ReportList reportList = dataSource.readData();
        Report report = new Report(,topic, detail);
        reportList.addReport(report);
        dataSource.writeData(reportList);
    }
    @FXML public void handleBackButton(ActionEvent actionEvent){
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }

}
