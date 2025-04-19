package student.view.actionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class SortAL {
    
    // Action Listener for Sort Button
    public static ActionListener sortButtonListener(JFrame frame, String[] heading) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                System.out.println("Sorting the list");
            }
        };
    } 
}
