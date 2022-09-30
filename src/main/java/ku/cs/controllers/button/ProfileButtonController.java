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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ProfileButtonController extends Button {
    @FXML
    private HBox button;
    private Account account = Util.account;

    @FXML public void initialize(){
        FileInputStream profileImage = null;
        try {
            profileImage = new FileInputStream("data" +
                    File.separator+ "image" +
                    File.separator+ "profileImage" +
                    File.separator+ account.getImagePath());
        } catch (FileNotFoundException e) {
            System.err.println("Cannot open image");
        }
        /*System.out.println(profileIMage.toString());
        Image image =  new Image(url,false);*/
        ((Circle)button.getChildren().get(1)).setFill(new ImagePattern(new Image(profileImage)));
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
