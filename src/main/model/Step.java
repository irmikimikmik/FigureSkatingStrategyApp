package model;

public class Step extends Element {

    private double level;

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

}
