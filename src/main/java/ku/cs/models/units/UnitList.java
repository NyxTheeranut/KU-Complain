package ku.cs.models.units;

import ku.cs.models.accounts.Account;
import ku.cs.models.accounts.Moderator;
import ku.cs.models.category.Category;
import ku.cs.services.datasource.accounts.AccountListFileDataSource;
import ku.cs.services.filter.AccountIdFilter;
import ku.cs.services.filter.UnitNameFilter;
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
                u.addModerator((Moderator) Util.search(id.toString(),new AccountListFileDataSource().readData().getAllAccount(),new AccountIdFilter()));
        }
    }
    public void addCategory(String unitName, Category category){
        Util.search(unitName,units,new UnitNameFilter()).addCategory(category);
    }

    public void removeModerator(String unitName, Moderator moderator){
        Util.search(unitName,units,new UnitNameFilter()).removeModerator(moderator);
    }

    public ArrayList<Unit> getAllUnits(){
        return units;
    }
}
