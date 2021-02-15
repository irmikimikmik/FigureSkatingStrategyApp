package model;

public class Spin extends Element {

    private double level;

    public Spin(String name, double basePoint, double goe, String type, double level) {
        super(name, basePoint, goe, type);
        this.level = level;
    }

    // REQUIRES: a an integer from 0 to 4 (the letter B is considered to be Level 0 in the names of the elements)
    // MODIFIES: this
    // EFFECTS: sets the level of the spin
    public void setRotationOrLevel(double d) {
        this.level = d;
    }

    // EFFECTS: get level of the spin
    public double getRotationOrLevel() {
        return this.level;
    }

}
