package ku.cs;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import com.github.saacsos.FXRouter;

public class ProjectApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXRouter.bind(this, stage, "KU RongRian Center", 1280,720);
        configRoute();
        FXRouter.goTo("home_student");
    }

    private static void configRoute() {
        String packageStr = "ku/cs/";
        FXRouter.when("home_student", packageStr+"home_student.fxml");
        FXRouter.when("intro", packageStr+"intro.fxml");

    }
    public static void main(String[] args) {
        launch();
    }
}
