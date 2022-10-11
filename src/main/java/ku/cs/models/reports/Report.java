package ku.cs.models.reports;

import ku.cs.models.accounts.User;


public class Report {

    private String type;
    private String id;
    private String topic;
    private String description;

    public Report(String type, String id, String topic, String description) {
        this.id = id;
        this.type = type;
        this.topic = topic;
        this.description = description;
    }

    public String getId() {
        return id;
    }
    public String getType() {
        return type;
    }
    public String getTopic() {
        return topic;
    }
    public String getDescription() {
        return description;
    }

    public void setType(String type) {
        this.type = type;
    }
    public void setTopic(String topic) {
        this.topic = topic;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
