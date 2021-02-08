package model;

import java.util.ArrayList;
import java.util.List;

public class Choreography {

    private float deductions;
    private boolean category; //true is ladies, false is men
    private int falls;
    private float duration;
    private boolean type; //true is short program, false is free program
    private float skatingSkillsComponent;
    private List<Element> listOfElements;

    public Choreography Choreography(){
        this.deductions = 0;
        this.category = true;
        this.falls = 0;
        this.duration = 0;
        this.type = true;
        this.skatingSkillsComponent = 0;
        this.listOfElements = new ArrayList<Element>();
    }

    public void setDeductions(float deduction){
        this.deductions = deduction;
    }

    public float getDeductions(){
        return this.deductions;
    }

    public void setSkaterCategory(boolean b){
        this.category = b;
    }

    public boolean getSkaterCategory(){
        return this.category;
    }

    public void addFalls(int i){
        this.falls = this.falls + i;
    }

    public int getFalls(){
        return this.falls;
    }

    public void setDuration(float f){
        this.duration = f;
    }

    public float getDuration(){
        return this.duration;
    }

    public void setType(boolean b){
        this.type = b;
    }

    public boolean getType(){
        return this.type;
    }

    public void setSkatingSkillsComponent(float f){
        this.skatingSkillsComponent = f;
    }

    public float getSkatingSkillsComponent(){
        return this.skatingSkillsComponent;
    }

    //!!!
    public void addElement(Element e){
        this.listOfElements.add(e);
    }

    public Element removeElement(){
        this.listOfElements.remove();
    }

    public int size(){
        int result = 0;
        for (Element e : this.listOfElements){
            result++;
        }
        return result;
    }




    // Write Specifications for the entire project!!!
    // add constructors to step jmp

    // deduction counter
    // contains more than twice?
    // is second half?
}
