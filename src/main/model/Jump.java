package model;

// This subclass of element is about a certain type of element which is Jumps. Jumps have very similar functionality to
//      the elements class but in addition, we also consider the rotation of a jump.
public class Jump extends Element {

    private double rotations;

    public Jump(String name, double basePoint, double goe, String type, double rotations) {
        super(name, basePoint, goe, type);
        this.rotations = rotations;
    }

    // REQUIRES: a positive decimal that is the half of an integer
    // MODIFIES: this
    // EFFECTS: sets the number of rotations of the jump
    public void setRotationOrLevel(double f) {
        this.rotations = f;
    }

    // EFFECTS: returns the number of rotations of the jump
    public double getRotationOrLevel() {
        return this.rotations;
    }
}
