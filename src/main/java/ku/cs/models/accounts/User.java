package ku.cs.models.accounts;

import ku.cs.models.accounts.Account;

import java.util.UUID;

public class User extends Account {
    public void getBanned(){
        isBanned = true;
    }
    public void getUnBanned(){
        isBanned = false;
    }
    public void setImagePath(String url){
        imagePath = url;
    }
    public User(UUID id, String username, String password, String name, String surname, String imagePath, Boolean isBanned) {
        super("user", id, username, password, name, surname, imagePath, isBanned);
    }

    public User(UUID id, String username, String password) {
        this(id, username, password, "", "", "default.png", false);
    }

}
