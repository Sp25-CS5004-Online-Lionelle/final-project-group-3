package student;

import student.controller.IAnimalController;
import student.model.IAnimalModel;
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

        //IController parser = new ArgsController();
        //parser.parseArgs(args);
        IAnimalModel model = IAnimalModel.getInstance("data/sample.csv");
        //parser.setModel(model);
        //parser.run();
        
    }    
}
