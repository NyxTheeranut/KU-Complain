package ku.cs.models.accounts;

public class Moderator extends Account {
    public Moderator(String id, String username, String password, String name, String surname, String imagePath, Boolean isBanned) {
        super("mod", name, password, imagePath);
    }


}
