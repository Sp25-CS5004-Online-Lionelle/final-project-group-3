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

import student.controller.AnimalJamController;
import student.controller.IAnimalController;
import student.controller.ListTypes;
import student.model.IAnimalModel;
import student.view.actionListeners.CollectionAL;

public class AnimalJamCollectionDisplay {

    private DefaultTableModel tableModel;
    private JFrame frame;
    private JButton favoriteListButton, collectionListButton, removeFromFavoriteListButton, addToFavoriteListButton;
    private JButton saveDisplayButton, clearFilterButton, sortDisplayButton, filterDisplayButton;

    public AnimalJamCollectionDisplay(String[][] data ,
        String[] heading,
        String collectionType,
        String switchList,
        IAnimalController controller
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
        searchButton.addActionListener(CollectionAL.searchButtonListener(searchText, controller, this));

        // Create Button to switch to Favorite List
        favoriteListButton = new JButton("Favorite List");
        favoriteListButton.setBounds(40, 500, 200, 28);
        favoriteListButton.addActionListener(CollectionAL.favoriteDisplayButtonListener(this, heading, controller));

        // Create Button to switch to Collection List
        collectionListButton = new JButton("Collection List");
        collectionListButton.setBounds(40, 500, 200, 28);
        collectionListButton.setVisible(false);
        collectionListButton.addActionListener(CollectionAL.collectionDisplayButtonListener(this, heading, controller));

        // Create Sort and Filter Button to open sort and filter displays
        sortDisplayButton = new JButton("Sort");
        sortDisplayButton.setBounds(600,500,150,28);
        sortDisplayButton.addActionListener(CollectionAL.sortDisplayButtonListener(frame, heading));

        filterDisplayButton = new JButton("Filter");
        filterDisplayButton.setBounds(780,500,150,28);
        filterDisplayButton.addActionListener(CollectionAL.filterDisplayButtonListener(frame, heading, controller, this));
        // Table model to add extra column
        tableModel = new DefaultTableModel(data, heading) {
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        
        // Create JTable for the Collection 
        JTable colTable = new JTable(tableModel);
        
        addToFavoriteListButton = new JButton("Add to Favorite List");
        addToFavoriteListButton.setBounds(40,30,200, 25);
        addToFavoriteListButton.addActionListener(CollectionAL.addToFavoriteButtonListener(colTable, controller));
        
        // Create Button to Remove from Favorite List
        removeFromFavoriteListButton = new JButton("Remove from Favorite List");
        removeFromFavoriteListButton.setBounds(40,30,200, 25);
        removeFromFavoriteListButton.setVisible(false);
        removeFromFavoriteListButton.addActionListener(CollectionAL.removeFromFavoriteButtonListener(this, colTable, controller));

        // Create Button to Save
        saveDisplayButton = new JButton("Save List");
        saveDisplayButton.setBounds(260, 30, 200, 25);
        saveDisplayButton.setVisible(false);
        saveDisplayButton.addActionListener(CollectionAL.saveButtonListener(frame, controller));

        // Create Button to Clear Search/Filter
        clearFilterButton = new JButton("Clear Search/Filter");
        clearFilterButton.setBounds(260, 30, 200, 25);
        clearFilterButton.setVisible(false);
        clearFilterButton.addActionListener(CollectionAL.clearFilterButtonListener(this, heading, searchText, controller));

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
        frame.add(saveDisplayButton);
        frame.add(searchButton);
        frame.add(favoriteListButton);
        frame.add(collectionListButton);
        frame.add(sortDisplayButton);
        frame.add(filterDisplayButton);
        frame.add(clearFilterButton);
        frame.setVisible(true);
    }

    public void updateDisplay(String[][] data, ListTypes listType) {
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
        if (listType == ListTypes.FAVOURITE) {
            frame.setTitle("AnimalJam: Favorite List");
            collectionListButton.setVisible(true);
            favoriteListButton.setVisible(false);
            removeFromFavoriteListButton.setVisible(true);
            addToFavoriteListButton.setVisible(false);
            saveDisplayButton.setVisible(true);
            clearFilterButton.setVisible(false);
            sortDisplayButton.setVisible(false);
            filterDisplayButton.setVisible(false);
        } else if (listType == ListTypes.COLLECTION) {
            frame.setTitle("AnimalJam: Collection");
            collectionListButton.setVisible(false);
            favoriteListButton.setVisible(true);
            removeFromFavoriteListButton.setVisible(false);
            addToFavoriteListButton.setVisible(true);
            saveDisplayButton.setVisible(false);
            clearFilterButton.setVisible(false);
            sortDisplayButton.setVisible(true);
            filterDisplayButton.setVisible(true);
        } else {
            frame.setTitle("AnimalJam: Filtered List");
            collectionListButton.setVisible(false);
            favoriteListButton.setVisible(true);
            removeFromFavoriteListButton.setVisible(false);
            addToFavoriteListButton.setVisible(true);
            saveDisplayButton.setVisible(false);
            clearFilterButton.setVisible(true);
            sortDisplayButton.setVisible(false);
            filterDisplayButton.setVisible(false);
        }
        
    }

    public static void main(String[] args) {

        IAnimalModel model = IAnimalModel.getInstance();
        String[][] data = {
            {"Animal1", "Type1", "Rarity1"},
            {"Animal2", "Type2", "Rarity2"},
            {"Animal3", "Type3", "Rarity3"}
        };
        String[] headings = {"Name", "Type", "Rarity"};
        IAnimalController controller = new AnimalJamController(model);

        AnimalJamCollectionDisplay display = new AnimalJamCollectionDisplay(data, headings
                                     ,"Collection List","Favorite List", controller);
    }
}