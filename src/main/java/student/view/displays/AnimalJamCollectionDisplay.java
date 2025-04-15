package student.view.displays;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class AnimalJamCollectionDisplay {
    public AnimalJamCollectionDisplay(String[][] data, String[] heading) {

        // Create JFrame for the collection display
        JFrame frame = new JFrame("AnimalJam");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        // Create textfield and button for searching function   
        JTextField searchText = new JTextField();
        searchText.setFont(new Font("Arial", Font.PLAIN, 16));
        searchText.setBounds(620,30,220,25);

        JButton searchButton = new JButton("Search");
        searchButton.setBounds(850,30,80,25);

        // Create JTable for the Collection 
        JTable colTable = new JTable(data, heading);
        JScrollPane scroll = new JScrollPane(colTable);
        scroll.setBounds(60,80,870,400);

        // Add elements to frame
        frame.add(scroll);
        frame.add(searchText);
        frame.add(searchButton);
        frame.setVisible(true);
    }

    public static void main(String[] args) {

        String[][] data = {{"Dog", "1", "fast", "30"},
                            {"Cat", "2", "fast", "20"}};
        String[] headings = {"Name", "Population", "Speed", "Weight"};

        AnimalJamCollectionDisplay display = new AnimalJamCollectionDisplay(data, headings);
    }
}