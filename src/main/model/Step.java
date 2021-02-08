package model;

public class Step extends Element{

    private int level;

    // REQUIRES: A step element which is not a Choreo Sequence, so the element must be a Step Sequence
    //!!!
    public void setLevel(int i){
        this.level = i;
    }

    public int getLevel(){
        return this.level;
    }

}
