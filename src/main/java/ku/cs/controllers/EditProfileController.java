package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class EditProfileController {
    @FXML
    public void handleBackButton(ActionEvent actionEvent){
        try{
            com.github.saacsos.FXRouter.goTo("home_student");
        } catch (IOException e){
            System.err.println("Error loading login page");
        }
    }
}
