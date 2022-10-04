package ku.cs.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import ku.cs.models.units.Unit;
import ku.cs.models.units.UnitList;
import ku.cs.services.datasource.DataSource;
import ku.cs.services.units.UnitListFileDataSource;

import java.awt.*;
import java.awt.event.ActionEvent;

public class UnitManageController {
    @FXML
    private AnchorPane anchorPane;
    @FXML private ListView list;
    @FXML private StackPane stackPane;

    private DataSource<UnitList> dataSource;
    private UnitList unitList;
    public void initialize(){
        dataSource = new UnitListFileDataSource();
        unitList = dataSource.readData();
        System.out.println(unitList.getAllUnits().size());
        showList();

    }

    public void showList(){
        list.getItems().addAll(unitList.getAllUnits());
        list.refresh();
        stackPane.getChildren().add(new Button("kppp"));
        stackPane.getChildren().add(new Button("2"));
    }
    public void handleAddButton(ActionEvent actionEvent){
        //anchorPane.getChildren().add();
    }
}
