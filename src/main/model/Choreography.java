package model;

import java.util.ArrayList;
import java.util.List;

// This class is about figure skating choreographies which consist of a list of elements and which have different
//     features like deductions, category, falls, duration, type and skatingSkillsComponent.
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
        this.listOfElements = new ArrayList<>();
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

    // EFFECTS: returns "short" if type is true and "free" if type is false
    public String returnTypeAsString() {
        String type;
        if (getType()) {
            type = "short";
        } else {
            type = "free";
        }
        return type;
    }

    // EFFECTS: returns "ladies" if type is true and "men" if type is false
    public String returnCategoryAsString() {
        String category;
        if (getSkaterCategory()) {
            category = "ladies";
        } else {
            category = "men";
        }
        return category;
    }

    // EFFECTS: calculates the deductions by considering the falls according to the ISU rule book.
    public double deductionCounter(int falls) {
        if (falls > 2) {
            return falls - 1;
        } else if (falls == 2) {
            return 1;
        } else if (falls == 1) {
            return 0.5;
        } else {
            return 0;
        }
    }

    // REQUIRES: a complete choreography
    // EFFECTS: makes sure that if its a short program there are 3 jumps, 2 spins and 1 step & if its a free
    //          program there are 7 jumps, 3 spins and 2 steps
    public boolean isEligibleChoreography() {

        int jumpCount = 0;
        int spinCount = 0;
        int stepCount = 0;

        for (Element e : listOfElements) {
            switch (e.getElementType()) {
                case "Jump":
                    jumpCount++;
                    break;
                case "Spin":
                    spinCount++;
                    break;
                case "Step":
                    stepCount++;
                    break;
            }
        }

        if (type) {
            return (jumpCount == 3 && stepCount == 1 && spinCount == 3);
        } else {
            return (jumpCount == 7 && stepCount == 2 && spinCount == 3);
        }
    }

    // MODIFIES: elements
    // EFFECTS: determines the elements that are in the second half of the choreography and multiplies their base
    //          values by 1*1 to add to their GOE as that's what the ISU rule book suggests
    public void determineSecondHalfElements() {

        if (type) {
            Element fifthElement = listOfElements.get(4);
            Element sixthElement = listOfElements.get(5);
            Element seventhElement =  listOfElements.get(6);

            fifthElement.addGOE(fifthElement.getBasePoint() * 1.1);
            sixthElement.addGOE(sixthElement.getBasePoint() * 1.1);
            seventhElement.addGOE(seventhElement.getBasePoint() * 1.1);

        } else {
            Element seventhElement = listOfElements.get(6);
            Element eighthElement = listOfElements.get(7);
            Element ninthElement = listOfElements.get(8);
            Element tenthElement = listOfElements.get(9);
            Element eleventhElement = listOfElements.get(10);
            Element twelfthElement = listOfElements.get(11);

            seventhElement.addGOE(seventhElement.getBasePoint() * 1.1);
            eighthElement.addGOE(eighthElement.getBasePoint() * 1.1);
            ninthElement.addGOE(ninthElement.getBasePoint() * 1.1);
            tenthElement.addGOE(tenthElement.getBasePoint() * 1.1);
            eleventhElement.addGOE(eleventhElement.getBasePoint() * 1.1);
            twelfthElement.addGOE(twelfthElement.getBasePoint() * 1.1);
        }
    }

    // EFFECTS: makes sure that the choreography has the right duration
    public boolean isEligibleDuration() {
        if (type) {
            return ((2.30 <= this.duration) && (this.duration <= 2.50));
        } else {
            return ((3.50 <= this.duration) && (this.duration <= 4.10));
        }
    }
}
