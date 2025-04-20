package student.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import student.controller.AnimalJamController;
import student.controller.IAnimalController;
import student.model.IAnimalModel;
import student.model.IAnimalModel.AnimalRecord;
import student.view.displays.AnimalJamCollectionDisplay;
import student.view.displays.DisplayUtils;

public class AnimalJamGUI implements IView{
    
    private IAnimalController controller;

    public AnimalJamGUI(IAnimalController controller) {
        
        this.controller = controller;
    }

    private void displayCollection() {

        List<AnimalRecord> records = new ArrayList<>(controller.getCollection());
        String[][] data = DisplayUtils.recordsToTableData(records);
        for(String[] dat : data){
            System.out.println(dat);
        }
        String[] headings = {"Name", "Population", "Speed", "Average Weight", "Diet", "Location"};
        
        AnimalJamCollectionDisplay collectionDisplay = new AnimalJamCollectionDisplay(
            data,
            headings,
            "Collection", 
            "Favorite List",
            controller);
    }

    @Override
    public void run() {
        // Display the collection
        displayCollection();
    }


    public static void main(String[] args) {
        IAnimalController controller = new AnimalJamController(IAnimalModel.getInstance());
        new AnimalJamGUI(controller);
    }

    
}
