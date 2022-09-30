package ku.cs.models.units;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;

public class UnitList {
    private ArrayList<Unit> units;

    public UnitList() {
        this.units = new ArrayList<>();
    }

    public void addUnit(Unit unit){
        units.add(unit);
    }

    public ArrayList<Unit> getAllUnits(){
        return units;
    }
}
