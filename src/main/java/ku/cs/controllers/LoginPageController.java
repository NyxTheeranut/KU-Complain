package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.github.saacsos.FXRouter;


import java.io.IOException;

public class LoginPageController {

    @FXML public void handleLoginButton(ActionEvent actionEvent){
        try{
            FXRouter.goTo("home_student");
        } catch (IOException e){
            System.err.println("Error loading student menu page");
        }
    }
    @FXML public void handleRegisterButton(ActionEvent actionEvent) {
        try{
            FXRouter.goTo("register_page");
        } catch (IOException e){
            System.err.println("Error loading register page");
        }
    }

    @FXML
    public void handleLoginButton(ActionEvent actionEvent) {
        try{
            com.github.saacsos.FXRouter.goTo("home_student");
        } catch (IOException e){
            System.err.println(e);
        }
    }
}
