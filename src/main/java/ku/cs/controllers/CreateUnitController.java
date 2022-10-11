package ku.cs.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ku.cs.models.units.Unit;
import ku.cs.models.units.UnitList;
import ku.cs.services.filter.UnitNameFilter;
import ku.cs.services.datasource.units.UnitListFileDataSource;
import ku.cs.util.Data;


public class CreateUnitController {
    @FXML private TextField unitNameTextField;
    @FXML private Label errorLabel;
    @FXML private Button backButton;

    public void handleBackButton(){
        close();
    }

    public void handleCreateButton(){
        UnitListFileDataSource dataSource = new UnitListFileDataSource();
        UnitList unitList = dataSource.readData();

        if(unitNameTextField.getText().trim().equals("")){
            errorLabel.setText("Please fill unit name.");
            return;
        }
        else if(Data.search(unitNameTextField.getText(),unitList.getAllUnits(), new UnitNameFilter()) != null){
            errorLabel.setText("This unit name is already used.");
            return;
        }

        unitList.addUnit(new Unit(unitNameTextField.getText().trim()));
        dataSource.writeData(unitList);

        close();
    }

    public void close(){
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }
}
