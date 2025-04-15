package student.view.displays;

import javax.swing.JRadioButton;

public final class DisplayUtils {
    
    private DisplayUtils() {
        // private constructor
    }

    public static void styleRadioButton(JRadioButton button){
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setBorder(null);
    }
}
