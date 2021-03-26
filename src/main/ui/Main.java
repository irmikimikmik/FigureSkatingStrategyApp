package ui;

import model.Choreography;
import model.Element;
import persistence.JsonReader;
import persistence.JsonWriter;
import sound.AudioPlayer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// This class is about the Graphical User Interface where the user can interact with a panel and enter the contents of
//     the choreography as well as see the results of the choreography on the interface.
public class Main extends JFrame implements ActionListener {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    JPanel panel;
    JFrame frame;
    private JButton typeButton;
    private JButton loadButton;
    private JButton saveButton;
    private JButton elementButton;
    private JButton durationButton;
    private JButton fallButton;
    private JButton sscButton;
    private JButton calculateButton;
    JTextArea choreographyText;
    JScrollPane choreographyScroll;
    JTextArea resultText;
    JLabel loadLabel;
    JLabel saveLabel;

    // code taken from https://www.codejava.net/coding/java-audio-player-sample-application-in-swing
    private AudioPlayer player = new AudioPlayer();
    private String audioFilePath = "./data/resultsAreReady.wav";

    private Choreography choreography;

    private static final String JSON_STORE = "./data/choreography.json";
    private final JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
    private final JsonReader jsonReader = new JsonReader(JSON_STORE);

    public Main() {
        super("Strategy GUI");
        this.choreography = new Choreography("My choreography", 0.0, 0, 0.0,
                true, 0.0, new ArrayList<>());
        initializeGraphics();

        try {
            player.load(audioFilePath);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // EFFECTS: creates a new panel and a frame object and sets their dimensions, labels, text boxes and buttons.
    public void initializeGraphics() {
        // the code after this in the constructor draws the JFrame window where this StrategyApp will operate
        // code taken from SimpleDrawingPlayer, https://www.youtube.com/watch?v=iE8tZ0hn2Ws and
        //                 https://www.youtube.com/watch?v=5o3fMLPY7qY
        panel = new JPanel();
        frame = new JFrame();

        frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        frame.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);

        createLabels();
        createButtons();
        createTextBoxes();

        frame.setVisible(true);
    }


    // EFFECTS: creates text boxes to display choreography's contents and results
    public void createTextBoxes() {
        // CHOREOGRAPHY BOX
        choreographyText = new JTextArea("Your choreography will appear\nhere when you click save or load.");
        choreographyText.setEditable(false);
        choreographyScroll = new JScrollPane(choreographyText);
        choreographyScroll.setBounds(45, 60, 225, 390);
        panel.add(choreographyScroll);

        // RESULT BOX
        resultText = new JTextArea("Results will be shown here when\nyou click calculate.\n\n");
        resultText.setBounds(300, 330, 240, 210);
        resultText.setEditable(false);
        panel.add(resultText);
    }

    // EFFECTS: creates buttons to set the contents of the choreography
    public void createButtons() {
        // LOAD BUTTON
        loadButton = new JButton("load");
        loadButton.setBounds(45,465,100,60);
        loadButton.addActionListener(this);
        panel.add(loadButton);

        // SAVE BUTTON
        saveButton = new JButton("save");
        saveButton.setBounds(165,465,100,60);
        saveButton.addActionListener(this);
        panel.add(saveButton);

        // TYPE BUTTON
        typeButton = new JButton("set type");
        typeButton.setBounds(300,30,240,40);
        typeButton.addActionListener(this);
        panel.add(typeButton);

        // ELEMENT BUTTON
        elementButton = new JButton("add elements");
        elementButton.setBounds(300,75,240,40);
        elementButton.addActionListener(this);
        panel.add(elementButton);

        // FALL BUTTON
        fallButton = new JButton("add falls");
        fallButton.setBounds(300,120,240,40);
        fallButton.addActionListener(this);
        panel.add(fallButton);

        // DURATION BUTTON
        durationButton = new JButton("enter duration");
        durationButton.setBounds(300,165,240,40);
        durationButton.addActionListener(this);
        panel.add(durationButton);

        // SKATING SKILLS COMPONENT BUTTON
        sscButton = new JButton("enter skating skills component");
        sscButton.setBounds(300,210,240,40);
        sscButton.addActionListener(this);
        panel.add(sscButton);

        // CALCULATE BUTTON
        calculateButton = new JButton("CALCULATE!!");
        calculateButton.setBounds(300,255,240,40);
        calculateButton.addActionListener(this);
        panel.add(calculateButton);
    }

    // EFFECTS: creates labels for choreography text box, result text box, label button and save button.
    public void createLabels() {
        // CHOREOGRAPHY LABEL
        JLabel choreographyLabel = new JLabel("Your choreography so far:");
        choreographyLabel.setBounds(50, 30, 300, 15);
        panel.add(choreographyLabel);

        // RESULT LABEL
        JLabel resultLabel = new JLabel("Result:");
        resultLabel.setBounds(305, 310, 100, 15);
        panel.add(resultLabel);

        // LOAD LABEL
        loadLabel = new JLabel("loaded!");
        loadLabel.setBounds(70, 535, 100, 15);
        loadLabel.setVisible(false);
        panel.add(loadLabel);

        // SAVE LABEL
        saveLabel = new JLabel("saved!");
        saveLabel.setBounds(195, 535, 100, 15);
        saveLabel.setVisible(false);
        panel.add(saveLabel);
    }

    // EFFECTS: performs an action based on the button clicked
    public void actionPerformed(ActionEvent e) {
        PopUpWindow newWindow = new PopUpWindow(this.choreography);
        if (e.getSource() == typeButton) {
            loadLabel.setVisible(false);
            saveLabel.setVisible(false);
            newWindow.giveInstructions("type");
        } else if (e.getSource() == elementButton) {
            loadLabel.setVisible(false);
            saveLabel.setVisible(false);
            newWindow.giveInstructions("element");
        } else if (e.getSource() == fallButton) {
            loadLabel.setVisible(false);
            saveLabel.setVisible(false);
            newWindow.giveInstructions("fall");
        } else if (e.getSource() == durationButton) {
            loadLabel.setVisible(false);
            saveLabel.setVisible(false);
            newWindow.giveInstructions("duration");
        } else if (e.getSource() == sscButton) {
            loadLabel.setVisible(false);
            saveLabel.setVisible(false);
            newWindow.giveInstructions("ssc");
        } else if (e.getSource() == calculateButton) {
            // code taken from https://www.codejava.net/coding/java-audio-player-sample-application-in-swing
            try {
                player.play();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            reportResults();
            player.stop();
        } else if (e.getSource() == loadButton) {
            loadChoreography();
        } else if (e.getSource() == saveButton) {
            saveChoreography();
        }
    }

    // taken from JSONSerializationDemo: https://github.com/stleary/JSON-java.git
    // MODIFIES: this
    // EFFECTS: loads choreography from file
    private void loadChoreography() {
        try {
            choreography = jsonReader.read();
            choreographyText.setText(returnChoreographyAsString());
            loadLabel.setVisible(true);
            saveLabel.setVisible(false);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }


    // taken from JSONSerializationDemo: https://github.com/stleary/JSON-java.git
    // EFFECTS: saves the choreography to file
    private void saveChoreography() {
        try {
            jsonWriter.open();
            jsonWriter.write(choreography);
            jsonWriter.close();
            choreographyText.setText(returnChoreographyAsString());
            saveLabel.setVisible(true);
            loadLabel.setVisible(false);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // EFFECTS: returns the contents of the choreography as a string for the result text box.
    private String returnChoreographyAsString() {
        double deductions = choreography.getDeductions();
        String typeString = choreography.returnTypeAsString();
        double skatingSkillsComponent = choreography.getSkatingSkillsComponent();
        double duration = choreography.getDuration();
        int falls = choreography.getFalls();
        List<Element> elements = choreography.getListOfElements();

        String everythingExceptForElements = "\n  type: " + typeString + "\n  duration: " + duration
                + "\n  deductions: " + deductions + "\n   falls: " + falls + "\n  skating component: "
                + skatingSkillsComponent;

        String elementsString = allFeaturesOfAllElementsAsString(elements);

        return everythingExceptForElements + "\n\n" + elementsString;
    }

    // EFFECTS: returns the contents of all the elements as a string for the result text box.
    private String allFeaturesOfAllElementsAsString(List<Element> elements) {
        StringBuilder result = new StringBuilder();
        int counter = 0;

        for (Element e : elements) {
            double basePoint = e.getBasePoint();
            String name = e.getElementName();
            String type = e.getElementType();
            double goe = e.getGOE();
            counter ++;

            result.append("  ").append(ordinal(counter)).append(" element:")
                    .append("\n     name: ").append(name).append("\n     type: ")
                    .append(type).append("\n     base point: ")
                    .append(String.format("%.2f", basePoint))
                    .append("\n     goe: ").append(String.format("%.2f", goe)).append("\n");
        }

        return result.toString();
    }

    // EFFECTS: reports the results to the result text box
    private void reportResults() {

        String choreographyType = this.choreography.returnTypeAsString();
        String eligibilityString = this.choreography.printOutEligibility(choreographyType);
        String calculateString = this.choreography.calculate();

        String entireText = eligibilityString + calculateString;
        resultText.setText(entireText);
    }

    // EFFECTS: converts an integer from decimal the ordinal. For example: 1 to 1st.
    // citations:
    // https://stackoverflow.com/questions/6810336/is-there-a-way-in-java-to-convert-an-integer-to-its-ordinal-name
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

    public static void main(String[] args) {
        new Main();
    }

}