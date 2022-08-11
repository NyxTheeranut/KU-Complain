package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class IntroController {
    @FXML
    public void handleIntroButton(ActionEvent actionEvent){
        try{
            com.github.saacsos.FXRouter.goTo("login_page");
        } catch(IOException e){
            System.err.println(e);
        }
    }
}
