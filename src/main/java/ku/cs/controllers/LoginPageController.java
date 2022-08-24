package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.github.saacsos.FXRouter;
import javafx.scene.control.Label;
import ku.cs.fontloader.FontLoader;
import ku.cs.fontloader.FontList;


import java.io.IOException;

public class LoginPageController {

    @FXML Label title;

    @FXML public void initialize(){
        //title.setFont(FontLoader.fontLoad("THSarabun Bold.ttf", 95));
        //title.setFont(FontList.fontSarabunBold);
    }

    @FXML public void handleLoginButton(ActionEvent actionEvent){
        try{
            FXRouter.goTo("home_student");
        } catch (IOException e){
            System.err.println("Error loading student menu page");
            System.err.println(e);
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
