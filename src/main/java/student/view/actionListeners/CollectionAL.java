package student.view.actionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import student.view.displays.AnimalJamFilterDisplay;
import student.view.displays.AnimalJamSortDisplay;

public final class CollectionAL {

    // Action Listener for sort Display button
    public static ActionListener sortDisplayButtonListener(JFrame frame, String[] heading) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                new AnimalJamSortDisplay(frame, heading);
            }
        };
    } 

    // Action Listener for filter Display button
    public static ActionListener filterDisplayButtonListener(JFrame frame, String[] heading) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                new AnimalJamFilterDisplay(frame, heading);
            }
        };
    } 

    // Action Listener for filter Display button
    public static ActionListener searchButtonListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                System.out.println("Searching Database");
            }
        };
    } 
    
    
}
