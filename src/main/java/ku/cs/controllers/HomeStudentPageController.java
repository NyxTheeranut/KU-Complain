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
    }
    @FXML protected void loadComplaintList(){
        content.getChildren().clear();
        content.getChildren().add(complaintList);
    }
    @FXML protected void loadComplaintPost(){
        content.getChildren().clear();
        content.getChildren().add(complaintPost);
    }
}