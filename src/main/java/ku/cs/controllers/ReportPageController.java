package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import ku.cs.models.reports.ReportList;
import ku.cs.services.reports.ReportListFileDataSource;

import javax.swing.*;
import java.io.IOException;

public class ReportPageController {


    @FXML public void handleBackButton(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("complaint_post");
        } catch (IOException e){
            System.err.println(e);
        }
    }

    @FXML public void handleReportButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("complaint_post");
        } catch (IOException e){
            System.err.println(e);
        }
    }

    /*
        ku.cs.services.DataSource<ReportList> dataSource = new ReportListFileDataSource();
        ReportList reportList = dataSource.readData();
        reportList.addReport(report);
        dataSource.writeData(reportList);
    */
}
