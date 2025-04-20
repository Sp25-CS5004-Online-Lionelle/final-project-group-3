package student.view.actionListeners;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

import student.controller.IAnimalController;
import student.model.IAnimalModel;
import student.model.IAnimalModel.AnimalRecord;
import student.model.formatters.Formats;

public class SaveAL {

    // Action Listener for save button
    public static ActionListener saveButtonListener(
        JFrame frame,
        JDialog dialog,
        JTextField fileName,
        List<JRadioButton> buttons,
        IAnimalController controller
    ) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){

                // Get the file name from the text field
                String fileNameText = fileName.getText();

                // Get the current selection export type

                String exportType = null;
                for(JRadioButton button : buttons) {
                    if (button.isSelected()) {
                        // Get the export type from the selected button
                        exportType = button.getText();
                        System.out.println("Exporting as " + exportType + " to " + fileNameText);
                    }
                }

                // // Get path from JFileChooser
                // JFileChooser fileChooser = new JFileChooser();
                // fileChooser.setDialogTitle("Select a file to save");
                // int userSelection = fileChooser.showSaveDialog(frame);

                // if (userSelection == JFileChooser.APPROVE_OPTION) {
                //     String filePath = fileToSave.getAbsolutePath();
                //     System.out.println("Saving to: " + filePath);
                // }

                // Replaced by the controller //

                // Get the favorite List from the controller
                List<AnimalRecord> favoriteList = new ArrayList<>(controller.getFavList());

                // Check if the file name is empty
                while(fileNameText == null || fileNameText.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please enter a file name.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Get the data type from the controller
                Formats format = Formats.containsValues(exportType);
                if (format == null) {
                    System.out.println("Invalid export type selected.");
                    return;
                }

                // Write the data to the file
                try {
                    IAnimalModel.writeRecords(favoriteList, format, new FileOutputStream(new File(fileNameText+"." + exportType)));
                } catch (Exception ex) {
                    System.out.println("Error saving file: " + ex.getMessage());
                }

                // Display success message
                JOptionPane.showMessageDialog(frame, "File saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

                // Close the dialog
                System.out.println("Closing save dialog");
                dialog.dispose();

            }
        };
    } 

}
