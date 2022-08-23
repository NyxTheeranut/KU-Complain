package ku.cs.models;

import ku.cs.models.Component;

import java.util.ArrayList;

public class ComponentList {
    private ArrayList<Component> components;

    public ComponentList() {
        components = new ArrayList<>();
    }

    public void addComponent(Component component){
        components.add(component);
    }

    public ArrayList<Component> getAllComponents() {
        return components;
    }
}
