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
import ku.cs.models.accounts.Account;
import ku.cs.models.complaints.Complaint;
import ku.cs.models.complaints.ComplaintList;
import ku.cs.models.complaints.Status;
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
import ku.cs.util.ThemeChanger;

public class ComplaintPageController {
    Account account;
    Complaint complaint;
    @FXML
    private Label topic;
    @FXML
    private Label category;
    @FXML
    private Label voteLabel;
    @FXML
    private Label voteButton;
    @FXML
    private FlowPane fieldArea;


    public void initialize() {
        account = ((ObjectStorage) FXRouter.getData()).getAccount();
        complaint = ((ObjectStorage) FXRouter.getData()).getComplaint();

        topic.setText(complaint.getTopic() + " Status : " + complaint.getStatus().toString());
        topic.setWrapText(true);

        category.setText(complaint.getCategory().getName());

        setupFieldArea();
        updateVote();
        if (complaint.getStatus() != Status.NOTSTARTED) fieldArea.getChildren().add(setupSolvingDetail());
    }
    private void setupFieldArea() {
        fieldArea.getChildren().clear();
        ArrayList<String> fields = complaint.getFields();
        ArrayList<Pair<String, String>> categoryFields = complaint.getCategory().getFields();

        for (int i=0; i<fields.size(); i++) {
            if (categoryFields.get(i).getKey().equals("text")) {
                fieldArea.getChildren().add(setupTextField(categoryFields.get(i).getValue(), fields.get(i)));
            }
            if (categoryFields.get(i).getKey().equals("pic")) {
                fieldArea.getChildren().add(setupPictureField(categoryFields.get(i).getValue(), fields.get(i)));
            }
            if (categoryFields.get(i).getKey().equals("detail")) {
                fieldArea.getChildren().add(setupDetailField(categoryFields.get(i).getValue(), fields.get(i)));
            }
        }
    }
    private HBox setupTextField(String fieldName, String fieldDetail) {
        //setup hBox
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setPadding(new Insets(0, 0, 0, 10));
        hBox.setPrefSize(940, 30);

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
        vBox.setPrefSize(940, 425);

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
        fieldImageView.setFitWidth(400/image.getHeight() * image.getWidth());
        fieldImageView.setFitHeight(400);

        vBox.getChildren().add(fieldNameLabel);
        vBox.getChildren().add(fieldImageView);

        return vBox;
    }
    private HBox setupDetailField(String fieldName, String fieldDetail) {
        //setup hBox
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.TOP_LEFT);
        hBox.setPadding(new Insets(0, 0, 0, 10));
        hBox.setPrefWidth(940);

        //setup fieldNameLabel
        Label fieldNameLabel = new Label();
        fieldNameLabel.setText(fieldName + " : ");
        fieldNameLabel.setFont(FontLoader.font("ths", 20));
        fieldNameLabel.setAlignment(Pos.CENTER_LEFT);

        //setup fieldDetailLabel
        Label fieldDetailLabel = new Label();
        fieldDetailLabel.setWrapText(true);
        fieldDetailLabel.setMaxWidth(850);
        fieldDetailLabel.setText(fieldDetail);
        fieldDetailLabel.setFont(FontLoader.font("ths", 20));

        hBox.getChildren().add(fieldNameLabel);
        hBox.getChildren().add(fieldDetailLabel);

        return hBox;
    }
    private VBox setupSolvingDetail() {
        VBox vBox = new VBox();
        vBox.setPrefWidth(940);
        vBox.setAlignment(Pos.TOP_LEFT);

        HBox hBox1 = new HBox();
        hBox1.setPrefWidth(940);
        hBox1.setPadding(new Insets(0,0,0,10));

        Label moderatorLabel = new Label();
        moderatorLabel.setText("หน่วยงาน : " + complaint.getModerator().getUnit());
        moderatorLabel.setFont(FontLoader.font("ths", 20));
        hBox1.getChildren().add(moderatorLabel);

        HBox hBox2 = new HBox();
        hBox2.setPrefWidth(940);
        hBox2.setPadding(new Insets(0,0,0,10));

        Label detailLabel = new Label("รายละเอียดการแก้ไข : ");
        detailLabel.setFont(FontLoader.font("ths", 20));
        hBox2.getChildren().add(detailLabel);

        Label detailTextField = new Label();
        detailTextField.setWrapText(true);
        detailTextField.setMaxWidth(800);
        detailTextField.setText(complaint.getSolvingDetail());
        detailTextField.setFont(FontLoader.font("ths", 20));
        hBox2.getChildren().add(detailTextField);

        vBox.getChildren().add(hBox1);
        vBox.getChildren().add(hBox2);

        return vBox;
    }
    public void handleReportButton() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/ku/cs/page/report.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Report");
        stage.setScene(scene);
        ThemeChanger.setTheme(scene);
        stage.show();
    }
    public void handleVoteButton() {vote();}
    private void vote() {
        ComplaintListFileDataSource dataSource = new ComplaintListFileDataSource();
        ComplaintList complaints = dataSource.readData();
        Data.search(complaint.getId().toString(), complaints.getAllComplaints(), new ComplaintIdFilter()).addVote(account);
        complaint.addVote(account);
        dataSource.writeData(complaints);
        updateVote();
    }

    private void updateVote() {
        voteLabel.setText(complaint.getVote()+"");
    }
}
