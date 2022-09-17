package ku.cs.controllers.button;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import ku.cs.controllers.HomeController;
import ku.cs.models.Button;
import ku.cs.models.accounts.Account;

import java.util.ArrayList;

import ku.cs.objectcollector.DataSource;

public class ProfileButtonController extends Button {
    @FXML
    private HBox button;
    private Account account = DataSource.account;

    @FXML public void initialize(){
        String url = getClass().getResource(packageStr+"image/"+account.getImagePath()).toExternalForm();
        Image image =  new Image(url,false);
        ((Circle)button.getChildren().get(1)).setFill(new ImagePattern(image));
        ((Label)button.getChildren().get(0)).setText(account.getName());

        pageName = "profile.fxml";
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

    @Override
    public void handleOnMouseClickButton() {
        button.setStyle("-fx-background-color: #03a96b");
        loadPage();
        /*
        ArrayList<Node> buttonList = homeController.getAllButton();
        for(Node i: buttonList){
            i.setStyle("-fx-background-color: #2f3337");
            ((HBox)i).getChildren().get(0).setStyle("-fx-text-fill: #9d9fa1");
            ((HBox)i).getChildren().get(1).setStyle("-fx-text-fill: #9d9fa1");
        }
        button.setStyle("-fx-background-color: #03a96b");
        handleOnMouseEnterButton();

         */
    }
}
