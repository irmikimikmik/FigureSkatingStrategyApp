package model;

public class Jump extends Element {

    private double rotations;

    public Jump(String name, double basePoint, double goe, String type, double rotations) {
        super(name, basePoint, goe, type);
        this.rotations = rotations;
    }

    public void setRotations(float f) {
        this.rotations = f;
    }

    public double getRotations() {
        return this.rotations;
    }

}
