package ku.cs.models.accounts;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Admin extends Account{
    public Admin(UUID id, String username, String password, String name, String surname, String imagePath, LocalDateTime lastLogin) {
        super("admin", id,username,password,name,surname,imagePath,lastLogin);
    }
    public void ban(User user) {
        user.setBanned(true);
    }
    public void unban(User user) {
        user.setLoginAttempt(0);
        user.setUnbanRequest("");
        user.setBanned(false);
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
                lastLogin.format(formatter) + ",,,,";
    }
}
