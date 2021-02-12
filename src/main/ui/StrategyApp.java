package ui;

import model.*;

import java.util.Scanner;

import static java.lang.Character.isDigit;

// Strategy Determining application for Figure Skaters
public class StrategyApp {

    private Choreography choreography;
    private Scanner input;

    public StrategyApp() {
        runStrategy();
    }

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
                keepGoing = false;
            } else if (command.equals("s")) {
                startQuestioning();
            }
        }

        System.out.println("\nThe prediction has ended. Please press 's' to restart.");
    }

    public void welcomeMessage() {
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
        System.out.println("\nPress 's' to start. Press 'q' to quit any time.");
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

            System.out.println("Any GOEs? (For example: 0.23 or -0.18)");
            String stringGOE = input.next();

            while (!checkIfProperGOE(stringGOE)) {
                System.out.println("Please enter a valid GOE in the form of *.** using decimal points.");
                stringGOE = input.next();
            }

            double elementGOE = Double.parseDouble(stringGOE);
            String elementType = typeFinder(elementName);
            int rotationOrLevel = Integer.parseInt(rotationOrLevelFinder(elementName, elementType));
            double elementBasePoint = basePointFinder(elementName);

            createAndAddElement(elementType, elementName, elementBasePoint, elementGOE, rotationOrLevel);
        }
    }

    public void createAndAddElement(String elementType, String elementName,
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

    public double basePointFinder(String s) {
        return 1.0; //!!!
    }

    // focus on stopping when q is done!!!
    // see if valid element by checking csv file.!!!
    // calculate methodunu tamamla!!!
    // add specificaitons !!!

    // 0 is Base
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

    public boolean checkIfProperElement(String s) {

        if (s.length() < 2) {
            return false;
        }
        char firstChar = s.charAt(0);
        char lastChar = s.charAt(s.length() - 1);
        char secondChar = s.charAt(1);
        return (isDigit(firstChar) || isDigit(lastChar)) && !isDigit(secondChar);
    }

    // check if below or above a point!!!
    public boolean checkIfProperGOE(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException d) {
            return false;
        }
        return true;
    }

    //!!!
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

}