package scr;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;

public class ContinuousCharReaderUI extends JFrame {
    private JTextField inputField;
    private Set<Character> keys;


    /*public void setMessageBasedSensorMode(MessageBasedSensorModel msg) {
        this.msg = msg;
    }*/

    public Set<Character> getKeys() {
        return keys;
    }

    public ContinuousCharReaderUI() {

        keys = new HashSet<Character>();
        // Set up the frame
        setTitle("Continuous Character Reader");
        setSize(300, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Initialize the text field for input
        inputField = new JTextField(20);
        add(inputField);
        
        // Add key listener to the text field
        inputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                char ch = e.getKeyChar();
                keys.remove(ch);

            }

            @Override
            public void keyTyped(KeyEvent e) {
                char ch = e.getKeyChar();
                keys.add(ch);
            }
        });

        // Make the frame visible
        setVisible(true);
    }

    public static void main(String[] args) {
        // Run the UI in the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> new ContinuousCharReaderUI());
    }
}
