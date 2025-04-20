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

        List<AnimalRecord> records = new ArrayList<>(controller.getCollection());
        String[][] data = DisplayUtils.recordsToTableData(records);
        for(String[] dat : data){
            System.out.println(dat);
        }
        String[] headings = {"Name", "Population", "Speed", "Average Weight", "Diet", "Location"};
        
        AnimalJamCollectionDisplay collection = new AnimalJamCollectionDisplay(
            data,
            headings,
            "Collection", 
            "Favorite List",
            controller);
    }

    public void displayCollection(Collection<AnimalRecord> collection) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'displayCollection'");
    }

    @Override
    public void displayMessage(String Message, boolean isError) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'displayMessage'");
    }

    @Override
    public void displayFilterPopup() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'displayFilterPopup'");
    }

    @Override
    public void displaySortPopup() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'displaySortPopup'");
    }

    @Override
    public void displaySavePopup() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'displaySavePopup'");
    }


    public static void main(String[] args) {
        IAnimalController controller = new AnimalJamController(IAnimalModel.getInstance());
        new AnimalJamGUI(controller);
    }

    
}
