package ku.cs.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class ThemeController {

    public ThemeController() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
        Scene scene = new Scene(root);

        scene.getStylesheets().add(scene.getClass().getResource("darkTheme.css").toExternalForm());
    }

    //setDarkTheme
}
