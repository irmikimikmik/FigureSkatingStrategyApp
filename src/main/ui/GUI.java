package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener {

    public static final int FONT_SIZE = 15;

    // code taken from ListDemoProject and SimpleDrawingPlayer
    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    private static final String setTypeString = "set type";
    private static final String addElementsString = "add elements";
    private static final String addFallsString = "add falls";
    private static final String enterDurationString = "enter duration";
    private static final String enterSSString = "enter skating skills component";
    private static final String loadString = "load";
    private static final String saveString = "save";
    private static final String calculateString = "CALCULATE!!";
    private JButton typeButton;
    private JButton elementButton;
    private JButton fallButton;
    private JButton durationButton;
    private JButton sscButton;
    private JButton loadButton;
    private JButton saveButton;
    private JButton calculateButton;
    private JTextField choreographyText;
    private JTextField resultText;

    // a constructor for GUI
    // EFFECTS: draws the JFrame window where this StrategyApp will operate
    // code taken from SimpleDrawingPlayer and https://www.youtube.com/watch?v=5o3fMLPY7qY
    public GUI() {

        Font f = new Font("serif", Font.PLAIN, FONT_SIZE);

        JPanel panel = new JPanel();

        JFrame frame = new JFrame();
        frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        frame.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);

        // CHOREOGRAPHY LABEL
        JLabel choreographyLabel = new JLabel("Your choreography so far:");
        choreographyLabel.setBounds(50, 30, 300, 15);
        choreographyLabel.setFont(f);
        panel.add(choreographyLabel);

        // RESULT LABEL
        JLabel resultLabel = new JLabel("Result:");
        resultLabel.setBounds(305, 310, 100, 15);
        resultLabel.setFont(f);
        panel.add(resultLabel);

        // CHOREOGRAPHY BOX
        this.choreographyText = new JTextField("Choreography:\n\n");
        choreographyText.setBounds(45, 60, 225, 390);
        choreographyText.setFont(f);
        panel.add(choreographyText);

        // RESULT BOX
        this.resultText = new JTextField("Results:\n\n");
        resultText.setBounds(300, 330, 240, 210);
        resultText.setFont(f);
        panel.add(resultText);

        // LOAD BUTTON
        loadButton = new JButton(loadString);
        loadButton.setBounds(45,480,100,60);
        panel.add(loadButton);

        // SAVE BUTTON
        saveButton = new JButton(saveString);
        saveButton.setBounds(165,480,100,60);
        panel.add(saveButton);

        // TYPE BUTTON
        typeButton = new JButton(setTypeString);
        typeButton.setBounds(300,30,240,40);
        panel.add(typeButton);

        // ELEMENT BUTTON
        elementButton = new JButton(addElementsString);
        elementButton.setBounds(300,75,240,40);
        panel.add(elementButton);

        // FALL BUTTON
        fallButton = new JButton(addFallsString);
        fallButton.setBounds(300,120,240,40);
        panel.add(fallButton);

        // DURATION BUTTON
        durationButton = new JButton(enterDurationString);
        durationButton.setBounds(300,165,240,40);
        panel.add(durationButton);

        // SKATING SKILLS COMPONENT BUTTON
        sscButton = new JButton(enterSSString);
        sscButton.setBounds(300,210,240,40);
        panel.add(sscButton);

        // CALCULATE BUTTON
        calculateButton = new JButton(calculateString);
        calculateButton.setBounds(300,255,240,40);
        panel.add(calculateButton);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

