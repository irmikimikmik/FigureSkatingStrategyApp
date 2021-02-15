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


    // REQUIRES: a positive decimal
    // MODIFIES: this
    // EFFECTS: changes the number of deductions in the choreography
    public void setDeductions(double deduction) {
        this.deductions = deduction;
    }

    // EFFECTS: returns the number of deductions in the choreography
    public double getDeductions() {
        return this.deductions;
    }

    // MODIFIES: this
    // EFFECTS: sets the skater category to either ladies (true) or men (false)
    public void setSkaterCategory(boolean b) {
        this.category = b;
    }

    // EFFECTS: returns the category of the skater who presents the choreography
    public boolean getSkaterCategory() {
        return this.category;
    }

    // REQUIRES: a positive integer
    // MODIFIES: this
    // EFFECTS: adds to the number of falls in the choreography
    public void addFalls(int i) {
        this.falls = this.falls + i;
    }

    // EFFECTS: returns the number of falls in the choreography
    public int getFalls() {
        return this.falls;
    }


    // REQUIRES: a positive decimal with one digit before and two digits after the decimal point
    // MODIFIES: this
    // EFFECTS: sets the duration of the choreography
    public void setDuration(double f) {
        this.duration = f;
    }

    // EFFECTS: returns the duration of the choreography
    public double getDuration() {
        return this.duration;
    }

    // MODIFIES: this
    // EFFECTS: sets the type of the choreography to either short program (true) or free program (false)
    public void setType(boolean b) {
        this.type = b;
    }

    // EFFECTS: returns the type of the choreography
    public boolean getType() {
        return this.type;
    }

    // REQUIRES: a positive decimal with two digits before and after the decimal point
    // MODIFIES: this
    // EFFECTS: sets the skating skills component of the choreography
    public void setSkatingSkillsComponent(double f) {
        this.skatingSkillsComponent = f;
    }

    // EFFECTS: gets the skating skills component of the choreography
    public double getSkatingSkillsComponent() {
        return this.skatingSkillsComponent;
    }

    // EFFECTS: returns the number of elements in the choreography
    public int size() {
        int result = 0;
        for (Element e : this.listOfElements) {
            result++;
        }
        return result;
    }

    // MODIFIES: this
    // EFFECTS: adds a new element to the choreography
    public void addElement(Element e) {
        this.listOfElements.add(e);
    }

    // EFFECTS: returns the elements that are in the choreography so far
    public List<Element> getListOfElements() {
        return this.listOfElements;
    }

}
