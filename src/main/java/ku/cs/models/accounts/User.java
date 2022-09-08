package ku.cs.models.accounts;

import ku.cs.models.accounts.Account;

public class User extends Account {
    public User(String name, String password, String imagePath) {
        super("user", name, password, imagePath);
    }

}
