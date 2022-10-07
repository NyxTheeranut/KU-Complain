package ku.cs.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.models.accounts.AccountList;
import ku.cs.models.accounts.Moderator;
import ku.cs.models.units.Unit;
import ku.cs.models.units.UnitList;
import ku.cs.services.datasource.DataSource;
import ku.cs.services.datasource.accounts.AccountListFileDataSource;
import ku.cs.services.filter.AccountUsernameFilter;
import ku.cs.services.units.UnitListFileDataSource;
import ku.cs.util.Data;
import ku.cs.util.Util;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

public class CreateModeratorController {
    @FXML private TextField userField;
    @FXML private TextField passwordField;
    @FXML private TextField passwordConfirmField;
    @FXML private TextField nameField;
    @FXML private TextField surnameField;
    @FXML private ComboBox<Unit> unitListField;
    @FXML private ImageView imageView;
    @FXML private Label errorLabel;

    private DataSource<UnitList> dataSource;
    private UnitList unitList;
    public void initialize(){
        setUnitListField();
    }

    public void setUnitListField(){
        dataSource = new UnitListFileDataSource();
        unitList = dataSource.readData();
        unitListField.getItems().addAll(unitList.getAllUnits());
    }

    public void handleSelectPictureButton() throws IOException {
        Image image = Util.selectImage();
        imageView.setImage(image);
    }

    public void handleCreateModeratorButton(){
        AccountListFileDataSource accountListFileDataSource = new AccountListFileDataSource();
        AccountList accountList = accountListFileDataSource.readData();

        UnitListFileDataSource unitListFileDataSource = new UnitListFileDataSource();
        UnitList unitList = unitListFileDataSource.readData();

        if(userField.getText().isEmpty() || passwordField.getText().isEmpty() || passwordConfirmField.getText().isEmpty() || nameField.getText().isEmpty() ||
         surnameField.getText().isEmpty() || unitListField.getValue() == null || imageView.getImage() == null || imageView.getImage().isError()){
            errorLabel.setText("Insufficient information"); //Empty Alert
            return;
        }
        if(!passwordField.getText().equals(passwordConfirmField.getText()) ){
            errorLabel.setText("Password doesn't match"); //wrong password alert
            return;
        }
        if(Data.search(userField.getText(),accountList.getAllAccount(),new AccountUsernameFilter()) != null){
            errorLabel.setText("This username is already used"); //duplicate username alert
            return;
        }
        Moderator mod = new Moderator(UUID.randomUUID() ,userField.getText(),passwordField.getText(),nameField.getText(),surnameField.getText()
                                    ,Util.saveImage(imageView.getImage(),"account"),false,LocalDateTime.now(),unitListField.getValue().getUnitName());
        accountList.addAccount(mod);
        accountListFileDataSource.writeData(accountList);

        //
        unitList.addModerator(unitListField.getValue().getUnitName(),mod.getId());
        unitListFileDataSource.writeData(unitList);
        //

        errorLabel.setText("");
        userField.clear();
        passwordField.clear();
        passwordConfirmField.clear();
        nameField.clear();
        surnameField.clear();
        unitListField.valueProperty().set(null);
        imageView.setImage(null);
        //if there's a creation success popup mun ja pung mak p'nut
    }
}
