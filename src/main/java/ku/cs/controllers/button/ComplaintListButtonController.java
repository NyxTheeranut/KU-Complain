package ku.cs.controllers.button;

import javafx.fxml.FXML;
import ku.cs.models.Button;

public class ComplaintListButtonController extends Button {
    @FXML
    public void initialize(){
        pageName = "complaint_list.fxml";
        setupFont();
    }
}
