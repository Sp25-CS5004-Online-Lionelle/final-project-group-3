import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.UnknownHostException;
import java.util.Collection;

import javax.swing.SortOrder;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import student.model.formatters.Formats;
import student.model.AnimalJamModel;
import student.model.IAnimalModel;
import student.model.IAnimalModel.AnimalRecord;;

public class TestAnimalJamModel {
    static IAnimalModel model;
    Collection<AnimalRecord> recordList;
    AnimalRecord singleRecord;

    @Test
    public void testGetInstanceCSV() {
        String filename = "data/sample.csv";
        model = IAnimalModel.getInstance(filename);
        recordList = model.getRecords();
        singleRecord = model.getRecord("Aardvark");
        System.out.println("Test Get Instance CSV and recordList size is: " + recordList.size());
        assertEquals(259, recordList.size());
        assertEquals("Aardvark", singleRecord.name());
    }

    
    @Test
    public void testGetRecords() {
        String filename = "data/sample.csv";
        model = IAnimalModel.getInstance(filename);
        recordList = model.getRecords();
        assertEquals(259, recordList.size());
    }

    
    @Test
    public void testGetRecord() {
        String filename = "data/sample.csv";
        model = IAnimalModel.getInstance(filename);
        singleRecord = model.getRecord("Aardvark");
        assertEquals("Aardvark", singleRecord.name());
    }

    
    @Test
    public void testWriteRecordsXML() {
        String filename = "data/sample.csv";
        String outFileName = "data/outsample.xml";

        //load model with sample.csv file records
        model = IAnimalModel.getInstance(filename);
        recordList = model.getRecords();
        singleRecord = model.getRecord("Aardvark");
        assertEquals(259, recordList.size());
        assertEquals("Aardvark", singleRecord.name());

        //output the collection to xml file format
        OutputStream output;
        try {
            output = new FileOutputStream(outFileName);
            IAnimalModel.writeRecords(recordList, Formats.XML, output);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // load model with xml file that was output
        // model = IAnimalModel.getInstance(outFileName);
        // recordList = model.getRecords();
        // singleRecord = model.getRecord("Aardvark");
        // assertEquals(259, recordList.size());
        // assertEquals("Aardvark", singleRecord.name());
    }

        
    @Test
    public void testWriteRecordsCSV() {
        String filename = "data/sample.csv";
        String outFileName = "data/outsample.csv";

        //load model with sample.csv file records
        model = IAnimalModel.getInstance(filename);
        recordList = model.getRecords();
        singleRecord = model.getRecord("Aardvark");
        assertEquals(259, recordList.size());
        assertEquals("Aardvark", singleRecord.name());

        //output the collection to xml file format
        OutputStream output;
        try {
            output = new FileOutputStream(outFileName);
            IAnimalModel.writeRecords(recordList, Formats.CSV, output);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // load model with xml file that was output
        model = IAnimalModel.getInstance(outFileName);
        recordList = model.getRecords();
        singleRecord = model.getRecord("Aardvark");
        assertEquals(259, recordList.size());
        assertEquals("Aardvark", singleRecord.name());
    }

            
    @Test
    public void testWriteRecordsJSON() {
        String filename = "data/sample.csv";
        String outFileName = "data/outsample.json";

        //load model with sample.csv file records
        model = IAnimalModel.getInstance(filename);
        recordList = model.getRecords();
        singleRecord = model.getRecord("Aardvark");
        assertEquals(259, recordList.size());
        assertEquals("Aardvark", singleRecord.name());

        //output the collection to xml file format
        OutputStream output;
        try {
            output = new FileOutputStream(outFileName);
            IAnimalModel.writeRecords(recordList, Formats.JSON, output);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // load model with xml file that was output
        // model = IAnimalModel.getInstance(outFileName);
        // recordList = model.getRecords();
        // singleRecord = model.getRecord("Aardvark");
        // assertEquals(259, recordList.size());
        // assertEquals("Aardvark", singleRecord.name());
    }
}
