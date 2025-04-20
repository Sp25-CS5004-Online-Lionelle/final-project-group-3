package student.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import student.model.IAnimalModel;
import student.model.IAnimalModel.AnimalRecord;
import student.model.formatters.Formats;

/**
 * Class implements IAnimalController methods
 */
public class AnimalJamController implements IAnimalController {
    /** Store handle to model. */
    private IAnimalModel model;
    /** Store filtered list. */
    private Collection<AnimalRecord> filteredList = null;
    

    /**
     * Contructor for controller.
     * @param model IAnimalModel handle
     */
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
    public Collection<AnimalRecord> filter(String str) {
        return filter(str, Columns.NAME);
        
    }

    @Override
    public Collection<AnimalRecord> filter(String str, Columns sortOn) {
        return filter(str, Columns.NAME, true);
        
    }

    @Override
    public Collection<AnimalRecord> filter(String str, Columns sortOn, boolean ascending) {
        //check if filtered list is empty than filter on Collection
        if ( (filteredList == null) || (filteredList.size() == 0) ) {
            filteredList = getCollection();
        }
        //Split multiple filters that are comman seperated
        String[] filters = str.split(",");
        //iterate over each filter to be applied on the list
        for(String f : filters) {
            filteredList = filterSingle(f).collect(Collectors.toList());
        }

        return filteredList;
    }

    /**
     * Method to perform filter operation.
     * @param filter String of filter operation
     * @return Stream Animal Record after filtering
     */
    private Stream<AnimalRecord> filterSingle(String filter) {
        Operations op = Operations.getOperatorFromStr(filter);

        if(op == null) {
            return filteredList.stream();
        }

        //remove spaces
        filter = filter.replaceAll(" ", "");
        //split filter string into parts to process
        String[] parts = filter.split(op.getOperator());
        if(parts.length != 2) {
            return filteredList.stream();
        }

        Columns col;
        try {
            //part[0] has the column information split from filter string
            col = Columns.fromString(parts[0].trim());
        } catch (IllegalArgumentException e) {
            return filteredList.stream();
        }
        //part[1] has the value information split from filter string
        String value = parts[1].trim();

        filteredList = filteredList.stream().filter(ar -> Filter.filter(ar, col, op, value)).toList();
        return filteredList.stream();
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
