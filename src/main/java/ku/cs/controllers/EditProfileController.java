package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import ku.cs.models.accounts.Account;
import ku.cs.models.accounts.AccountList;
import ku.cs.models.accounts.User;
import ku.cs.services.datasource.DataSource;
import ku.cs.services.datasource.accounts.AccountListFileDataSource;
import ku.cs.services.searcher.SearchAccountByUserName;
import ku.cs.util.Util;


import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;


public class EditProfileController extends AccountList {

    @FXML private Label usernameLabel;

    @FXML private TextField rePasswordTextField;
    @FXML private ImageView profilePicture;

    @FXML public void handleProfilePicture(ActionEvent actionEvent) throws IOException {
        selectPicture();
    }
    @FXML public void handleRePassword(ActionEvent actionEvent) throws IOException {
       changePassword();
    }

    @FXML public void initialize(){
        usernameLabel.setText(Util.account.getName());
    }

    public void selectPicture() throws IOException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg"));
        File selectedFile = fileChooser.showOpenDialog(null);
        Image image = new Image(selectedFile.toURI().toString());
        profilePicture.setImage(image);
        Path from = Paths.get(selectedFile.toURI());
        Path to = Paths.get("data\\image\\profileImage\\" + selectedFile.getName());
        CopyOption[] options = new CopyOption[]{
                StandardCopyOption.REPLACE_EXISTING,
                StandardCopyOption.COPY_ATTRIBUTES};
        Files.copy(from.toFile().toPath(), to.toFile().toPath(),options);
        DataSource<AccountList> dataSource = new AccountListFileDataSource();
        AccountList accountList = dataSource.readData();
        Account account = Util.search(Util.account.getName(),accountList.getAllAccount(),new SearchAccountByUserName());
        accountList.changePicture(Util.account, selectedFile.getName());
        account.setImagePath(selectedFile.getName());

    }
    public void changePassword(){
        DataSource<AccountList> dataSource = new AccountListFileDataSource();
        AccountList accountList = dataSource.readData();
        Account account = Util.search(Util.account.getName(),accountList.getAllAccount(),new SearchAccountByUserName());
        String newName = rePasswordTextField.getText();
        accountList.changePassword(Util.account, newName);
        dataSource.writeData(accountList);
    }
    @FXML
    public void handleBackButton(ActionEvent actionEvent){
        try{
            com.github.saacsos.FXRouter.goTo("profile");
        } catch (IOException e){
            System.err.println("Error loading profile page");
        }
    }
}
