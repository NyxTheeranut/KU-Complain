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
import ku.cs.services.datasource.DataSource;
import ku.cs.services.datasource.accounts.AccountListFileDataSource;
import ku.cs.services.filter.AccountUsernameFilter;
import ku.cs.util.ObjectStorage;
import ku.cs.util.Util;


import java.io.*;
import java.nio.file.*;


public class EditProfileController extends AccountList {

    @FXML private Label passwordLabel;
    @FXML private Label surnameLabel;
    @FXML private Label nameLabel;
    @FXML private TextField rePasswordTextField;
    @FXML private TextField nameTextField;
    @FXML private TextField surnameTextField;
    @FXML private TextField oldPasswordTextField;
    @FXML private ImageView profilePicture;


    public void handleProfilePicture(ActionEvent actionEvent) throws IOException {
        selectPicture();
    }
    public void handleRePassword(ActionEvent actionEvent) throws IOException {
       changePassword();
    }
   /* public void handleName(ActionEvent actionEvent) throws IOException {
        changeName();
    }*/

    public void handleSurname(ActionEvent actionEvent) throws  IOException{
        changeSurname();
    }

    @FXML public void initialize(){
        nameLabel.setText(((ObjectStorage) com.github.saacsos.FXRouter.getData()).getAccount().getName());
        surnameLabel.setText(((ObjectStorage) com.github.saacsos.FXRouter.getData()).getAccount().getSurname());
        passwordLabel.setText(((ObjectStorage) com.github.saacsos.FXRouter.getData()).getAccount().getPassword());
    }

    public void selectPicture() throws IOException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg"));
        File selectedFile = fileChooser.showOpenDialog(null);
        Image image = new Image(selectedFile.toURI().toString());
        profilePicture.setImage(image);
        Path from = Paths.get(selectedFile.toURI());
        Path to = Paths.get("data\\image\\profileImage\\" + selectedFile.getName());
        String pictureName = selectedFile.getName();
        CopyOption[] options = new CopyOption[]{
                StandardCopyOption.REPLACE_EXISTING,
                StandardCopyOption.COPY_ATTRIBUTES};
        Files.copy(from.toFile().toPath(), to.toFile().toPath(),options);
        DataSource<AccountList> dataSource = new AccountListFileDataSource();
        AccountList accountList = dataSource.readData();
        Account account = Util.search(((ObjectStorage) com.github.saacsos.FXRouter.getData()).getAccount().getName(),accountList.getAllAccount(),new AccountUsernameFilter());
        accountList.changePicture(((ObjectStorage) com.github.saacsos.FXRouter.getData()).getAccount(), selectedFile.getName());
        account.setImagePath(selectedFile.getName());

    }
    public void changePassword(){
        DataSource<AccountList> dataSource = new AccountListFileDataSource();
        AccountList accountList = dataSource.readData();
        Account account = Util.search(((ObjectStorage) com.github.saacsos.FXRouter.getData()).getAccount().getName(),accountList.getAllAccount(),new AccountUsernameFilter());
        String newPassword = rePasswordTextField.getText();
        String passwordOld = oldPasswordTextField.getText();
        if( passwordOld.equals(((ObjectStorage) com.github.saacsos.FXRouter.getData()).getAccount().getPassword())){
            accountList.changePassword(((ObjectStorage) com.github.saacsos.FXRouter.getData()).getAccount(), newPassword);
            dataSource.writeData(accountList);
            ((ObjectStorage) com.github.saacsos.FXRouter.getData()).setAccount(account);
            account.setPassword(newPassword);
            passwordLabel.setText(newPassword);
            rePasswordTextField.setPromptText("new password");
            oldPasswordTextField.setPromptText("old password");
        }
    }

    /*public  void changeName(){
        DataSource<AccountList> dataSource = new AccountListFileDataSource();
        AccountList accountList = dataSource.readData();
        Account account = Util.search(((ObjectStorage) com.github.saacsos.FXRouter.getData()).getAccount().getName(),accountList.getAllAccount(),new AccountUsernameFilter());
        String name = nameTextField.getText();
        if(nameTextField.getText().isEmpty()){
            nameTextField.setPromptText("please input name");
        }else{
            accountList.changeName(((ObjectStorage) com.github.saacsos.FXRouter.getData()).getAccount(), name);
            dataSource.writeData(accountList);
            ((ObjectStorage) com.github.saacsos.FXRouter.getData()).setAccount(account);
            account.setName(name);
            nameLabel.setText(name);
            nameTextField.setPromptText("name");
        }
    }*/

    public void changeSurname(){
        DataSource<AccountList> dataSource = new AccountListFileDataSource();
        AccountList accountList = dataSource.readData();
        Account account = Util.search(((ObjectStorage) com.github.saacsos.FXRouter.getData()).getAccount().getName(),accountList.getAllAccount(),new AccountUsernameFilter());
        String surname = surnameTextField.getText();
        if(surnameTextField.getText().isEmpty()){
            surnameTextField.setPromptText("please input surname");
        }else{
            accountList.changeSurname(((ObjectStorage) com.github.saacsos.FXRouter.getData()).getAccount(), surname);
            dataSource.writeData(accountList);
            ((ObjectStorage) com.github.saacsos.FXRouter.getData()).setAccount(account);
            account.setSurname(surname);
            surnameLabel.setText(surname);
            surnameTextField.setPromptText("name");
        }
    }
    @FXML
    public void handleBackButton(ActionEvent actionEvent){
        try{
            com.github.saacsos.FXRouter.goTo("home");
        } catch (IOException e){
            System.err.println("Error loading profile page");
        }
    }
}
