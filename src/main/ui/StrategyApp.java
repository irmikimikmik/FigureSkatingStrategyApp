package ui;

import jdk.jfr.Category;
import model.Choreography;

import java.sql.SQLOutput;

// Strategy Determining application for Figure Skaters
public class StrategyApp {

    private Choreography choreography;

    public StrategyApp() {
        runStrategy();
    }

    // !!!
    private void runStrategy() {
        boolean keepGoing = true;
        String args = null;

        choreography = new Choreography();

        while (keepGoing) {

            WelcomeMessage();
            args = args.toLowerCase();

            if (args.equals("s")) {
                TypeQuestion();

                if (Choreography.getType() == false) {
                    CategoryQuestion();
                }

                System.out.println("First element of choreography:");
                Choreography.addElement(args);
                System.out.println("Second element of choreography:");
                Choreography.addElement(args);
                System.out.println("Third element of choreography:");
                Choreography.addElement(args);
                System.out.println("Fourth element of choreography:");
                Choreography.addElement(args);
                System.out.println("Fifth element of choreography:");
                Choreography.addElement(args);
                System.out.println("Sixth element of choreography:");
                Choreography.addElement(args);
                System.out.println("Seventh element of choreography:");
                Choreography.addElement(args);

                if (Choreography.getType() == false){
                    System.out.println("Eighth element of choreography:");
                    Choreography.addElement(args);
                    System.out.println("Ninth element of choreography:");
                    Choreography.addElement(args);
                    System.out.println("Tenth element of choreography:");
                    Choreography.addElement(args);
                    System.out.println("Eleventh element of choreography:");
                    Choreography.addElement(args);

                    if(Choreography.getSkaterCategory() == false){
                        System.out.println("Twelfth element of choreography:");
                        Choreography.addElement(args);
                    }
                }

                // Ask for GOE

                System.out.println("How many falls were there in the choreography?");
                Choreography.addFalls(args);


                System.out.println("What is the duration of the choreography?");
                System.out.println("\nExample: If 3 minutes 15 seconds, write 3.15");
                Choreography.setDuration(args);

                System.out.println("At last, what is the estimated skating skills component?");
                System.out.println("\nExample: 40.77");
                Choreography.setSkatingSkillsComponent(args);

                System.out.println(calculate());
            }

            if (args.equals("q")) {
                keepGoing = false;
            }
        }
        System.out.println("\nThe prediction has ended. Please press 's' to restart.");

        if(args.equals("s")){
            runStrategy();
        }
    }

    // EFFECTS: displays menu of options to user
    @SuppressWarnings("checkstyle:OperatorWrap")
    private void WelcomeMessage() {
        System.out.println("\nLets predict your competition score!");
        System.out.println("\nPress 's' to start. Press 'q' to quit any time.");
        System.out.println("\nNOTE: Please use the ISU abbreviations for all elements. For example:");
        System.out.println("\tTriple Toeloop : 3T            Double Loop: 2Lo");
        System.out.println("\tDouble Axel + Half Loop + Triple Flip: 2A+1Eu+3F");
        System.out.println("\tLevel 3 Step Sequence : StSq3");
        System.out.println("\tLevel 2 Choreography Sequence: ChSq2");
        System.out.println("\tLevel 4 Change Leg Combo Spin: CCoSp4");
        System.out.println("\tLevel 1 Layback Spin: LSp1");

        System.out.println("\nMake sure that for jumps, the number is at the front and for spins & steps," +
                "the number is at the end of the element.");
    }

    // !!!
    private void TypeQuestion() {
        System.out.println("Please enter the type of your choreography: 'short' or 'free'?");
        if (command.equals("short")) {
            Choreography.setType(true);
        } else if (command.equals("free")) {
            Choreography.setType(false);
        } else {
            System.out.println("Selection not valid... Please try again");
            TypeQuestion();
        }
    }

    private void CategoryQuestion() {
        System.out.println("Please enter your category: 'ladies' or 'men'?");
        if (command.equals("ladies")) {
            Choreography.setSkaterCategory(true);
        } else if (command.equals("men")) {
            Choreography.setSkaterCategory(false);
        } else {
            System.out.println("Selection not valid... Please try again");
            CategoryQuestion();
        }
    }

    private void calculate() {

        // multiply by 1.1 for second half elements
        // look at deduction due to duration
        // see if number of jumps and step and spin is appropriate, give deduction and warning if not
        // add goe
        // add elements
        // add skating skills point

    }

}