package ku.cs.services.units;

import ku.cs.models.accounts.*;
import ku.cs.models.units.Unit;
import ku.cs.models.units.UnitList;
import ku.cs.services.datasource.DataSource;
import ku.cs.services.datasource.accounts.AccountListFileDataSource;
import ku.cs.services.filter.AccountIdFilter;
import ku.cs.util.Util;

import java.io.*;
import java.util.UUID;

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
            while((line = buffer.readLine()) != null){
                String[] data = line.split(",");
                Unit unit = new Unit(data[0]);
                for(int i=1;i<data.length;i++) unit.addModerator((Moderator) Util.search(data[i],accountList.getAllAccount(),new AccountIdFilter()));
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
                for(Moderator mod:unit.getModeratorList()) line += ","+mod.getId();
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
