package ku.cs.models.accounts;

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

    public Account(String role, UUID id, String username, String password, String name, String surname, String imagePath, boolean isBanned) {
        this.role = role;
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.imagePath = imagePath;
        this.isBanned = isBanned;
    }

    public boolean checkLogin(String password) {
        return this.password.equals(password);
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
    public boolean getIsBanned() {
        return isBanned;
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
}
