package ku.cs;

import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;
import com.github.saacsos.FXRouter;
import ku.cs.util.FontLoader;
import ku.cs.util.Util;

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

        FXRouter.when("home_user", packageStr+"home_student.fxml");
        FXRouter.when("intro", packageStr+"intro.fxml");
        FXRouter.when("login_page", packageStr+"login.fxml");
        FXRouter.when("register_page", packageStr+"register.fxml");
        FXRouter.when("about_page", packageStr+"about.fxml");
        FXRouter.when("tutorial_page", packageStr+"tutorial.fxml");
        FXRouter.when("edit_profile", packageStr+"edit_profile.fxml");
        FXRouter.when("profile", packageStr+"profile.fxml");
        FXRouter.when("complaint", packageStr+"complaint.fxml");
        FXRouter.when("user_list", packageStr+"user_list.fxml");

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
        Util.add("packagestr", "/ku/cs/");
    }
    public static void main(String[] args) {
        launch();
    }
    //test
}
