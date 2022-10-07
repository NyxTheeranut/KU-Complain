package ku.cs.util;

import javafx.scene.text.Font;

import java.util.AbstractMap;
import java.util.HashMap;

public final class FontLoader {

    private static AbstractMap<String, RouteFont> routes = new HashMap();

    private FontLoader(){}

    public static void bind(String fontLabel, String fontPath) {
        RouteFont routeFont = new RouteFont(fontPath);
        routes.put(fontLabel, routeFont);
    }

    public static Font font(String fontLabel, double size){
        RouteFont route = (RouteFont)routes.get(fontLabel);
        Font font = Font.loadFont(FontLoader.class.getResource(route.fontPath).toExternalForm().replaceAll("%20", " "), size);
        if (font == null) {
            System.err.println(
                    "Load font <" + fontLabel + "> failed.\n" +
                    "please check font path"
            );
            return Font.font("System");
        }
        return font;
    }
    private static class RouteFont {
        private String fontPath;
        private double fontSize;

        private RouteFont(String fontPath){
            this.fontPath = fontPath;
        }

    }
}


