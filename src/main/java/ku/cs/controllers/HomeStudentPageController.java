package ku.cs.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class HomeStudentPageController {
    @FXML private StackPane content;
    @FXML private Parent complaintPost;
    @FXML private Parent complaintList;
    @FXML private Parent profile;
    @FXML private Parent about;
    @FXML private Parent tutorial;
    private String packageStr = "/ku/cs/";

    @FXML public void initialize(){

        try{
            complaintPost = FXMLLoader.load(getClass().getResource(packageStr + "complaint_post.fxml"));
        } catch (IOException e){
            System.err.println("error loading complaint post page");
        }

        try{
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
    @FXML protected void loadComplaintList(){
        content.getChildren().clear();
        content.getChildren().add(complaintList);
    }
    @FXML protected void loadComplaintPost(){
        content.getChildren().clear();
        content.getChildren().add(complaintPost);
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