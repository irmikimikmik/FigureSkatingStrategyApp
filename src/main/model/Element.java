package model;

import org.json.JSONObject;
import persistence.Writable;

// This abstract class is about figure skating elements which are included in skaters' choreography and which have
//     different features like name, base point, GOE and type.
public abstract class Element implements Writable {

    protected String name;
    protected double basePoint;
    protected double goe;
    protected String type;

    // constructs an Element object
    public Element(String name, double basePoint, double goe, String type) {
        this.name = name;
        this.basePoint = basePoint;
        this.goe = goe;
        this.type = type;
    }

    // MODIFIES: this
    // EFFECTS: sets the name of the element
    public void setElementName(String name) {
        this.name = name;
    }

    // EFFECTS: returns the name of the element
    public String getElementName() {
        return this.name;
    }

    // there is no setBasePoint method because all the base points are already set in the csv files.

    // EFFECTS: returns the base point of the element
    public double getBasePoint() {
        return this.basePoint;
    }

    // MODIFIES: this
    // EFFECTS: adds to the GOE of the element
    public void addGOE(double f) {
        this.goe = this.goe + f;
    }

    // EFFECTS: returns the GOE of the element
    public double getGOE() {
        return this.goe;
    }

    // MODIFIES: this
    // EFFECTS: sets the type of the element either as "Jump", "Spin" or "Step"
    public void setElementType(String type) {
        this.type = type;
    }

    // EFFECTS: returns the type of the element
    public String getElementType() {
        return this.type;
    }

    public abstract void setRotationOrLevel(double d);

    public abstract double getRotationOrLevel();

    public abstract JSONObject toJson();

}
