package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.github.saacsos.FXRouter;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import ku.cs.models.accounts.Account;
import ku.cs.util.FontLoader;
import ku.cs.util.ObjectStorage;

import java.io.IOException;

public class ProfilePageController {
    @FXML private Label nameLabel;
    @FXML private Label roleLabel;
    @FXML private HBox titleHBox;

    @FXML private ImageView image;
    Account account;

    @FXML
    public void initialize(){
        account = ((ObjectStorage)FXRouter.getData()).getAccount();

        image.setImage(account.getImage());
        nameLabel.setText(account.getName() + " " + account.getSurname());
        roleLabel.setText(account.getRole());

        if (account.getRole().equals("user")) {

            Button myComplaintButton = new Button();
            myComplaintButton.setText("เรื่องร้องเรียนของฉัน");
            myComplaintButton.setFont(FontLoader.font("ths", 24));
            HBox.setMargin(myComplaintButton, new Insets(30,0,0,760));
            myComplaintButton.setOnAction(event -> handleMyComplaintButton());

            titleHBox.getChildren().add(myComplaintButton);
        }

    }
    public void handleMyComplaintButton() {
        ((ObjectStorage) FXRouter.getData()).getHomeController().loadPage("my_complaint.fxml");
    }

    public void handleEditProfileButton(ActionEvent actionEvent){
        ((ObjectStorage) FXRouter.getData()).getHomeController().loadPage("edit_profile.fxml");
    }

    @FXML
    public void handleLogOutButton(ActionEvent actionEvent){
        try{
            FXRouter.goTo("login_page");
        } catch (IOException e){
            System.err.println("Error Logging out");
        }
    }

}
