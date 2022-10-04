package ku.cs.controllers;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javafx.stage.Modality;
import javafx.util.Pair;
import ku.cs.models.Button;
import ku.cs.models.accounts.Account;
import ku.cs.models.complaints.Complaint;
import ku.cs.models.complaints.ComplaintList;
import ku.cs.services.datasource.complaints.ComplaintListFileDataSource;
import ku.cs.services.filter.ComplaintIdFilter;
import ku.cs.util.Data;
import ku.cs.util.FontLoader;
import ku.cs.util.ObjectStorage;
import com.github.saacsos.FXRouter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ComplaintPageController {
    Account account;
    Complaint complaint;
    @FXML
    private Label topic;
    @FXML
    private Label category;
    @FXML
    private Label upVoteLabel;
    @FXML
    private Label downVoteLabel;
    @FXML
    private Label upVoteButton;
    @FXML
    private Label downVoteButton;
    @FXML
    private FlowPane fieldArea;


    public void initialize() {
        account = ((ObjectStorage) FXRouter.getData()).getAccount();
        complaint = ((ObjectStorage) FXRouter.getData()).getComplaint();



        topic.setText(complaint.getTopic());
        topic.setWrapText(true);

        category.setText(complaint.getCategory().getName());

        setupFieldArea();
        updateVote();
    }
    private void setupFieldArea() {

        ArrayList<String> fields = complaint.getFields();
        ArrayList<Pair<String, String>> categoryFields = complaint.getCategory().getFields();

        for (int i=0; i<fields.size(); i++) {
            if (categoryFields.get(i).getKey().equals("text")) {
                fieldArea.getChildren().add(setupTextField(categoryFields.get(i).getValue(), fields.get(i)));
            }
            if (categoryFields.get(i).getKey().equals("pic")) {
                fieldArea.getChildren().add(setupPictureField(categoryFields.get(i).getValue(), fields.get(i)));
            }
        }
    }
    private HBox setupTextField(String fieldName, String fieldDetail){
        //setup hBox
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setPadding(new Insets(0, 0, 0, 10));
        hBox.setPrefSize(1000, 30);

        //setup fieldNameLabel
        Label fieldNameLabel = new Label();
        fieldNameLabel.setText(fieldName + " : ");
        fieldNameLabel.setFont(FontLoader.font("ths", 20));
        fieldNameLabel.setAlignment(Pos.CENTER_LEFT);

        //setup fieldDetailLabel
        Label fieldDetailLabel = new Label();
        fieldDetailLabel.setText(fieldDetail);
        fieldDetailLabel.setFont(FontLoader.font("ths", 20));

        hBox.getChildren().add(fieldNameLabel);
        hBox.getChildren().add(fieldDetailLabel);

        return hBox;
    }
    private VBox setupPictureField(String fieldName, String imagePath) {
        //setup hBox
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setPadding(new Insets(0, 0, 0, 10));
        vBox.setPrefSize(1000, 500);

        //setup fieldNameLabel
        Label fieldNameLabel = new Label();
        fieldNameLabel.setText(fieldName + " : ");
        fieldNameLabel.setFont(FontLoader.font("ths", 20));

        //setup image
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream("data" +
                    File.separator+ "image" +
                    File.separator+ "complaint" +
                    File.separator+ imagePath);
        } catch (FileNotFoundException e) {
            System.err.println("Cannot open image");
            System.err.println(e);
        }
        Image image = new Image(fileInputStream);

        //setup ImageView
        ImageView fieldImageView = new ImageView();
        fieldImageView.setImage(image);
        fieldImageView.setFitWidth(475/image.getHeight() * image.getWidth());
        fieldImageView.setFitHeight(475);

        vBox.getChildren().add(fieldNameLabel);
        vBox.getChildren().add(fieldImageView);

        return vBox;
    }
    public void handleReportButton() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/ku/cs/page/report.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Report");
        stage.setScene(scene);
        stage.show();
    }
    public void handleUpVoteButton() {
        vote(true);
    }
    public void handleDownVoteButton() {
        vote(false);
    }
    private void vote(Boolean vote) {
        ComplaintListFileDataSource dataSource = new ComplaintListFileDataSource();
        ComplaintList complaints = dataSource.readData();
        Data.search(complaint.getId().toString(), complaints.getAllComplaints(), new ComplaintIdFilter()).addVote(account, vote);
        complaint.addVote(account, vote);
        dataSource.writeData(complaints);
        updateVote();
    }

    private void updateVote() {
        upVoteLabel.setText(complaint.getUpVote()+"");
        downVoteLabel.setText(complaint.getDownVote()+"");
    }
}
