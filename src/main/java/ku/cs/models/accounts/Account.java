package ku.cs.models.accounts;

public class Account {

    private String id;
    private String role;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String imagePath;

    public Account(String role, String name, String password, String imagePath) {
        this.role = role;
        this.username = name;
        this.password = password;
        this.imagePath = imagePath; //ku/cs/image/default.png

    }

    public String getRole(){
        return role;
    }
    public String getName() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getImagePath() { return  imagePath; }

    public void setName(String name) {
        this.username = name;
    }
    private void setPassword(String password) {
        this.password = password;
    }


    public boolean checkLogin(String password) {
        return this.password.equals(password);
    }


}
