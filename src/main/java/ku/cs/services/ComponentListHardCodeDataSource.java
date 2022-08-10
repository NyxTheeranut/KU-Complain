package ku.cs.services;

import ku.cs.models.Component;
import ku.cs.models.ComponentList;

public class ComponentListHardCodeDataSource {
    private ComponentList componentList;

    public ComponentListHardCodeDataSource() {
        componentList = new ComponentList();
        readData();
    }

    public void readData(){
        componentList.addComponent(new Component("บาบาบิบุ"));
        componentList.addComponent(new Component("บุบาบิเบอ"));
        componentList.addComponent(new Component("บิบุบาเบ"));
        componentList.addComponent(new Component("บาบิบุบอ"));
        componentList.addComponent(new Component("บอบาบุบิ"));
    }

    public ComponentList getAllComponents(){
        return componentList;
    }
}
