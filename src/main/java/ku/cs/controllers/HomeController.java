package ku.cs.controllers;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import ku.cs.models.accounts.Account;
import ku.cs.util.*;

import java.io.IOException;

import com.github.saacsos.FXRouter;

public class HomeController {
    @FXML private AnchorPane home;
    @FXML private AnchorPane menu;
    @FXML private VBox buttonVBox;
    @FXML private StackPane content;
    @FXML private ImageView themeIcon;
    private final String packageStrPage = "/ku/cs/page/";
    private final int menuCloseWidth = -230;

    private Account account;

    private Boolean isLightTheme = true;
    @FXML
    public void initialize() {

        menu.setTranslateX(menuCloseWidth); //set menu on close state

        ((ObjectStorage) FXRouter.getData()).setHomeController(this);
        account = ((ObjectStorage) FXRouter.getData()).getAccount();

        ((ObjectStorage) FXRouter.getData()).setTheme(Theme.LIGHT);
        themeIcon.setImage(ImageManager.loadImage("data/image/lightTheme.png"));

        if (account.getRole().equals("user")) loadUserButton();
        else if (account.getRole().equals("mod")) loadModButton();
        else if (account.getRole().equals("admin")) loadAdminButton();
    }

    public void changeTheme() {

        isLightTheme = !isLightTheme;
        Image image;
        if (isLightTheme) image = ImageManager.loadImage("data/image/lightTheme.png");
        else image = ImageManager.loadImage("data/image/darkTheme.png");
        themeIcon.setImage(image);

        ((ObjectStorage) FXRouter.getData()).setTheme((isLightTheme)? Theme.LIGHT : Theme.DARK);
        ThemeChanger.setTheme(home);
    }

    public void loadUserButton(){

        String[] buttonDataList = {
                "รายการร้องเรียน,\uF00B,complaint_list.fxml",
                "เสนอเรื่องร้องเรียน,\uF044,complaint_post.fxml",
                "วิธีใช้งาน,\uF0AD,tutorial.fxml",
                "เกี่ยวกับ,\uF0C0,about.fxml"
        };

        buttonVBox.getChildren().add(newProfileButton(account.getUsername(), ImageManager.loadImage("data/image/account/" + account.getImagePath()), "profile.fxml"));

        for(String buttonData:buttonDataList){
            String[] data = buttonData.split(",");
            HBox button = newButton(data[0],data[1],data[2]);
            //button.setOnMouseClicked(event -> handleOnMouseClickedButton(box,button,data[2]));
            //button.setOnMouseEntered(event -> handleOnMouseEnterButton(button));
            //button.setOnMouseExited(event -> handleOnMouseExitButton(button));
            buttonVBox.getChildren().add(button);
        }

        loadPage("complaint_list.fxml");
    }

    public void loadAdminButton(){
        VBox box = (VBox) menu.getChildren().get(0);
        box.getChildren().add(newProfileButton(account.getUsername(), ImageManager.loadImage("data/image/account/" + account.getImagePath()), "profile.fxml"));

        String[] buttonDataList = {
                "รายชื่อบัญชี,\uF00B,account_list.fxml",
                "รายงานจากผู้ใช้,\uF02F,report_list.fxml",
                "blacklist,\uF00D,blacklist.fxml",
                "เพิ่มเจ้าหน้าที่,\uF007,add_moderator.fxml",
                "จัดการหน่วยงาน,\uF044,unit_manage.fxml",
                "จัดการหมวดหมู่,\uF040,add_category.fxml",
                "เกี่ยวกับ,\uF0C0,about.fxml"
        };

        for(String buttonData:buttonDataList){
            String[] data = buttonData.split(",");
            HBox button = newButton(data[0],data[1],data[2]);
            box.getChildren().add(button);
        }

        loadPage("account_list.fxml");
    }

    public void loadModButton(){
        VBox box = (VBox) menu.getChildren().get(0);
        box.getChildren().add(newProfileButton(account.getUsername(), ImageManager.loadImage("data/image/account/" + account.getImagePath()), "profile.fxml"));

        String[] buttonDataList = {
                "จัดการเรื่องร้องเรียน,\uF0AD,moderator_complaint_list.fxml",
                "เกี่ยวกับ,\uF0C0,about.fxml"
        };

        for(String buttonData:buttonDataList){
            String[] data = buttonData.split(",");
            HBox button = newButton(data[0],data[1],data[2]);
            box.getChildren().add(button);
        }

        loadPage("moderator_complaint_list.fxml");
    }

    private HBox newButton(String buttonName, String icon, String page) {
        Color textFill = new Color(0.6157, 0.6235, 0.6314, 1.0);
        //setup hBox
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_RIGHT);
        //hBox.setStyle("-fx-background-color:#2f3337;");
        hBox.setPadding(new Insets(0, 15, 0, 0));
        hBox.getStyleClass().setAll("secondary-color");
        hBox.setPrefSize(300, 60);
        hBox.setOnMouseClicked(event -> handleOnMouseClickedButton((VBox) (menu.getChildren().get(0)), hBox, page));
        hBox.setOnMouseEntered(event -> handleOnMouseEnterButton(hBox));
        hBox.setOnMouseExited(event -> handleOnMouseExitButton(hBox));

        //setup buttonNameLabel
        Label buttonNameLabel = new Label();
        buttonNameLabel.setText(buttonName);
        buttonNameLabel.setFont(FontLoader.font("ths_b", 36));
        buttonNameLabel.setTextFill(textFill);
        buttonNameLabel.setPrefHeight(35);

        //setup buttonIconLabel
        Label buttonIconLabel = new Label();
        buttonIconLabel.setText(icon);
        buttonIconLabel.setFont(FontLoader.font("fa_wf", 36));
        buttonIconLabel.setTextFill(textFill);
        buttonIconLabel.setPrefSize(40,40);
        HBox.setMargin(buttonIconLabel, new Insets(0, 0, 0, 15));

        hBox.getChildren().add(buttonNameLabel);
        hBox.getChildren().add(buttonIconLabel);

        return hBox;
    }

    private  HBox newProfileButton(String username, Image image, String page) {
        Color textFill = new Color(0.6157, 0.6235, 0.6314, 1.0);
        //setup hBox
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_RIGHT);
        hBox.getStyleClass().setAll("secondary-color");
        //hBox.setStyle("-fx-background-color:#2f3337;");
        hBox.setPadding(new Insets(0, 0, 0, 0));
        hBox.setPrefSize(300, 60);
        hBox.setOnMouseClicked(event -> handleOnMouseClickedButton((VBox) (menu.getChildren().get(0)), hBox, page));
        hBox.setOnMouseEntered(event -> handleOnMouseEnterButton(hBox));
        hBox.setOnMouseExited(event -> handleOnMouseExitButton(hBox));
        //setup usernameLabel
        Label usernameLabel = new Label();
        usernameLabel.setText(username);
        usernameLabel.setFont(FontLoader.font("ths_b", 36));
        usernameLabel.setTextFill(textFill);
        usernameLabel.setPrefHeight(35);
        //setup circle
        Circle profilePic = new Circle();
        profilePic.setRadius(25);
        HBox.setMargin(profilePic, new Insets(0, 10.5, 0, 7.5));
        profilePic.setFill(new ImagePattern(image));

        hBox.getChildren().add(usernameLabel);
        hBox.getChildren().add(profilePic);

        return hBox;
    }

    public void loadPage(String pagePath) {
        Parent page = null;
        try {
            page = FXMLLoader.load(getClass().getResource(packageStrPage+pagePath));
        } catch (IOException e) {
            System.err.println("error loading "+pagePath);
            System.err.println(e);
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
        button.getChildren().get(0).getStyleClass().setAll("primary-text-color"); // #9d9fa1
        if (button.getChildren().get(1) instanceof Label)
            button.getChildren().get(1).getStyleClass().setAll("primary-text-color"); // #9d9fa1
    }
    @FXML
    public void handleOnMouseExitButton(HBox button){
        if (button.getStyleClass().get(0).toString().equals("tertiary-color")) return;
        button.getChildren().get(0).getStyleClass().setAll("secondary-text-color"); // #9d9fa1
        if (button.getChildren().get(1) instanceof Label)
            button.getChildren().get(1).getStyleClass().setAll("secondary-text-color"); // #9d9fa1
    }
    @FXML
    public void handleOnMouseClickedButton(VBox menu,HBox button,String pagePath){
        button.getStyleClass().setAll("tertiary-color");
        for(Node b:buttonVBox.getChildren()) {
            if (button == b) continue;
            HBox hBox = (HBox)b;
            hBox.getStyleClass().setAll("secondary-color");
            hBox.getChildren().get(0).getStyleClass().setAll("secondary-text-color"); // #9d9fa1
            if (hBox.getChildren().get(1) instanceof Label)
                hBox.getChildren().get(1).getStyleClass().setAll("secondary-text-color"); // #9d9fa1
        }
        loadPage(pagePath);
    }
}


