import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.UnknownHostException;
import java.util.ArrayList;
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

    @BeforeAll
    public static void setup() {
        String filename = "data/sample.csv";
        model = IAnimalModel.getInstance(filename);    
    }

    @Test
    public void testGetInstance() { 
        recordList = model.getRecords();
        singleRecord = model.getRecord("Aardvark");
        assertEquals(259, recordList.size());
        assertEquals("Aardvark", singleRecord.name());
    }

    
    @Test
    public void testGetInstanceBadPath() { 
        String flname = "badpath/sample.csv";
        IAnimalModel emptyModel = IAnimalModel.getInstance(flname);    

        recordList = emptyModel.getRecords();
        assertEquals(0, recordList.size());
        
    }

    
    @Test
    public void testGetRecords() {
        recordList = model.getRecords();
        assertEquals(259, recordList.size());
    }

    
    @Test
    public void testGetRecord() {

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
        String outFileName = "data/outsample.xml";
        String badFileName = "data/Outsample.xml";

        recordList = model.getRecords();
        singleRecord = model.getRecord("Aardvark");
        assertEquals(259, recordList.size());
        assertEquals("Aardvark", singleRecord.name());

        //output the collection to xml file format.
        OutputStream output;
        try {
            output = new FileOutputStream(outFileName);
            IAnimalModel.writeRecords(recordList, Formats.XML, output);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //negative case output stream not available.
        try {
            output = new FileOutputStream(badFileName);
            IAnimalModel.writeRecords(recordList, Formats.XML, output);
        } catch (Exception e) {
            assertEquals((FileNotFoundException.class), e.getClass());
        }

    }

        
    @Test
    public void testWriteRecordsCSV() {
        String outFileName = "data/outsample.csv";
        String badFileName = "data/Outsample.csv";

        recordList = model.getRecords();
        singleRecord = model.getRecord("Aardvark");
        assertEquals(259, recordList.size());
        assertEquals("Aardvark", singleRecord.name());

        //output the collection to csv file format
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

        //negative case output stream not available
        try {
            output = new FileOutputStream(badFileName);
            IAnimalModel.writeRecords(recordList, Formats.CSV, output);
        } catch (Exception e) {
            assertEquals((FileNotFoundException.class), e.getClass());
        }

    }

            
    @Test
    public void testWriteRecordsJSON() {
        String outFileName = "data/outsample.json";
        String badFileName = "data/Outsample.json";

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

        
        //negative case output stream not available
        try {
            output = new FileOutputStream(badFileName);
            IAnimalModel.writeRecords(recordList, Formats.JSON, output);
        } catch (Exception e) {
            assertEquals((FileNotFoundException.class), e.getClass());
        }

    }

                
    @Test
    public void testWriteRecordsTXT() {
        String outFileName = "data/outsample.txt";
        String badFileName = "data/Outsample.txt";

        recordList = model.getRecords();
        singleRecord = model.getRecord("Aardvark");
        assertEquals(259, recordList.size());
        assertEquals("Aardvark", singleRecord.name());

        //output the collection to xml file format
        OutputStream output;
        try {
            output = new FileOutputStream(outFileName);
            IAnimalModel.writeRecords(recordList, Formats.TXT, output);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        
        //negative case output stream not available
        try {
            output = new FileOutputStream(badFileName);
            IAnimalModel.writeRecords(recordList, Formats.TXT, output);
        } catch (Exception e) {
            assertEquals((FileNotFoundException.class), e.getClass());
        }

    }

            
    @Test
    public void testGetFavList() {
        Collection<AnimalRecord> favList;
        recordList = model.getRecords();
        AnimalRecord ar1 = model.getRecord("Aardvark");
        AnimalRecord ar2 = model.getRecord("Cat");
        AnimalRecord ar3 = model.getRecord("Dog");
        AnimalRecord ar4 = model.getRecord("Seal");

        //check happy paths
        model.addToFavList(ar1.name(), recordList.stream());
        model.addToFavList(ar2.name(), recordList.stream());
        model.addToFavList(ar3.name(), recordList.stream());
        favList = model.getFavList();
        assertEquals(3, favList.size());
    }
    
    
    @Test
    public void testaddToFavList() {
        Collection<AnimalRecord> favList;
        recordList = model.getRecords();
        AnimalRecord ar1 = model.getRecord("Aardvark");
        AnimalRecord ar2 = model.getRecord("Cat");
        AnimalRecord ar3 = model.getRecord("Dog");
        AnimalRecord ar4 = model.getRecord("Seal");
        AnimalRecord badAr = new AnimalRecord("Chuppacabra", 5, 20, 30, "human", "Americas");

        //check happy paths
        model.addToFavList(ar1.name(), recordList.stream());
        favList = model.getFavList();
        assertEquals(1, favList.size());

        model.addToFavList(ar2.name(), recordList.stream());
        model.addToFavList(ar3.name(), recordList.stream());
        favList = model.getFavList();
        assertEquals(3, favList.size());

        //test to add record that is not in the list
        model.addToFavList(badAr.name(), recordList.stream());
        assertEquals(3, favList.size());


        //negative path adding the same record again
        model.addToFavList(ar2.name(), recordList.stream());
        model.addToFavList(ar3.name(), recordList.stream());
        favList = model.getFavList();
        assertEquals(3, favList.size());

        //negative path adding with null name
        model.addToFavList(null, recordList.stream());
        favList = model.getFavList();
        assertEquals(3, favList.size());

        //negative path adding with null filtered list
        model.addToFavList(ar4.name(), null);
        favList = model.getFavList();
        assertEquals(3, favList.size());

        //negative path adding with null filtered list and name string
        model.addToFavList(null, null);
        favList = model.getFavList();
        assertEquals(3, favList.size());

    }
            
    @Test
    public void testremoveFromFavList() {
        Collection<AnimalRecord> favList;
        recordList = model.getRecords();
        AnimalRecord ar1 = model.getRecord("Aardvark");
        AnimalRecord ar2 = model.getRecord("Cat");
        AnimalRecord ar3 = model.getRecord("Dog");
        AnimalRecord ar4 = model.getRecord("Seal");


        model.addToFavList(ar1.name(), recordList.stream());
        model.addToFavList(ar2.name(), recordList.stream());
        model.addToFavList(ar3.name(), recordList.stream());
        favList = model.getFavList();
        assertEquals(3, favList.size());
        
        //check happy paths        
        model.removeFromFavList(ar1.name());
        favList = model.getFavList();
        assertEquals(2, favList.size());

        //remove all records
        model.removeFromFavList(ar2.name());
        model.removeFromFavList(ar3.name());
        favList = model.getFavList();
        assertEquals(0, favList.size());

        //negative path remove from empty list
        model.removeFromFavList(ar2.name());
        favList = model.getFavList();
        assertEquals(0, favList.size());


        //add records back to favList
        model.addToFavList(ar1.name(), recordList.stream());
        model.addToFavList(ar2.name(), recordList.stream());
        model.addToFavList(ar3.name(), recordList.stream());
        favList = model.getFavList();
        assertEquals(3, favList.size());

        //negative path removing the record that does not exist
        model.removeFromFavList(ar4.name());
        favList = model.getFavList();
        assertEquals(3, favList.size());

        //negative path removing with null name
        model.removeFromFavList(null);
        favList = model.getFavList();
        assertEquals(3, favList.size());

    }

}
