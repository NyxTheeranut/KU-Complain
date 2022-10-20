package ku.cs.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ku.cs.models.accounts.AccountList;
import ku.cs.models.accounts.Moderator;
import ku.cs.models.category.Category;
import ku.cs.models.category.CategoryList;
import ku.cs.models.units.Unit;
import ku.cs.models.units.UnitList;
import ku.cs.services.datasource.DataSource;
import ku.cs.services.datasource.accounts.AccountListFileDataSource;
import ku.cs.services.datasource.categorytlists.CategoryListFileDataSource;
import ku.cs.services.datasource.units.UnitListFileDataSource;
import ku.cs.services.filter.AccountIdFilter;
import ku.cs.services.filter.AffiliationFilter;

import java.io.IOException;

import com.github.saacsos.FXRouter;
import ku.cs.util.Data;
import ku.cs.util.ObjectStorage;
import ku.cs.util.ThemeChanger;

public class UnitManageController {
    @FXML private ListView<Unit> unitListView;
    @FXML private Label unitLabel;
    @FXML private ListView<Moderator> modListView;
    @FXML private ListView<Category> categoryListView;
    @FXML private ComboBox<Category> categoryComboBox;
    @FXML private ComboBox<Moderator> freeModComboBox;
    private Unit selectedUnit;
    private DataSource<UnitList> unitListDataSource;
    private DataSource<CategoryList> categoryListDataSource;
    private DataSource<AccountList> accountListDataSource;
    public void initialize(){
        unitListDataSource = new UnitListFileDataSource();
        categoryListDataSource = new CategoryListFileDataSource();
        accountListDataSource = new AccountListFileDataSource();

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

    public void updateModerator(){
        modListView.getItems().clear();
        modListView.getItems().addAll(selectedUnit.getModeratorList());
        modListView.refresh();

        freeModComboBox.getItems().clear();
        AccountList accountList = new AccountListFileDataSource().readData();
        freeModComboBox.getItems().addAll(Data.filter("none",accountList.getAllMod(), new AffiliationFilter()));
    }

    public void handleSelectedListView() {
        unitListView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if(newValue != null) {
                        showSelectedUnit(newValue);
                        //System.out.println(newValue.getUnitName() + " is selected");
                    }
                });
    }

    public void showSelectedUnit(Unit unit){
        selectedUnit = unit;

        unitLabel.setText(unit.getUnitName());
        updateModerator();
        updateCategory();
    }

    public void handleCreateUnitButton() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/ku/cs/page/create_unit.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Create unit");
        stage.setScene(scene);
        ThemeChanger.setTheme(scene);
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
        ThemeChanger.setTheme(scene);
        ((ObjectStorage)FXRouter.getData()).setUnit(selectedUnit);
        stage.showAndWait();
        updateUnitList();
        showSelectedUnit(((ObjectStorage) FXRouter.getData()).getUnit());
    }

    public void handleAddCategoryButton(){
        if(categoryComboBox.getSelectionModel().getSelectedItem() == null) return;

        selectedUnit.addCategory(categoryComboBox.getSelectionModel().getSelectedItem());
        UnitList unitList = unitListDataSource.readData();
        unitList.addCategory(selectedUnit.getUnitName(),categoryComboBox.getSelectionModel().getSelectedItem());
        unitListDataSource.writeData(unitList);

        updateUnitList();
        updateCategory();
    }

    public void handleRemoveModeratorButton(){
        if(modListView.getSelectionModel().getSelectedItem() == null) return;

        AccountList accountList = accountListDataSource.readData();

        ((Moderator) Data.search(modListView.getSelectionModel().getSelectedItem().getId().toString(),
                accountList.getAllAccount(), new AccountIdFilter())).setUnit("none");

        accountListDataSource.writeData(accountList);

        UnitList unitList = unitListDataSource.readData();
        unitList.removeModerator(selectedUnit.getUnitName(),modListView.getSelectionModel().getSelectedItem());
        unitListDataSource.writeData(unitList);

        selectedUnit.removeModerator(modListView.getSelectionModel().getSelectedItem());
        updateUnitList();
        updateCategory();
        updateModerator();
    }

    public void handleAddModeratorButton(){
        if(freeModComboBox.getSelectionModel().getSelectedItem() == null) return;

        Moderator moderator = freeModComboBox.getSelectionModel().getSelectedItem();
        moderator.setUnit(selectedUnit.getUnitName());
        selectedUnit.addModerator(moderator);

        UnitList unitList = unitListDataSource.readData();
        unitList.addModerator(selectedUnit.getUnitName(),moderator.getId());
        unitListDataSource.writeData(unitList);

        AccountList accountList = accountListDataSource.readData();
        ((Moderator)Data.search(moderator.getId().toString(),accountList.getAllAccount(),new AccountIdFilter())).setUnit(selectedUnit.getUnitName());
        accountListDataSource.writeData(accountList);

        updateUnitList();
        updateCategory();
        updateModerator();
    }
}