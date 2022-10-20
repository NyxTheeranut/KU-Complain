package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.github.saacsos.FXRouter;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ku.cs.models.accounts.Account;
import ku.cs.models.accounts.AccountList;
import ku.cs.models.accounts.User;
import ku.cs.services.datasource.DataSource;
import ku.cs.services.datasource.accounts.AccountListFileDataSource;
import ku.cs.util.ObjectStorage;

import java.io.IOException;
import java.util.UUID;

public class RegisterPageController {
    @FXML Button registerButton;
    @FXML TextField usernameField;
    @FXML PasswordField passwordField;
    @FXML PasswordField confirmPasswordField;
    @FXML Label wrongRegister;

    @FXML public void handleBackButton(ActionEvent actionEvent){
        try{
            FXRouter.goTo("login_page");
        } catch (IOException e){
            System.err.println(e);
        }
    }

    @FXML public void handleRegisterButton(ActionEvent actionEvent) {
        String usernameText = usernameField.getText(); //get content from username field
        String passwordText = passwordField.getText(); //get content from password field
        String confirmText = confirmPasswordField.getText(); //get content from confirm password field
        if (usernameText.isEmpty() || passwordText.isEmpty() || confirmText.isEmpty()) { //missing infomation
            wrongRegister.setText("กรุณาข้อมูลให้ครบถ้วน");
        }
        else if (passwordText.length() < 8) {
            wrongRegister.setText("รหัสผ่านต้องมีความยาวอย่างน้อย 8 ตัวอักษร");
        }
        else if (passwordText.equals(confirmText)) {
            register(usernameText, passwordText);
        }
        else {
            wrongRegister.setText("รหัสผ่านไม่ตรงกัน ลองใหม่อีกครั้ง");
        }
    }

    private void register(String username, String password){
        DataSource<AccountList> dataSource = new AccountListFileDataSource();
        AccountList accountList = dataSource.readData();
        if (accountList.checkRegister(username)){
            Account account = new User(UUID.randomUUID(), username, password);
            accountList.addAccount(account);
            dataSource.writeData(accountList);

            try {
                FXRouter.goTo("login_page");
            } catch (IOException e) {
                System.err.println(e);
            }
        }

    }
}
