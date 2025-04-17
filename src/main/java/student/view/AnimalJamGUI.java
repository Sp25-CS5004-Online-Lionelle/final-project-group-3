package student.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import student.model.IAnimalModel;
import student.model.IAnimalModel.AnimalRecord;

public class AnimalJamGUI implements IView{
    
    IAnimalModel model;

    public AnimalJamGUI(IAnimalModel model) {
        
        this.model = model;

        List<AnimalRecord> records = new ArrayList<>(model.getRecords());
        

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

    @Override
    public String[][] recordsToTableData(Collection<AnimalRecord> records){
        String[][] data = new String[records.size()][6];

        int i = 0;
        for(AnimalRecord record : records){
            data[i][0] = record.name();
            data[i][1] = String.valueOf(record.population());
            data[i][2] = String.valueOf(record.speed());
            data[i][3] = String.valueOf(record.averageWeight());
            data[i][4] = record.diet();
            data[i][5] = record.location();
            i++;
        }

        return data;
    }

    
}
