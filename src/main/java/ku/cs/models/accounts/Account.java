package ku.cs.models.accounts;

public class Account {

    private String imagePath;
    private String name;
    private String password;
    private String role;

    public Account(String role, String name, String password, String imagePath) {
        this.role = role;
        this.name = name;
        this.password = password;
        this.imagePath = imagePath; //ku/cs/image/default.png

    }

    public String getRole(){
        return role;
    }
    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }
    public String getImagePath() { return  imagePath; }

    public void setName(String name) {
        this.name = name;
    }
    private void setPassword(String password) {
        this.password = password;
    }


    public boolean checkLogin(String password) {
        return this.password.equals(password);
    }


}
