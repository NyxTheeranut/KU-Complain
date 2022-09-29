package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.github.saacsos.FXRouter;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.models.accounts.Account;
import ku.cs.util.Util;

import java.io.IOException;

public class ProfilePageController {

    @FXML private Label nameLabel;
    @FXML private ImageView image;
    Account account = Util.account;


    @FXML
    public void initialize(){
        String url = getClass().getResource("/ku/cs/image/"+account.getImagePath()).toExternalForm();
        image.setImage(new Image(url));
        nameLabel.setText(account.getName());
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
