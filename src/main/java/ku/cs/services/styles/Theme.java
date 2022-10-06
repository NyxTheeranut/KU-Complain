package ku.cs.services.styles;

import javafx.scene.layout.AnchorPane;

public class Theme {
    public static void setTheme(AnchorPane node){
        //set style naja
        node.getScene().getRoot().getStylesheets().add(Theme.class.getResource("/ku/cs/styles/darkTheme.css").toExternalForm());
    }
}
