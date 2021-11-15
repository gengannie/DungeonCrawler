package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

// start point of the game, where users get a TextField to enter their name
// generates new GraphicInterface when name is successfully entered
public class TextInput extends JFrame implements ActionListener {
    private final JTextField inputField;
    private static final String ENTER_NAME = "Please enter your hero's name: ";
    private JLabel label;
    private String name;

    //EFFECTS: constructs JTextLabel for a place to enter name
    public TextInput() {
        JPanel contentPanel = new JPanel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false);

        JButton submit = new JButton("Done!");
        submit.setPreferredSize(new Dimension(100, 50));
        submit.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 20));
        submit.addActionListener(this);

        label = new JLabel(ENTER_NAME);
        label.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 50));
        inputField = new JTextField(20);
        contentPanel.add(label);
        Font bigFont = inputField.getFont().deriveFont(Font.PLAIN, 50);
        inputField.setFont(bigFont);
        contentPanel.add(inputField);
        inputField.setBounds(50, 100, 200, 100);
        contentPanel.add(submit);
        add(contentPanel);
        setSize(400, 400);
        pack();
        centreOnScreen();
        setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: starting point of this game, starts with entering name
    // throws IO Exception if cannot find image in GraphicInterface class
    public static void main(String[] args) throws IOException {
        new TextInput();

    }

    // EFFECTS: if "Done!" button pressed, game is launched, IO Exception is caught and a stack trace gets printed out
    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s.equals("Done!")) {
            // set the text of the label to the text of the field
            name = inputField.getText();
            setVisible(false);
            try {
                new GraphicInterface(name);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }


    // This method references code from CPSC210/B02-SpaceInvadersBase
    // Link: https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase

    // Centres frame on desktop
    // MODIFIES: this
    // EFFECTS:  location of frame is set so frame is centred on desktop
    private void centreOnScreen() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screen.width - getWidth()) / 2, (screen.height - getHeight()) / 2);
    }
}
