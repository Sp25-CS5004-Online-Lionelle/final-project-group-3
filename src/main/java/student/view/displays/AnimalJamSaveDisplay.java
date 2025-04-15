package student.view.displays;

import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.UIManager;

public class AnimalJamSaveDisplay {

    public AnimalJamSaveDisplay(JFrame frame) {

        // Create and style save Dialog
        JDialog saveDialog = new JDialog(frame ,"Save", true);
        saveDialog.setSize(400, 300);
        saveDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        saveDialog.setLocationRelativeTo(null);
        saveDialog.setLayout(null);

        // Create radio button for save file type
        JLabel fileTypesLabel = new JLabel("Export FIle Type: ");
        fileTypesLabel.setBounds(40, 20, 200, 25);
        
        JRadioButton txtButton = new JRadioButton("txt", true);
        txtButton.setBounds(40, 40, 100, 30);
        styleRadioButton(txtButton);

        JRadioButton jsonButton = new JRadioButton("json");
        jsonButton.setBounds(40, 60, 100, 30);
        styleRadioButton(jsonButton);

        JRadioButton xmlButton = new JRadioButton("xml");
        xmlButton.setBounds(40, 80, 100, 30);
        styleRadioButton(xmlButton);

        JRadioButton csvButton = new JRadioButton("csv");
        csvButton.setBounds(40, 100, 100, 30);
        styleRadioButton(csvButton);

        // Create a group for radio buttons
        ButtonGroup fileTypes = new ButtonGroup();
        fileTypes.add(txtButton);
        fileTypes.add(jsonButton);
        fileTypes.add(xmlButton);
        fileTypes.add(csvButton);

        // Add components to Dialog
        saveDialog.add(fileTypesLabel);
        saveDialog.add(txtButton);
        saveDialog.add(jsonButton);
        saveDialog.add(xmlButton);
        saveDialog.add(csvButton);
        saveDialog.setVisible(true);
    }

    private static void styleRadioButton(JRadioButton button){
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setBorder(null);
    }

    public static void main(String[] args) {
        
        JFrame frame = new JFrame("AnimalJam");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(800, 600);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        AnimalJamSaveDisplay save = new AnimalJamSaveDisplay(frame);

    }
}


