package student;

import student.controller.AnimalJamController;
import student.controller.IAnimalController;
import student.model.IAnimalModel;
import student.view.IView;
import student.view.AnimalJamGUI;
/**
 * Main driver for the program.
 * 
 */
public final class AnimalJamApp {
    /** Private constructor to prevent instantiation. */
    private AnimalJamApp() {
        // empty
    }

    /**
     * Main entry point for the program.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        IAnimalModel model = IAnimalModel.getInstance("data/sample.csv");
        IAnimalController controller = new AnimalJamController(model);

        IView view = new AnimalJamGUI(controller);
        view.run();
        
    }    
}
