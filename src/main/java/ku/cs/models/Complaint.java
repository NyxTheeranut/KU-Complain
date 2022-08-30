package ku.cs.models;

public class Complaint {
    private String topic;

    private String category;

    //Something to load category's component //??map
    private String detail;

    public Complaint(String topic, String category) {
        this.topic = topic;
        this.category = category;
    }

    @Override
    public String toString() {
        return  topic + " (" + category + ')';
    }

    public String getTopic() {
        return topic;
    }

    public String getCategory() {
        return category;
    }
}
