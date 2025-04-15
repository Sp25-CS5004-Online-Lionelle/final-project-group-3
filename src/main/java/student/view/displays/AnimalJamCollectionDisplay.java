package student.view.displays;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

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

        // Create Button to switch to Favorite List
        JButton favoriteListButton = new JButton("Favorite List");
        favoriteListButton.setBounds(60, 500, 200, 28);

        // Create Sort and Filter Button to open sort and filter displays
        JButton sortDisplayButton = new JButton("Sort");
        sortDisplayButton.setBounds(600,500,150,28);
        JButton filterDisplayButton = new JButton("Filter");
        filterDisplayButton.setBounds(780,500,150,28);

        // Create JTable for the Collection 
        JTable colTable = new JTable(data, heading);

        // Center-align all columns
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < colTable.getColumnCount(); i++) {
            colTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Customize header
        JTableHeader header = colTable.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 12));

        
        JScrollPane scroll = new JScrollPane(colTable);
        scroll.setBounds(60,80,870,400);


        // Add elements to frame
        frame.add(scroll);
        frame.add(searchText);
        frame.add(searchButton);
        frame.add(favoriteListButton);
        frame.add(sortDisplayButton);
        frame.add(filterDisplayButton);
        frame.setVisible(true);
    }

    public static void main(String[] args) {

        String[][] data = {{"Dog", "1", "fast", "30", "Blah"},
                            {"Cat", "2", "fast", "20", "Blah"}};
        String[] headings = {"Name", "Population", "Speed", "Weight", "Blah"};

        AnimalJamCollectionDisplay display = new AnimalJamCollectionDisplay(data, headings);
    }
}