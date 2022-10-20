package ku.cs.util;

import javafx.scene.layout.AnchorPane;
import ku.cs.controllers.HomeController;
import ku.cs.models.accounts.Account;
import ku.cs.models.complaints.Complaint;
import ku.cs.models.units.Unit;

import java.util.AbstractMap;
import java.util.HashMap;

public class ObjectStorage {
    private AbstractMap<String, Object> map = new HashMap<>();
    //composition
    private Account account;
    private Complaint complaint;
    private HomeController homeController;
    private Unit unit;
    private Theme theme;

    /**
     *
     * @param objectKey Key to accesses object
     * @param object Object to store
     */
    public void put(String objectKey, Object object) {
        map.put(objectKey, object);
    }
    /**
     *
     * @param objectKey - Key of the object
     * @return value of key (Object)
     */
    public Object get(String objectKey){
        Object object = map.get(objectKey);
        if (object == null) {
            System.err.println(
                    "Load object <" + objectKey + "> failed.\n" +
                            "please check object key"
            );
            return null;
        }
        return object;
    }

    public Account getAccount() {
        return account;
    }
    public Complaint getComplaint() {
        return complaint;
    }
    public HomeController getHomeController() {
        return homeController;
    }
    public Theme getTheme() {
        return theme;
    }
    public void setAccount(Account account) {
        this.account = account;
    }
    public void setComplaint(Complaint complaint) {
        this.complaint = complaint;
    }
    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
    }
    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
