package ku.cs.models.accounts;

public class Account {

    private String role;
    private String id;
    private String username;
    private String password;
    private String name;
    private String surname;
    protected String imagePath;
    protected boolean isBanned;

    public Account(String role, String name, String password, String imagePath) {
        this.role = role;
        this.username = name;
        this.password = password;
        this.imagePath = imagePath; //ku/cs/image/default.png

    }

    public boolean checkLogin(String password) {
        return this.password.equals(password);
    }

    //getter
    public String getRole(){
        return role;
    }
    public String getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public String getImagePath() { return  imagePath; }
    public boolean getIsBanned() {
        return isBanned;
    }

    //setter
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

}
