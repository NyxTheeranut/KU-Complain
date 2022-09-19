package ku.cs.models.complaints;

import ku.cs.models.category.Category;

import java.util.ArrayList;

public class Complaint {
    private String id;
    private String topic;
    private Category category;
    //Something to load category's component //??map
    private ArrayList<String> fields;

    public Complaint(String topic, Category category, ArrayList<String> fields) {
        this.topic = topic;
        this.category = category;
        this.fields = fields;
    }
    public Complaint(String topic, Category category){
        this(topic, category, null);
    }

    //Getter
    public String getId() {
        return id;
    }
    public String getTopic() {
        return topic;
    }
    public Category getCategory() {
        return category;
    }
    public ArrayList<String> getFields() {
        return fields;
    }

    @Override
    public String toString() {
        return  topic + " (" + category.getName() + ')';
    }
}
