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
import ku.cs.services.filter.AccountIdFilter;
import ku.cs.util.Data;
import ku.cs.util.ImageManager;
import ku.cs.util.ObjectStorage;


import java.io.*;
import java.nio.file.*;

import com.github.saacsos.FXRouter;
public class EditProfileController extends AccountList {

    @FXML
    private Label surnameLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private TextField newPasswordTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField surnameTextField;
    @FXML
    private TextField oldPasswordTextField;
    @FXML
    private ImageView profilePicture;
    @FXML
    private Account account;
    @FXML
    private Account account1;
    @FXML
    private AccountList accountList;

    @FXML
    public void initialize() {
        account = ((ObjectStorage) com.github.saacsos.FXRouter.getData()).getAccount();

        DataSource<AccountList> dataSource = new AccountListFileDataSource();
        accountList = dataSource.readData();

        account1 = Data.search(account.getId().toString(), accountList.getAllAccount(), new AccountIdFilter());

        nameLabel.setText(account.getName());
        surnameLabel.setText(account.getSurname());
        profilePicture.setImage(ImageManager.loadImage("data/image/account/" + account.getImagePath()));
    }

    public void handleProfilePicture(ActionEvent actionEvent) throws IOException {
        selectPicture();
    }
    public void handleChangePassword(ActionEvent actionEvent) throws IOException {
        changePassword();
    }
    public void handleName(ActionEvent actionEvent) throws IOException {
        changeName();
    }
    public void selectPicture() throws IOException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg"));
        File selectedFile = fileChooser.showOpenDialog(null);
        Image image = new Image(selectedFile.toURI().toString());
        profilePicture.setImage(image);
        Path from = Paths.get(selectedFile.toURI());
        Path to = Paths.get("data\\image\\account\\" + account.getId() + ".jpg");
        CopyOption[] options = new CopyOption[]{
                StandardCopyOption.REPLACE_EXISTING,
                StandardCopyOption.COPY_ATTRIBUTES};
        Files.copy(from.toFile().toPath(), to.toFile().toPath(), options);
        DataSource<AccountList> dataSource = new AccountListFileDataSource();

        account.setImagePath(account.getId() + ".jpg");
        account1.setImagePath(account.getId() + ".jpg");

        dataSource.writeData(accountList);

    }
    public void changePassword() {
        DataSource<AccountList> dataSource = new AccountListFileDataSource();

        String newPassword = newPasswordTextField.getText();
        String oldPassword = oldPasswordTextField.getText();
        if (oldPassword.equals(account.getPassword())) {
            account1.setPassword(newPassword);
            account.setPassword(newPassword);

            dataSource.writeData(accountList);

            newPasswordTextField.setText("");
            oldPasswordTextField.setText("");
        }
    }
    public void changeName(){
        DataSource<AccountList> dataSource = new AccountListFileDataSource();
        String name = nameTextField.getText();
        String surname = surnameTextField.getText();
        if (!name.isEmpty()){
            account.setName(name);
            account1.setName(name);

            nameLabel.setText(name);
            nameTextField.setText("");
        }
        if(!surname.isEmpty()){
            account.setSurname(surname);
            account1.setSurname(surname);

            surnameLabel.setText(surname);
            surnameTextField.setText("");
        }

        dataSource.writeData(accountList);
    }

    @FXML
    public void handleBackButton(ActionEvent actionEvent) {
        ((ObjectStorage) FXRouter.getData()).getHomeController().loadPage("profile.fxml");
    }
}
