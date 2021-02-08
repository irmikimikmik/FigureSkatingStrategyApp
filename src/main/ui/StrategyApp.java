package ui;

import model.Choreography;

// Strategy Determining application for Figure Skaters
public class StrategyApp {

    private Choreography choreography;

    public StrategyApp() {
        runStrategy();
    }

    // command ne amk
    // !!!
    private void runStrategy() {
        boolean keepGoing = true;
        choreography = new Choreography(0.0, true, 0, 0.0,
                true, 0.0);
        String command = null;

        while (keepGoing) {
            welcomeMessage();
            command = command.toLowerCase();

            if (command.equals("s")) {
                System.out.println("Hi");
            } else if (command.equals("q")) {
                keepGoing = false;
            } else {
                System.out.println("Please enter a valid instruction. 's' or 'q'?");
            }
        }
        System.out.println("\nThe prediction has ended. Please press 's' to restart.");
    }

    public void welcomeMessage() {
        System.out.println("\nLets predict your competition score!");
        System.out.println("\nPress 's' to start. Press 'q' to quit any time.");
        System.out.println("\nNOTE: Please use the ISU abbreviations for all elements. For example:");
        System.out.println("\tTriple Toeloop : 3T            Double Loop: 2Lo");
        System.out.println("\tDouble Axel + Half Loop + Triple Flip: 2A+1Eu+3F");
        System.out.println("\tLevel 3 Step Sequence : StSq3");
        System.out.println("\tLevel 2 Choreography Sequence: ChSq2");
        System.out.println("\tLevel 4 Change Leg Combo Spin: CCoSp4");
        System.out.println("\tLevel 1 Layback Spin: LSp1");
        System.out.println("\nMake sure that for jumps, the number is at the front and for spins & steps,"
                + "the number is at the end of the element.");
    }


}