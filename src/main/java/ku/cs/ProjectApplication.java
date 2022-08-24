package ku.cs;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.IOException;
import com.github.saacsos.FXRouter;
import ku.cs.fontloader.FontLoader;

public class ProjectApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXRouter.bind(this, stage, "KU RongRian Center", 1280,720);
        configRoute();
        FXRouter.goTo("login_page");
    }
    private static void configRoute() {
        String packageStr = "ku/cs/";
        FXRouter.when("home_student", packageStr+"home_student.fxml");
        FXRouter.when("intro", packageStr+"intro.fxml");
        FXRouter.when("login_page", packageStr+"login_page.fxml");
        FXRouter.when("register_page", packageStr+"register_page.fxml");
        FXRouter.when("about_page", packageStr+"about.fxml");
        FXRouter.when("tutorial_page", packageStr+"tutorial.fxml");

    }
    public static void main(String[] args) {
        launch();
    }
    //test
}
