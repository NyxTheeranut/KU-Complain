package ku.cs.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.util.Callback;
import javafx.util.Pair;
import ku.cs.models.category.Category;
import ku.cs.models.category.CategoryList;
import ku.cs.models.complaints.Complaint;
import ku.cs.models.complaints.ComplaintList;
import ku.cs.models.complaints.Status;
import ku.cs.services.comparator.CompareComplaintByDatePost;
import ku.cs.services.comparator.CompareComplaintByName;
import ku.cs.services.comparator.CompareComplaintByVote;
import ku.cs.services.datasource.categorytlists.CategoryListFileDataSource;
import ku.cs.services.filter.ComplaintCategoryFilter;
import ku.cs.services.filter.ComplaintStatusFilter;
import ku.cs.services.filter.ComplaintTopicFilter;
import ku.cs.services.filter.ComplaintVoteFilter;
import ku.cs.util.Data;
import ku.cs.util.FontLoader;
import ku.cs.util.ObjectStorage;
import ku.cs.services.datasource.complaints.ComplaintListFileDataSource;
import ku.cs.services.datasource.DataSource;
import com.github.saacsos.FXRouter;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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
    private ComboBox<String> statusComboBox;
    @FXML
    private TextField searchTextField;
    @FXML
    private TextField fromVoteTextField;
    @FXML
    private TextField toVoteTextField;
    @FXML
    private Button reverseButton;

    private DataSource<ComplaintList> dataSource;
    private ArrayList<Complaint> complaints;
    private ArrayList<Complaint> filteredComplaintList;

    //constant
    private final Font topicFont = FontLoader.font("ths_b", 30);
    private final Font categoryFont = FontLoader.font("ths", 20);
    private final Font datePostFont = FontLoader.font("ths", 20);
    private final Font statusFont = FontLoader.font("ths", 20);

    public void initialize(){
        //get complaint list from dataSource
        dataSource = new ComplaintListFileDataSource();
        complaints = dataSource.readData().getAllComplaints();
        filteredComplaintList = complaints;

        setupComplaintArea(complaints);
        setupCategoryComboBox();
        setupSortComboBox();
        setupStatusComboBox();

        reverseButton.setFont(FontLoader.font("fa_wf", 15));
    }

    private void setupComplaintArea(ArrayList<Complaint> complaints){

        complaintArea.getChildren().clear();

        for(Complaint i : complaints){
            //System.out.println(i);
            //setup hBox
            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER);
            hBox.setPrefSize(980, 100);

//            Test hBox
//            hBox.setStyle("-fx-background-color: #2969c0");
//            hBox.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

            //setup vBox
            VBox vBox = new VBox();
            vBox.setAlignment(Pos.TOP_LEFT);
            vBox.setPadding(new Insets(0, 0, 0, 40));
            vBox.setPrefSize(980, 100);

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
            datePostLabel.setPrefWidth(600);
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
            statusHBox.setSpacing(2);
            statusHBox.setPrefSize(125, 30);

            //System.out.println(i.getStatus().name());

            //setup status Label
            Label status = new Label("สถานะ");
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

        ArrayList<Pair<Comparator, String>> comparatorList = new ArrayList<>();
        comparatorList.add(new Pair(new CompareComplaintByName(), "Name"));
        comparatorList.add(new Pair(new CompareComplaintByDatePost(), "Date posted"));
        comparatorList.add(new Pair(new CompareComplaintByVote(), "Vote"));

        sortComboBox.setItems(FXCollections.observableArrayList(comparatorList));
    }
    private void setupStatusComboBox() {
        statusComboBox.setItems(FXCollections.observableArrayList(
                "NOTSTARTED",
                "INPROGRESS",
                "DONE"
        ));
    }

    private void filterComplaint() {
        String topic = searchTextField.getText();
        Category category = categoryComboBox.getValue();
        Comparator comparator;
        String status = statusComboBox.getValue();
        try {
            comparator = sortComboBox.getValue().getKey();
        } catch (NullPointerException e) {
            comparator = null;
        }
        String voteRange = fromVoteTextField.getText() + "," + toVoteTextField.getText();

        filteredComplaintList = Data.filter(topic, complaints, new ComplaintTopicFilter());
        if (category != null)
            filteredComplaintList = Data.filter(category.getName(), filteredComplaintList, new ComplaintCategoryFilter());
        if (comparator != null)
            Collections.sort(filteredComplaintList, comparator);
        if (status != null)
            filteredComplaintList = Data.filter(status, filteredComplaintList, new ComplaintStatusFilter());

        filteredComplaintList = Data.filter(voteRange, filteredComplaintList, new ComplaintVoteFilter());


        setupComplaintArea(filteredComplaintList);
    }
    public void handleSearchButton() {
        filterComplaint();
    }
    public void handleResetButton() {
        ((ObjectStorage) FXRouter.getData()).getHomeController().loadPage("complaint_list.fxml");
    }
    public void handleReverseButton() {
        if ((reverseButton.getText().equals("\uF0D8"))) reverseButton.setText("\uF0D7");
        else reverseButton.setText("\uF0D8");
        Collections.reverse(filteredComplaintList);
        setupComplaintArea(filteredComplaintList);
    }
}
