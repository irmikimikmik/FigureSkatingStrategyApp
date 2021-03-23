package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Character.isDigit;

// Strategy Determining application for Figure Skaters
public class StrategyApp extends JFrame {

    private static final String JSON_STORE = "./data/choreography.json";
    protected Choreography choreography;
    // code taken from TellerApp
    private Scanner input = new Scanner(System.in);
    private final JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
    private final JsonReader jsonReader = new JsonReader(JSON_STORE);


    // EFFECTS: runs the Strategy Application
    public StrategyApp() {
        choreography = new Choreography("My choreography", 0.0, 0, 0.0,
                true, 0.0, new ArrayList<>());
        choreography.setChoreographyName("My choreography");

        runStrategy();
    }

    public Choreography getChoreography() { return this.choreography; }

    // EFFECTS: depending on the input from the user, proceeds to start to prediction or quits.
    private void runStrategy() {

        boolean keepGoing = true;
        String command;
        input = new Scanner(System.in);

        System.out.println("\nLets predict your competition score!");
        System.out.println("\nWe have to understand what you plan to do in your choreography first. Please make sure"
                + " that you fill out every section IN ORDER before asking for an accurate result.");

        while (keepGoing) {
            mainMenu();
            command = input.next();
            command = command.toLowerCase();
            
            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nThe prediction has ended. Goodbye!");
    }

    // taken from JSONSerializationDemo: https://github.com/stleary/JSON-java.git
    // EFFECTS: saves the choreography to file
    private void saveChoreography() {
        try {
            jsonWriter.open();
            jsonWriter.write(choreography);
            jsonWriter.close();
            System.out.println("Saved " + choreography.getChoreographyName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // taken from JSONSerializationDemo: https://github.com/stleary/JSON-java.git
    // MODIFIES: this
    // EFFECTS: loads choreography from file
    private void loadChoreography() {
        try {
            choreography = jsonReader.read();
            System.out.println("Loaded " + choreography.getChoreographyName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: prints out the main menu message
    private void mainMenu() {
        System.out.println("\t1- t --> type of choreography");
        System.out.println("\t2- e --> the elements in choreography");
        System.out.println("\t3- f --> the number of falls in choreography");
        System.out.println("\t4- d --> duration of choreography");
        System.out.println("\t5- p --> skating skills component of choreography");
        System.out.println("\t6- c --> CALCULATE!!!");
        System.out.println("\n");
        System.out.println("\tl --> load a previously saved choreography");
        System.out.println("\ts --> save your choreography");
        System.out.println("\tq --> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if ("t".equals(command)) {
            typeQuestion();
        } else if ("e".equals(command)) {
            if (choreography.getType()) {
                elementMessage();
                elementsQuestion(7);
            } else {
                elementMessage();
                elementsQuestion(12);
            }
        } else if ("f".equals(command)) {
            fallQuestion();
        } else if ("d".equals(command)) {
            durationQuestion();
        } else if ("p".equals(command)) {
            skatingSkillsQuestion();
        } else if ("c".equals(command)) {
            calculate();
        } else if ("l".equals(command)) {
            loadChoreography();
        } else if ("s".equals(command)) {
            saveChoreography();
        }
    }

    // MODIFIES: choreography
    // EFFECTS: asks how many falls there are and adds them to the choreography
    private void fallQuestion() {
        System.out.println("How many falls were there in the entire choreography?");
        String command = input.next();

        if (!checkIfProperFall(command)) {
            System.out.println("Please type a positive integer.");
            fallQuestion();
        }
        choreography.addFalls(Integer.parseInt(command));
    }

    // EFFECTS: checks if the input is in the correct format: "d" or "dd" where d is a digit.
    private boolean checkIfProperFall(String str) {
        if (str.length() >= 3) {
            return false;
        }

        if (str.length() == 2) {
            return ((Character.isDigit(str.charAt(0))) && (Character.isDigit(str.charAt(1))));
        } else {
            return (Character.isDigit(str.charAt(0)));
        }
    }

    // MODIFIES: choreography
    // EFFECTS: asks the duration and adds the duration information to the choreography object
    private void durationQuestion() {
        System.out.println("What is the duration of your choreography? For example: 3.15 if 3 minutes and 15 seconds.");
        String command = input.next();

        if (!checkIfProperDuration(command)) {
            System.out.println("Please type a positive number with a decimal point (in the form of *.**).");
            durationQuestion();
        }
        choreography.setDuration(Float.parseFloat(command));
    }

    // MODIFIES: choreography
    // EFFECTS: asks the previous skating skills component and adds the points to the choreography
    private void skatingSkillsQuestion() {
        System.out.println("What is the most recent skating skills component score you have received?"
                + " For example: 44.56 points.");
        String command = input.next();

        if (!checkIfProperComponent(command)) {
            System.out.println("Please type a positive number with a decimal point (in the form of **.**).");
            skatingSkillsQuestion();
        }
        choreography.setSkatingSkillsComponent(Float.parseFloat(command));
    }

    // EFFECTS: checks if the input is in the correct format: "dd.dd" where d is a digit.
    private boolean checkIfProperComponent(String str) {

        if (str.length() != 5) {
            return false;
        }
        char firstChar = str.charAt(0);
        char secondChar = str.charAt(1);
        char thirdChar = str.charAt(2);
        char fourthChar = str.charAt(3);
        char fifthChar = str.charAt(4);

        return Character.isDigit(firstChar) && Character.isDigit(secondChar) && thirdChar == 46
                && Character.isDigit(fourthChar) && Character.isDigit(fifthChar);
    }

    // EFFECTS: checks if the input is in the correct format: "d.dd" where d is a digit.
    private boolean checkIfProperDuration(String str) {

        if (str.length() != 4) {
            return false;
        }
        char firstChar = str.charAt(0);
        char secondChar = str.charAt(1);
        char thirdChar = str.charAt(2);
        char fourthChar = str.charAt(3);

        return Character.isDigit(firstChar) && secondChar == 46
                && Character.isDigit(thirdChar) && Character.isDigit(fourthChar);
    }

    // MODIFIES: choreography
    // EFFECTS: asks the type of the choreography and adds the info to the choreography object
    private void typeQuestion() {

        System.out.println("Please enter the type of your choreography: 'short' or 'free'?");

        String command = input.next();
        command = command.toLowerCase();

        if (command.equals("short")) {
            choreography.setType(true);
            System.out.println("There must be 7 elements in your choreography.");
        } else if (command.equals("free")) {
            choreography.setType(false);
            System.out.println("There must be 12 elements in your choreography.");
        } else {
            System.out.println("Selection not valid... Please try again.");
            typeQuestion();
        }
    }

    // MODIFIES: choreography
    // EFFECTS: asks the names and the GOEs of the elements one by one until necessary number of elements are met,
    //          also handles the exception that comes from basePointFinder
    private void elementsQuestion(int i) {

        for (int x = 0; x < i; x++) {
            System.out.println(ordinal(x + 1) + " element: ");
            String elementName = input.next();
            while (!checkIfProperElement(elementName)) {
                System.out.println("Please enter valid elements that are either spins, steps or jumps.");
                elementName = input.next();
            }
            double elementGOE = Double.parseDouble(askGOE());
            String elementType = typeFinder(elementName);
            int rotationOrLevel = Integer.parseInt(rotationOrLevelFinder(elementName, elementType));
            double elementBasePoint = 0;

            try {
                elementBasePoint = basePointFinder(elementName);
            } catch (IOException e) {
                e.printStackTrace();
            }

            createAndAddElement(elementType, elementName, elementBasePoint, elementGOE, rotationOrLevel);
        }
    }

    private void elementMessage() {
        System.out.println("\nNOTE: Please use the ISU abbreviations for all elements.");
        System.out.println("ISU abbreviations can be found here: https://www.isu.org/inside-isu/rules-regulations/"
                + "isu-congresses/17142-isu-communication-2168/file.");
        System.out.println("\nExamples of abbreviations:");
        System.out.println("\tTriple Toeloop : 3T");
        System.out.println("\tUnder-rotated Double Loop : 2Lo<");
        System.out.println("\tUnder-rotated and Edge Singe Lutz : 1Lz<e");
        System.out.println("\tLevel 3 Step Sequence : StSq3");
        System.out.println("\tChoreography Sequence: ChSq1");
        System.out.println("\tLevel 4 Change Leg Combo Spin: CCoSp4");
        System.out.println("\tLevel 1 Layback Spin: LSp1");
        System.out.println("\tLevel Base Flying Upright Spin with Volume: FUSpBV");
    }

    // MODIFIES: choreography
    // EFFECTS: asks the GOE, checks if its valid and returns the value of the GOE as a String
    private String askGOE() {
        System.out.println("Any GOEs? (For example: +0.23 or -0.18)");
        String stringGOE = input.next();

        while (!checkIfProperGOE(stringGOE)) {
            System.out.println("Please enter a valid GOE in the form of -*.** or +*.** using digits as '*'s.");
            stringGOE = input.next();
        }
        return stringGOE;
    }

    // MODIFIES: choreography
    // EFFECTS: depending on the type, declares a subtype of element and adds the new object to the choreography
    private void createAndAddElement(String elementType, String elementName,
                                 double elementBasePoint, double elementGOE, int rotationOrLevel) {
        switch (elementType) {
            case "Jump": {
                Element newElement = new Jump(elementName, elementBasePoint, elementGOE,
                        elementType, rotationOrLevel);
                choreography.addElement(newElement);
                break;
            }
            case "Spin": {
                Element newElement = new Spin(elementName, elementBasePoint, elementGOE,
                        elementType, rotationOrLevel);
                choreography.addElement(newElement);
                break;
            }
            case "Step": {
                Element newElement = new Step(elementName, elementBasePoint, elementGOE,
                        elementType, rotationOrLevel);
                choreography.addElement(newElement);
                break;
            }
        }
    }

    // EFFECTS: reads the csv document containing base points and returns the base point of the given element,
    //          throws an exception if the element is not found
    // citations: https://www.javatpoint.com/how-to-read-csv-file-in-java and
    //            https://stackabuse.com/reading-and-writing-csvs-in-java/
    public static double basePointFinder(String elementName) throws IOException {

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

    // EFFECTS: checks if the element can be found in the csv file containing all the names of the elements in skating,
    //          also handles the exception that comes from basePointFinder
    private boolean checkIfProperElement(String elementName) {
        try {
            if (basePointFinder(elementName) == 0.0) {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    // EFFECTS: determines the rotation or level depending on the type of element, returns the result (0 if Base level)
    public static String rotationOrLevelFinder(String name, String type) {

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
    public static String typeFinder(String s) {
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

    // MODIFIES: choreography
    // EFFECTS: checks if the GOE is in the correct format: "+d.dd" or "-d.dd" where d is a digit.
    public static boolean checkIfProperGOE(String str) {

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

    // EFFECTS: converts an integer from decimal the ordinal. For example: 1 to 1st.
    // citations:
    // https://stackoverflow.com/questions/6810336/is-there-a-way-in-java-to-convert-an-integer-to-its-ordinal-name
    public static String ordinal(int i) {
        String[] suffixes = new String[] { "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th" };
        switch (i % 100) {
            case 11:
            case 12:
            case 13:
                return i + "th";
            default:
                return i + suffixes[i % 10];
        }
    }

    // EFFECTS: considers the total technical score, skating skills score, falls and prints the final result
    private void calculate() {

        List<Element> elements = choreography.getListOfElements();
        choreography.determineSecondHalfElements();

        double technicalPoints = 0;
        for (Element e : elements) {
            double basePoint = e.getBasePoint();
            double goePoint = e.getGOE();
            technicalPoints = technicalPoints + basePoint + goePoint;
        }

        double skatingPoints = choreography.getSkatingSkillsComponent();
        choreography.setDeductions(choreography.deductionCounter(choreography.getFalls()));
        double deductedPoints = choreography.getDeductions();
        double finalPoint = technicalPoints + skatingPoints - deductedPoints;

        String type = choreography.returnTypeAsString();
        printOutEligibility(type);

        System.out.println("\tThe points you will get from this choreography is: " + String.format("%.2f", finalPoint));
        System.out.println("\tYou have " + String.format("%.2f", deductedPoints)
                + " deductions. Your technical score is " + String.format("%.2f", technicalPoints)
                + " and your skating skills component is " + String.format("%.2f", skatingPoints) + ".");
        System.out.println("\n");
    }

    private void printOutEligibility(String type) {
        if (choreography.isEligibleChoreography() && choreography.isEligibleDuration()) {
            System.out.println("\tYour choreography matches the rules of ISU rule book's " + type
                    + " program definition.");
        } else if (!choreography.isEligibleChoreography() && choreography.isEligibleDuration()) {
            System.out.println("\tYour choreography DOES NOT match the rules of ISU rule book's " + type
                    + " program definition due to incorrect arrangement of elements.");
        } else if (choreography.isEligibleChoreography() && !choreography.isEligibleDuration()) {
            System.out.println("\tYour choreography DOES NOT match the rules of ISU rule book's " + type
                    + " program definition due to incorrect duration.");
        } else {
            System.out.println("\tYour choreography DOES NOT match the rules of ISU rule book's " + type
                    + " program definition due to incorrect arrangement of elements and duration.");
        }
    }
}