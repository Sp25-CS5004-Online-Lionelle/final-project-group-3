package student.view.displays;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import student.view.actionListeners.CollectionAL;

public class AnimalJamCollectionDisplay {
    public AnimalJamCollectionDisplay(String[][] data, String[] heading, String collectionType, String switchList) {

        // Create JFrame for the collection display
        JFrame frame = new JFrame("AnimalJam: " + collectionType);
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
        JButton favoriteListButton = new JButton(switchList);
        favoriteListButton.setBounds(60, 500, 200, 28);

        // Create Sort and Filter Button to open sort and filter displays
        JButton sortDisplayButton = new JButton("Sort");
        sortDisplayButton.setBounds(600,500,150,28);
        sortDisplayButton.addActionListener(CollectionAL.sortDisplayButtonListener(frame, heading));

        JButton filterDisplayButton = new JButton("Filter");
        filterDisplayButton.setBounds(780,500,150,28);
        filterDisplayButton.addActionListener(CollectionAL.filterDisplayButtonListener(frame, heading));

        // Table model to add extra column
        DefaultTableModel tableModel = new DefaultTableModel(data, heading) {
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };

        // Create JTable for the Collection 
        JTable colTable = new JTable(tableModel);

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
        scroll.setBounds(40,80,900,400);


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

        DisplayUtils.CSVResult result = DisplayUtils.ParseCSV();

        AnimalJamCollectionDisplay display = new AnimalJamCollectionDisplay(result.data, result.headings
                                     ,"Collection List","Favorite List");
    }
}