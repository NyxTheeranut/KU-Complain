package ku.cs.models.components;

import java.util.ArrayList;

public class Component {
    private String category;

    //1
    private ArrayList<String> textField;
    private ArrayList<String> picture;
    //....

    //2
    private ArrayList<String> fields;
    // textField-fieldName1
    // picture-fieldName2
    // textField-fieldName3

    public Component(String category) {
        this.category = category;
    }
}
