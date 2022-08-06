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
        FXMLLoader fxmlLoader = new FXMLLoader(ProjectApplication.class.getResource("intro.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setTitle("KU RongRian Center");
        stage.setScene(scene);
        stage.show();
    }

    private static void configRoute() {
        String packageStr = "ku/cs/";
        FXRouter.when("intro", packageStr+"intro.fxml");
    }
    public static void main(String[] args) {
        launch();
    }
}
