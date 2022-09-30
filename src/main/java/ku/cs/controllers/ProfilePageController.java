package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.github.saacsos.FXRouter;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.models.accounts.Account;
import ku.cs.util.Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

public class ProfilePageController {

    @FXML private Label roleLabel;
    @FXML private Label nameLabel;
    @FXML private ImageView image;
    Account account = Util.account;


    @FXML
    public void initialize(){
        FileInputStream profileIMage = null;
        try {
            profileIMage = new FileInputStream("data" +
                    File.separator+ "image" +
                    File.separator+ "profileImage" +
                    File.separator+ account.getImagePath());
        } catch (FileNotFoundException e) {
            System.err.println("Cannot open image");
        }
        image.setImage(new Image(profileIMage));
        nameLabel.setText(account.getName());
        roleLabel.setText(account.getRole());
    }
    @FXML
    public void handleEditProfileButton(ActionEvent actionEvent){
        try{
            FXRouter.goTo("edit_profile");
        } catch (IOException e){
            System.err.println("Error loading edit profile page");
        }
    }

}
