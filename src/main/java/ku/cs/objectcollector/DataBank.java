package ku.cs.objectcollector;

import ku.cs.models.accounts.Account;
import ku.cs.models.complaints.Complaint;

import java.util.*;

public final class DataBank {
    static AbstractMap<String, Object> map = new HashMap<>();
    public static Account account;
    public static Complaint complaint;
    private DataBank(){};

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
