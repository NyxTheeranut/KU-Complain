package ku.cs.controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import ku.cs.models.complaints.Complaint;
import ku.cs.util.FontLoader;
import ku.cs.util.Util;
import com.github.saacsos.FXRouter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class ComplaintPageController {
    Complaint complaint;

    @FXML
    private Label topic;
    @FXML
    private Label category;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private FlowPane fieldArea;


    public void initialize() {
        complaint = Util.complaint;

        topic.setText(complaint.getTopic());
        topic.setWrapText(true);
        topic.setMaxWidth(1110);

        //setupFieldArea();

        scrollPane.setVvalue(0.0);
    }

    private void setupFieldArea() {
        final double textHeight = 40;
        final double fontSize = 25;

        fieldArea.setOrientation(Orientation.VERTICAL);
        fieldArea.setVgap(5);

        category.setText(complaint.getCategory().getName());

        ArrayList<Pair<String, String>> field = complaint.getCategory().getFields();
        ArrayList<String> fields = complaint.getFields();

        for (int i=0; i<fields.size(); i++) {

            Label fieldName = new Label(field.get(i).getValue());
            fieldName.setPrefSize(100, 0);
            fieldName.setMinWidth(100);
            fieldName.setFont(FontLoader.font("ths", fontSize));

            if (field.get(i).getKey().equals("text")) {

                HBox hBox = new HBox();
                hBox.setSpacing(20);
                hBox.setPrefSize(0, 0);
                hBox.setPadding(new Insets(0,0,0,70));

                Label fieldLabel = new Label(fields.get(i));
                fieldLabel.setPrefSize(930, 0);
                fieldLabel.setFont(FontLoader.font("ths", fontSize));

                hBox.getChildren().add(fieldName);
                hBox.getChildren().add(fieldLabel);

                fieldArea.setPrefHeight(fieldArea.getPrefHeight() + textHeight);
                fieldArea.getChildren().add(hBox);
            }
            else if (field.get(i).getKey().equals("pic")) {

                VBox vBox = new VBox();
                vBox.setSpacing(0);
                vBox.setPrefSize(0,0);
                vBox.setPadding( new Insets(10,0,20,425));
                vBox.getChildren().add(fieldName);

                FileInputStream fileInputStream = null;
                try {
                    fileInputStream = new FileInputStream("data" +
                            File.separator+ "image" +
                            File.separator+ "complaint" +
                            File.separator+ fields.get(i));
                } catch (FileNotFoundException e) {
                    System.err.println("Cannot open image");
                }

                Image image = new Image(fileInputStream);

                double imageWidth = 400;
                double imageHeight = imageWidth/image.getWidth() * image.getHeight();

                ImageView fieldImageView = new ImageView();
                fieldImageView.setImage(image);
                vBox.getChildren().add(fieldImageView);
                fieldImageView.setFitWidth(imageWidth);
                fieldImageView.setFitHeight(imageHeight);

                fieldArea.setPrefHeight(fieldArea.getPrefHeight()+imageHeight+textHeight+30);
                fieldArea.getChildren().add(vBox);

            }

        }

//        HBox hBox = new HBox();
//        hBox.setPrefSize(1280, 50);
//
//        Button backButton = new Button();
//        backButton.setText("Back");
//        backButton.setOnAction(event -> handleBackButton());
//
//        hBox.getChildren().add(backButton);
//        hBox.setPadding(new Insets(0,50,0,0));
//        hBox.setAlignment(Pos.CENTER_RIGHT);
//
//        fieldArea.getChildren().add(hBox);
//        fieldArea.setPrefHeight(fieldArea.getPrefHeight() + 50);
    }

    public void handleBackButton() {
        try {
            FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("Error loading home page");
        }
    }
}
