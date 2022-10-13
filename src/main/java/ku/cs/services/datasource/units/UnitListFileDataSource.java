package ku.cs.services.datasource.units;

import ku.cs.models.accounts.*;
import ku.cs.models.category.Category;
import ku.cs.models.category.CategoryList;
import ku.cs.models.units.Unit;
import ku.cs.models.units.UnitList;
import ku.cs.services.datasource.DataSource;
import ku.cs.services.datasource.accounts.AccountListFileDataSource;
import ku.cs.services.datasource.categorytlists.CategoryListFileDataSource;
import ku.cs.services.filter.AccountIdFilter;
import ku.cs.util.Data;
import ku.cs.services.filter.CategoryNameFilter;

import java.io.*;

public class UnitListFileDataSource implements DataSource<UnitList> {
    private final String directoryName = "data";
    private final String fileName = "unit_list.csv";
    @Override
    public UnitList readData() {
        UnitList unitList = new UnitList();
        File file = new File(directoryName+File.separator+fileName);
        FileReader reader = null;
        BufferedReader buffer = null;
        Account account = null;
        try {
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);
            String line = "";
            AccountListFileDataSource dataSource = new AccountListFileDataSource();
            AccountList accountList = dataSource.readData();
            CategoryListFileDataSource dataSource2 = new CategoryListFileDataSource();
            CategoryList categoryList = dataSource2.readData();
            while((line = buffer.readLine()) != null){
                //System.out.println(line);
                String[] data = line.split(",");
                //System.out.println(data.length);
                Unit unit = new Unit(data[0]);
                if(data.length > 1 && !data[1].equals("")) for(String moderatorID: data[1].split(":")) unit.addModerator((Moderator) Data.search(moderatorID,accountList.getAllAccount(),new AccountIdFilter()));
                if(data.length > 2 && !data[2].equals("")) for(String categoryName : data[2].split(":")) unit.addCategory((Category) Data.search(categoryName,categoryList.getAllCategory(),new CategoryNameFilter()));
                unitList.addUnit(unit);
            }
        }catch (FileNotFoundException e){
            throw new RuntimeException(e);
        }catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                buffer.close();
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return unitList;
    }

    @Override
    public void writeData(UnitList unitList) {
        File file = new File(directoryName + File.separator + fileName);
        FileWriter writer = null;
        BufferedWriter buffer = null;
        try {
            writer = new FileWriter(file);
            buffer = new BufferedWriter(writer);
            for(Unit unit:unitList.getAllUnits()) {
                String line = unit.getUnitName();
                line += ",";
                //System.out.println(1);
                for(int i=0; i < unit.getModeratorList().size(); i++ ) {
                    if(i>0) line += ":";
                    line += unit.getModeratorList().get(i).getId();
                }
                //System.out.println(2);
                line += ",";
                for(int i=0; i < unit.getCategoryList().size(); i++ ) {
                    if(i>0) line += ":";
                    line += unit.getCategoryList().get(i).getName();
                }
                //System.out.println(3);
                buffer.append(line);
                buffer.newLine();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Invalid file path");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                buffer.close();
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
