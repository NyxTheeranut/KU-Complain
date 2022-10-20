package ku.cs.models.accounts;

import java.time.LocalDateTime;
import java.util.UUID;

public class Account {

    protected String role;
    protected UUID id;
    protected String username;
    protected String password;
    protected String name;
    protected String surname;
    protected String imagePath;
    protected LocalDateTime lastLogin;

    public Account(String role, UUID id, String username, String password, String name, String surname, String imagePath, LocalDateTime lastLogin) {
        this.role         = role;
        this.id           = id;
        this.username     = username;
        this.password     = password;
        this.name         = name;
        this.surname      = surname;
        this.imagePath    = imagePath;
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
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
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
                '}';
    }
}
