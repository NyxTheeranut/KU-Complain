package ku.cs.controllers.button;

import javafx.fxml.FXML;
import ku.cs.models.Button;

public class AboutButtonController extends Button {

    @FXML
    public void initialize(){
        pageName = "about.fxml";
        setupFont();
    }

}
