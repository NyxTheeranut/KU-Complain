package ku.cs.models.accounts;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class User extends Account {
    private boolean isBanned;
    private String unbanRequest;
    private int loginAttempt;

    public User(UUID id, String username, String password, String name, String surname, String imagePath, LocalDateTime lastLogin, Boolean isBanned, String unbanRequest, int loginAttempt) {
        super("user", id, username, password, name, surname, imagePath, lastLogin);
        this.isBanned = isBanned;
        this.unbanRequest = unbanRequest;
        this.loginAttempt = loginAttempt;

    }
    public User(UUID id, String username, String password) {
        this(id, username, password, "", "", "default.jpg", LocalDateTime.now(), false, "", 0);
    }

    public boolean isBanned() {
        return isBanned;
    }
    public int getLoginAttempt() {
        return loginAttempt;
    }
    public String getUnbanRequest() {
        return unbanRequest;
    }

    public void setBanned(boolean banned){
        this.isBanned = banned;
    }
    public void setLoginAttempt(int loginAttempt) {
        this.loginAttempt = loginAttempt;
    }
    public void setUnbanRequest(String unbanRequest) {
        this.unbanRequest = unbanRequest;
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
                lastLogin.format(formatter) + "," +
                isBanned + ',' +
                unbanRequest + ',' +
                loginAttempt + ',';
    }
}
