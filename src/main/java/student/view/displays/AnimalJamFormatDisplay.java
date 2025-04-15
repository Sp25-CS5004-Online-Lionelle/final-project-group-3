package student.view.displays;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class AnimalJamFormatDisplay {
    
    JFrame frame;

    public AnimalJamFormatDisplay(JFrame frame) {

        // Get passed in frame
        this.frame = frame;

        // Create a JDIalog 
        JDialog formatDialog = new JDialog(frame,"Format", true);
        formatDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // Set JFrame size
        formatDialog.setSize(400,300);

        // Set location of the JDialog
        formatDialog.setLocationRelativeTo(null);

        // Set frame to visible
        formatDialog.setVisible(true);
    }

    public static void main(String[] args) {
        
        JFrame frame = new JFrame("AnimalJam");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(800, 600);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        AnimalJamFormatDisplay format = new AnimalJamFormatDisplay(frame);

    }
}


