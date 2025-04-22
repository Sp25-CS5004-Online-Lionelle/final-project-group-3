import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.SortOrder;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import student.model.formatters.Formats;
import student.controller.AnimalJamController;
import student.controller.Columns;
import student.controller.IAnimalController;
import student.model.AnimalJamModel;
import student.model.IAnimalModel;
import student.model.IAnimalModel.AnimalRecord;

public class TestAnimalJamController {
    static IAnimalModel model;
    static IAnimalController controller;
    Collection<AnimalRecord> recordList;

    @BeforeAll
    public static void setup() {
        String filename = "data/test_sample.csv";
        model = IAnimalModel.getInstance(filename); 
        controller = new AnimalJamController(model);
    }

    @Test
    public void testGetColletion() {
        //Get list of records
        recordList = controller.getCollection();
        assertEquals(16, recordList.size());
    }

    @Test
    public void testGetFavList() {
        List<AnimalRecord> animalList;
        Collection<AnimalRecord> favList;

        recordList = controller.getCollection();
        animalList = recordList.stream().toList();
        AnimalRecord ar1 = animalList.get(0);
        AnimalRecord ar2 = animalList.get(1);
        controller.addToFavList(ar1.name());
        controller.addToFavList(ar2.name());
    
        favList = controller.getFavList();
        assertEquals(2, favList.size());
    }

    @Test
    public void testAddToFavList() {
        List<AnimalRecord> animalList;
        Collection<AnimalRecord> favList;
        recordList = controller.getCollection();
        animalList = recordList.stream().toList();
        AnimalRecord ar1 = animalList.get(0);
        AnimalRecord ar2 = animalList.get(1);
        AnimalRecord badAr = new AnimalRecord("Chuppacabra", 5, 20, 30, "human", "Americas");
        controller.addToFavList(ar1.name());
        controller.addToFavList(ar2.name());
        favList = controller.getFavList();
        assertEquals(2, favList.size());

        //add again the same record
        controller.addToFavList(ar1.name());
        assertEquals(2, favList.size());
    }

    
    @Test
    public void testRemoveToFavList() {
        List<AnimalRecord> animalList;
        Collection<AnimalRecord> favList;
        recordList = controller.getCollection();
        animalList = recordList.stream().toList();
        AnimalRecord ar1 = animalList.get(0);
        AnimalRecord ar2 = animalList.get(1);
        AnimalRecord ar3 = animalList.get(2);
        AnimalRecord badAr = new AnimalRecord("Chuppacabra", 5, 20, 30, "human", "Americas");
        controller.addToFavList(ar1.name());
        controller.addToFavList(ar2.name());
        favList = controller.getFavList();
        assertEquals(2, favList.size());

        //remove record that does not exist in favorite list
        controller.removeFromFavList(ar3.name());
        favList = controller.getFavList();
        assertEquals(2, favList.size());

        //remove record
        controller.removeFromFavList(ar1.name());
        favList = controller.getFavList();
        assertEquals(1, favList.size());

        //remove record that is already removed
        controller.removeFromFavList(ar1.name());
        favList = controller.getFavList();
        assertEquals(1, favList.size());

        //remove record from empty list
        controller.removeFromFavList(ar2.name());
        favList = controller.getFavList();
        assertEquals(0, favList.size());
    }

        
    @Test
    public void testFilterByName() {
        List<AnimalRecord> animalList;
        //Collection<AnimalRecord> favList;
        Collection<AnimalRecord> filterList;
        recordList = controller.getCollection();
        animalList = recordList.stream().toList();
        AnimalRecord ar1 = animalList.get(0);
        AnimalRecord ar2 = animalList.get(1);
        AnimalRecord ar3 = animalList.get(2);
        AnimalRecord badAr = new AnimalRecord("Chuppacabra", 5, 20, 30, "human", "Americas");
        
        String filterStrNameContains = "name ~= Indi";
        String filterStrNameEquals = "name == Indian leopard";
        String filterStrNameNotEquals = "name != Indigo bunting";
        
        //filter test with name field and contains operation
        controller.filter(filterStrNameContains, Columns.NAME, true);
        filterList = controller.getFilteredList();
        assertEquals(3, filterList.size());

        //filter test with name field and Not Equals operation
        controller.filter(filterStrNameNotEquals);
        filterList = controller.getFilteredList();
        assertEquals(2, filterList.size());
        
        //filter test with name field and Equals operation
        controller.filter(filterStrNameEquals);
        filterList = controller.getFilteredList();
        assertEquals(1, filterList.size());
    }
}
