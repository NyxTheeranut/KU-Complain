package ku.cs.controllers.button;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import ku.cs.models.Button;
import ku.cs.models.accounts.Account;

import ku.cs.util.Util;

public class ProfileButtonController extends Button {
    @FXML
    private HBox button;
    private Account account = Util.account;

    @FXML public void initialize(){
        String url = getClass().getResource("/ku/cs/image/"+account.getImagePath()).toExternalForm();
        Image image =  new Image(url,false);
        ((Circle)button.getChildren().get(1)).setFill(new ImagePattern(image));
        ((Label)button.getChildren().get(0)).setText(account.getUsername());
    }

    @Override
    public void handleOnMouseEnterButton() {
        button.getChildren().get(0).setStyle("-fx-text-fill: #ffffff");
    }

    @Override
    public void handleOnMouseExitButton() {
        if (button.getStyle().equals("-fx-background-color: #03a96b")) return;
        button.getChildren().get(0).setStyle("-fx-text-fill: #9d9fa1");
    }
}
