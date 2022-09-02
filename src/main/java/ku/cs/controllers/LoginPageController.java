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
import ku.cs.services.DataSource;
import ku.cs.services.accounts.AccountListFileDataSource;
import ku.cs.services.accounts.AccountListHardCodeDataSource;

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

        if (username.isEmpty() || password.isEmpty()) {
            wrongLogin.setText("กรุณาระบุชื่อบัญชีและรหัสผ่าน");
        }
        else {
            DataSource<AccountList> dataSource = new AccountListFileDataSource();
            AccountList accountlist = dataSource.readData();
            for (Account account : accountlist.getAllAccount()) {
                if (account.getName().equals(username)) {
                    if (account.getPassword().equals(password)) {
                        try {
                            FXRouter.goTo("home_student", account);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                }
            }
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
