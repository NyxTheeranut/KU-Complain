package ku.cs.fontloader;

import javafx.scene.text.Font;

public class FontLoader {

    private static String packageStr = "/ku/cs/fonts/";

    public static Font fontLoad(String name, double size){
        Font font = Font.loadFont(FontLoader.class.getResource(packageStr+name).toExternalForm()
                .replaceAll("%20", " "), size);
        return font;
    }

    public static Font fontLoad(String name){
        return fontLoad(name, 10);
    }
}
