package ku.cs.services;

import ku.cs.models.accounts.Account;
import ku.cs.models.complaints.Complaint;
import ku.cs.services.searcher.Searcher;

import java.util.*;

public final class Utility {
    //Object Storage
    static AbstractMap<String, Object> map = new HashMap<>();
    public static Account account;
    public static Complaint complaint;
    private Utility(){};
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

    public static <T> T search(String id, ArrayList<T> objects, Searcher<T> searcher){
        for(T i : objects){
            //System.out.println(((Account)i).getName());
            if (searcher.found(i, id)){
                return i;
            }
        }
        return null;
    }

}
