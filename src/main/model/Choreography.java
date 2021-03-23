package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static java.lang.Character.isDigit;

// This class is about figure skating choreographies which consist of a list of elements and which have different
//     features like deductions, falls, duration, type and skatingSkillsComponent.
public class Choreography implements Writable {

    private String choreographyName;
    private double deductions;
    private int falls;
    private double duration;
    private boolean type; //true is short program, false is free program
    private double skatingSkillsComponent;
    private List<Element> listOfElements;

    public Choreography(String choreographyName, double deductions, int falls,
                        double duration, boolean type, double component, List<Element> elementsList) {
        this.choreographyName = choreographyName;
        this.deductions = deductions;
        this.falls = falls;
        this.duration = duration;
        this.type = type;
        this.skatingSkillsComponent = component;
        this.listOfElements = elementsList;
    }

    // MODIFIES: this
    // EFFECTS: sets the name of the choreography
    public void setChoreographyName(String s) {
        this.choreographyName = s;
    }

    // EFFECTS: returns the name of the choreography
    public String getChoreographyName() {
        return this.choreographyName;
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

        if (listOfElements.size() <= 6) {
            System.out.println("You don't have enough elements in your choreography to calculate!!");
            return;
        }

        if (type) {
            for (int i = 4; i <= 6; i++) {
                Element e = listOfElements.get(i);
                e.addGOE(e.getBasePoint() * 1.1);
            }
        } else {
            for (int i = 6; i <= 11; i++) {
                Element e = listOfElements.get(i);
                e.addGOE(e.getBasePoint() * 1.1);
            }
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

    public String printOutEligibility(String type) {
        if (this.isEligibleChoreography() && this.isEligibleDuration()) {
             return "Your choreography matches the\nrules of ISU rule book's " + type
                    + "\nprogram definition.";
        } else if (!this.isEligibleChoreography() && this.isEligibleDuration()) {
            return "Your choreography DOES NOT match\nthe rules of ISU rule book's " + type
                    + "\nprogram definition due to incorrect\narrangement of elements.";
        } else if (this.isEligibleChoreography() && !this.isEligibleDuration()) {
            return "Your choreography DOES NOT match\nthe rules of ISU rule book's " + type
                    + "\nprogram definition due to incorrect\nduration.";
        } else {
            return "Your choreography DOES NOT match\nthe rules of ISU rule book's " + type
                    + "\nprogram definition due to incorrect\narrangement of elements\nand duration.";
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", choreographyName);
        json.put("deductions", deductions);
        json.put("falls", falls);
        json.put("duration", duration);
        json.put("type", type);
        json.put("skatingSkillsComponent", skatingSkillsComponent);
        json.put("elements", elementsToJson());
        return json;
    }

    // EFFECTS: returns elements in this choreography as a JSON array
    public JSONArray elementsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Element e : listOfElements) {
            jsonArray.put(e.toJson());
        }

        return jsonArray;
    }

    // MODIFIES: choreography
    // EFFECTS: checks if the GOE is in the correct format: "+d.dd" or "-d.dd" where d is a digit.
    public boolean checkIfProperGOE(String str) {

        if (str.length() != 5) {
            return false;
        }

        char firstChar = str.charAt(0);
        char secondChar = str.charAt(1);
        char thirdChar = str.charAt(2);
        char fourthChar = str.charAt(3);
        char fifthChar = str.charAt(4);

        return (firstChar == 43 || firstChar == 45)
                && Character.isDigit(secondChar) && thirdChar == 46
                && Character.isDigit(fourthChar) && Character.isDigit(fifthChar);
    }

    // EFFECTS: reads the csv document containing base points and returns the base point of the given element,
    //          throws an exception if the element is not found
    // citations: https://www.javatpoint.com/how-to-read-csv-file-in-java and
    //            https://stackabuse.com/reading-and-writing-csvs-in-java/
    public double basePointFinder(String elementName) throws IOException {

        File f = new File("./data/AllBaseValues.csv");
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);

        String row;

        while ((row = br.readLine()) != null) {
            String[] data = row.split(",");

            String name = data[0];
            String point = data[1];

            if (elementName.equals(name)) {
                return Double.parseDouble(point);
            }
        }

        return 0.00;
    }

    // EFFECTS: determines the rotation or level depending on the type of element, returns the result (0 if Base level)
    public String rotationOrLevelFinder(String name, String type) {

        String firstChar = Character.toString(name.charAt(0));
        String lastChar = Character.toString(name.charAt(name.length() - 1));
        String secondLastChar = Character.toString(name.charAt(name.length() - 2));

        if (type.equals("Jump")) {
            return firstChar;
        }

        if (lastChar.equals("V")) {
            if (secondLastChar.equals("B")) {
                return "0";
            }
            return secondLastChar;
        } else if (lastChar.equals("B")) {
            return "0";
        }
        return lastChar;
    }

    // EFFECTS: finds the type of the element depending on its name and returns it
    public String typeFinder(String s) {
        char firstChar = s.charAt(0);

        if (isDigit(firstChar)) {
            return "Jump";
        } else {
            if (s.contains("StSq") || s.contains("ChSq")) {
                return "Step";
            } else if (s.contains("Sp")) {
                return "Spin";
            } else {
                System.out.println("The element name is still not valid. Please use ISU abbreviations.");
            }
        }

        return "The element name is still not valid. Please use ISU abbreviations.";
    }

    // EFFECTS: considers the total technical score, skating skills score, falls and prints the final result
    public String calculate() {

        List<Element> elements = this.getListOfElements();
        this.determineSecondHalfElements();

        double technicalPoints = 0;
        for (Element e : elements) {
            double basePoint = e.getBasePoint();
            double goePoint = e.getGOE();
            technicalPoints = technicalPoints + basePoint + goePoint;
        }

        double skatingPoints = this.getSkatingSkillsComponent();
        this.setDeductions(this.deductionCounter(this.getFalls()));
        double deductedPoints = this.getDeductions();
        double finalPoint = technicalPoints + skatingPoints - deductedPoints;

        String type = this.returnTypeAsString();
        printOutEligibility(type);

        return "\n\nThe points you will get from this\nchoreography is: " + String.format("%.2f", finalPoint) +  "."
                + "\n\nYou have " + String.format("%.2f", deductedPoints)
                + " deductions.\nYour technical score is " + String.format("%.2f", technicalPoints) + "."
                + "\nYour skating skills component\nis " + String.format("%.2f", skatingPoints) + ".";
    }
}
