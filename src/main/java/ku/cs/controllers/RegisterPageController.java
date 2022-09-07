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

    @FXML public void checkRegister() throws RuntimeException {
        String nameText = usernameField.getText();
        String passwordText = passwordField.getText();
        String confirmText = confirmPasswordField.getText();
        if (nameText.isEmpty() || passwordText.isEmpty() || confirmText.isEmpty()) {
            wrongRegister.setText("กรุณาข้อมูลให้ครบถ้วน");
        }
        else if (passwordText.equals(confirmText)) {
            Account user = new User(nameText, confirmText,"default.png");
            register(user);
            try {
                FXRouter.goTo("login_page");
            } catch (IOException e) {
                throw new RuntimeException(e);
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
