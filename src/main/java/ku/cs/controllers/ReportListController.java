package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import ku.cs.models.accounts.Account;
import ku.cs.models.accounts.AccountList;
import ku.cs.models.complaints.Complaint;
import ku.cs.models.complaints.ComplaintList;
import ku.cs.models.reports.Report;
import ku.cs.models.reports.ReportList;
import ku.cs.services.datasource.DataSource;
import ku.cs.services.datasource.accounts.AccountListFileDataSource;
import ku.cs.services.datasource.complaints.ComplaintListFileDataSource;
import ku.cs.services.filter.AccountIdFilter;
import ku.cs.services.filter.AccountUsernameFilter;
import ku.cs.services.filter.ComplaintIdFilter;
import ku.cs.services.reports.ReportListFileDataSource;
import ku.cs.util.Data;
import ku.cs.util.FontLoader;
import ku.cs.util.ObjectStorage;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReportListController {
    @FXML
    private FlowPane reportListArea;
    private ReportList reportList;

    public void initialize() {
        DataSource<ReportList> reportDataSource = new ReportListFileDataSource();
        reportList = reportDataSource.readData();

        updateReportList();
    }
    Font ths1 = FontLoader.font("ths", 30);
    Font ths2 = FontLoader.font("ths", 20);

    private void updateReportList(){
        reportListArea.getChildren().clear();

        for(Report i : reportList.getAllReport()){
            DataSource<AccountList> accountListDataSource = new AccountListFileDataSource();
            DataSource<ComplaintList> complaintListDataSource = new ComplaintListFileDataSource();
            AccountList accountList = accountListDataSource.readData();
            ComplaintList complaintList = complaintListDataSource.readData();

            Account account = null;
            Complaint complaint = null;

            if (i.getType().equals("Account")) {
                account = Data.search(i.getId().toString(), accountList.getAllAccount(), new AccountIdFilter());
            }
            else {
                complaint = Data.search(i.getId().toString(), complaintList.getAllComplaints(), new ComplaintIdFilter());
            }

            HBox hBox = new HBox();
            hBox.setPadding(new Insets(0, 0, 0, 20));
            hBox.setPrefSize(995, 100);

            VBox vBox = new VBox();
            vBox.setPadding(new Insets(0, 0, 0, 20));
            vBox.setPrefSize(995, 100);

            HBox hBox1 = new HBox();
            hBox1.setPrefSize(995, 40);
            hBox1.setSpacing(20);

            Label nameLabel = new Label();
            if (i.getType().equals("Account")) nameLabel.setText("Account name : " + account.getName());
            else nameLabel.setText("Complaint topic : " + complaint.getTopic());
            nameLabel.setFont(ths1);
            nameLabel.setPrefWidth(500);

            hBox1.getChildren().add(nameLabel);

            HBox hBox2 = new HBox();
            hBox2.setPrefSize(995, 31);
            hBox2.setSpacing(20);

            Label topicLabel = new Label();
            topicLabel.setText("หัวข้อเรื่องร้องเรียน : " + i.getTopic());
            topicLabel.setFont(ths2);
            topicLabel.setPrefWidth(800);

            hBox2.getChildren().add(topicLabel);

            Button removeButton = new Button();
            Button banButton = new Button();
            if(i.getType().equals("Account")){
                banButton.setText("ban");
                banButton.setPrefSize(70,40);
                hBox2.getChildren().add(banButton);
            }else{
                removeButton.setText("remove");
                removeButton.setPrefSize(70,40);
                hBox2.getChildren().add(removeButton);
            }

            HBox hBox3 = new HBox();
            hBox3.setPrefSize(995, 31);
            hBox3.setSpacing(20);

            Label descriptionLabel = new Label();
            descriptionLabel.setText("รายละเอียดเรื่องร้องเรียน : " + i.getDescription());
            descriptionLabel.setFont(ths2);
            descriptionLabel.setPrefWidth(800);

            Button dismissButton = new Button();
            dismissButton.setText("dismiss");
            dismissButton.setPrefSize(70,40);

            hBox3.getChildren().add(descriptionLabel);
            hBox3.getChildren().add(dismissButton);

            vBox.getChildren().add(hBox1);
            vBox.getChildren().add(hBox2);
            vBox.getChildren().add(hBox3);

            hBox.getChildren().add(vBox);

            reportListArea.getChildren().add(hBox);

            banButton.setOnAction(event -> ban(i));

            removeButton.setOnAction(event -> removeComplaint(i));

            dismissButton.setOnAction(event -> dismiss(i));
        }

    }

    private void ban(Report report) {
        DataSource<AccountList> dataSource = new AccountListFileDataSource();
        AccountList accountList = dataSource.readData();

        Account account =  Data.search(report.getId().toString(), accountList.getAllAccount(), new AccountIdFilter());
        account.setBanned(true);
        dataSource.writeData(accountList);

        dismiss(report);
    }
    private void removeComplaint(Report report) {
        DataSource<ComplaintList> dataSource = new ComplaintListFileDataSource();
        ComplaintList complaintList = dataSource.readData();

        complaintList.removeComplaint(report.getId());
        dataSource.writeData(complaintList);

        dismiss(report);
    }
    private void dismiss(Report report) {
        DataSource<ReportList> dataSource = new ReportListFileDataSource();
        reportList.getAllReport().remove(report);

        dataSource.writeData(reportList);

        updateReportList();
    }
}
