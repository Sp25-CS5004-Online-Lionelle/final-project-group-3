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
    String DATABASE = "data/records.xml";

    void addToFavList(String str, Stream<AnimalRecord> filtered);
    
    void removeFromFavList(String str);

    Collection<AnimalRecord> getRecords();

    AnimalRecord getRecord(String name);

    Collection<AnimalRecord> getFavList();

    static void writeRecords(Collection<AnimalRecord> records, Formats format, OutputStream out) {
        DataFormatter.write(records, format, out);
    }

    static IAnimalModel getInstance() {
        return getInstance(DATABASE);
    }

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
