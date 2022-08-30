package ku.cs.controllers;

import javafx.animation.TranslateTransition;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;

import javafx.util.Duration;

import java.util.HashMap;

import java.io.IOException;



public class HomeStudentPageController {
    @FXML private StackPane content;
    HashMap<Integer, String> boxId = new HashMap<Integer, String>();
    Integer currentPage = 1;
    //Hbox
    @FXML private HBox complaintPostButton;
    @FXML private HBox complaintListButton;
    @FXML private HBox tutorialButton;
    @FXML private HBox aboutButton;
    //Page
    @FXML private Parent complaintPost;
    @FXML private Parent complaintList;
    @FXML private Parent profile;
    @FXML private Parent about;
    @FXML private Parent tutorial;
    @FXML private AnchorPane menu;

    //instance
    private String packageStr = "/ku/cs/";
    private int menuCloseWidth = -230;


    private boolean menuState = false;

    @FXML public void initialize(){

        menu.setTranslateX(menuCloseWidth); //set menu on close state
        loadPage();
        setUpBoxId();
        loadComplaintList();
        handleOnMouseClickButton(1);

    }
    @FXML protected void loadProfile(){ //load profile to stackpane
        content.getChildren().clear();
        content.getChildren().add(profile);
    }
    @FXML protected void loadComplaintPost(){ //load complaint post to stackpane
        content.getChildren().clear();
        content.getChildren().add(complaintPost);
        handleOnMouseClickButton(0);
    }
    @FXML protected void loadComplaintList() { //load complaint list to stackpane
        content.getChildren().clear();
        content.getChildren().add(complaintList);
        handleOnMouseClickButton(1);
    }
    @FXML protected void loadTutorial(){ //load tutorial to stackpane
        content.getChildren().clear();
        content.getChildren().add(tutorial);
        handleOnMouseClickButton(2);
    }
    @FXML protected void loadAbout(){ //load about to stackpane
        content.getChildren().clear();
        content.getChildren().add(about);
        handleOnMouseClickButton(3);
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

    @FXML private void handleOnMouseEnterComplaintPostButton(){
        handleOnMouseEnterButton(complaintPostButton);
    }
    @FXML private void handleOnMouseEnterComplaintListButton(){
        handleOnMouseEnterButton(complaintListButton);
    }
    @FXML private void handleOnMouseEnterTutorialButton(){
        handleOnMouseEnterButton(tutorialButton);
    }
    @FXML private void handleOnMouseEnterAboutButton(){
        handleOnMouseEnterButton(aboutButton);
    }
    @FXML private void handleOnMouseExitComplaintPostButton(){
        handleOnMouseExitButton(complaintPostButton);
    }
    @FXML private void handleOnMouseExitComplaintListButton(){
        handleOnMouseExitButton(complaintListButton);
    }
    @FXML private void handleOnMouseExitTutorialButton(){
        handleOnMouseExitButton(tutorialButton);
    }
    @FXML private void handleOnMouseExitAboutButton(){
        handleOnMouseExitButton(aboutButton);
    }
    @FXML private void handleOnMouseEnterButton(HBox box){
        box.getChildren().get(0).setStyle("-fx-text-fill: #ffffff");
        box.getChildren().get(1).setStyle("-fx-text-fill: #ffffff");
    }
    @FXML private void handleOnMouseExitButton(HBox box){
        System.out.println(box.getId());
        if (boxId.get(currentPage).replaceAll("#", "").equals(box.getId())){
            return;
        }
        box.getChildren().get(0).setStyle("-fx-text-fill: #9d9fa1");
        box.getChildren().get(1).setStyle("-fx-text-fill: #9d9fa1");
    }
    @FXML private void handleOnMouseClickButton(Integer id){
        //System.out.println(boxId.get(id));
        currentPage = id;
        for(int i=0; i<boxId.size(); i++){
            HBox box = (HBox)(menu.getChildren().get(0)).lookup(boxId.get(i));
            if(i!=id){
                box.setStyle("-fx-background-color: #2f3337");
                handleOnMouseExitButton(box);
            }
            else{
                box.setStyle("-fx-background-color: #03a96b");
                handleOnMouseEnterButton(box);
            }

        }

    }

    @FXML private void setUpBoxId(){
        boxId.put(0, "#complaintPostButton");
        boxId.put(1, "#complaintListButton");
        boxId.put(2, "#tutorialButton");
        boxId.put(3, "#aboutButton");
    }
    @FXML private void loadPage(){
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

        try{ //pre load profile page
            profile = FXMLLoader.load(getClass().getResource(packageStr + "profile.fxml"));
        } catch (IOException e){
            System.err.println("error loading profile page");
        }

        try{ //pre load about page
            about = FXMLLoader.load(getClass().getResource(packageStr + "about.fxml"));
        } catch (IOException e){
            System.err.println("error loading about page");
        }

        try{ //pre load tutorial page
            tutorial = FXMLLoader.load(getClass().getResource(packageStr + "tutorial.fxml"));
        } catch (IOException e){
            System.err.println("error loading tutorial page");
        }
    }
    @FXML protected void loadComplaintList(){ //load complaint list to stackpane
        try{ //pre load complaint list page
            complaintList = FXMLLoader.load(getClass().getResource(packageStr + "complaint_list.fxml"));
        } catch (IOException e){
            System.err.println("error loading complaint list page");
        }
        content.getChildren().clear();
        content.getChildren().add(complaintList);
    }
    @FXML protected void loadComplaintPost(){ //load complaint post to stackpane
        content.getChildren().clear();
        content.getChildren().add(complaintPost);
    }
    @FXML protected void loadProfile(){ //load profile to stackpane
        content.getChildren().clear();
        content.getChildren().add(profile);
    }
    @FXML protected void loadAbout(){ //load about to stackpane
        content.getChildren().clear();
        content.getChildren().add(about);
    }

    @FXML protected void loadTutorial(){ //load tutorial to stackpane
        content.getChildren().clear();
        content.getChildren().add(tutorial);
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