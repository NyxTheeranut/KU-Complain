package ku.cs.services.filter;

import ku.cs.models.units.Unit;

import java.util.UUID;

public class UnitNameFilter implements Filterer<Unit>{
    @Override
    public boolean found(Unit obj, String filter) {
        if (filter.equals("")) return false;
        if (obj.getUnitName().equals(filter)) return true;
        return false;
    }

    @Override
    public String getName() {
        return "unit name";
    }
}
