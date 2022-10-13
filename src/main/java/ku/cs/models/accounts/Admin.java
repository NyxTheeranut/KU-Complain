package ku.cs.models.accounts;

import java.time.LocalDateTime;
import java.util.UUID;

public class Admin extends Account{
    public Admin(UUID id, String username, String password, String name, String surname, String imagePath, Boolean isBanned, int loginAttempt, String unbanRequest, LocalDateTime lastLogin) {
        super("admin", id,username,password,name,surname,imagePath,isBanned,loginAttempt,unbanRequest,lastLogin);
    }
}
