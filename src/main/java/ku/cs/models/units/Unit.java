package ku.cs.models.units;

import ku.cs.models.accounts.Account;
import ku.cs.models.accounts.Moderator;

import java.util.ArrayList;

public class Unit {
    private String unitName;
    private ArrayList<Moderator> moderatorList;

    public Unit(String unitName){
        this.unitName = unitName;
        moderatorList = new ArrayList<>();
    }

    public void addModerator(Moderator moderator){
        moderatorList.add(moderator);
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public ArrayList<Moderator> getModeratorList() {
        return moderatorList;
    }
}
