package ui;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class PopUpWindow extends JFrame implements ActionListener {

    JFrame frame = new JFrame();
    Choreography choreography;

    // TYPE
    JButton shortButton;
    JButton freeButton;
    JLabel typeLabel1;
    JLabel typeLabel2;
    JLabel programTypeLabel;

    //ELEMENT
    JLabel elementLabel1;
    JLabel elementLabel2;
    JLabel elementLabel3;
    JLabel elementLabel4;
    JLabel elementLabel5;
    JLabel elementLabel6;
    JLabel elementLabel7;
    JLabel elementLabel8;
    JLabel elementLabel9;
    JLabel elementLabel10;
    JLabel elementLabel11;
    JLabel elementLabel12;
    JLabel goeLabel1;
    JLabel warningForGOE;
    JLabel warningForElement;
    JLabel endOfElements;
    JTextField elementTextBox;
    JTextField goeTextBox;
    JButton nextElementButton;
    int elementNumber = 1;

    // FALL
    JLabel fallLabel;
    JTextField fallTextBox;
    JButton setFallButton;
    JLabel fallIsSetLabel;

    // DURATION
    JLabel durationLabel1;
    JTextField durationTextBox;
    JButton setDurationButton;
    JLabel durationIsSetLabel;

    // SSC
    JLabel sscLabel;
    JTextField sscTextBox;
    JButton setSscButton;
    JLabel sscIsSetLabel;


    public PopUpWindow(Choreography c) {
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(false);
        this.choreography = c;
    }

    public void giveInstructions(String s) {

        if (s.equals("type")) {
            typePopUp();
        } else if (s.equals("element")) {
            elementPopUp();
        } else if (s.equals("fall")) {
            fallPopUp();
        } else if (s.equals("duration")) {
            durationPopUp();
        } else if (s.equals("ssc")) {
            sscPopUp();
        }

        frame.setVisible(true);
    }

    private void sscPopUp() {
        frame.setMinimumSize(new Dimension(600, 300));
        frame.setMaximumSize(new Dimension(600, 300));

        sscLabel = new JLabel("Please enter your most recent skating skills component score:");
        sscTextBox = new JTextField("Example: 36.74");
        setSscButton = new JButton("enter");
        sscIsSetLabel = new JLabel("The skating skills component has been set to 0.00.");

        sscLabel.setBounds(50,70,500,15);
        sscTextBox.setBounds(50,120,350,40);
        setSscButton.setBounds(430,120,100,40);
        sscIsSetLabel.setBounds(50,180,500,15);

        setSscButton.addActionListener(this);
        sscIsSetLabel.setVisible(false);

        frame.add(sscLabel);
        frame.add(sscTextBox);
        frame.add(setSscButton);
        frame.add(sscIsSetLabel);
    }

    private void durationPopUp() {
        frame.setMinimumSize(new Dimension(600, 300));
        frame.setMaximumSize(new Dimension(600, 300));

        durationLabel1 = new JLabel("Please enter the duration of your choreography:");
        durationTextBox = new JTextField("Example: 2.15 for 2 minutes 15 seconds");
        setDurationButton = new JButton("enter");
        durationIsSetLabel = new JLabel("The duration has been set to 0.00.");

        durationLabel1.setBounds(50,70,500,15);
        durationTextBox.setBounds(50,120,350,40);
        setDurationButton.setBounds(430,120,100,40);
        durationIsSetLabel.setBounds(50,180,500,15);

        setDurationButton.addActionListener(this);
        durationIsSetLabel.setVisible(false);

        frame.add(durationLabel1);
        frame.add(durationTextBox);
        frame.add(setDurationButton);
        frame.add(durationIsSetLabel);
    }

    private void fallPopUp() {
        frame.setMinimumSize(new Dimension(500, 300));
        frame.setMaximumSize(new Dimension(500, 300));

        fallLabel = new JLabel("Please enter how many falls there were in the choreography");
        fallTextBox = new JTextField("Example: 2");
        setFallButton = new JButton("enter");
        fallIsSetLabel = new JLabel("The number of falls have been set to 0.");

        fallLabel.setBounds(50,70,500,15);
        fallTextBox.setBounds(50,120,150,40);
        setFallButton.setBounds(230,120,100,40);
        fallIsSetLabel.setBounds(50,180,500,15);

        setFallButton.addActionListener(this);
        fallIsSetLabel.setVisible(false);

        frame.add(fallLabel);
        frame.add(fallTextBox);
        frame.add(setFallButton);
        frame.add(fallIsSetLabel);
    }

    private void elementPopUp() {
        frame.setMinimumSize(new Dimension(1000, 400));
        frame.setMaximumSize(new Dimension(1000, 400));

        instantiateElementScreen();
        setBoundsOnElementScreen();
        addToFrameOfElementScreen();

        nextElementButton.addActionListener(this);
        elementNumber = 1;
    }

    private void instantiateElementScreen() {
        elementLabel1 = new JLabel("NOTE: Please use the ISU abbreviations for all elements.");
        elementLabel2 = new JLabel("ISU abbreviations can be found here:"
                + "https://www.isu.org/inside-isu/rules-regulations/isu-congresses/17142-isu-communication-2168/file.");
        warningForGOE = new JLabel("WARNING: In the case of entering an invalid element or GOE, the program"
                + " will crash. Enter a valid GOE in the form of '-*.**' or '+*.**'.");
        elementLabel3 = new JLabel("Examples of abbreviations:");
        elementLabel4 = new JLabel("Triple Toeloop : 3T");
        elementLabel5 = new JLabel("Under-rotated Double Loop : 2Lo<");
        elementLabel6 = new JLabel("Under-rotated and Edge Singe Lutz : 1Lz<e");
        elementLabel7 = new JLabel("Level 3 Step Sequence : StSq3");
        elementLabel8 = new JLabel("Choreography Sequence: ChSq1");
        elementLabel9 = new JLabel("Level 4 Change Leg Combo Spin: CCoSp4");
        elementLabel10 = new JLabel("Level 1 Layback Spin: LSp1");
        elementLabel11 = new JLabel("Level Base Flying Upright Spin with Volume: FUSpBV");
        elementLabel12 = new JLabel(ordinal(elementNumber) + " element:");
        goeLabel1 = new JLabel("GOE:");
        nextElementButton = new JButton("Next");
        elementTextBox = new JTextField("Example: 3Lo");
        goeTextBox = new JTextField("Example: +2.34");
    }

    private void setBoundsOnElementScreen() {
        elementLabel1.setBounds(50,45,500,15);
        elementLabel2.setBounds(50,75,1000,15);
        warningForGOE.setBounds(50, 105, 1000, 15);

        elementLabel3.setBounds(50,140,500,15);
        elementLabel4.setBounds(50,170,500,15);
        elementLabel5.setBounds(50,190,500,15);
        elementLabel6.setBounds(50,210,500,15);
        elementLabel7.setBounds(50,230,500,15);
        elementLabel8.setBounds(50,250,500,15);
        elementLabel9.setBounds(50,270,500,15);
        elementLabel10.setBounds(50,290,500,15);
        elementLabel11.setBounds(50,310,500,15);
        elementLabel12.setBounds(410,185,500,15);
        goeLabel1.setBounds(660,185,500,15);
        nextElementButton.setBounds(780,260,100,50);
        elementTextBox.setBounds(400, 200, 150, 40);
        goeTextBox.setBounds(650, 200, 150, 40);
    }

    private void addToFrameOfElementScreen() {
        frame.add(elementLabel1);
        frame.add(elementLabel2);
        frame.add(elementLabel3);
        frame.add(elementLabel4);
        frame.add(elementLabel5);
        frame.add(elementLabel6);
        frame.add(elementLabel7);
        frame.add(elementLabel8);
        frame.add(elementLabel9);
        frame.add(elementLabel10);
        frame.add(elementLabel11);
        frame.add(elementLabel12);
        frame.add(goeLabel1);
        frame.add(elementTextBox);
        frame.add(goeTextBox);
        frame.add(nextElementButton);
        frame.add(warningForGOE);
    }

    private void typePopUp() {
        frame.setMinimumSize(new Dimension(400, 400));
        frame.setMaximumSize(new Dimension(400, 400));

        typeLabel1 = new JLabel("Please enter the type of your choreography:");
        typeLabel2 = new JLabel("short or free?");
        programTypeLabel = new JLabel("Your program type has been set to: short");

        typeLabel1.setBounds(50,70,500,15);
        typeLabel2.setBounds(50,100,500,15);
        programTypeLabel.setBounds(50,200,500,40);

        shortButton = new JButton("short");
        shortButton.setBounds(50,140,100,40);
        shortButton.addActionListener(this);

        freeButton = new JButton("free");
        freeButton.setBounds(150,140,100,40);
        freeButton.addActionListener(this);

        programTypeLabel.setVisible(false);

        frame.add(typeLabel1);
        frame.add(typeLabel2);
        frame.add(shortButton);
        frame.add(freeButton);
        frame.add(programTypeLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == shortButton) {
            this.choreography.setType(true);
            programTypeLabel.setVisible(true);
        } else if (e.getSource() == freeButton) {
            this.choreography.setType(false);
            programTypeLabel.setText("Your program type has been set to: free");
            programTypeLabel.setVisible(true);
        } else if (e.getSource() == nextElementButton) {
            try {
                goToNextElementQuestion();
            } catch (IOException ioException) {
                warningForElement = new JLabel("Please try again and enter a valid element.");
                warningForElement.setBounds(400,220,500,15);
                frame.add(warningForElement);
            }
        } else if (e.getSource() == setFallButton) {
            int falls = Integer.parseInt(fallTextBox.getText());
            this.choreography.addFalls(falls);
            fallIsSetLabel.setText("The number of falls have been set to " + falls + ".");
            fallIsSetLabel.setVisible(true);
        } else if (e.getSource() == setDurationButton) {
            double duration = Double.parseDouble(durationTextBox.getText());
            this.choreography.setDuration(duration);
            durationIsSetLabel.setText("The duration has been set to " + duration + ".");
            durationIsSetLabel.setVisible(true);
        } else if (e.getSource() == setSscButton) {
            double ssc = Double.parseDouble(sscTextBox.getText());
            this.choreography.setSkatingSkillsComponent(ssc);
            sscIsSetLabel.setText("The skating skills component has been set to " + ssc + ".");
            sscIsSetLabel.setVisible(true);
        }

    }

    private void goToNextElementQuestion() throws IOException {

        String elementName = elementTextBox.getText();
        Double elementBasePoint = this.choreography.basePointFinder(elementName);
        Double elementGOE = Double.parseDouble(goeTextBox.getText());
        String elementType = this.choreography.typeFinder(elementName);
        Integer rotationOrLevel = Integer.parseInt(this.choreography.rotationOrLevelFinder(elementName, elementType));

        if (elementType.equals("Jump")) {
            Element newJump = new Jump(elementName, elementBasePoint, elementGOE, elementType, rotationOrLevel);
            this.choreography.addElement(newJump);
        } else if (elementType.equals("Spin")) {
            Element newSpin = new Spin(elementName, elementBasePoint, elementGOE, elementType, rotationOrLevel);
            this.choreography.addElement(newSpin);
        } else if (elementType.equals("Step")) {
            Element newStep = new Step(elementName, elementBasePoint, elementGOE, elementType, rotationOrLevel);
            this.choreography.addElement(newStep);
        }

        elementTextBox.setText("");
        goeTextBox.setText("");

        elementNumber++;
        elementLabel12.setText(ordinal(elementNumber) + " element:");

        if ((this.choreography.getType() && (elementNumber - 1 == 7))
                || (!this.choreography.getType() && (elementNumber - 1 == 12))) {
            elementLabel12.setVisible(false);
            goeLabel1.setVisible(false);
            elementTextBox.setVisible(false);
            goeTextBox.setVisible(false);
            endOfElements = new JLabel("You have entered all the elements. Please click the exit "
                    + "button on the top left corner.");
            nextElementButton.setVisible(false);
            endOfElements.setBounds(400, 220, 600, 15);;
            frame.add(endOfElements);
        }
    }

    public Choreography getChoreography() { return this.choreography; }

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
}