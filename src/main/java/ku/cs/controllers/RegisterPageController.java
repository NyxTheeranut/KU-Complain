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
import ku.cs.services.DataSource;
import ku.cs.services.accounts.AccountListFileDataSource;

import java.io.IOException;

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
        checkRegister();
    }

    @FXML public void checkRegister() {
        String nameText = usernameField.getText(); //get content from username field
        String passwordText = passwordField.getText(); //get content from password field
        String confirmText = confirmPasswordField.getText(); //get content from confirm password field
        if (nameText.isEmpty() || passwordText.isEmpty() || confirmText.isEmpty()) { //missing infomation
            wrongRegister.setText("กรุณาข้อมูลให้ครบถ้วน");
        }
        else if (passwordText.length() < 8) {
            wrongRegister.setText("รหัสผ่านต้องมีความยาวอย่างน้อย 8 ตัวอักษร");
        }
        else if (passwordText.equals(confirmText)) {
            Account user = new User(nameText, passwordText,"default.png"); //creating new user object
            register(user); //add user object to datasource
            try {
                FXRouter.goTo("login_page");
            } catch (IOException e) {
                System.err.println("Error loading login page");
            }
        }
        else {
            wrongRegister.setText("รหัสผ่านไม่ตรงกัน ลองใหม่อีกครั้ง");
        }
    }

    private void register(Account account){
        DataSource<AccountList> dataSource = new AccountListFileDataSource();
        AccountList accountList = dataSource.readData();
        accountList.addAccount(account);
        dataSource.writeData(accountList);
    }
}
