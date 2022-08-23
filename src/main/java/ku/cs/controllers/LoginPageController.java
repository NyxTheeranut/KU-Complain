package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.github.saacsos.FXRouter;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;


public class LoginPageController {
        @FXML
        private Button loginButton;
        @FXML
        private TextField username;
        @FXML
        private PasswordField password;
        @FXML
        private Label wrongLogin;

    @FXML public void handleLoginButton(ActionEvent actionEvent) throws IOException {
        checkLogin();
    }

    @FXML public void checkLogin() throws IOException {
        if (username.getText().toString().equals("test") && password.getText().toString().equals("123")) {
            try{
                FXRouter.goTo("home_student");
            } catch (IOException e) {
                System.err.println("Error loading student menu page");
                System.err.println(e);
            }
        }

        else if (username.getText().toString().isEmpty() && password.getText().toString().isEmpty()) {
            wrongLogin.setText("กรุณาระบุชื่อบัญชีและรหัสผ่าน");
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
