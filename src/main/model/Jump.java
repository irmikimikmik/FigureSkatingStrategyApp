package model;

public class Jump extends Element{

    private float rotations;

    public Jump(String name, double basePoint, double goe, String type) {
        this.name = name;
        this.basePoint = basePoint;
        this.goe = goe;
        this.type = type;
    }
    public void setRotations(float f){
        this.rotations = f;
    }

    public float getRotations(){
        return this.rotations;
    }

}
