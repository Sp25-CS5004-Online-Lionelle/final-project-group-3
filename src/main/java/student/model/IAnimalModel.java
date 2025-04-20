package student.model;

import java.io.OutputStream;

import java.util.Collection;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import student.model.formatters.DataFormatter;
import student.model.formatters.Formats;

/**
 * Interface to the model.
 */
public interface IAnimalModel {
    /**
     * Default String database path.
     */
    String DATABASE = "data/sample.csv";

    /**
     * Method to add record from filtered list to Favorite list.
     * @param str Name of animal to search in filtered list.
     * @param filtered Handle to Animal record list.
     */
    void addToFavList(String str, Stream<AnimalRecord> filtered);
    
    /**
     * Method to remove record from favorite list.
     * @param str String name of animal record to remove.
     */
    void removeFromFavList(String str);

    /**
     * Method to empty the favorite list
     */
    void clearFavList();

    /**
     * Method to get animal records of the database
     * @return Collection of Animal Records
     */
    Collection<AnimalRecord> getRecords();

    /**
     * Method to get single animal record from database
     * @param name String name of animal record
     * @return Animal Record 
     */
    AnimalRecord getRecord(String name);

    /**
     * Method to get animal records in favorite list
     * @return Collection of Animal Records
     */
    Collection<AnimalRecord> getFavList();

    /**
     * Method to write record list to output file
     * @param records List of animal records
     * @param format Format enum for output file
     * @param out String name of output file
     */
    static void writeRecords(Collection<AnimalRecord> records, Formats format, OutputStream out) {
        DataFormatter.write(records, format, out);
    }

    /**
     * Get the instance of model using default location
     * @return the instance of the model
     */
    static IAnimalModel getInstance() {
        return getInstance(DATABASE);
    }

    /**
     * Method to get instance of model using path provided
     * @param database String path to file to read as database
     * @return the instnace of the model
     */
    static IAnimalModel getInstance(String database) {
        return new AnimalJamModel(database);
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JacksonXmlRootElement(localName = "animal")
    @JsonPropertyOrder({"name", "population", "speed", "avergeWeight", "diet", "location"})
    record AnimalRecord(String name, double population, double speed, double averageWeight, 
                    String diet, String location) {
    }
                    
}
