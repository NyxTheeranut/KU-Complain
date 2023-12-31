package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.github.saacsos.FXRouter;

import java.io.IOException;

public class ProjectController {
    @FXML public void handleLoginButton(ActionEvent actionEvent){
        try{
            FXRouter.goTo("menu_student");
        } catch(IOException e){
            System.err.println(e);
        }
    }

    @FXML public void handleRegisterButton(ActionEvent actionEvent){
        try{
            FXRouter.goTo("register_page");
        } catch(IOException e){
            System.err.println(e);
        }
    }
}
