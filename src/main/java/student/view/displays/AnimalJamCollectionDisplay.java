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

import student.controller.IAnimalController;
import student.model.IAnimalModel;
import student.view.actionListeners.CollectionAL;

public class AnimalJamCollectionDisplay {

    private DefaultTableModel tableModel;
    private JFrame frame;
    private JButton favoriteListButton;
    private JButton collectionListButton;

    public AnimalJamCollectionDisplay(String[][] data ,
        String[] heading,
        String collectionType,
        String switchList,
        IAnimalModel model
    ) {
        // Create JFrame for the collection display
        frame = new JFrame("AnimalJam: " + collectionType);
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
        favoriteListButton = new JButton("Favorite List");
        favoriteListButton.setBounds(40, 500, 200, 28);
        favoriteListButton.addActionListener(CollectionAL.favoriteDisplayButtonListener(this, heading, model));

        // Create Button to switch to Collection List
        collectionListButton = new JButton("Collection List");
        collectionListButton.setBounds(40, 500, 200, 28);
        collectionListButton.setVisible(false);
        collectionListButton.addActionListener(CollectionAL.collectionDisplayButtonListener(this, heading, model));

        
        // Create Sort and Filter Button to open sort and filter displays
        JButton sortDisplayButton = new JButton("Sort");
        sortDisplayButton.setBounds(600,500,150,28);
        sortDisplayButton.addActionListener(CollectionAL.sortDisplayButtonListener(frame, heading));

        JButton filterDisplayButton = new JButton("Filter");
        filterDisplayButton.setBounds(780,500,150,28);
        filterDisplayButton.addActionListener(CollectionAL.filterDisplayButtonListener(frame, heading));
        // Table model to add extra column
        tableModel = new DefaultTableModel(data, heading) {
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        
        // Create JTable for the Collection 
        JTable colTable = new JTable(tableModel);
        
        JButton addToFavoriteListButton = new JButton("Add to Favorite List");
        addToFavoriteListButton.setBounds(40,30,200, 25);
        addToFavoriteListButton.addActionListener(CollectionAL.addToFavoriteButtonListener(colTable, model));
        
        // Create Button to Remove from Favorite List
        JButton removeFromFavoriteListButton = new JButton("Remove from Favorite List");
        removeFromFavoriteListButton.setBounds(40,30,200, 25);
        removeFromFavoriteListButton.setVisible(false);

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
        frame.add(addToFavoriteListButton);
        frame.add(removeFromFavoriteListButton);
        frame.add(searchButton);
        frame.add(favoriteListButton);
        frame.add(collectionListButton);
        frame.add(sortDisplayButton);
        frame.add(filterDisplayButton);
        frame.setVisible(true);
    }

    public void updateDisplay(String[][] data, String collectionType, boolean isFavoriteList) {
        // Get the columns from the table model
        int columnCount = tableModel.getColumnCount();
        String[] columnNames = new String[columnCount];
        for (int i = 0; i < columnCount; i++) {
            columnNames[i] = tableModel.getColumnName(i);
        }

        // Update the table model with new data
        tableModel.setDataVector(data, columnNames);
        tableModel.fireTableDataChanged();

        // Update the frame title
        if (isFavoriteList) {
            frame.setTitle("AnimalJam: Favorite List");
            collectionListButton.setVisible(true);
            favoriteListButton.setVisible(false);
        } else {
            frame.setTitle("AnimalJam: " + collectionType);
            collectionListButton.setVisible(false);
            favoriteListButton.setVisible(true);
        }
        
    }

    // public static void main(String[] args) {

    //     IAnimalModel model = IAnimalModel.getInstance();
    //     DisplayUtils.CSVResult result = DisplayUtils.ParseCSV();

    //     AnimalJamCollectionDisplay display = new AnimalJamCollectionDisplay(result.data, result.headings
    //                                  ,"Collection List","Favorite List", model);
    // }
}