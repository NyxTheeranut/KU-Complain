package ku.cs.models.complaints;

import javafx.util.Pair;
import ku.cs.models.accounts.Account;
import ku.cs.models.category.Category;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class Complaint {
    private static UUID id;
    private static Account author; //Store account id
    private String topic;
    private Category category;
    private ArrayList<String> fields;
    private LocalDateTime datePosted;
    private Status status;
    private Account moderator;
    private String solvingDetail;
    private ArrayList<UUID> votes;

    public Complaint(UUID id, Account author, String topic, Category category, LocalDateTime datePosted, Status status,
                     Account moderator, String solvingDetail, ArrayList<String> fields, ArrayList<UUID> votes) {
        this.id            = id;
        this.author        = author;
        this.topic         = topic;
        this.category      = category;
        this.datePosted    = datePosted;
        this.status        = status;
        this.moderator     = moderator;
        this.solvingDetail = solvingDetail;
        this.fields        = fields;
        this.votes         = votes;
    }

    public Complaint(UUID id, Account author, String topic, Category category, LocalDateTime datePosted,
                     ArrayList<String> fields) {
        this(id, author, topic, category, datePosted, Status.NOTSTARTED, null, "", fields, new ArrayList<>());
    }
    public Boolean addVote(Account account) {
        if (checkVote(account)) {
            votes.add(account.getId());
            return true;
        }
        return false;
    }
    public boolean checkVote(Account account) {
        for (UUID i:votes) {
            if (i.equals(account.getId())) return false;
        }
        return true;
    }
    //Setter
    public void setStatus(Status status) {
        this.status = status;
    }
    public void setModerator(Account moderator) {
        this.moderator = moderator;
    }
    public void setSolvingDetail(String solvingDetail) {
        this.solvingDetail = solvingDetail;
    }


    //Getter
    public static UUID getId() {
        return id;
    }
    public static Account getAuthor() {
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
    public Status getStatus() {
        return status;
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
    public ArrayList<UUID> getVotes() {
        return votes;
    }
    public int getVote() {
        int co=0;
        for (UUID i: votes) {
            co++;
        }
        return co;
    }

    @Override
    public String toString() {
        return topic;
//        return "Complaint{" +
//                "id=" + id +
//                ", author=" + author +
//                ", topic='" + topic + '\'' +
//                ", category=" + category +
//                ", fields=" + fields +
//                ", datePosted=" + datePosted +
//                ", status=" + status +
//                ", moderator=" + moderator +
//                ", solvingDetail='" + solvingDetail + '\'' +
//                ", votes=" + votes +
//                '}';
    }
}
