package student.view.displays;

import java.util.List;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import student.controller.IAnimalController;
import student.view.actionListeners.FilterAL;

public class AnimalJamFilterDisplay {
    
    JFrame frame;
    String[] options;
    AnimalJamCollectionDisplay instance;
    IAnimalController controller;

    public AnimalJamFilterDisplay(AnimalJamCollectionDisplay instance, JFrame frame, String[] options, IAnimalController controller) {

        // Get passed in variables
        this.frame = frame;
        this.options = options;
        this.instance = instance;
        this.controller = controller;

        // Create a JDDialog and set size and location
        JDialog formatDialog = new JDialog(frame,"Filter", true);
        formatDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        formatDialog.setSize(400,370);
        formatDialog.setLocationRelativeTo(frame);
        formatDialog.setLayout(null);
        
        // Create Text Label for filter
        JLabel filterLabel = new JLabel("Enter Filters: ");
        filterLabel.setBounds(40, 30, 80, 25);

        // Create JTextBox to input filter
        JTextField filter = new JTextField();
        filter.setBounds(40, 60, 300, 30);

        // Create Text Label
        JLabel filterOnLabel = new JLabel("Sort on: ");
        filterOnLabel.setBounds(40, 110, 80, 25);

        // Create JComboBox with columns to choose from
        JComboBox<String> filterColumn = new JComboBox<>(options);
        filterColumn.setBounds(40, 140, 300, 30);
        

        // Create label for the filter order
        JLabel sortOrderLabel = new JLabel("Sort Order: ");
        sortOrderLabel.setBounds(40, 190, 80, 25);

        // Create radio buttons for ascending and descending order
        JRadioButton ascending = new JRadioButton("Ascending", true);
        ascending.setBounds(40, 225, 100, 25);
        JRadioButton descending = new JRadioButton("Descending", false);
        descending.setBounds(40, 260, 100, 25);

        // Add radio buttons to a list
        List<JRadioButton> radioButtons = new ArrayList<>();
        radioButtons.add(ascending);
        radioButtons.add(descending);
        
        // Group radio buttons
        ButtonGroup group = new ButtonGroup();
        group.add(ascending);
        group.add(descending);

        // Create buttons to apply and cancel filters
        JButton apply = new JButton("Apply");
        apply.setBounds(150, 285, 100, 25);
        apply.addActionListener(FilterAL.applyFilterButtonListener(instance, formatDialog, filter, filterColumn, radioButtons, controller));

        JButton cancel = new JButton("Cancel");
        cancel.setBounds(270, 285, 100, 25);
        cancel.addActionListener(DisplayUtils.cancelButtonListener(formatDialog));

        // Add Components to JDialog
        formatDialog.add(filterOnLabel);
        formatDialog.add(filterColumn);
        formatDialog.add(filterLabel);
        formatDialog.add(filter);
        formatDialog.add(sortOrderLabel);
        formatDialog.add(ascending);
        formatDialog.add(descending);
        formatDialog.add(apply);
        formatDialog.add(cancel);

        // Set frame to visible
        formatDialog.setVisible(true);
    }

    // public static void main(String[] args) {
        
    //     JFrame frame = new JFrame("AnimalJam");
    //     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //     frame.setSize(800, 600);

    //     frame.setLocationRelativeTo(null);
    //     frame.setVisible(true);

    //     String[] options = {"Name","Population","Speed","Average Weight","Diet","Continental Location"};

    //     AnimalJamFilterDisplay filter = new AnimalJamFilterDisplay(frame, options);

    // }
}


