import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.UnknownHostException;
import java.util.Collection;

import javax.swing.SortOrder;

import org.apache.commons.lang3.ObjectUtils.Null;
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

        try {
            singleRecord = model.getRecord("Aardvark");
            assertEquals("Aardvark", singleRecord.name());    
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //negative test get record that does not exist
        try {
            singleRecord = model.getRecord("Ardvark");
        } catch (Exception e) {
            assertEquals((NullPointerException.class), e.getClass());
        }
        
    }

    
    @Test
    public void testWriteRecordsXML() {
        String filename = "data/sample.csv";
        String outFileName = "data/outsample.xml";
        String badFileName = "output/outsample.xml";

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

        //negative case output stream not available
        try {
            output = new FileOutputStream(badFileName);
            IAnimalModel.writeRecords(recordList, Formats.XML, output);
        } catch (Exception e) {
            assertEquals((FileNotFoundException.class), e.getClass());
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
        String badFileName = "data/Outsample.csv";

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

            // reload model with csv file that was output
            model = IAnimalModel.getInstance(outFileName);
            recordList = model.getRecords();
            singleRecord = model.getRecord("Aardvark");
            assertEquals(259, recordList.size());
            assertEquals("Aardvark", singleRecord.name());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        
    }

            
    @Test
    public void testWriteRecordsJSON() {
        String filename = "data/sample.csv";
        String outFileName = "output/outsample.json";

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
