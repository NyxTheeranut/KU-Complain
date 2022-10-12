package ku.cs.services.styles;

import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

public class Theme {

    public static void setTheme(AnchorPane node, String change){
        if(change == "toDark") {
            node.getScene().getRoot().getStylesheets().setAll(String.valueOf(Theme.class.getResource("/ku/cs/styles/darkTheme.css").toExternalForm()));
        }
        else {

            node.getScene().getRoot().getStylesheets().setAll(String.valueOf(Theme.class.getResource("/ku/cs/styles/lightTheme.css").toExternalForm()));
        }

    }
}