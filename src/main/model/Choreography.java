package model;

import java.util.ArrayList;
import java.util.List;

public class Choreography {

    private double deductions;
    private boolean category; //true is ladies, false is men
    private int falls;
    private double duration;
    private boolean type; //true is short program, false is free program
    private double skatingSkillsComponent;
    private List<Element> listOfElements;

    public Choreography(double deductions, boolean category, int falls,
                        double duration, boolean type, double sscomponent) {
        this.deductions = deductions;
        this.category = category;
        this.falls = falls;
        this.duration = duration;
        this.type = type;
        this.skatingSkillsComponent = sscomponent;
        this.listOfElements = new ArrayList<Element>();
    }

    public void setDeductions(float deduction) {
        this.deductions = deduction;
    }

    public double getDeductions() {
        return this.deductions;
    }

    public void setSkaterCategory(boolean b) {
        this.category = b;
    }

    public boolean getSkaterCategory() {
        return this.category;
    }

    public void addFalls(int i) {
        this.falls = this.falls + i;
    }

    public int getFalls() {
        return this.falls;
    }

    public void setDuration(float f) {
        this.duration = f;
    }

    public double getDuration() {
        return this.duration;
    }

    public void setType(boolean b) {
        this.type = b;
    }

    public boolean getType() {
        return this.type;
    }

    public void setSkatingSkillsComponent(float f) {
        this.skatingSkillsComponent = f;
    }

    public double getSkatingSkillsComponent() {
        return this.skatingSkillsComponent;
    }

    public int size() {
        int result = 0;
        for (Element e : this.listOfElements) {
            result++;
        }
        return result;
    }


    //!!!
    public void addElement(Element e) {
        this.listOfElements.add(e);
    }

//    public Element removeElement(){
//        this.listOfElements.remove();
//    }

    // Write Specifications for the entire project!!!
    // add constructors to step jmp

    // deduction counter
    // contains more than twice?
    // is second half?
}
