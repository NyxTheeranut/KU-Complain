package ku.cs.models.accounts;

import java.util.UUID;

public class Moderator extends Account {
    public Moderator(UUID id, String username, String password, String name, String surname, String imagePath, Boolean isBanned) {
        super("mod",id,username,password,name,surname,imagePath,isBanned);
    }


}
