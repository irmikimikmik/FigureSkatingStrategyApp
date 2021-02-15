package ui;

import model.*;

import java.io.*;
import java.util.List;
import java.util.Scanner;

import static java.lang.Character.isDigit;

// Strategy Determining application for Figure Skaters
public class StrategyApp {

    private Choreography choreography;
    private Scanner input;

    // EFFECTS: runs the Strategy Application
    public StrategyApp() {
        runStrategy();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runStrategy() {

        choreography = new Choreography(0.0, true, 0, 0.0,
                true, 0.0);
        boolean keepGoing = true;
        String command;
        input = new Scanner(System.in);

        while (keepGoing) {
            welcomeMessage();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                System.out.println("\nThe prediction has ended.");
                keepGoing = false;
            } else if (command.equals("s")) {
                startQuestioning();
                calculate();
                System.out.println("\nThe prediction has ended. Please press 's' to restart and 'q' to quit.");

                command = input.next();
                command = command.toLowerCase();

                if (command.equals("s")) {
                    runStrategy();
                }

                keepGoing = false;
            }
        }
    }

    private void welcomeMessage() {
        System.out.println("\nLets predict your competition score!");
        System.out.println("\nNOTE: Please use the ISU abbreviations for all elements.");
        System.out.println("ISU abbreviatons can be found here: https://www.isu.org/inside-isu/rules-regulations/"
                + "isu-congresses/17142-isu-communication-2168/file.");
        System.out.println("\nExamples of abbreviations:");
        System.out.println("\tTriple Toeloop : 3T");
        System.out.println("\tUnder-rotated Double Loop : 2Lo<");
        System.out.println("\tUnder-rotated and Edge Singe Lutz : 1Lz<e");
        System.out.println("\tDouble Axel + Half Loop + Triple Flip: 2A + 1Eu + 3F");
        System.out.println("\tLevel 3 Step Sequence : StSq3");
        System.out.println("\tChoreography Sequence: ChSq1");
        System.out.println("\tLevel 4 Change Leg Combo Spin: CCoSp4");
        System.out.println("\tLevel 1 Layback Spin: LSp1");
        System.out.println("\tLevel Base Flying Upright Spin with Volume: FUSpBV");
        System.out.println("\nPress 's' to start. Press 'q' to quit.");
    }

    private void startQuestioning() {
        categoryQuestion();
        typeQuestion();

        if (choreography.getType()) {
            System.out.println("There must be 7 elements in your choreography.");
            int numOfElements = 7;
            System.out.println("Please type in all elements in order and according to the ISU abbreviations.");
            elementsQuestion(numOfElements);
        } else {
            if (choreography.getSkaterCategory()) {
                System.out.println("There must be 11 elements in your choreography.");
                int numOfElements = 11;
                System.out.println("Please type in all elements in order and according to the ISU abbreviations.");
                elementsQuestion(numOfElements);
            } else {
                System.out.println("There must be 12 elements in your choreography.");
                int numOfElements = 12;
                System.out.println("Please type in all elements in order and according to the ISU abbreviations.");
                elementsQuestion(numOfElements);
            }
        }

        fallQuestion();
        durationQuestion();
        skatingSkillsQuestion();
    }

    private void fallQuestion() {
        System.out.println("How many falls were there in the entire choreography?");
        String command = input.next();

        if (!checkIfProperFall(command)) {
            command = "0";
            System.out.println("Please type a positive integer.");
            fallQuestion();
        }
        choreography.addFalls(Integer.parseInt(command));
    }

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

    private void durationQuestion() {
        System.out.println("What is the duration of your choreography? For example: 3.15 if 3 minutes and 15 seconds.");
        String command = input.next();

        if (!checkIfProperDuration(command)) {
            System.out.println("Please type a positive number with a decimal point (in the form of *.**).");
            durationQuestion();
        }
        choreography.setDuration(Float.parseFloat(command));
    }

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

    // check if below or above a point -6.25 or 6.25
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

    // check if below or above a point -6.25 or 6.25
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

    private void categoryQuestion() {
        System.out.println("Please enter your category: 'ladies' or 'men'?");

        String command = input.next();
        command = command.toLowerCase();

        if (command.equals("ladies")) {
            choreography.setSkaterCategory(true);
        } else if (command.equals("men")) {
            choreography.setSkaterCategory(false);
        } else {
            System.out.println("Selection not valid... Please try again.");
            categoryQuestion();
        }
    }

    private void typeQuestion() {
        System.out.println("Please enter the type of your choreography: 'short' or 'free'?");

        String command = input.next();
        command = command.toLowerCase();

        if (command.equals("short")) {
            choreography.setType(true);
        } else if (command.equals("free")) {
            choreography.setType(false);
        } else {
            System.out.println("Selection not valid... Please try again.");
            typeQuestion();
        }
    }

    private void elementsQuestion(int i) {
        for (int x = 0; x < i; x++) {
            System.out.println(ordinal(x + 1) + " element: ");
            String elementName = input.next();
            while (!checkIfProperElement(elementName)) {
                System.out.println("Please enter valid elements that are either spins, steps or jumps.");
                elementName = input.next();
            }
            System.out.println("Any GOEs? (For example: +0.23 or -0.18)");
            String stringGOE = input.next();
            while (!checkIfProperGOE(stringGOE)) {
                System.out.println("Please enter a valid GOE in the form of -*.** or +*.** using digits as 'd's.");
                stringGOE = input.next();
            }
            double elementGOE = Double.parseDouble(stringGOE);
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

    //add author !!! https://www.javatpoint.com/how-to-read-csv-file-in-java
    //https://stackabuse.com/reading-and-writing-csvs-in-java/
    private double basePointFinder(String elementName) throws IOException {

        File f = new File("/Users/irmakbayir/Desktop/project_w1q0i/src/main/data/AllBaseValues.csv");
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

    // 0 is Base
    private String rotationOrLevelFinder(String name, String type) {

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

    private String typeFinder(String s) {
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

    // check if below or above a point -6.25 or 6.25
    private boolean checkIfProperGOE(String str) {

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

    // add author!!!
    private static String ordinal(int i) {
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

    private void calculate() {

        List<Element> elements = choreography.getListOfElements();

        double technicalPoints = 0;
        for (Element e : elements) {
            double basePoint = e.getBasePoint();
            double goePoint = e.getGOE();
            technicalPoints = technicalPoints + basePoint + goePoint;
        }
        double skatingPoints = choreography.getSkatingSkillsComponent();
        choreography.setDeductions(choreography.getFalls());
        double deductedPoints = choreography.getDeductions();
        double finalPoint = technicalPoints + skatingPoints - deductedPoints;

        String type;
        if (choreography.getType()) {
            type = "short";
        } else {
            type = "free";
        }

        String category;
        if (choreography.getSkaterCategory()) {
            category = "ladies";
        } else {
            category = "men";
        }

        System.out.println("\n");
        System.out.println("\tYour choreography matches the rules for " + category + " " + type
                + " program according to the ISU rule book.");
        System.out.println("\tThe points you will get from this choreography is: " + String.format("%.2f", finalPoint));
        System.out.println("\tYou have " + String.format("%.2f", deductedPoints)
                + " deductions. Your technical score is " + String.format("%.2f", technicalPoints)
                + " and your skating skills component is " + String.format("%.2f", skatingPoints) + ".");
    }

}

// calculate methodunu tamamla!!!
// choreography ye çağır param olarak pass etme!!!???
// deductionCounter(choreography);
// iseligibleChoregraphy(choreography);
//check if amount is correct and each element is at the right number
//check if each element is not repeated for more than twice
// determineSecondHalfJumps(choreography);
//choreography = new Choreography(0.0, true, 0, 0.0, true, 0.0);

// add specificaitons to stragyapp!!!
// add tests!!!
// check if duration, goe, fall cok benziyo abstraction yap
// warninglere bak