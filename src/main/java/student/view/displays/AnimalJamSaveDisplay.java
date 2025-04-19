package student.view.displays;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import student.model.IAnimalModel;
import student.view.actionListeners.SaveAL;

public class AnimalJamSaveDisplay {

    public AnimalJamSaveDisplay(JFrame frame, IAnimalModel model) {

        // Create and style save Dialog
        JDialog saveDialog = new JDialog(frame ,"Save", true);
        saveDialog.setSize(400, 300);
        saveDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        saveDialog.setLocationRelativeTo(null);
        saveDialog.setLayout(null);

        // Create text field for file name
        JLabel fileNameLabel = new JLabel("Save As: ");
        fileNameLabel.setBounds(40, 15, 200, 25);
        JTextField fileName = new JTextField();
        fileName.setBounds(40, 50, 300, 25);

        // Create radio button for save file type
        JLabel fileTypesLabel = new JLabel("Export FIle Type: ");
        fileTypesLabel.setBounds(40, 90, 200, 25);

        JRadioButton txtButton = new JRadioButton("txt", true);
        txtButton.setBounds(40, 120, 100, 30);
        DisplayUtils.styleRadioButton(txtButton);

        JRadioButton jsonButton = new JRadioButton("json");
        jsonButton.setBounds(40, 145, 100, 30);
        DisplayUtils.styleRadioButton(jsonButton);

        JRadioButton xmlButton = new JRadioButton("xml");
        xmlButton.setBounds(40, 170, 100, 30);
        DisplayUtils.styleRadioButton(xmlButton);

        JRadioButton csvButton = new JRadioButton("csv");
        csvButton.setBounds(40, 195, 100, 30);
        DisplayUtils.styleRadioButton(csvButton);

        List<JRadioButton> radioButtons = new ArrayList<>();
        radioButtons.add(txtButton);
        radioButtons.add(jsonButton);
        radioButtons.add(xmlButton);
        radioButtons.add(csvButton);

        // Create a group for radio buttons
        ButtonGroup fileTypes = new ButtonGroup();
        fileTypes.add(txtButton);
        fileTypes.add(jsonButton);
        fileTypes.add(xmlButton);
        fileTypes.add(csvButton);

        // Create Button to save
        JButton save = new JButton("Save");
        save.setBounds(150, 210, 100, 25);
        save.addActionListener(SaveAL.saveButtonListener(frame, fileName, radioButtons, model));

        // Create Button to cancel
        JButton cancel = new JButton("Cancel");
        cancel.setBounds(270, 210, 100, 25);

        // Add components to Dialog
        saveDialog.add(fileNameLabel);
        saveDialog.add(fileName);
        saveDialog.add(fileTypesLabel);
        saveDialog.add(txtButton);
        saveDialog.add(jsonButton);
        saveDialog.add(xmlButton);
        saveDialog.add(csvButton);
        saveDialog.add(save);
        saveDialog.add(cancel);
        saveDialog.setVisible(true);
    }

    public static void main(String[] args) {
        
        IAnimalModel model = IAnimalModel.getInstance();
        JFrame frame = new JFrame("AnimalJam");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(800, 600);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        AnimalJamSaveDisplay save = new AnimalJamSaveDisplay(frame, model);

    }
}


