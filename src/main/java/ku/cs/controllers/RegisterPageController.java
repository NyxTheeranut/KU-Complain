package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.github.saacsos.FXRouter;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ku.cs.models.User;

import java.io.IOException;

public class RegisterPageController {

    private User userData;
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

    @FXML public void handleRegisterButton(ActionEvent actionEvent) {
        checkRegister();
    }

    @FXML public void checkRegister() throws RuntimeException {
        if (username.getText().isEmpty() && password.getText().isEmpty() && confirmPassword.getText().isEmpty()) {
            wrongRegister.setText("กรุณาระบุชื่อบัญชีและรหัสผ่าน");
        }
        else if (! username.getText().isEmpty()) {
                String pass = password.getText();
                String conPass = confirmPassword.getText();
                if (! pass.equals(conPass)) {
                    wrongRegister.setText("รหัสผ่านไม่ตรงกัน ลองใหม่อีกครั้ง");
                }
                else {
                    User userData = new User(username.getText(), password.getText());
            }
        }
        else {
            wrongRegister.setText("กรุณาระบุชื่อบัญชีและรหัสผ่าน");
        }
    }
}
