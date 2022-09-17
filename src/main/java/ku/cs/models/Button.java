package ku.cs.models;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import ku.cs.fontloader.FontLoader;

public class Button {
    @FXML
    protected HBox button;
    
    public void setupFont() {
        ((Label)button.getChildren().get(1)).setFont(FontLoader.font("fa_r", 32));
    }

    @FXML
    public void handleOnMouseEnterButton() {
        button.getChildren().get(0).setStyle("-fx-text-fill: #ffffff");
        button.getChildren().get(1).setStyle("-fx-text-fill: #ffffff");
    }

    public void handleOnMouseExitButton() {
        if (button.getStyle().equals("-fx-background-color: #03a96b")) return;
        button.getChildren().get(0).setStyle("-fx-text-fill: #9d9fa1");
        button.getChildren().get(1).setStyle("-fx-text-fill: #9d9fa1");
    }

    public void handleOnMouseClickButton() {
    }
}
