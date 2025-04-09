package student.model.formatters;

import java.io.InputStream;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import student.model.AnimalInfo;
import student.model.IAnimalModel.AnimalRecord;;

public final class InputReader {

    /** Private constructor to prevent instantiation. */
    private InputReader() {
        // empty
    }

    /**
     * Method to read Animal information from specified file.
     * @param source InputStream to read data from
     * @param format Format of the data
     * @return List of Animal records
     */
    public static List<AnimalRecord> readAnimalInfo(InputStream source, Formats format) {

        switch (format) {
            case CSV:
                return readCSV(source);
            case JSON:
                return readJSON(source);
            case XML:
                return readXML(source);
            default:
                throw new IllegalArgumentException("Unsupported format: " + format);
        }
    }


    private static List<AnimalRecord> readXML(InputStream source) {
       
        ObjectMapper mapper = new XmlMapper();
        List<AnimalRecord> animalInfoList = null;
        try {
            animalInfoList = mapper.readValue(source, new TypeReference<List<AnimalRecord>>() { });

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("The size of animalInfoList in readXML is : " + animalInfoList.size());
        return animalInfoList;
    }

    private static List<AnimalRecord> readJSON(InputStream source) {
        throw new UnsupportedOperationException("Not implemented yet");
    }


    private static List<AnimalRecord> readCSV(InputStream source) {
        CsvSchema schema = CsvSchema.emptySchema().withHeader();

        CsvMapper mapper = new CsvMapper();

        try {
              
            MappingIterator<AnimalInfo> it = mapper.readerFor(AnimalInfo.class)
                     .with(schema)
                     .readValues(source);
            List<AnimalInfo> animalInfoList = it.readAll();
              //take out as we use hostname as id
              //for (DomainNameInfo dnInfo : dnInfoList) {
              //      if(dnInfo.getId() == null) dnInfo.setId(slugify(dnInfo.getHostname()));
              //}
            System.out.println("The size of animalInfoList in readXML is : " + animalInfoList.size());
            return animalInfoList.stream().map(AnimalInfo::toRecord).toList();
        } catch (Exception e) {
              e.printStackTrace();
        }
        return null;
    }
    
}
