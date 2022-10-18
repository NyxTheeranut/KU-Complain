package ku.cs.models.accounts;

import javafx.scene.image.Image;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.UUID;

public class Account {

    private String role;
    private UUID id;
    private String username;
    private String password;
    private String name;
    private String surname;
    protected String imagePath;
    protected boolean isBanned;
    private int loginAttempt;
    private String unbanRequest;
    private LocalDateTime lastLogin;

    public Account(String role, UUID id, String username, String password, String name, String surname, String imagePath, boolean isBanned, int loginAttempt, String unbanRequest, LocalDateTime lastLogin) {
        this.role         = role;
        this.id           = id;
        this.username     = username;
        this.password     = password;
        this.name         = name;
        this.surname      = surname;
        this.imagePath    = imagePath;
        this.isBanned     = isBanned;
        this.loginAttempt = loginAttempt;
        this.unbanRequest = unbanRequest;
        this.lastLogin    = lastLogin;
    }
    //getter
    public String getRole(){
        return role;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getImagePath() { return  imagePath; }

    public boolean isBanned() {
        return isBanned;
    }

    public int getLoginAttempt() {
        return loginAttempt;
    }

    public String getUnbanRequest() {
        return unbanRequest;
    }

    public Image getImage() {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream("data" +
                    File.separator+ "image" +
                    File.separator+ "account" +
                    File.separator+ imagePath);
        } catch (FileNotFoundException e) {
            System.err.println("Cannot open image");
            System.err.println(e);
        }
        Image image = new Image(fileInputStream);
        return image;
    }

    public LocalDateTime getLastLogin() {
        return this.lastLogin;
    }

    //setter
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setLoginAttempt(int loginAttempt) {
        this.loginAttempt = loginAttempt;
    }

    public void setUnbanRequest(String unbanRequest) {
        this.unbanRequest = unbanRequest;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public void setBanned(boolean banned) {
        isBanned = banned;
    }

    @Override
    public String toString() {
        return "Account{" +
                "role='" + role + '\'' +
                ", id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", isBanned=" + isBanned +
                '}';
    }
}
