package ku.cs.models.accounts;

public class Account {

    private String imagePath;
    private String name;
    private String password;

    public Account(String name, String password) {
        this.name = name;
        this.password = password;
        this.imagePath = "src\\main\\resources\\ku\\cs\\image";

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    public boolean checkLogin(String password) {
        return this.password.equals(password);
    }


}
