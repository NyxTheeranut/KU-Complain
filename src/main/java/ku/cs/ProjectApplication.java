package ku.cs;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.IOException;
import com.github.saacsos.FXRouter;
import ku.cs.fontloader.FontLoader;
import ku.cs.objectcollector.ObjectCollector;

public class ProjectApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXRouter.bind(this, stage, "KU RongRian Center", 1280,720);
        configRoute();
        configFont();
        configObject();
        FXRouter.goTo("login_page");
    }
    private static void configRoute() {
        String packageStr = "ku/cs/page/";

        FXRouter.when("home", packageStr+"home.fxml");

        FXRouter.when("home_student", packageStr+"home_student.fxml");
        FXRouter.when("intro", packageStr+"intro.fxml");
        FXRouter.when("login_page", packageStr+"login.fxml");
        FXRouter.when("register_page", packageStr+"register.fxml");
        FXRouter.when("about_page", packageStr+"about.fxml");
        FXRouter.when("tutorial_page", packageStr+"tutorial.fxml");
        FXRouter.when("edit_profile", packageStr+"edit_profile.fxml");

    }
    private static void configFont() {
        String packageStr = "/ku/cs/fonts/";
        FontLoader.bind("ths", packageStr+"THSarabun.ttf");
        FontLoader.bind("ths_b", packageStr+"THSarabunBold.ttf");
        FontLoader.bind("ths_bi", packageStr+"THSarabunBoldItalic.ttf");
        FontLoader.bind("ths_i", packageStr+"THSarabunItalic.ttf");
        FontLoader.bind("fa_wf", packageStr+"fontawesome-webfont.ttf");
        FontLoader.bind("fa_r", packageStr+"fa-regular.ttf");
    }

    private static void configObject(){
        ObjectCollector.add("packagestr", "/ku/cs/");
    }
    public static void main(String[] args) {
        launch();
    }
    //test
}
