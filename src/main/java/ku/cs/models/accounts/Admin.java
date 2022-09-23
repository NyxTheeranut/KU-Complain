package ku.cs.models.accounts;

public class Admin extends Account{
    public Admin(String id, String username, String password, String name, String surname, String imagePath, Boolean isBanned) {
        super("admin", id,username,password,name,surname,imagePath,isBanned);
    }
}
