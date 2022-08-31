package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.github.saacsos.FXRouter;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ku.cs.models.Account;
import ku.cs.services.AccountListHardCodeDataSource;

import java.io.IOException;

public class LoginPageController {
    @FXML
    private Button loginButton;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label wrongLogin;

    @FXML public void handleLoginButton(ActionEvent actionEvent) throws IOException {
        checkLogin();
    }

    @FXML public void checkLogin() throws RuntimeException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        AccountListHardCodeDataSource userList = new AccountListHardCodeDataSource();
        Account account = userList.getUserList().getAccount(username);

        if (username.isEmpty() || password.isEmpty()) {
            wrongLogin.setText("กรุณาระบุชื่อบัญชีและรหัสผ่าน");
        }
        else if (account == null) {
            wrongLogin.setText("ชื่อบัญชีหรือรหัสผ่านไม่ถูกต้อง");
        }
        else if (account.checkLogin(password)) {
            try{
                FXRouter.goTo("home_student");
            } catch (IOException e) {
                System.err.println("Error loading student menu page");
                System.err.println(e);
            }
        }
        else {
            wrongLogin.setText("ชื่อบัญชีหรือรหัสผ่านไม่ถูกต้อง");
        }

    }

    @FXML public void handleRegisterButton(ActionEvent actionEvent) {
        try{
            FXRouter.goTo("register_page");
        } catch (IOException e){
            System.err.println("Error loading register page");
        }
    }
}
