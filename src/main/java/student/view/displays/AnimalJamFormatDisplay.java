package student.view.displays;

import java.awt.*;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;

public class AnimalJamFormatDisplay {
    
    JFrame frame;
    String[] options;

    public AnimalJamFormatDisplay(JFrame frame, String[] options) {

        // Get passed in frame
        this.frame = frame;
        this.options = options;

        // Create a JDIalog and set size and location
        JDialog formatDialog = new JDialog(frame,"Format", true);
        formatDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        formatDialog.setSize(400,300);
        formatDialog.setLocationRelativeTo(frame);
        formatDialog.setLayout(null);
        
        // Create Text Label
        JLabel filterOnLabel = new JLabel("Filter on: ");
        filterOnLabel.setBounds(40, 40, 80, 25);

        // Create JComboBox with options to choose from
        JComboBox<String> filterOptions = new JComboBox<>(options);
        filterOptions.setBounds(40, 70, 300, 30);
        
        // Add Components to JDialog
        formatDialog.add(filterOnLabel);
        formatDialog.add(filterOptions);

        // formatDialog.add(filterOnPanel);

        // Set frame to visible
        formatDialog.setVisible(true);
    }

    public static void main(String[] args) {
        
        JFrame frame = new JFrame("AnimalJam");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(800, 600);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        String[] options = {"Name","Population","Speed","Average Weight","Diet","Continental Location"};

        AnimalJamFormatDisplay format = new AnimalJamFormatDisplay(frame, options);

    }
}


