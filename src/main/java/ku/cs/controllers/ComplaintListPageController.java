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
import javafx.util.Pair;
import ku.cs.models.category.Category;
import ku.cs.models.category.CategoryList;
import ku.cs.models.complaints.Complaint;
import ku.cs.models.complaints.ComplaintList;
import ku.cs.services.comparator.CompareComplaintByDatePost;
import ku.cs.services.comparator.CompareComplaintByDownVote;
import ku.cs.services.comparator.CompareComplaintByName;
import ku.cs.services.comparator.CompareComplaintByUpVote;
import ku.cs.services.datasource.categorytlists.CategoryListFileDataSource;
import ku.cs.services.filter.ComplaintCategoryFilter;
import ku.cs.services.filter.ComplaintTopicFilter;
import ku.cs.services.filter.Filterer;
import ku.cs.util.Data;
import ku.cs.util.FontLoader;
import ku.cs.util.ObjectStorage;
import ku.cs.util.Util;
import ku.cs.services.datasource.complaints.ComplaintListFileDataSource;
import ku.cs.services.datasource.DataSource;
import com.github.saacsos.FXRouter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class ComplaintListPageController {
    @FXML
    private FlowPane complaintArea;
    @FXML
    private ComboBox<Category> categoryComboBox;
    @FXML
    private ComboBox<Pair<Comparator, String>> sortComboBox;
    @FXML
    private TextField searchTextField;
    private DataSource<ComplaintList> dataSource;
    private ComplaintList complaintList;

    private ComplaintList searchedComplaintList;
    private ComplaintList filteredComplaintList;

    //constant
    private final Font topicFont = FontLoader.font("ths", 30);
    private final Font categoryFont = FontLoader.font("ths", 20);
    private final Font datePostFont = FontLoader.font("ths", 20);
    private final Font statusFont = FontLoader.font("ths", 20);

    public void initialize(){
        //get complaint list from dataSource
        dataSource = new ComplaintListFileDataSource();
        complaintList = dataSource.readData();
        searchedComplaintList = new ComplaintList();
        searchedComplaintList.setComplaints(complaintList.getAllComplaints());
        filteredComplaintList = new ComplaintList();
        filteredComplaintList.setComplaints(complaintList.getAllComplaints());

        setupComplaintArea(complaintList);
        setupCategoryComboBox();
        setupSortComboBox();
    }

    private void setupComplaintArea(ComplaintList complaintList){

        complaintArea.getChildren().clear();

        for(Complaint i : complaintList.getAllComplaints()){
            //System.out.println(i);
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
        ((ObjectStorage) FXRouter.getData()).setComplaint(complaint);
        ((ObjectStorage) FXRouter.getData()).getHomeController().loadPage("complaint.fxml");
    }

    private void setupCategoryComboBox() {
        DataSource<CategoryList> dataSource = new CategoryListFileDataSource();
        CategoryList categoryList = dataSource.readData();

        Callback<ListView<Category>, ListCell<Category>> factory = lv -> new ListCell<Category>() {
            @Override
            protected void updateItem(Category item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getName());
            }
        };

        categoryComboBox.setCellFactory(factory);
        categoryComboBox.setButtonCell(factory.call(null));

        categoryComboBox.setOnAction(event -> handleSelectedCategoryComboBox());
        categoryComboBox.setItems(FXCollections.observableArrayList(categoryList.getAllCategory()));
    }
    private void setupSortComboBox() {
        Callback<ListView<Pair<Comparator, String>>, ListCell<Pair<Comparator, String>>> factory = lv -> new ListCell<>() {
            @Override
            protected void updateItem(Pair<Comparator, String> item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getValue());
            }
        };

        sortComboBox.setCellFactory(factory);
        sortComboBox.setButtonCell(factory.call(null));

        sortComboBox.setOnAction(event -> handleSelectedSortComboBox());

        ArrayList<Pair<Comparator, String>> comparatorList = new ArrayList<>();
        comparatorList.add(new Pair(new CompareComplaintByName(), "Name"));
        comparatorList.add(new Pair(new CompareComplaintByDatePost(), "Date posted"));
        comparatorList.add(new Pair(new CompareComplaintByUpVote(), "Up vote"));
        comparatorList.add(new Pair(new CompareComplaintByDownVote(), "Down vote"));

        sortComboBox.setItems(FXCollections.observableArrayList(comparatorList));
    }
    private void handleSelectedCategoryComboBox() {
        Category category = categoryComboBox.getValue();
        filteredComplaintList = new ComplaintList();
        ArrayList<Complaint> filteredComplaintArrayList = Data.filter(category.getName(), searchedComplaintList.getAllComplaints(), new ComplaintCategoryFilter());

        for(Complaint i : filteredComplaintArrayList) {
            filteredComplaintList.addComplaint(i);
        }
        setupComplaintArea(filteredComplaintList);
    }
    private void handleSelectedSortComboBox() {
        try {
            Comparator comparator = sortComboBox.getValue().getKey();
            Collections.sort(filteredComplaintList.getAllComplaints(), comparator);
            setupComplaintArea(filteredComplaintList);
        }catch (NullPointerException e){

        }
    }
    public void handleSearchButton() {
        searchedComplaintList.setComplaints(Data.filter(searchTextField.getText(), complaintList.getAllComplaints(), new ComplaintTopicFilter()));
        filteredComplaintList.setComplaints(searchedComplaintList.getAllComplaints());
        setupComplaintArea(filteredComplaintList);
    }
    public void handleResetButton() {
        searchedComplaintList.setComplaints(complaintList.getAllComplaints());
        filteredComplaintList.setComplaints(complaintList.getAllComplaints());
        setupComplaintArea(complaintList);
    }
}
