package ku.cs.models;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import ku.cs.controllers.HomeController;
import ku.cs.fontloader.FontLoader;
import ku.cs.objectcollector.DataSource;

import java.io.IOException;
import java.util.ArrayList;

public class Button {
    @FXML
    protected HBox button;
    @FXML
    private Parent page = null;
    protected String pageName;
    protected final String packageStr = "/ku/cs/";
    
    public void setupFont() {
        ((Label)button.getChildren().get(1)).setFont(FontLoader.font("fa_r", 32));
    }
    public void loadPage() {

        try{ //load complaint list page
            page = FXMLLoader.load(getClass().getResource(packageStr + "page/" + pageName));
        } catch (IOException e){
            System.err.println("error loading page");
        }
        StackPane content = (StackPane) DataSource.find("content");
        content.getChildren().clear();
        content.getChildren().add(page);
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
        /*ArrayList<Node> buttonList = homeController.getAllButton();
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
