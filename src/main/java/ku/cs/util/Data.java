package ku.cs.util;

import ku.cs.services.filter.Filterer;

import java.util.ArrayList;

public final class Data {
    public static <T> T search(String id, ArrayList<T> objects, Filterer<T> filterer){
        for(T i : objects){
            //System.out.println(((Account)i).getName());
            if (filterer.found(i, id)){
                return i;
            }
        }
        return null;
    }
    public static <T> ArrayList<T> filter(String filter, ArrayList<T> objects, Filterer<T> filterer) {
        ArrayList<T> result = new ArrayList<>();
        for(T i : objects){
            //System.out.println(((Account)i).getName());
            if (filterer.found(i, filter)){
                result.add(i);
            }
        }
        return result;
    }
}
