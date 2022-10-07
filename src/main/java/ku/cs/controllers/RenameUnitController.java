package ku.cs.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ku.cs.models.units.Unit;
import ku.cs.models.units.UnitList;
import ku.cs.services.filter.UnitNameFilter;
import ku.cs.services.units.UnitListFileDataSource;
import ku.cs.util.Data;
import ku.cs.util.ObjectStorage;
import ku.cs.util.Util;

import com.github.saacsos.FXRouter;
public class RenameUnitController {
    @FXML private TextField unitNameTextField;
    @FXML private Label errorLabel;
    @FXML private Button cancelButton;

    public void handleCancelButton(){
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
        Unit u = ((ObjectStorage) FXRouter.getData()).getUnit();
        //unitList.addUnit(new Unit(unitNameTextField.getText().trim()));
        Data.search(u.getUnitName(),unitList.getAllUnits(),new UnitNameFilter()).setUnitName(unitNameTextField.getText());
        dataSource.writeData(unitList);

        u.setUnitName(unitNameTextField.getText());
        ((ObjectStorage) FXRouter.getData()).setUnit(u);
        close();
    }

    public void close(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
