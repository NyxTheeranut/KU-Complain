package ku.cs.models.category;

import javafx.util.Pair;

import java.util.ArrayList;

public class Category {
    private String name;

    private ArrayList<Pair<String, String>> fields = new ArrayList<>();
    // text-fieldName1
    // pic-fieldName2
    // detail-fieldName3

    public Category(String category) {
        this.name = category;
    }
    public void addField(String type, String name){
        Pair<String, String> field = new Pair<String, String>(type, name);
        this.fields.add(field);
    }
    public String getName() {
        return name;
    }
    public ArrayList<Pair<String, String>> getFields() {
        return fields;
    }

    public String toString() { return name; }
}
