package student.view.displays;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

public class AnimalJamSortDisplay {


    public AnimalJamSortDisplay(JFrame frame, String[] columns) {

        // Create a JDIalog and set size and location
        JDialog formatDialog = new JDialog(frame,"Format", true);
        formatDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        formatDialog.setSize(400,300);
        formatDialog.setLocationRelativeTo(frame);
        formatDialog.setLayout(null);
        
        // Create Text Label
        JLabel filterOnLabel = new JLabel("Sort on: ");
        filterOnLabel.setBounds(40, 30, 80, 25);

        // Create JComboBox with columns to choose from
        JComboBox<String> filterColumn = new JComboBox<>(columns);
        filterColumn.setBounds(40, 60, 300, 30);
        
        // Create Text Label for filter
        JLabel filterLabel = new JLabel("Sort Order: ");
        filterLabel.setBounds(40, 110, 80, 25);

        JRadioButton ascButton = new JRadioButton("Ascending", true);
        ascButton.setBounds(40, 140, 100, 30);
        DisplayUtils.styleRadioButton(ascButton);

        JRadioButton dscButton = new JRadioButton("Descending");
        dscButton.setBounds(40, 170, 100, 30);
        DisplayUtils.styleRadioButton(dscButton);

        // Create buttons to apply and cancel filters
        JButton apply = new JButton("Apply");
        apply.setBounds(150, 210, 100, 25);
        JButton cancel = new JButton("Cancel");
        cancel.setBounds(270, 210, 100, 25);

        // Add Components to JDialog
        formatDialog.add(filterOnLabel);
        formatDialog.add(filterColumn);
        formatDialog.add(filterLabel);
        formatDialog.add(ascButton);
        formatDialog.add(dscButton);
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

        String[] columns = {"Name","Population","Speed","Average Weight","Diet","Continental Location"};

        AnimalJamSortDisplay format = new AnimalJamSortDisplay(frame, columns);

    }
}


