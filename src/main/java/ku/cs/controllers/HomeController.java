package ku.cs.controllers;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import ku.cs.objectcollector.DataSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class HomeController {
    @FXML private AnchorPane home;
    @FXML private AnchorPane menu;
    @FXML private StackPane content;

    private final String packageStr = "/ku/cs/button/";
    private final int menuCloseWidth = -230;

    @FXML
    public void initialize() {
        menu.setTranslateX(menuCloseWidth); //set menu on close state

        content = (StackPane) home.getChildren().get(0);

        loadUserButton();

        DataSource.add("content", content);
        DataSource.add("menu", menu);

    }

    public void loadUserButton(){
        VBox box = (VBox) menu.getChildren().get(0);
        try {
            box.getChildren().add(FXMLLoader.load(getClass().getResource(packageStr+"profilebutton.fxml")));
            box.getChildren().add(FXMLLoader.load(getClass().getResource(packageStr+"complaintlistbutton.fxml")));
            box.getChildren().add(FXMLLoader.load(getClass().getResource(packageStr+"complaintpostbutton.fxml")));
            box.getChildren().add(FXMLLoader.load(getClass().getResource(packageStr+"tutorialbutton.fxml")));
            box.getChildren().add(FXMLLoader.load(getClass().getResource(packageStr+"aboutbutton.fxml")));
        } catch (IOException e) {
            System.err.println("Error loading user button");
        }
    }

    public void loadPage(Parent page) {
        content.getChildren().clear();
        content.getChildren().add(page);
    }

    public ArrayList<Node> getAllButton() {
        ArrayList<Node> buttonList = new ArrayList<>();
        for (Node i: ((VBox) menu.getChildren().get(0)).getChildren()) {
            buttonList.add(i);
        }
        return buttonList;
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


