package student.view.displays;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AnimalJamFilterDisplay {
    
    JFrame frame;
    String[] options;

    public AnimalJamFilterDisplay(JFrame frame, String[] options) {

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
        filterOnLabel.setBounds(40, 30, 80, 25);

        // Create JComboBox with columns to choose from
        JComboBox<String> filterColumn = new JComboBox<>(options);
        filterColumn.setBounds(40, 60, 300, 30);
        
        // Create Text Label for filter
        JLabel filterLabel = new JLabel("Filter: ");
        filterLabel.setBounds(40, 110, 80, 25);

        // Create JTextBox to imput label
        JTextField filter = new JTextField();
        filter.setText("Enter Filter Here");
        filter.setBounds(40, 140, 300, 30);

        // Create buttons to apply and cancel filters
        JButton apply = new JButton("Apply");
        apply.setBounds(150, 210, 100, 25);
        JButton cancel = new JButton("Cancel");
        cancel.setBounds(270, 210, 100, 25);

        // Add Components to JDialog
        formatDialog.add(filterOnLabel);
        formatDialog.add(filterColumn);
        formatDialog.add(filterLabel);
        formatDialog.add(filter);
        formatDialog.add(apply);
        formatDialog.add(cancel);

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

        AnimalJamFilterDisplay filter = new AnimalJamFilterDisplay(frame, options);

    }
}


