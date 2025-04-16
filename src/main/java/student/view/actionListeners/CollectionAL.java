package student.view.actionListeners;

import java.awt.Desktop.Action;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import student.view.displays.AnimalJamSortDisplay;

public final class CollectionAL {

    public static ActionListener sortDisplayButtonListener(JFrame frame, String[] heading) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                new AnimalJamSortDisplay(frame, heading);
            }
        };
    }    
}
