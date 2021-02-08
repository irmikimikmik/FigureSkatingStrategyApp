package ui;

import model.Choreography;
import model.Element;

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
        System.out.println("\nNOTE: Please use the ISU abbreviations for all elements. For example:");
        System.out.println("\tTriple Toeloop : 3T");
        System.out.println("\tDouble Loop : 2L");
        System.out.println("\tDouble Axel + Half Loop + Triple Flip: 2A+1Eu+3F");
        System.out.println("\tLevel 3 Step Sequence : StSq3");
        System.out.println("\tLevel 2 Choreography Sequence: ChSq2");
        System.out.println("\tLevel 4 Change Leg Combo Spin: CCoSp4");
        System.out.println("\tLevel 1 Layback Spin: LSp1");
        System.out.println("\nMake sure that for jumps, the number is at the front and for spins & steps,"
                + "the number is at the end of the element.");
        System.out.println("\nPress 's' to start. Press 'q' to quit any time.");
    }

    private void startQuestioning() {
        categoryQuestion();
        typeQuestion();

        if (choreography.getType() == true) {
            System.out.println("There must be 7 elements in your choreography.");
            int numOfElements = 7;
            System.out.println("Please type in all elements in order and according to the ISU abbreviations.");
            elementsQuestion(numOfElements);
        } else {
            if (choreography.getSkaterCategory() == true) {
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
            System.out.println(ordinal(x) + " element: ");
            String elementName = input.next();

            if (checkIfProperElement(elementName) == false) {
                System.out.println("Please enter valid elements that are either spins, steps or jumps.");
                break;
                elementsQuestion(x--);
            }

            System.out.println("Any GOEs? (For example: 0.15) ");
            String elementGOE = input.next();
            checkIfProperGOE(elementGOE);

            double elementBasePoint = basePointFinder(elementName);
            String elementType = typeFinder(elementName);
            Element newElement = new Element(elementName, elementBasePoint, elementGOE, elementType);
            choreography.addElement(newElement);
        }

    }

    //basePointFinder
    //typeFinder
    //checkIfProperElement
    //checkIfProperGOE

    public boolean checkIfProperElement(String s) {
        char firstChar = s.charAt(0);
        char lastChar = s.charAt(s.length() - 1);
        if (isDigit(firstChar) || isDigit(lastChar)) {
            return true;
        }
        return false;
    }

    public boolean checkIfProperGOE(String str) {

        if (str == "\d\d.\d\d")
        if (Double.parseDouble(str));
    }

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