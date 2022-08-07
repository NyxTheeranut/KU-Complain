package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class LoginPageController {
    @FXML
    public void handleRegisterButton(ActionEvent actionEvent) {
        try{
            com.github.saacsos.FXRouter.goTo("register_page");
        } catch (IOException e){
            System.err.println(e);
        }
    }
}
