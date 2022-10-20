package ku.cs.models.accounts;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Moderator extends Account {
    private String unit;
    public Moderator(UUID id, String username, String password, String name, String surname, String imagePath, LocalDateTime lastLogin, String unit) {
        super("mod",id,username,password,name,surname,imagePath,lastLogin);
        this.unit = unit;
    }

    public String getUnit(){ return unit; }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String format() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return role + ',' +
                id + ',' +
                username + ',' +
                password + ',' +
                name + ',' +
                surname + ',' +
                imagePath + ',' +
                lastLogin.format(formatter) + ',' +
                unit;
    }

}
