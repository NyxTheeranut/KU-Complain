package ku.cs.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ku.cs.models.accounts.Moderator;
import ku.cs.models.category.Category;
import ku.cs.models.category.CategoryList;
import ku.cs.models.units.Unit;
import ku.cs.models.units.UnitList;
import ku.cs.services.datasource.DataSource;
import ku.cs.services.datasource.categorytlists.CategoryListFileDataSource;
import ku.cs.services.units.UnitListFileDataSource;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;

import com.github.saacsos.FXRouter;
import ku.cs.util.ObjectStorage;
import ku.cs.util.Util;

public class UnitManageController {
    @FXML
    private AnchorPane anchorPane;
    @FXML private ListView<Unit> unitListView;
    @FXML private StackPane stackPane;
    @FXML private Label unitLabel;
    @FXML private ListView<Moderator> modListView;
    @FXML private ListView<Category> categoryListView;
    @FXML private ComboBox<Category> categoryComboBox;
    private Unit selectedUnit;
    private DataSource<UnitList> unitListDataSource;
    private DataSource<CategoryList> categoryListDataSource;
    private CategoryList categoryList;
    public void initialize(){
        unitListDataSource = new UnitListFileDataSource();
        categoryListDataSource = new CategoryListFileDataSource();
        //System.out.println(unitList.getAllUnits().size());
        updateUnitList();
        handleSelectedListView();
        unitListView.getSelectionModel().select(0);
    }

    public void updateUnitList(){
        UnitList unitList = unitListDataSource.readData();
        unitListView.getItems().clear();
        unitListView.getItems().addAll(unitList.getAllUnits());
        unitListView.refresh();
    }

    public void updateCategory(){
        categoryListView.getItems().clear();
        categoryListView.getItems().addAll(selectedUnit.getCategoryList());
        categoryListView.refresh();

        CategoryList categoryAddList = categoryListDataSource.readData();
        categoryAddList.removeAll(selectedUnit.getCategoryList());

        categoryComboBox.getItems().clear();
        categoryComboBox.getItems().addAll(categoryAddList.getAllCategory());
    }

    public void handleSelectedListView() {

        unitListView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if(newValue != null) {
                        showSelectedUnit(newValue);
                        System.out.println(newValue.getUnitName() + " is selected");
                    }
                });
    }

    public void showSelectedUnit(Unit unit){
        selectedUnit = unit;

        unitLabel.setText(unit.getUnitName());

        modListView.getItems().clear();
        modListView.getItems().addAll(unit.getModeratorList());

        updateCategory();

        for (Moderator m : unit.getModeratorList()) System.out.println(m.getName());
    }

    public void handleCreateUnitButton() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/ku/cs/page/create_unit.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Create unit");
        stage.setScene(scene);
        stage.showAndWait();
        updateUnitList();
    }

    public void handleRenameUnitButton() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/ku/cs/page/rename_unit.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Rename unit");
        stage.setScene(scene);
        ((ObjectStorage)FXRouter.getData()).setUnit(selectedUnit);
        stage.showAndWait();
        updateUnitList();
        //showSelectedUnit((Unit) unitListView.getSelectionModel().getSelectedItem());
        showSelectedUnit(((ObjectStorage) FXRouter.getData()).getUnit());
    }

    public void handleAddCategoryButton(){
        if(categoryComboBox.getSelectionModel().getSelectedItem() == null) return;

        System.out.println("Adding "+ categoryComboBox.getSelectionModel().getSelectedItem().getName() + " to " + selectedUnit.getUnitName());

        selectedUnit.addCategory(categoryComboBox.getSelectionModel().getSelectedItem());
        UnitList unitList = unitListDataSource.readData();
        unitList.addCategory(selectedUnit.getUnitName(),categoryComboBox.getSelectionModel().getSelectedItem());
        unitListDataSource.writeData(unitList);

        updateUnitList();
        updateCategory();
    }
}