package model;

import org.json.JSONObject;

// This subclass of element is about a certain type of element which is Steps. Steps have very similar functionality to
//      the elements class but in addition, we also consider the level of a step.
public class Step extends Element {

    private double level;

    // creates a Step object
    public Step(String name, double basePoint, double goe, String type, double level) {
        super(name, basePoint, goe, type);
        this.level = level;
    }

    // REQUIRES: a an integer from 0 to 4 (the letter B is considered to be Level 0 in the names of the elements)
    // MODIFIES: this
    // EFFECTS: sets the level of the step
    public void setRotationOrLevel(double i) {
        this.level = i;
    }

    // EFFECTS: returns the level of the step
    public double getRotationOrLevel() {
        return this.level;
    }

    // EFFECTS: converts a Step object to a JSONObject
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("basePoint", basePoint);
        json.put("goe", goe);
        json.put("type", type);
        json.put("level", level);
        return json;
    }

}
