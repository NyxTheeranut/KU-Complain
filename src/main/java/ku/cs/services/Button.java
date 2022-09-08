package ku.cs.services;

import javafx.fxml.FXML;
import javafx.scene.Parent;

public interface Button {
    String packageStr = "/ku/cs/";
    void loadPage();
    @FXML
    void handleOnMouseEnterButton();
    @FXML
    void handleOnMouseExitButton();
    @FXML
    void handleOnMouseClickButton();
}
