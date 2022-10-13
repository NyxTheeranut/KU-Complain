package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.github.saacsos.FXRouter;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ku.cs.models.accounts.Account;
import ku.cs.models.accounts.AccountList;
import ku.cs.util.ObjectStorage;
import ku.cs.services.datasource.DataSource;
import ku.cs.services.datasource.accounts.AccountListFileDataSource;

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

    @FXML public void handleLoginButton(ActionEvent actionEvent) {
        try {
            checkLogin();
        } catch (IOException e) {
            System.err.println("Error login");
            System.err.println(e);
        }
    }

    @FXML public void checkLogin() throws RuntimeException, IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            wrongLogin.setText("กรุณาระบุชื่อบัญชีและรหัสผ่าน");
        }
        else {
            DataSource<AccountList> dataSource = new AccountListFileDataSource();
            AccountList accountList = dataSource.readData();
            Account account = accountList.checkLogin(username, password);
            if (account != null) {
                ((ObjectStorage) FXRouter.getData()).setAccount(account);
                if (account.isBanned()) showBannedWindow();
                else {
                    try {
                        FXRouter.goTo("home", FXRouter.getData());
                    } catch (IOException e) {
                        System.err.println("Error loading home page");
                        System.err.println(e);
                    }
                }
            }
            else wrongLogin.setText("ชื่อบัญชีหรือรหัสผ่านไม่ถูกต้อง");
        }
    }

    private void showBannedWindow() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/ku/cs/page/unban.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Unban request");
        stage.setScene(scene);
        stage.show();
    }

    @FXML public void handleRegisterButton(ActionEvent actionEvent) {
        try{
            FXRouter.goTo("register_page");
        } catch (IOException e){
            System.err.println("Error loading register page");
        }
    }
}
