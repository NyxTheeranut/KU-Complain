package ku.cs.models.accounts;

public class Moderator extends Account {
    public Moderator(String name, String password, String imagePath) {
        super("mod", name, password, imagePath);
    }


}
