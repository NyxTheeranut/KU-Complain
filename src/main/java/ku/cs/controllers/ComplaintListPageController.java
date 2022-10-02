package ku.cs.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Callback;
import ku.cs.models.category.Category;
import ku.cs.models.category.CategoryList;
import ku.cs.models.complaints.Complaint;
import ku.cs.models.complaints.ComplaintList;
import ku.cs.services.datasource.categorytlists.CategoryListFileDataSource;
import ku.cs.services.filter.Filterer;
import ku.cs.util.FontLoader;
import ku.cs.util.Util;
import ku.cs.services.datasource.complaints.ComplaintListFileDataSource;
import ku.cs.services.datasource.DataSource;
import com.github.saacsos.FXRouter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class ComplaintListPageController {
    @FXML
    private FlowPane complaintArea;
    @FXML
    private ComboBox<Filterer> filterComboBox;
    @FXML
    private TextField searchTextField;
    private DataSource<ComplaintList> dataSource;
    private ComplaintList complaintList;

    //constant
    private final Font topicFont = FontLoader.font("ths", 30);
    private final Font categoryFont = FontLoader.font("ths", 20);
    private final Font datePostFont = FontLoader.font("ths", 20);
    private final Font statusFont = FontLoader.font("ths", 20);

    public void initialize(){
        //get complaint list from dataSource
        dataSource = new ComplaintListFileDataSource();
        complaintList = dataSource.readData();

        setupComplaintArea(complaintList);
        setupFilterComboBox();
    }

    private void setupComplaintArea(ComplaintList complaintList){

        complaintArea.getChildren().clear();

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
            datePostLabel.setPrefWidth(645);
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

            //setup status hBox
            HBox statusHBox = new HBox();
            statusHBox.setAlignment(Pos.CENTER_LEFT);
            statusHBox.setSpacing(5);
            statusHBox.setPrefSize(125, 30);

            //System.out.println(i.getStatus().name());

            //setup status Label
            Label status = new Label("Status");
            status.setFont(statusFont);
            Label statusLabel = new Label();
            statusLabel.setFont(statusFont);
            statusLabel.setText(i.getStatus().name());

            //add Status label to hBox
            statusHBox.getChildren().add(status);
            statusHBox.getChildren().add(statusLabel);

            complaintDetailHBox.getChildren().add(statusHBox);

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
            FXRouter.goTo("complaint");
        } catch (IOException e) {
            System.err.println("Error loading complaint page");
            System.err.println(e);
        }
    }

    private void setupFilterComboBox() {

        Callback<ListView<Filterer>, ListCell<Filterer>> factory = lv -> new ListCell<Filterer>() {
            @Override
            protected void updateItem(Filterer item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getName());
            }
        };

        filterComboBox.setCellFactory(factory);
        filterComboBox.setButtonCell(factory.call(null));

        //filterComboBox.setOnAction(event -> handleSelectedCategoryComboBox());
        filterComboBox.setItems(FXCollections.observableArrayList(Util.complaintFilter));
    }

    public void handleSearchButton() {
        ComplaintList filteredComplaintList = new ComplaintList();

        Filterer filterer = filterComboBox.getValue();

        ArrayList<Complaint> filteredComplaintArrayList = new ArrayList<>();
        filteredComplaintArrayList = Util.filter(searchTextField.getText(), complaintList.getAllComplaints(), filterer);

        for(Complaint i : filteredComplaintArrayList) {
            filteredComplaintList.addComplaint(i);
        }

        setupComplaintArea(filteredComplaintList);
    }
}
