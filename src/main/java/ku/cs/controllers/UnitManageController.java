package ku.cs.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ku.cs.models.units.Unit;
import ku.cs.models.units.UnitList;
import ku.cs.services.datasource.DataSource;
import ku.cs.services.units.UnitListFileDataSource;

import java.io.IOException;

import com.github.saacsos.FXRouter;
import ku.cs.util.ObjectStorage;

public class UnitManageController {
    @FXML
    private AnchorPane anchorPane;
    @FXML private ListView unitListView;
    @FXML private StackPane stackPane;
    @FXML private Label unitLabel;
    @FXML private ListView modListView;
    private Unit selectedUnit;
    private DataSource<UnitList> dataSource;
    private UnitList unitList;
    public void initialize(){
        dataSource = new UnitListFileDataSource();

        //System.out.println(unitList.getAllUnits().size());
        updateUnitList();
        handleSelectedListView();
        unitListView.getSelectionModel().select(0);
    }

    public void updateUnitList(){
        unitList = dataSource.readData();
        unitListView.getItems().clear();
        unitListView.getItems().addAll(unitList.getAllUnits());
        unitListView.refresh();
    }

    public void handleSelectedListView() {

        unitListView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Unit>() {
                    @Override
                    public void changed(ObservableValue<? extends Unit>
                                                observable,
                                       Unit oldValue, Unit newValue) {
                        System.out.println(newValue.getUnitName() + " is selected");
                        showSelectedUnit(newValue);
                    }
                });
    }

    public void showSelectedUnit(Unit unit){
        selectedUnit = unit;
        modListView.getItems().clear();
        unitLabel.setText(unit.getUnitName());
        modListView.getItems().addAll(unit.getModeratorList());
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

}
