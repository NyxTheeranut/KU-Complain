package ku.cs.objectcollector;

import javafx.scene.text.Font;
import ku.cs.fontloader.FontLoader;

import java.util.*;

public final class ObjectCollector {
    static AbstractMap<String, Object> map = new HashMap<>();

    private ObjectCollector(){};

    public static void add(String objectKey, Object object) {
        map.put(objectKey, object);
    }

    public static Object find(String objectKey){
        Object object = map.get(objectKey);
        if (object == null) {
            System.err.println(
                    "Load object <" + objectKey + "> failed.\n" +
                            "please check object key"
            );
            return null;
        }
        return object;
    }

}
