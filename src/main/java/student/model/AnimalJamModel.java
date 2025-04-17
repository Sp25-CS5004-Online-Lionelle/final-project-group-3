package student.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import student.model.formatters.Formats;
import student.model.formatters.InputReader;

public class AnimalJamModel implements IAnimalModel {
    /** Map to store Animal records. */
    private final Map<String, AnimalRecord> animalInfoLibrary = new LinkedHashMap<String, AnimalRecord>();

    /** Store favourite list records  */
    private final List<AnimalRecord> animalFavList = new LinkedList<>();

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
        AnimalRecord tmp = animalInfoLibrary.get(id);
        //if(tmp == null) {
             // grab from online the network id, add the book to the collection, save out the collection
        //}
        return animalInfoLibrary.get(id);
    }

    @Override
    public void addToFavList(String str, Stream<AnimalRecord> filtered) {

    }
    
    
    @Override
    public void removeFromFavList(String str) {

    }

    @Override
    public Collection<AnimalRecord> getFavList() {
        return animalFavList;
    }
    
}
