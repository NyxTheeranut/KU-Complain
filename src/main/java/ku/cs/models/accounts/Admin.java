package ku.cs.models.accounts;

public class Admin extends Account{
    public Admin(String id, String username, String password, String name, String surname, String imagePath, Boolean isBanned) {
        super("admin", name, password, "default.png");
    }
}
