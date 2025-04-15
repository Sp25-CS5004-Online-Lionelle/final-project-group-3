package student.view.displays;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class AnimalJamSaveDisplay {

    public AnimalJamSaveDisplay(JFrame frame) {

        // Create and style save Dialog
        JDialog saveDialog = new JDialog(frame ,"Save", true);
        saveDialog.setSize(400, 300);
        saveDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        saveDialog.setLocationRelativeTo(null);

        saveDialog.setVisible(true);
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


