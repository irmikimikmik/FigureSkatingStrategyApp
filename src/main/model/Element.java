package model;

public abstract class Element {

    protected String name;
    protected double basePoint;
    protected double goe;
    protected String type;

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

}
