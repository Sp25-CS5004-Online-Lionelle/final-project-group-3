package student.model.formatters;

import java.io.PrintStream;
import java.io.OutputStream;
import java.util.Collection;
import javax.annotation.Nonnull;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import student.model.IModel.AnimalRecord;

public final class DataFormatter {
    /**
     * Private constructor to prevent instantiation.
     */
    private DataFormatter() {
        // empty
    }

    /**
     * Pretty print the data in a human readable format.
     * 
     * @param records the records to print
     * @param out the output stream to write to
     */
    private static void prettyPrint(Collection<AnimalRecord> records, OutputStream out) {
        PrintStream pout = new PrintStream(out); // so i can use println
        System.out.println("prettyPrint called with Collection size = " + records.size());
        for (AnimalRecord record : records) {
            prettySingle(record, pout);
            pout.println();
        }
    }

    /**
     * Pretty print a single record.
     * 
     * Let this as an example, so you didn't have to worry about spacing.
     * 
     * @param record the record to print
     * @param out the output stream to write to
     */
    private static void prettySingle(@Nonnull AnimalRecord record, @Nonnull PrintStream out) {
        out.println(record.hostname());
        out.println("             IP: " + record.ip());
        out.println("       Location: " + record.city() + ", " + record.region() + ", "
                + record.country() + ", " + record.postal());
        out.println("    Coordinates: " + record.latitude() + ", " + record.longitude());

    }

    /**
     * Write the data as XML.
     * 
     * @param records the records to write
     * @param out the output stream to write to
     */
    private static void writeXmlData(Collection<AnimalRecord> records, OutputStream out) {
        ObjectMapper mapper = new XmlMapper();
        DomainXmlWrapper wrapper = new DomainXmlWrapper(records);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            mapper.writeValue(out, wrapper);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Write the data as JSON.
     * 
     * @param records the records to write
     * @param out the output stream to write to
     */
    private static void writeJsonData(Collection<AnimalRecord> records, OutputStream out) {
         ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            mapper.writeValue(out, records);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Write the data as CSV.
     * 
     * @param records the records to write
     * @param out the output stream to write to
     */
    private static void writeCSVData(Collection<AnimalRecord> records, OutputStream out) {
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(AnimalRecord.class).withHeader();
        try {
            mapper.writer(schema).writeValues(out).writeAll(records);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Write the data in the specified format.
     * 
     * @param records the records to write
     * @param format the format to write the records in
     * @param out the output stream to write to
     */
    public static void write(@Nonnull Collection<AnimalRecord> records, @Nonnull Formats format,
            @Nonnull OutputStream out) {

        switch (format) {
            case XML:
                writeXmlData(records, out);
                break;
            case JSON:
                writeJsonData(records, out);
                break;
            case CSV:
                writeCSVData(records, out);
                break;
            default:
                prettyPrint(records, out);

        }
    }    
}
