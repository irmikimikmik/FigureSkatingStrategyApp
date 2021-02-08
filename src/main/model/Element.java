package model;

java.lang.String;

public abstract class Element {

    private String name;
    private double basePoint;
    private double goe;
    private String type;

    public Element(String name, double basePoint, double goe, String type) {
        this.name = name;
        this.basePoint = basePoint;
        this.goe = goe;
        this.type = type;

    }

    public void setElementName(String name) {
        this.name = name;
    }

    public String getElementName() {
        return this.name;
    }

    public void setBasePoint(float f) {
        this.basePoint = f;
    }

    public double getBasePoint() {
        return this.basePoint;
    }

    public void addGOE(float f) {
        this.goe = this.goe + f;
    }

    public double getGOE() {
        return this.goe;
    }

    public void setElementType(String type) {
        this.type = type;
    }

    public String getElementType() {
        return this.type;
    }


    // something in regards to the order of the elements may be added
    // a list of elements with their base values must be created in data folder
    // also do you really need these getters and setters if you already declare all the fields in the constructor?

}
