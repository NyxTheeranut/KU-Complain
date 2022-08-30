package ku.cs.models;

import java.util.ArrayList;

public class UserList {
    private ArrayList<User> nameAndPass;

    public UserList() {
        nameAndPass = new ArrayList<>();
    }

    public void addData(User data){
        nameAndPass.add(data);
    }
}
