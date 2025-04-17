package student.view.actionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTable;

import student.controller.IAnimalController;
import student.model.IAnimalModel;
import student.model.IAnimalModel.AnimalRecord;
import student.view.AnimalJamGUI;
import student.view.displays.AnimalJamCollectionDisplay;
import student.view.displays.AnimalJamFilterDisplay;
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
        String[] heading
    ) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                new AnimalJamFilterDisplay(frame, heading);
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

    // Action Listener for favorite List button
    public static ActionListener favoriteDisplayButtonListener(String[] headings, IAnimalModel model) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                List<AnimalRecord> favoriteList = new ArrayList<>(model.getFavList());
                String[][] data = DisplayUtils.recordsToTableData(favoriteList);

                new AnimalJamCollectionDisplay(data, headings, "Favorite List", "Collection List", model);
            }
        };
    } 
    
    // Action Listener for the add to favorite List button
    public static ActionListener addToFavoriteButtonListener(JTable table, IAnimalModel model) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                int selectedRow = table.getSelectedRow();

                if(selectedRow != -1){
                    Object selectedName = table.getValueAt(selectedRow, 0);
                    String objName = selectedName.toString();
                    AnimalRecord record = model.getRecord(objName);
                    List<AnimalRecord> recordsToAdd = new ArrayList<>();
                    recordsToAdd.add(record);
                    model.addToFavList(objName, recordsToAdd.stream());
                }

            }
        };
    } 
    
}
