package ku.cs.controllers;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;

public class HomeController {
    @FXML private AnchorPane home;
    @FXML private AnchorPane menu;
    @FXML private StackPane content;

    private final String packageStrButton = "/ku/cs/button/";
    private final String packageStrPage = "/ku/cs/page/";
    private final int menuCloseWidth = -230;

    private ArrayList<HBox> buttonList;

    @FXML
    public void initialize() {
        menu.setTranslateX(menuCloseWidth); //set menu on close state

        loadUserButton();
    }

    public void loadUserButton(){
        VBox box = (VBox) menu.getChildren().get(0);
        try {
            box.getChildren().add(FXMLLoader.load(getClass().getResource(packageStrButton+"profilebutton.fxml")));
            box.getChildren().get(0).setOnMouseClicked(event -> loadPage("profile.fxml"));
            box.getChildren().add(FXMLLoader.load(getClass().getResource(packageStrButton+"complaintlistbutton.fxml")));
            box.getChildren().get(1).setOnMouseClicked(event -> loadPage("complaint_list.fxml"));
            box.getChildren().add(FXMLLoader.load(getClass().getResource(packageStrButton+"complaintpostbutton.fxml")));
            box.getChildren().get(2).setOnMouseClicked(event -> loadPage("complaint_post.fxml"));
            box.getChildren().add(FXMLLoader.load(getClass().getResource(packageStrButton+"tutorialbutton.fxml")));
            box.getChildren().get(3).setOnMouseClicked(event -> loadPage("tutorial.fxml"));
            box.getChildren().add(FXMLLoader.load(getClass().getResource(packageStrButton+"aboutbutton.fxml")));
            box.getChildren().get(4).setOnMouseClicked(event -> loadPage("about.fxml"));
        } catch (IOException e) {
            System.err.println("Error loading user button");
        }
    }

    public void loadPage(String pagePath) {
        Parent page = null;
        try {
            page = FXMLLoader.load(getClass().getResource(packageStrPage+pagePath));
        } catch (IOException e) {
            System.err.println("error loading "+pagePath);
        }
        content.getChildren().clear();
        content.getChildren().add(page);
    }

    @FXML private void handleOnMouseEnterMenu(){ //open menu
        //menu.setTranslateX(menuCloseWidth);
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.2));
        slide.setNode(menu);

        slide.setToX(0);
        slide.play();

        //menu.setTranslateX(menuCloseWidth);
    }
    @FXML private void handleOnMouseExitMenu(){ //close menu
        //menu.setTranslateX(0);
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.2));
        slide.setNode(menu);

        slide.setToX(menuCloseWidth);
        slide.play();

        //menu.setTranslateX(0);
    }

}


