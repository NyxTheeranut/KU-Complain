package ku.cs.models.units;

import ku.cs.models.accounts.Moderator;
import ku.cs.services.datasource.accounts.AccountListFileDataSource;
import ku.cs.services.filter.AccountIdFilter;
import ku.cs.util.Data;
import ku.cs.util.Util;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.UUID;

public class UnitList {
    private ArrayList<Unit> units;

    public UnitList() {
        this.units = new ArrayList<>();
    }

    public void addUnit(Unit unit){
        units.add(unit);
    }
    public void addModerator(String unitName, UUID id) {
        for (Unit u:units) {
            if(unitName.equals(u.getUnitName()))
                u.addModerator((Moderator) Data.search(id.toString(),new AccountListFileDataSource().readData().getAllAccount(),new AccountIdFilter()));
        }
    }

    public ArrayList<Unit> getAllUnits(){
        return units;
    }
}
