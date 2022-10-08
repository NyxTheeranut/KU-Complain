package ku.cs.models.accounts;

import java.util.UUID;

public class Moderator extends Account {
    private String affiliation;
    public Moderator(UUID id, String username, String password, String name, String surname, String imagePath, Boolean isBanned, String affiliation) {
        super("mod",id,username,password,name,surname,imagePath,isBanned);
        this.affiliation = affiliation;
    }

    public String getAffiliation(){ return affiliation; }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public String toString() { return this.getName()+" "+this.getSurname()+" ("+affiliation+")"; }

}
