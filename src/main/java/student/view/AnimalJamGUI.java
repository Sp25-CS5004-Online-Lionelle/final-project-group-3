package student.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import student.controller.IAnimalController;
import student.model.IAnimalModel.AnimalRecord;
import student.view.displays.AnimalJamCollectionDisplay;
import student.view.displays.DisplayUtils;

public class AnimalJamGUI implements IView{
    
    private IAnimalController controller;

    public AnimalJamGUI(IAnimalController controller) {
        
        this.controller = controller;
    }

    private void displayCollection() {

        List<AnimalRecord> records = new ArrayList<>(controller.getCollection());
        String[][] data = DisplayUtils.recordsToTableData(records);
        String[] headings = {"Name", "Population", "Speed", "AverageWeight", "Diet", "Location"};
        
        AnimalJamCollectionDisplay collectionDisplay = new AnimalJamCollectionDisplay(
            data,
            headings,
            "Collection", 
            "Favorite List",
            controller);
    }

    private void displayWelcome() {

        // Create frame
        JFrame frame = new JFrame("Welcome to Animal Jam!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setLocationRelativeTo(null); // Center on screen

        // Create main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);

        // Add image
        ImageIcon image = new ImageIcon("images/Welcome.jpg");
        Image scaledImage = image.getImage().getScaledInstance(600, 600, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);

        JLabel imageLabel = new JLabel(scaledImageIcon);
        imageLabel.setBounds(0, -130, 600, 600);
        imageLabel.setLayout(null);
        
        // Enter button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBounds(0,250, 600, 600);
        buttonPanel.setOpaque(false);


        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.setAlignmentY(Component.CENTER_ALIGNMENT);

        // Enter button
        JButton enterButton = new JButton("Enter");
        enterButton.setFont(new Font("Arial", Font.BOLD, 30));
        enterButton.setBackground(new Color(50, 178, 163));
        enterButton.setForeground(Color.WHITE);
        enterButton.setMaximumSize(new Dimension(200,50));
        enterButton.addActionListener(e -> {
            // Close the welcome screen and display the collection
            frame.dispose();
            displayCollection();
        });

        enterButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Add components to the message panel
        buttonPanel.add(Box.createVerticalStrut(80));
        buttonPanel.add(enterButton);


        // Add components to the main panel
        mainPanel.add(buttonPanel);
        mainPanel.add(imageLabel);
        
        frame.add(mainPanel);
        frame.setVisible(true);

    }

    @Override
    public void run() {
        // Display the welcome screen
        displayWelcome(); 
    }

    
}
