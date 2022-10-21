package ku.cs.util;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class ThemeChanger {

    public static String setTheme(Scene node){
        Theme theme = ((ObjectStorage) com.github.saacsos.FXRouter.getData()).getTheme();
        if(theme == Theme.DARK) {
            node.getRoot().getStylesheets().setAll((new Object() {}).getClass().getResource("/ku/cs/styles/darkTheme.css").toExternalForm());
        }
        if(theme == Theme.LIGHT) {
            node.getRoot().getStylesheets().setAll((new Object() {}).getClass().getResource("/ku/cs/styles/lightTheme.css").toExternalForm());
        }
        return (new Object() {}).getClass().getResource("/ku/cs/styles/darkTheme.css").toExternalForm();
    }
    public static String setTheme(AnchorPane node){
        Theme theme = ((ObjectStorage) com.github.saacsos.FXRouter.getData()).getTheme();
        if(theme == Theme.DARK) {
            node.getScene().getRoot().getStylesheets().setAll((new Object() {}).getClass().getResource("/ku/cs/styles/darkTheme.css").toExternalForm());
        }
        if(theme == Theme.LIGHT) {
            node.getScene().getRoot().getStylesheets().setAll((new Object() {}).getClass().getResource("/ku/cs/styles/lightTheme.css").toExternalForm());
        }
        return (new Object() {}).getClass().getResource("/ku/cs/styles/darkTheme.css").toExternalForm();
    }
}
