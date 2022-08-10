package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.github.saacsos.FXRouter;

import java.io.IOException;

public class TutorialPageController {
    @FXML
    public void handleBackButton(ActionEvent actionEvent){
        try{
            FXRouter.goTo("home_student");
        } catch (IOException e){
            System.err.println(e);
        }
    }

}
