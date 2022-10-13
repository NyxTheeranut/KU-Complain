package ku.cs.models.units;

import ku.cs.models.accounts.AccountList;
import ku.cs.models.accounts.Moderator;
import ku.cs.models.category.Category;
import ku.cs.services.datasource.accounts.AccountListFileDataSource;

import java.util.ArrayList;

public class Unit {
    private String unitName;

    private ArrayList<Category> categoryList;
    private ArrayList<Moderator> moderatorList;

    public Unit(String unitName){
        this.unitName = unitName;
        moderatorList = new ArrayList<>();
        categoryList = new ArrayList<>();
    }

    public void addModerator(Moderator moderator){
        moderatorList.add(moderator);
    }
    public void removeModerator(Moderator moderator) {
        for(Moderator m : moderatorList){
            if(m.getId().equals(moderator.getId())){
                moderatorList.remove(m);
                return;
            }
        }
    }
    public void addCategory(Category category) { categoryList.add(category); }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        AccountListFileDataSource dataSource = new AccountListFileDataSource();
        AccountList accountList = dataSource.readData();
        accountList.changeUnit(this.unitName, unitName);
        dataSource.writeData(accountList);

        this.unitName = unitName;
        for(Moderator m: moderatorList) m.setUnit(unitName);
    }

    public ArrayList<Category> getCategoryList() {
        return categoryList;
    }

    public ArrayList<Moderator> getModeratorList() {
        return moderatorList;
    }

    public String toString() { return unitName; }
}
