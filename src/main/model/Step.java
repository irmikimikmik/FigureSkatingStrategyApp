package model;

public class Step extends Element {

    private int level;

    public Step(String name, double basePoint, double goe, String type, double rotations, int level) {
        super(name, basePoint, goe, type);
        this.level = level;
    }

    // REQUIRES: A step element which is not a Choreo Sequence, so the element must be a Step Sequence
    //!!!
    public void setLevel(int i) {
        this.level = i;
    }

    public int getLevel() {
        return this.level;
    }

}
