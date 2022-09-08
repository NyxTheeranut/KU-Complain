package ku.cs.models.accounts;

public class Admin extends Account{
    public Admin(String name, String password) {
        super("admin", name, password, "default.png");
    }
}
