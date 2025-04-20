package student.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import student.model.formatters.Formats;
import student.model.formatters.InputReader;

public class AnimalJamModel implements IAnimalModel {
    /** Map to store Animal records. */
    private final Map<String, AnimalRecord> animalInfoLibrary = new LinkedHashMap<String, AnimalRecord>();

    /** Store favourite list records  */
    private final Set<AnimalRecord> animalFavList = new HashSet();

    /**
     * Contructor.
     * @param database Input file name
     */
    public AnimalJamModel(String database) {
        try {
            loadAnimalInfo(new FileInputStream(database), Formats.CSV);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadAnimalInfo(InputStream in, Formats format) {
        animalInfoLibrary.putAll(
            InputReader.readAnimalInfo(in, format).stream().collect(
                Collectors.toMap(animalInfo -> animalInfo.name(),  animalInfo -> animalInfo)));
        //debug print
        System.out.println("In loadAnimalInfo and size is : " + animalInfoLibrary.size());
        }

    @Override
    public Collection<AnimalRecord> getRecords() {
        return animalInfoLibrary.values();
    }

    @Override
    public AnimalRecord getRecord(String id) {
        AnimalRecord tmp = null;
        try {
            tmp = animalInfoLibrary.get(id);
            return tmp;
        } catch (NullPointerException e) {
            return null;
        }
        
    }

    @Override
    public void addToFavList(String str, Stream<AnimalRecord> filtered) {
        //check if the string has the name of Animal to add to list
        List<AnimalRecord> animalList = filtered.filter(anr -> anr.name().equalsIgnoreCase(str.trim())).collect(Collectors.toList());

        //add to fav list if found a matching record with name as key
        if(animalList.size() != 0) {
            animalFavList.add(animalInfoLibrary.get(animalList.get(0).name()));
        }
    }
    
    
    @Override
    public void removeFromFavList(String str) {

        Collection<AnimalRecord> animalKeys = animalInfoLibrary.values();
        //check if the string has the name of Animal to add to list
        List<AnimalRecord> animalList = animalKeys.stream().filter(anr -> anr.name().equalsIgnoreCase(str.trim())).collect(Collectors.toList());

        //add to fav list if found a matching record with name as key
        if(animalList.size() != 0) {
            animalFavList.remove(animalInfoLibrary.get(animalList.get(0).name()));
        }
    }

    @Override
    public Collection<AnimalRecord> getFavList() {
        return animalFavList;
    }
    
}
