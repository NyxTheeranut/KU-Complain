package ku.cs.controllers;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.io.IOException;

public class HomeStudentPageController {
    @FXML private StackPane content;
    @FXML private Parent complaintPost;
    @FXML private Parent complaintList;
    @FXML private AnchorPane menu;

    //instance
    @FXML private Parent profile;
    @FXML private Parent about;
    @FXML private Parent tutorial;
    private String packageStr = "/ku/cs/";
    private int menuCloseWidth = -180;


    private boolean menuState = false;

    @FXML public void initialize(){

        menu.setTranslateX(menuCloseWidth); //set menu on close state

        try{ //pre load complaint post page
            complaintPost = FXMLLoader.load(getClass().getResource(packageStr + "complaint_post.fxml"));
        } catch (IOException e){
            System.err.println("error loading complaint post page");
        }

        try{ //pre load complaint list page
            complaintList = FXMLLoader.load(getClass().getResource(packageStr + "complaint_list.fxml"));
        } catch (IOException e){
            System.err.println("error loading complaint list page");
        }

        try{
            profile = FXMLLoader.load(getClass().getResource(packageStr + "profile.fxml"));
        } catch (IOException e){
            System.err.println("error loading profile page");
        }

        try{
            profile = FXMLLoader.load(getClass().getResource(packageStr + "about.fxml"));
        } catch (IOException e){
            System.err.println("error loading about page");
        }

        try{
            profile = FXMLLoader.load(getClass().getResource(packageStr + "tutorial.fxml"));
        } catch (IOException e){
            System.err.println("error loading tutorial page");
        }
    }
    @FXML protected void loadComplaintList(){ //load complaint list to stackpane
        content.getChildren().clear();
        content.getChildren().add(complaintList);
    }
    @FXML protected void loadComplaintPost(){ //load complaint post to stackpane
        content.getChildren().clear();
        content.getChildren().add(complaintPost);
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
    @FXML protected void loadProfile(){
        content.getChildren().clear();
        content.getChildren().add(profile);
    }

    @FXML protected void loadAbout(){
        content.getChildren().clear();
        content.getChildren().add(about);
    }

    @FXML protected void loadTutorial(){
        content.getChildren().clear();
        content.getChildren().add(tutorial);
    }
}