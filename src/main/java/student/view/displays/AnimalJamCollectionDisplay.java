package student.view.displays;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class AnimalJamCollectionDisplay {
    public AnimalJamCollectionDisplay(String[][] data, String[] heading) {

        // Create JFrame for the collection display
        JFrame frame = new JFrame("AnimalJam");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        // Create JTable for the Collection 
        JTable colTable = new JTable(data, heading);
        JScrollPane scroll = new JScrollPane(colTable);
        scroll.setBounds(60,80,870,400);

        // Add elements to frame
        frame.add(scroll);
        frame.setVisible(true);
    }

    public static void main(String[] args) {

        String[][] data = {{"Dog", "1", "fast", "30"},
                            {"Cat", "2", "fast", "20"}};
        String[] headings = {"Name", "Population", "Speed", "Weight"};

        AnimalJamCollectionDisplay display = new AnimalJamCollectionDisplay(data, headings);
    }
}