package student.view.actionListeners;

import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import student.controller.Columns;
import student.controller.IAnimalController;
import student.controller.ListTypes;
import student.model.IAnimalModel.AnimalRecord;
import student.view.displays.AnimalJamCollectionDisplay;
import student.view.displays.DisplayUtils;


public class FilterAL {

    // Action Listener for Filter Button
    public static ActionListener applyFilterButtonListener(
        AnimalJamCollectionDisplay instance,
        JDialog filterDialog,
        JTextField filter,
        JComboBox<String> filterColumn,
        List<JRadioButton> buttons,
        IAnimalController controller
    ){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                // Get the filter string and selected column and attach them
                String filters = filter.getText().trim();
                String sortColumn = (String) filterColumn.getSelectedItem();

                // Check if the filter string is empty or null
                if (filters.isEmpty()){
                    filters = null;
                }

                // Get the sort order
                boolean ascending = true;
                for (JRadioButton button : buttons) {
                    if (button.isSelected()) {
                        ascending = button.getText().equalsIgnoreCase("Ascending");
                    }
                }

                // Get the selected column from the JComboBox
                try{
                    Columns filterColumnEnum = Columns.fromString(sortColumn.toUpperCase());
                    controller.filter(filters, filterColumnEnum, ascending);
                } catch (IllegalArgumentException i){
                    JOptionPane.showMessageDialog(filterDialog, i + ": Invalid column");
                    return;
                }

                // Print a success message
                JOptionPane.showMessageDialog(filterDialog, "Filter was applied successfully");

                // Get the filtered data from the controller
                List<AnimalRecord> filteredRecords = new ArrayList<>(controller.getFilteredList());
                String[][] filteredData = DisplayUtils.recordsToTableData(filteredRecords);

                // Update the display with the filtered data
                instance.updateDisplay(filteredData, ListTypes.FILTERED);

                // Dispose of the filter dialog
                filterDialog.dispose();
            }
        };
    } 
     
}
