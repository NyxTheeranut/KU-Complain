package ku.cs.controllers.button;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import ku.cs.controllers.HomeController;
import ku.cs.models.accounts.Account;
import ku.cs.services.Button;

import java.io.IOException;
import java.util.ArrayList;

import ku.cs.objectcollector.ObjectCollector;

public class ProfileButtonController implements Button {
    @FXML
    private HBox button;
    @FXML
    Parent page = null;
    private Account account = (Account) ObjectCollector.find("account");

    @FXML public void initialize(){
        String url = getClass().getResource(packageStr+"image/"+account.getImagePath()).toExternalForm();
        Image image =  new Image(url,false);
        ((Circle)button.getChildren().get(1)).setFill(new ImagePattern(image));
        ((Label)button.getChildren().get(0)).setText(account.getName());
    }

    @Override
    public void loadPage() {
        try{ //load complaint list page
            page = FXMLLoader.load(getClass().getResource(packageStr + "page/" + "profile.fxml"));
        } catch (IOException e){
            System.err.println("error loading profile page");
        }
        HomeController.loadPage(page);
    }

    @FXML
    public void handleOnMouseEnterButton() {
        button.getChildren().get(0).setStyle("-fx-text-fill: #ffffff");
    }

    public void handleOnMouseExitButton() {
        if (button.getStyle().equals("-fx-background-color: #03a96b")) return;
        button.getChildren().get(0).setStyle("-fx-text-fill: #9d9fa1");
    }

    public void handleOnMouseClickButton() {
        button.setStyle("-fx-background-color: #03a96b");
        loadPage();
        ArrayList<Node> buttonList = HomeController.getAllButton();
        for(Node i: buttonList){
            i.setStyle("-fx-background-color: #2f3337");
            ((HBox)i).getChildren().get(0).setStyle("-fx-text-fill: #9d9fa1");
            ((HBox)i).getChildren().get(1).setStyle("-fx-text-fill: #9d9fa1");
        }
        button.setStyle("-fx-background-color: #03a96b");
        handleOnMouseEnterButton();
    }
}
