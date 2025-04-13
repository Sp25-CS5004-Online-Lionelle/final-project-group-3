package student.model;

import java.io.OutputStream;

import java.util.Collection;
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

    Collection<AnimalRecord> getRecords();

    AnimalRecord getRecord(String name);

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
    @JsonPropertyOrder({"name", "population_size", "speed", "averge_weight", "diet", "location"})
    record AnimalRecord(String name, double population_size, double speed, double average_weight, 
                    String diet, String location) {
    }
                    
}
