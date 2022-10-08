package ku.cs.models.units;

import ku.cs.models.accounts.Account;
import ku.cs.models.accounts.AccountList;
import ku.cs.models.accounts.Moderator;
import ku.cs.services.datasource.accounts.AccountListFileDataSource;

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
        AccountListFileDataSource dataSource = new AccountListFileDataSource();
        AccountList accountList = dataSource.readData();
        accountList.changeUnit(this.unitName, unitName);
        dataSource.writeData(accountList);

        this.unitName = unitName;
        for(Moderator m: moderatorList) m.setAffiliation(unitName);
    }

    public ArrayList<Moderator> getModeratorList() {
        return moderatorList;
    }

    public String toString() { return unitName; }
}
