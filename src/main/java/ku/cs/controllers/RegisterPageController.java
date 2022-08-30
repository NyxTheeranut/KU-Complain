package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.github.saacsos.FXRouter;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class RegisterPageController {

    @FXML Button registerButton;
    @FXML TextField username;
    @FXML PasswordField password;
    @FXML PasswordField confirmPassword;
    @FXML Label wrongRegister;


    @FXML public void handleBackButton(ActionEvent actionEvent){
        try{
            FXRouter.goTo("login_page");
        } catch (IOException e){
            System.err.println(e);
        }
    }

    @FXML public void handleRegisterButton(ActionEvent actionEvent){
        checkRegister();
    }

    @FXML public void checkRegister() throws IOException {
        if () {}
        else if (username.getText().toString().isEmpty() && password.getText().toString().isEmpty() && confirmPassword.getText().toString().isEmpty()) {
            wrongRegister.setText("กรุณาระบุชื่อบัญชีและรหัสผ่าน");
        }

        else {
            wrongRegister.setText("ชื่อบัญชีหรือรหัสผ่านไม่ถูกต้อง");
        }
    }
}
