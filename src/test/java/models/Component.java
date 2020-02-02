package models;

public class Component {
    public String componentType;
    public String componentName;

    public Component(String componentName) {
        this.componentName = componentName;
    }

    public Component(String componentType, String componentName) {
        this.componentName = componentName;
        this.componentType = componentType;
    }

    public String getComponentType() {
        return componentType;
    }

    public void setComponentType(String componentType) {
        this.componentType = componentType;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }
}

