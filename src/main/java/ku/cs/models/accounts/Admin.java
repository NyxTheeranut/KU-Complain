package ku.cs.models.accounts;

import java.util.UUID;

public class Admin extends Account{
    public Admin(UUID id, String username, String password, String name, String surname, String imagePath, Boolean isBanned) {
        super("admin", id,username,password,name,surname,imagePath,isBanned);
    }
}
