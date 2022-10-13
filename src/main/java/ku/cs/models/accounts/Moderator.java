package ku.cs.models.accounts;

import java.time.LocalDateTime;
import java.util.UUID;

public class Moderator extends Account {
    private String unit;
    public Moderator(UUID id, String username, String password, String name, String surname, String imagePath, Boolean isBanned, int loginAttempt, String unbanRequest, LocalDateTime lastLogin, String unit) {
        super("mod",id,username,password,name,surname,imagePath,isBanned,loginAttempt,unbanRequest,lastLogin);
        this.unit = unit;
    }

    public String getUnit(){ return unit; }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String toString() {
        String str = this.getName()+" "+this.getSurname();
        if(unit != "") str += " (" + unit + ")";
        return str;
    }

}
