package ui;

import model.Choreography;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

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
    JTextArea resultText;
    JLabel loadLabel;
    JLabel saveLabel;

    private Choreography choreography;

    private static final String JSON_STORE = "./data/choreography.json";
    private final JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
    private final JsonReader jsonReader = new JsonReader(JSON_STORE);

    public Main() {
        super("Strategy GUI");
        this.choreography = new Choreography("My choreography", 0.0, 0, 0.0,
                true, 0.0, new ArrayList<>());
        initializeGraphics();
    }

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


    public void createTextBoxes() {
        // CHOREOGRAPHY BOX
        choreographyText = new JTextArea("Your choreography will appear here if you click save or load.");
        choreographyText.setBounds(45, 60, 225, 390);
        choreographyText.setEditable(false);
        panel.add(choreographyText);

        // RESULT BOX
        resultText = new JTextArea("Results will be shown here...\n\n");
        resultText.setBounds(300, 330, 240, 210);
        resultText.setEditable(false);
        panel.add(resultText);
    }

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
            reportResults();
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
            choreographyText.setText(""); //!!!
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
            choreographyText.setText(""); //!!!
            saveLabel.setVisible(true);
            loadLabel.setVisible(false);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    private void reportResults() {

        String choreographyType = this.choreography.returnTypeAsString();
        String eligibilityString = this.choreography.printOutEligibility(choreographyType);
        String calculateString = this.choreography.calculate();

        String entireText = eligibilityString + calculateString;
        resultText.setText(entireText);
    }


    public static void main(String[] args) {
        new Main();
    }

}