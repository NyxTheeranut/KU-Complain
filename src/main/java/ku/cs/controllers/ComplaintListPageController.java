package ku.cs.controllers;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import ku.cs.models.complaints.Complaint;
import ku.cs.models.complaints.ComplaintList;
import ku.cs.util.FontLoader;
import ku.cs.util.Util;
import ku.cs.services.datasource.complaints.ComplaintListFileDataSource;
import ku.cs.services.datasource.DataSource;
import com.github.saacsos.FXRouter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ComplaintListPageController {
    @FXML
    private FlowPane complaintArea;

    //private ComplaintListHardCodeDataSource dataSource;
    private DataSource<ComplaintList> dataSource;
    private ComplaintList complaintList;

    //constant
    private final Font topicFont = FontLoader.font("ths", 30);
    private final Font categoryFont = FontLoader.font("ths", 20);
    private final Font datePostFont = FontLoader.font("ths", 20);

    public void initialize(){
        //get complaint list from dataSource
        dataSource = new ComplaintListFileDataSource();
        complaintList = dataSource.readData();

        setupComplaintArea(complaintList);
    }

    private void setupComplaintArea(ComplaintList complaintList){
        for(Complaint i : complaintList.getAllComplaints()){
            //setup hBox
            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER);
            hBox.setPrefSize(1000, 100);

//            Test hBox
//            hBox.setStyle("-fx-background-color: #2969c0");
//            hBox.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

            //setup vBox
            VBox vBox = new VBox();
            vBox.setAlignment(Pos.TOP_LEFT);
            vBox.setPadding(new Insets(0, 0, 0, 40));
            vBox.setPrefSize(1000, 100);

//            Test vBox
//            vBox.setStyle("-fx-background-color: #2969c0");
//            vBox.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

            //setup topic label
            Label complaintTopicLabel = new Label();
            complaintTopicLabel.setText(i.getTopic());
            complaintTopicLabel.setFont(topicFont);
            complaintTopicLabel.setAlignment(Pos.TOP_LEFT);
            complaintTopicLabel.setPrefHeight(70);
            vBox.getChildren().add(complaintTopicLabel);

            //setup complaintDetail hBox
            HBox complaintDetailHBox = new HBox();
            complaintDetailHBox.setSpacing(20);
            complaintDetailHBox.setPrefSize(960, 30);

            //setup category label
            Label categoryLabel = new Label();
            categoryLabel.setText(i.getCategory().getName());
            categoryLabel.setFont(categoryFont);
            categoryLabel.setPrefWidth(150);
            complaintDetailHBox.getChildren().add(categoryLabel);

            //setup dataPostLabel
            Label datePostLabel = new Label();
            LocalDateTime postTime = i.getDatePosted();

            Long yearsBetween = ChronoUnit.YEARS.between(postTime, LocalDateTime.now());
            Long monthsBetween = ChronoUnit.MONTHS.between(postTime, LocalDateTime.now());
            Long weeksBetween = ChronoUnit.WEEKS.between(postTime, LocalDateTime.now());
            Long daysBetween = ChronoUnit.DAYS.between(postTime, LocalDateTime.now());
            Long hoursBetween = ChronoUnit.HOURS.between(postTime, LocalDateTime.now());
            Long minutesBetween = ChronoUnit.MINUTES.between(postTime, LocalDateTime.now());
            if      (yearsBetween  >= 1) datePostLabel.setText(yearsBetween + " ปี");
            else if (monthsBetween >= 1) datePostLabel.setText(monthsBetween + " เดือน");
            else if (weeksBetween  >= 1) datePostLabel.setText(weeksBetween + " สัปดาห์");
            else if (daysBetween   >= 1) datePostLabel.setText(daysBetween + " วัน");
            else if (hoursBetween  >= 1) datePostLabel.setText(hoursBetween + " ชั่วโมง");
            else                         datePostLabel.setText(minutesBetween + " นาที");

            datePostLabel.setFont(datePostFont);
            complaintDetailHBox.getChildren().add(datePostLabel);

            //add complaint detail to vBox
            vBox.getChildren().add(complaintDetailHBox);

            //add vBox to hBox
            hBox.getChildren().add(vBox);

            hBox.setOnMouseClicked(mouseEvent -> showSelectedComplaint(i));

            //add hBox(complaint) to complaintArea(flowPane)
            complaintArea.getChildren().add(hBox);

        }
    }

    private void showSelectedComplaint(Complaint complaint) {
        Util.complaint = complaint;
        try {
            System.out.println("x");
            FXRouter.goTo("complaint");
        } catch (IOException e) {
            System.err.println("Error loading complaint page");
            System.err.println(e);
        }
    }
}
