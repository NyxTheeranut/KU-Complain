package ku.cs.models.complaints;

import ku.cs.models.accounts.Account;
import ku.cs.models.accounts.Moderator;
import ku.cs.models.category.Category;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Complaint {
    private String id;
    private Account author; //Store account id
    private String topic;
    private Category category;
    private ArrayList<String> fields;
    private LocalDateTime datePosted;

    private Account moderator;
    private String solvingDetail;

    public Complaint(String id, Account author, String topic, Category category, LocalDateTime datePosted,
                     Account moderator, String solvingDetail, ArrayList<String> fields) {
        this.id            = id;
        this.author        = author;
        this.topic         = topic;
        this.category      = category;
        this.datePosted    = datePosted;
        this.moderator     = moderator;
        this.solvingDetail = solvingDetail;
        this.fields        = fields;
    }

    public Complaint(String id, Account author, String topic, Category category, LocalDateTime datePosted,
                     ArrayList<String> fields) {
        this(id, author, topic, category, datePosted, null, "", fields);
    }

    //Getter
    public String getId() {
        return id;
    }
    public Account getAuthor() {
        return author;
    }
    public String getTopic() {
        return topic;
    }
    public Category getCategory() {
        return category;
    }
    public LocalDateTime getDatePosted() {
        return datePosted;
    }
    public Account getModerator() {
        return moderator;
    }
    public String getSolvingDetail() {
        return solvingDetail;
    }
    public ArrayList<String> getFields() {
        return fields;
    }

    @Override
    public String toString() {
        return  topic + " (" + category.getName() + ')';
    }
}
