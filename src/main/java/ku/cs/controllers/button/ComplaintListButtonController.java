package ku.cs.controllers.button;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import ku.cs.controllers.HomeController;
import ku.cs.services.Button;

import java.io.IOException;
import java.util.ArrayList;

public class ComplaintListButtonController implements Button {
    @FXML
    private HBox button;
    @FXML
    Parent page = null;
    @Override
    public void loadPage() {
        try{ //load complaint list page
            page = FXMLLoader.load(getClass().getResource(packageStr + "complaint_list.fxml"));
        } catch (IOException e){
            System.err.println("error loading complaint list page");
        }
        HomeController.loadPage(page);
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
