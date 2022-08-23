package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.github.saacsos.FXRouter;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


import java.io.IOException;

public class LoginPageController {

    @FXML Label title;

    @FXML public void initialize(){
        Font font = Font.loadFont(getClass().getResource("/ku/cs/fonts/THSarabun.ttf").toExternalForm(), 10);
        System.out.println(font);
        title.setFont(Font.font("Arial", FontWeight.BOLD, 10));
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
