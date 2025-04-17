package student.view.actionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import student.controller.Columns;
import student.controller.IAnimalController;

public class FilterAL {

    private JDialog filterDialog;
    private JTextField filter;
    private JComboBox<String> filterColumn;
    private IAnimalController filterClass;

    public FilterAL(JDialog filterDialog, JTextField filter,JComboBox<String> filterColumn, IAnimalController filterClass){
        this.filterDialog = filterDialog;
        this.filter = filter;
        this.filterColumn = filterColumn;
        this.filterClass = filterClass;
    }

    // Action Listener for Filter Button
    public static ActionListener applyFilterButtonListener(
        JDialog filterDialog,
        JTextField filter,
        JComboBox<String> filterColumn,
        IAnimalController filterClass
    ){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String filterString = filter.getText();
                String selectedColumn = (String) filterColumn.getSelectedItem();

                if (filterString == null || filterString.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(filterDialog, "Please Enter a filter");
                }

                try{
                    Columns filterColumn = Columns.valueOf(selectedColumn.toUpperCase());
                    filterClass.filter(filterString, filterColumn);
                } catch (IllegalArgumentException i){
                    JOptionPane.showMessageDialog(filterDialog, i + ": Invalid column");
                    return;
                }

               filterDialog.dispose();
            }
        };
    } 
     
}
