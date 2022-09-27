package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ku.cs.models.reports.Report;
import ku.cs.models.reports.ReportList;
import ku.cs.services.reports.ReportListFileDataSource;


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

    @FXML public void handleReportButton(ActionEvent actionEvent) {
        ku.cs.services.datasource.DataSource<ReportList> dataSource = new ReportListFileDataSource();
        ReportList reportList = dataSource.readData();
        reportList.addReport(report);
        dataSource.writeData(reportList);
    }

    @FXML public void handleBackButton(ActionEvent actionEvent){
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }

}
