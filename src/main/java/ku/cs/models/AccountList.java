package ku.cs.models;

import java.util.ArrayList;

public class UserList {
    private ArrayList<User> users;

    public UserList() {
        users = new ArrayList<>();
    }

    public void addUser(User user){
        users.add(user);
    }

    public ArrayList<User> getAllUser() {
        return users;
    }
}
