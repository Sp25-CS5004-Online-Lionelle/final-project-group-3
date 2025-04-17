package student.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Collection;

import student.model.IAnimalModel;
import student.model.IAnimalModel.AnimalRecord;
import student.model.formatters.Formats;

public class AnimalJamController implements IAnimalController {
    /** Store handle to model. */
    private IAnimalModel model;
    /** Store filtered list. */
    private Collection<AnimalRecord> filteredList = null;
    
    /** Contructor for controller. */
    public AnimalJamController(IAnimalModel model) {
        this.model = model;
    }

    @Override
    public Collection<AnimalRecord> getCollection() {
        return model.getRecords();
    }

    @Override
    public Collection<AnimalRecord> getFavList() {
        return model.getFavList();
    }

    @Override
    public Collection<AnimalRecord> getFilteredList() {
        return filteredList;
    }

    @Override
    public void addToFavList(String str) {
        //check filtered list first to add record
        if(filteredList != null) {
            model.addToFavList(str, filteredList.stream());
        }
        else {
            model.addToFavList(str, model.getRecords().stream());
        }
        
    }

    @Override
    public void removeFromFavList(String str) {    
       model.removeFromFavList(str); 
    }

    @Override
    public void filter(String str) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void filter(String str, Columns sortOn) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void filter(String str, Columns sortOn, boolean ascending) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void resetFilter() {
        filteredList.clear();
        
    }

    @Override
    public void writeFile(ListTypes listTypes, Formats format, String filename) {
        OutputStream output;
        Collection<AnimalRecord> recordList;

        switch (listTypes) {
            case COLLECTION:
                recordList = model.getRecords();
                break;
            case FAVOURITE:
            recordList = model.getFavList();
                break;
            case FILTERED:
                recordList = filteredList;
                break;
            default:
                recordList = model.getRecords();

        }
        try {
            output = new FileOutputStream(filename);
            IAnimalModel.writeRecords(recordList, Formats.XML, output);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }    
        
    }
    
}
