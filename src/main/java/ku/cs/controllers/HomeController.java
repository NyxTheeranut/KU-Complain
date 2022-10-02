package ku.cs.controllers;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import ku.cs.util.FontLoader;
import ku.cs.util.Util;

import javax.swing.text.LabelView;
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

        loadPage("complaint_list.fxml");

        loadUserButton();
    }

    public void loadUserButton(){
        VBox box = (VBox) menu.getChildren().get(0);
        String[] buttonDataList = {};
        String[] user = {Util.account.getUsername()+",default.png,profile.fxml","รายการร้องเรียน,\uF0AD,complaint_list.fxml",
                                    "เสนอเรื่องร้องเรียน,\uF0AD,complaint_post.fxml","วิธีใช้งาน,\uF0AD,tutorial.fxml","เกี่ยวกับ,\uF0AD,about.fxml"};
        String[] mod = {Util.account.getUsername()+",default.png,profile.fxml","รายการร้องเรียน,\uF0AD,manage_complaint.fxml",
                        "วิธีใช้งาน,\uF0AD,tutorial.fxml","เกี่ยวกับ,\uF0AD,about.fxml"};
        String[] admin = {Util.account.getUsername()+",default.png,profile.fxml","รายชื่อบัญชี,\uF0AD,account_list.fxml","รายงานจากผู้ใช้,\uF0AD,report_list.fxml",
                        "เพิ่มเจ้าหน้าที่,\uF0AD,create_moderator.fxml","จัดการหน่วยงาน,\uF0AD,unit_manage.fxml","วิธีใช้งาน,\uF0AD,tutorial.fxml","เกี่ยวกับ,\uF0AD,about.fxml"};

        if(Util.account.getRole() == "user") buttonDataList = user;
        else if(Util.account.getRole() == "mod") buttonDataList = mod;
        else if(Util.account.getRole() == "admin") buttonDataList = admin;

        for(String buttonData:buttonDataList){
            String[] data = buttonData.split(",");
            HBox button = Util.createButton(data[0],data[1]);
            //button.setOnMouseClicked(event -> loadPage(data[2]));
            button.setOnMouseClicked(event -> handleOnMouseClickedButton(box,button,data[2]));
            button.setOnMouseEntered(event -> handleOnMouseEnterButton(button));
            button.setOnMouseExited(event -> handleOnMouseExitButton(button));
            box.getChildren().add(button);
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
    @FXML
    public void handleOnMouseEnterButton(HBox button){
        button.getChildren().get(0).setStyle("-fx-text-fill: #ffffff");
        button.getChildren().get(1).setStyle("-fx-text-fill: #ffffff");
    }
    @FXML
    public void handleOnMouseExitButton(HBox button){
        if (button.getStyle().equals("-fx-background-color: #03a96b")) return;
        button.getChildren().get(0).setStyle("-fx-text-fill: #9d9fa1"); // #9d9fa1
        button.getChildren().get(1).setStyle("-fx-text-fill: #9d9fa1"); // #9d9fa1
    }
    @FXML
    public void handleOnMouseClickedButton(VBox menu,HBox button,String pagePath){
        for(Node b:menu.getChildren()) b.setStyle("-fx-background-color: #2f3337");
        button.setStyle("-fx-background-color: #03a96b");
        loadPage(pagePath);
    }
}


