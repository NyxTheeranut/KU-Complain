package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.github.saacsos.FXRouter;
import ku.cs.util.ObjectStorage;

import java.io.IOException;

public class TutorialPageController {

    @FXML public void handleHowToButton(ActionEvent actionEvent) {
        ((ObjectStorage) FXRouter.getData()).getHomeController().loadPage("tutorial_how.fxml");
    }
    @FXML public void handleFollowButton(ActionEvent actionEvent) {
        ((ObjectStorage) FXRouter.getData()).getHomeController().loadPage("tutorial_follow.fxml");
    }
    @FXML public void handleAccountButton(ActionEvent actionEvent) {
        ((ObjectStorage) FXRouter.getData()).getHomeController().loadPage("tutorial_account.fxml");
    }
}


