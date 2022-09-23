package ku.cs.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.Pair;
import ku.cs.models.complaints.Complaint;
import ku.cs.models.complaints.ComplaintList;
import ku.cs.models.category.Category;
import ku.cs.models.category.CategoryList;
import ku.cs.util.Util;
import ku.cs.services.datasource.categorytlists.CategoryListFileDataSource;
import ku.cs.services.datasource.complaints.ComplaintListFileDataSource;
import ku.cs.services.datasource.DataSource;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ComplaintPostPageController {
    @FXML private TextField topicField;
    @FXML private ComboBox<Category> categoryComboBox;
    @FXML private FlowPane fieldArea;

    public void initialize(){
        fieldArea.setOrientation(Orientation.VERTICAL);
        fieldArea.setVgap(10);
        setupComboBox();
    }

    public void addComplaint(){
        DataSource<ComplaintList> complaintDataSource = new ComplaintListFileDataSource();
        ComplaintList complaintList = complaintDataSource.readData();

//        DataSource<CategoryList> categoryDataSource = new CategoryListFileDataSource();
//        CategoryList categoryList = categoryDataSource.readData();

        ArrayList<String> fields = new ArrayList<>();
        ObservableList<Node> child = fieldArea.getChildren();
        for(int i=0; i<child.size(); i++){
            HBox hBox = (HBox) child.get(i);
            if (categoryComboBox.getValue().getFields().get(i).getKey().equals("text")) { //text
                fields.add(( (TextField) hBox.getChildren().get(1)).getText());
            }
            else if (categoryComboBox.getValue().getFields().get(i).getKey().equals("pic")) { //pic
                Image image = ((ImageView)((VBox) hBox.getChildren().get(2)).getChildren().get(1)).getImage();
                if (image == null) System.out.printf("X");
                fields.add(Util.saveImage(image, "complaint"));
            }
        }

        complaintList.addComplaint(
                new Complaint(
                        complaintList.getAllComplaints().size()+1+"",
                        Util.account,
                        topicField.getText(),
                        categoryComboBox.getValue(),
                        LocalDateTime.now(),
                        fields
                        ));
        complaintDataSource.writeData(complaintList);
    }
    @FXML
    private void handleSelectedCategoryComboBox(){

        fieldArea.getChildren().clear(); //Reset flowpane
        fieldArea.setPrefHeight(0);

        Category category = categoryComboBox.getValue(); //Get category
        for (Pair<String, String> i : category.getFields()) {
            HBox hBox = new HBox();
            hBox.setSpacing(20); //Set spacing for HBox

            Label fieldName = new Label(); //Name label
            fieldName.setPrefSize(80, 30);

            hBox.getChildren().add(fieldName);

            if (i.getKey().equals("text")){
                TextField field = new TextField(); //Textfield
                field.setPrefSize(930, 30);
                hBox.getChildren().add(field);

            }
            else if (i.getKey().equals("pic")){

                Button button = new Button();
                button.setPrefSize(100, 30);
                button.setText("Upload รูปภาพ");
                button.setAlignment(Pos.CENTER);

                VBox vBox = new VBox();
                ImageView previewImageView = new ImageView();
                Label imagePathLabel = new Label();
                imagePathLabel.setPrefSize(820, 30);

                vBox.getChildren().add(imagePathLabel);
                vBox.getChildren().add(previewImageView);

                button.setOnAction(event -> { //set on action method for button
                    try {
                        Image image = Util.selectImage();
                        imagePathLabel.setText(image.getUrl());

                        double imageWidth = 200;
                        double imageHeight = 200/image.getWidth() * image.getHeight();

                        if (previewImageView.getImage()!=null) { //reset size of flowpane
                            fieldArea.setPrefHeight(fieldArea.getPrefHeight() -
                                    (200/previewImageView.getImage().getWidth() * previewImageView.getImage().getHeight()));
                        }
                        fieldArea.setPrefHeight(fieldArea.getPrefHeight()+imageHeight); //adjust flowpane size

                        previewImageView.setImage(image);
                        previewImageView.setFitWidth(200);
                        previewImageView.setFitHeight(200/image.getWidth() * image.getHeight());



                    } catch (IOException e) {
                        System.err.println(e);
                    }
                });

                hBox.getChildren().add(button);
                hBox.getChildren().add(vBox); //Add vbox to hbox
            }

            fieldName.setText(i.getValue()); //Set name label



            fieldArea.setPrefHeight(fieldArea.getPrefHeight()+40); //Increase flowpane height
            fieldArea.getChildren().add(hBox); //add HBox to flowpane
        }
    }

    @FXML
    private void handleSendComplaintButton(){
        addComplaint();
    }

    private void setupComboBox(){
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

}
