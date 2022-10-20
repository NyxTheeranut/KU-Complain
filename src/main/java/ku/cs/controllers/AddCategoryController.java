package ku.cs.controllers;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import ku.cs.models.category.Category;
import ku.cs.models.category.CategoryList;
import ku.cs.services.datasource.DataSource;
import ku.cs.services.datasource.categorytlists.CategoryListFileDataSource;
import ku.cs.util.FontLoader;

public class AddCategoryController {
    @FXML
    private TextField categoryNameField;
    @FXML
    private FlowPane fieldArea;

    private Font ths1 = FontLoader.font("ths", 30);
    private Font ths2 = FontLoader.font("ths", 20);

    public void initialize() {
        fieldArea.getChildren().clear();
    }

    public void addText() {
        fieldArea.getChildren().add(printHBox("ชื่อกล่องข้อความ :"));
    }
    public void addPicture() {
        fieldArea.getChildren().add(printHBox("ชื่อกล่องรูปภาพ :"));
    }
    public void addDetail() {
        fieldArea.getChildren().add(printHBox("ชื่อกล่องรายละเอียด :"));
    }
    private HBox printHBox(String name) {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setSpacing(10);
        hBox.setPrefSize(1040, 50);
        hBox.setPadding(new Insets(0, 0, 0, 20));

        Label nameLabel = new Label();
        nameLabel.setFont(ths1);
        nameLabel.setText(name);

        TextField nameTextField = new TextField();
        nameTextField.setFont(ths2);

        hBox.getChildren().add(nameLabel);
        hBox.getChildren().add(nameTextField);

        return hBox;
    }

    public void handleAddCategoryButton() {
        Category category = new Category(categoryNameField.getText());
        for (Node i:fieldArea.getChildren()) {
            String text = ((Label) ((HBox) i).getChildren().get(0)).getText();
            String name = ((TextField) ((HBox) i).getChildren().get(1)).getText();

            if (text.equals("ชื่อกล่องข้อความ :")) {
                category.addField("text", name);
            }
            if (text.equals("ชื่อกล่องรูปภาพ :")) {
                category.addField("pic", name);
            }
            if (text.equals("ชื่อกล่องรายละเอียด :")) {
                category.addField("detail", name);
            }
        }
        DataSource<CategoryList> dataSource = new CategoryListFileDataSource();
        CategoryList categoryList = dataSource.readData();
        categoryList.addCategory(category);
        dataSource.writeData(categoryList);
    }

}
