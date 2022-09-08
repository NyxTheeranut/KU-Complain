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
import ku.cs.services.Button;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class HomeController {
    @FXML private AnchorPane home;
    @FXML private AnchorPane menu;
    @FXML private static StackPane content;
    @FXML private static VBox box = null;

    private ButtonList buttonList;

    private final String packageStr = "/ku/cs/button/";
    private final int menuCloseWidth = -230;

    @FXML
    public void initialize() {
        box = (VBox)menu.getChildren().get(0); //get VBox
        menu.setTranslateX(menuCloseWidth); //set menu on close state

        content = (StackPane) home.getChildren().get(0);

        buttonList = new ButtonList();
        buttonList.loadButton();
        loadUserButton();
    }

    public void loadUserButton() {
        for (Node button: buttonList.buttonList) {
            box.getChildren().add(button);
        }
    }

    public static void loadPage(Parent page) {
        content.getChildren().clear();
        content.getChildren().add(page);
    }

    public static ArrayList<Node> getAllButton() {
        ArrayList<Node> buttonList = new ArrayList<>();
        for (Node i: box.getChildren()) {
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

    public class ButtonList {
        ArrayList<String> buttonPathList =
                new ArrayList<>(Arrays.asList(
                        "profilebutton.fxml",
                        "complaintpostbutton.fxml",
                        "complaintlistbutton.fxml",
                        "tutorialbutton.fxml",
                        "aboutbutton.fxml"
                ));
        ArrayList<Node> buttonList = new ArrayList<>();

        public void loadButton() {
            for (String i: buttonPathList) {
                try {
                    buttonList.add(FXMLLoader.load(getClass().getResource(packageStr + i)));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}


