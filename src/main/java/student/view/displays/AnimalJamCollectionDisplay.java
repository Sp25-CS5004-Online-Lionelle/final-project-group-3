package student.view.displays;

import javax.swing.JFrame;

public class AnimalJamCollectionDisplay {
    public AnimalJamCollectionDisplay() {

        JFrame frame = new JFrame("AnimalJam");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,600);
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        
        AnimalJamCollectionDisplay display = new AnimalJamCollectionDisplay();
    }
}