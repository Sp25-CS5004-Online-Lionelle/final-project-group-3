package student.view;

import java.util.Collection;
import student.model.IAnimalModel;

import student.view.IView;

public class AnimalJamGUI implements IView{
    
    public AnimalJamGUI() {

    }

    public void displayCollection(Collection<IAnimalModel.AnimalRecord> collection) {
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

    
}
