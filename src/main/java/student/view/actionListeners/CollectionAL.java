package student.view.actionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTable;

import student.controller.IAnimalController;
import student.controller.ListTypes;
import student.model.IAnimalModel.AnimalRecord;
import student.view.displays.AnimalJamCollectionDisplay;
import student.view.displays.AnimalJamFilterDisplay;
import student.view.displays.AnimalJamSaveDisplay;
import student.view.displays.AnimalJamSortDisplay;
import student.view.displays.DisplayUtils;

public final class CollectionAL {

    // Action Listener for sort Display button
    public static ActionListener sortDisplayButtonListener(JFrame frame, String[] heading) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                new AnimalJamSortDisplay(frame, heading);
            }
        };
    } 

    // Action Listener for filter Display button
    public static ActionListener filterDisplayButtonListener(
        JFrame frame, 
        String[] heading,
        IAnimalController controller,
        AnimalJamCollectionDisplay instance
    ) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                new AnimalJamFilterDisplay(instance, frame, heading, controller);
            }
        };
    } 

    // Action Listener for filter Display button
    public static ActionListener searchButtonListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                System.out.println("Searching Database");
            }
        };
    } 

    // Action Listener for save Display button
    public static ActionListener saveButtonListener(JFrame frame, IAnimalController controller) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                new AnimalJamSaveDisplay(frame, controller);
            }
        };
    } 

    // Action Listener for favorite List button
    public static ActionListener favoriteDisplayButtonListener(
        AnimalJamCollectionDisplay instance,
        String[] headings,
        IAnimalController controller
    ) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                List<AnimalRecord> favoriteList = new ArrayList<>(controller.getFavList());
                String[][] data = DisplayUtils.recordsToTableData(favoriteList);

                instance.updateDisplay(
                    data,
                    ListTypes.FAVOURITE
                );
            }
        };
    } 

    // Action Listener for collection List button
    public static ActionListener collectionDisplayButtonListener(
        AnimalJamCollectionDisplay instance,
        String[] headings,
        IAnimalController controller
        ) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                List<AnimalRecord> collectionList = new ArrayList<>(controller.getCollection());
                String[][] data = DisplayUtils.recordsToTableData(collectionList);

                instance.updateDisplay(
                    data,
                    ListTypes.COLLECTION
                );
            }
        };
    } 
    
    // Action Listener for the add to favorite List button
    public static ActionListener addToFavoriteButtonListener(JTable table, IAnimalController controller) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                int selectedRow = table.getSelectedRow();

                if(selectedRow != -1){
                    Object selectedName = table.getValueAt(selectedRow, 0);
                    String objName = selectedName.toString();
                    controller.addToFavList(objName);
                }

            }
        };
    } 

    // Action Listener for the remove from favorite List button
    public static ActionListener removeFromFavoriteButtonListener(
        AnimalJamCollectionDisplay instance,
        JTable table,
        IAnimalController controller
        ) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){

                // Get the selected row from the table
                int selectedRow = table.getSelectedRow();

                
                // Check if a row is selected, remove the selected item from the favorite list
                // and update the display
                if(selectedRow != -1){
                    Object selectedName = table.getValueAt(selectedRow, 0);
                    String objName = selectedName.toString();
                    
                    controller.removeFromFavList(objName);
                    
                    // Get the favorite list from the controller
                    List<AnimalRecord> favoriteList = new ArrayList<>(controller.getFavList());
                    String[][] data = DisplayUtils.recordsToTableData(favoriteList);

                    System.out.println("Removed " + objName + " from favorite list.");

                    instance.updateDisplay(
                    data,
                    ListTypes.FAVOURITE
                    );
                }


            }
        };
    } 
    
}
