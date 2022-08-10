package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.github.saacsos.FXRouter;

import java.io.IOException;

public class ProfilePageController {
    @FXML
    public void handleBackButton(ActionEvent actionEvent){
        try{
            FXRouter.goTo("login_page");
        } catch (IOException e){
            System.err.println("Error loading login page");
        }
    }
}
