package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.github.saacsos.FXRouter;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import ku.cs.models.accounts.Account;
import ku.cs.util.ObjectStorage;

public class ProfilePageController {

    @FXML private Label usernameLabel;
    @FXML private Label nameLabel;
    @FXML private Label roleLabel;

    @FXML private ImageView image;
    Account account;

    @FXML
    public void initialize(){
        account = ((ObjectStorage)FXRouter.getData()).getAccount();

        image.setImage(account.getImage());
        usernameLabel.setText(account.getUsername());
        nameLabel.setText(account.getName() + " " + account.getSurname());
        roleLabel.setText(account.getRole());
    }
    public void handleMyComplaintButton() {
        ((ObjectStorage) FXRouter.getData()).getHomeController().loadPage("my_complaint.fxml");
    }

    public void handleEditProfileButton(ActionEvent actionEvent){
        ((ObjectStorage) FXRouter.getData()).getHomeController().loadPage("edit_profile.fxml");
    }

}
