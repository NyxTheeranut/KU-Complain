package ku.cs.models.units;

import ku.cs.models.accounts.Account;
import ku.cs.models.accounts.AccountList;
import ku.cs.models.accounts.Moderator;
import ku.cs.models.category.Category;
import ku.cs.services.datasource.accounts.AccountListFileDataSource;
import ku.cs.services.filter.AccountUnitFilter;
import ku.cs.util.Data;

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

        ArrayList<Account> moderatorArrayList = Data.filter(this.unitName, accountList.getAllAccount(), new AccountUnitFilter());

        for (Account i : moderatorArrayList) {
            ((Moderator) i).setUnit(unitName);
        }
        dataSource.writeData(accountList);

        this.unitName = unitName;
    }

    public ArrayList<Category> getCategoryList() {
        return categoryList;
    }

    public ArrayList<Moderator> getModeratorList() {
        return moderatorList;
    }

    public String toString() { return unitName; }
}
