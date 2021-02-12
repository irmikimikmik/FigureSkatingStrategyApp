package model;

public class Spin extends Element {

    private int level;

    public Spin(String name, double basePoint, double goe, String type, int level) {
        super(name, basePoint, goe, type);
        this.level = level;
    }

    public void setLevel(int i) {
        this.level = i;
    }

    public int getLevel() {
        return this.level;
    }

}
