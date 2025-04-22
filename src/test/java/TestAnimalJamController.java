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
    public void testResetFilterList() {
        List<AnimalRecord> animalList;
        //Collection<AnimalRecord> favList;
        Collection<AnimalRecord> filterList;
        recordList = controller.getCollection();
        animalList = recordList.stream().toList();
        
        
        // filter with blank filter to just sort items by default name field
        controller.filter("");
        filterList = controller.getFilteredList();
        assertEquals(16, filterList.size());

        // reset filter with items in filterList
        controller.resetFilter();
        filterList = controller.getFilteredList();
        assertEquals(0, filterList.size());

         // reset filter again with zero items in filterList
         controller.resetFilter();
         filterList = controller.getFilteredList();
         assertEquals(0, filterList.size());
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

        
    @Test
    public void testFilterByDiet() {
        List<AnimalRecord> animalList;
        //Collection<AnimalRecord> favList;
        Collection<AnimalRecord> filterList;
        recordList = controller.getCollection();
        animalList = recordList.stream().toList();
        AnimalRecord ar1 = animalList.get(0);
        AnimalRecord ar2 = animalList.get(1);
        AnimalRecord ar3 = animalList.get(2);
        AnimalRecord badAr = new AnimalRecord("Chuppacabra", 5, 20, 30, "human", "Americas");
        
        String filterStrContainsOp = "diet ~= omni";
        String filterStrEqualsOp = "diet == Herbivore";
        String filterStrNotEqualsOp = "diet != Insectivore";
        
        //filter test with name field and contains operation
        controller.filter(filterStrContainsOp);
        filterList = controller.getFilteredList();
        assertEquals(5, filterList.size());

        //filter test with name field and Not Equals operation
        controller.resetFilter();
        controller.filter(filterStrNotEqualsOp);
        filterList = controller.getFilteredList();
        assertEquals(15, filterList.size());
        
        //filter test with name field and Equals operation
        controller.resetFilter();
        controller.filter(filterStrEqualsOp);
        filterList = controller.getFilteredList();
        assertEquals(4, filterList.size());
    }

        
    @Test
    public void testFilterByLocation() {
        List<AnimalRecord> animalList;
        //Collection<AnimalRecord> favList;
        Collection<AnimalRecord> filterList;
        recordList = controller.getCollection();
        animalList = recordList.stream().toList();
        AnimalRecord ar1 = animalList.get(0);
        AnimalRecord ar2 = animalList.get(1);
        AnimalRecord ar3 = animalList.get(2);
        AnimalRecord badAr = new AnimalRecord("Chuppacabra", 5, 20, 30, "human", "Americas");
        
        String filterStrContainsOp = "location ~= rica";
        String filterStrEqualsOp = "location == Worldwide";
        String filterStrNotEqualsOp = "location != Asia";
        
        //filter test with name field and contains operation
        controller.filter(filterStrContainsOp);
        filterList = controller.getFilteredList();
        assertEquals(8, filterList.size());

        //filter test with name field and Not Equals operation
        controller.resetFilter();
        controller.filter(filterStrNotEqualsOp);
        filterList = controller.getFilteredList();
        assertEquals(13, filterList.size());
        
        //filter test with name field and Equals operation
        controller.resetFilter();
        controller.filter(filterStrEqualsOp);
        filterList = controller.getFilteredList();
        assertEquals(1, filterList.size());
    }

        
    @Test
    public void testFilterBySpeed() {
        List<AnimalRecord> animalList;
        //Collection<AnimalRecord> favList;
        Collection<AnimalRecord> filterList;
        recordList = controller.getCollection();
        animalList = recordList.stream().toList();
        AnimalRecord ar1 = animalList.get(0);
        AnimalRecord ar2 = animalList.get(1);
        AnimalRecord ar3 = animalList.get(2);
        AnimalRecord badAr = new AnimalRecord("Chuppacabra", 5, 20, 30, "human", "Americas");
        
        String filterStrGTEOp = "speed >= 60";
        String filterStrLTEOp = "speed <= 32";
        String filterStrGTOp = "speed > 65";
        String filterStrLTOp = "speed < 32";
        String filterStrEqualsOp = "speed == 60";
        String filterStrNotEqualsOp = "speed != 0.05";
        
        //filter test with name field and Greater than and equal operation
        controller.filter(filterStrGTEOp);
        filterList = controller.getFilteredList();
        assertEquals(4, filterList.size());

        //filter test with name field and Less than and equal operation
        controller.resetFilter();
        controller.filter(filterStrLTEOp);
        filterList = controller.getFilteredList();
        assertEquals(4, filterList.size());
        
        //filter test with name field and Greater than operation
        controller.resetFilter();
        controller.filter(filterStrGTOp);
        filterList = controller.getFilteredList();
        assertEquals(1, filterList.size());

        
        //filter test with name field and Less than operation
        controller.resetFilter();
        controller.filter(filterStrLTOp);
        filterList = controller.getFilteredList();
        assertEquals(1, filterList.size());

      
        //filter test with name field and Equals operation
        controller.resetFilter();
        controller.filter(filterStrEqualsOp);
        filterList = controller.getFilteredList();
        assertEquals(2, filterList.size());

            
        //filter test with name field and Not Equals operation
        controller.resetFilter();
        controller.filter(filterStrNotEqualsOp);
        filterList = controller.getFilteredList();
        assertEquals(15, filterList.size());

    }

            
    @Test
    public void testFilterByPopulation() {
        List<AnimalRecord> animalList;
        //Collection<AnimalRecord> favList;
        Collection<AnimalRecord> filterList;
        recordList = controller.getCollection();
        animalList = recordList.stream().toList();
        AnimalRecord ar1 = animalList.get(0);
        AnimalRecord ar2 = animalList.get(1);
        AnimalRecord ar3 = animalList.get(2);
        AnimalRecord badAr = new AnimalRecord("Chuppacabra", 5, 20, 30, "human", "Americas");
        
        String filterStrGTEOp = "population >= 490000";
        String filterStrLTEOp = "population <= 400000";
        String filterStrGTOp = "population > 500000";
        String filterStrLTOp = "population < 500000";
        String filterStrEqualsOp = "population == 10000";
        String filterStrNotEqualsOp = "population != 1000000000";
        
        //filter test with name field and Greater than and equal operation
        controller.filter(filterStrGTEOp);
        filterList = controller.getFilteredList();
        assertEquals(5, filterList.size());

        //filter test with name field and Less than and equal operation
        controller.resetFilter();
        controller.filter(filterStrLTEOp);
        filterList = controller.getFilteredList();
        assertEquals(11, filterList.size());
        
        //filter test with name field and Greater than operation
        controller.resetFilter();
        controller.filter(filterStrGTOp);
        filterList = controller.getFilteredList();
        assertEquals(3, filterList.size());

        
        //filter test with name field and Less than operation
        controller.resetFilter();
        controller.filter(filterStrLTOp);
        filterList = controller.getFilteredList();
        assertEquals(11, filterList.size());

      
        //filter test with name field and Equals operation
        controller.resetFilter();
        controller.filter(filterStrEqualsOp);
        filterList = controller.getFilteredList();
        assertEquals(2, filterList.size());

            
        //filter test with name field and Not Equals operation
        controller.resetFilter();
        controller.filter(filterStrNotEqualsOp);
        filterList = controller.getFilteredList();
        assertEquals(15, filterList.size());

    }

            
    @Test
    public void testFilterByAvgWeight() {
        List<AnimalRecord> animalList;
        //Collection<AnimalRecord> favList;
        Collection<AnimalRecord> filterList;
        recordList = controller.getCollection();
        animalList = recordList.stream().toList();
        AnimalRecord ar1 = animalList.get(0);
        AnimalRecord ar2 = animalList.get(1);
        AnimalRecord ar3 = animalList.get(2);
        AnimalRecord badAr = new AnimalRecord("Chuppacabra", 5, 20, 30, "human", "Americas");
        
        String filterStrGTEOp = "averageWeight >= 300000";
        String filterStrLTEOp = "averageWeight <= 300000";
        String filterStrGTOp = "averageWeight > 300000";
        String filterStrLTOp = "averageWeight < 300000";
        String filterStrEqualsOp = "averageWeight == 60000";
        String filterStrNotEqualsOp = "averageWeight != 150000000";
        
        //filter test with name field and Greater than and equal operation
        controller.filter(filterStrGTEOp);
        filterList = controller.getFilteredList();
        assertEquals(6, filterList.size());

        //filter test with name field and Less than and equal operation
        controller.resetFilter();
        controller.filter(filterStrLTEOp);
        filterList = controller.getFilteredList();
        assertEquals(11, filterList.size());
        
        //filter test with name field and Greater than operation
        controller.resetFilter();
        controller.filter(filterStrGTOp);
        filterList = controller.getFilteredList();
        assertEquals(5, filterList.size());

        
        //filter test with name field and Less than operation
        controller.resetFilter();
        controller.filter(filterStrLTOp);
        filterList = controller.getFilteredList();
        assertEquals(10, filterList.size());

      
        //filter test with name field and Equals operation
        controller.resetFilter();
        controller.filter(filterStrEqualsOp);
        filterList = controller.getFilteredList();
        assertEquals(1, filterList.size());

            
        //filter test with name field and Not Equals operation
        controller.resetFilter();
        controller.filter(filterStrNotEqualsOp);
        filterList = controller.getFilteredList();
        assertEquals(15, filterList.size());

    }

        
    @Test
    public void testSortByName() {
        List<AnimalRecord> animalList;
        //Collection<AnimalRecord> favList;
        Collection<AnimalRecord> filterList;
        recordList = controller.getCollection();
        animalList = recordList.stream().toList();
        AnimalRecord ar1;

        String filterStrNameContains = "";
        Object[] animalArray;
        
        //filter test sort based on name field in ascending order
        controller.filter(filterStrNameContains, Columns.NAME, true);
        filterList = controller.getFilteredList();
        animalArray = filterList.toArray();
        ar1 = (AnimalRecord) animalArray[0];
        assertEquals(ar1.name(), "Aardvark");

        //filter test sort based on name field in ascending order
        controller.filter(filterStrNameContains, Columns.NAME, false);
        filterList = controller.getFilteredList();
        animalArray = filterList.toArray();
        ar1 = (AnimalRecord) animalArray[0];
        assertEquals(ar1.name(), "Walrus");
     
    }

        
    @Test
    public void testSortByDiet() {
        List<AnimalRecord> animalList;
        //Collection<AnimalRecord> favList;
        Collection<AnimalRecord> filterList;
        recordList = controller.getCollection();
        animalList = recordList.stream().toList();
        AnimalRecord ar1;

        String filterStrNameContains = "";
        Object[] animalArray;
        
        //filter test sort based on name field in ascending order
        controller.filter(filterStrNameContains, Columns.DIET, true);
        filterList = controller.getFilteredList();
        animalArray = filterList.toArray();
        ar1 = (AnimalRecord) animalArray[0];
        assertEquals(ar1.name(), "Arctic wolf");

        //filter test sort based on name field in ascending order
        controller.filter(filterStrNameContains, Columns.DIET, false);
        filterList = controller.getFilteredList();
        animalArray = filterList.toArray();
        ar1 = (AnimalRecord) animalArray[0];
        assertEquals(ar1.name(), "Arctic fox");
     
    }

            
    @Test
    public void testSortByLocation() {
        List<AnimalRecord> animalList;
        //Collection<AnimalRecord> favList;
        Collection<AnimalRecord> filterList;
        recordList = controller.getCollection();
        animalList = recordList.stream().toList();
        AnimalRecord ar1;

        String filterStrNameContains = "";
        Object[] animalArray;
        
        //filter test sort based on name field in ascending order
        controller.filter(filterStrNameContains, Columns.LOCATION, true);
        filterList = controller.getFilteredList();
        animalArray = filterList.toArray();
        ar1 = (AnimalRecord) animalArray[0];
        assertEquals(ar1.name(), "Aardvark");

        //filter test sort based on name field in ascending order
        controller.filter(filterStrNameContains, Columns.LOCATION, false);
        filterList = controller.getFilteredList();
        animalArray = filterList.toArray();
        ar1 = (AnimalRecord) animalArray[0];
        assertEquals(ar1.name(), "Blue whale");
     
    }

            
    @Test
    public void testSortByPopulation() {
        List<AnimalRecord> animalList;
        //Collection<AnimalRecord> favList;
        Collection<AnimalRecord> filterList;
        recordList = controller.getCollection();
        animalList = recordList.stream().toList();
        AnimalRecord ar1;

        String filterStrNameContains = "";
        Object[] animalArray;
        
        //filter test sort based on name field in ascending order
        controller.filter(filterStrNameContains, Columns.POPULATION, true);
        filterList = controller.getFilteredList();
        animalArray = filterList.toArray();
        ar1 = (AnimalRecord) animalArray[0];
        assertEquals(ar1.name(), "Blue whale");

        //filter test sort based on name field in ascending order
        controller.filter(filterStrNameContains, Columns.POPULATION, false);
        filterList = controller.getFilteredList();
        animalArray = filterList.toArray();
        ar1 = (AnimalRecord) animalArray[0];
        assertEquals(ar1.name(), "Indigo bunting");
     
    }

            
    @Test
    public void testSortBySpeed() {
        List<AnimalRecord> animalList;
        //Collection<AnimalRecord> favList;
        Collection<AnimalRecord> filterList;
        recordList = controller.getCollection();
        animalList = recordList.stream().toList();
        AnimalRecord ar1;

        String filterStrNameContains = "";
        Object[] animalArray;
        
        //filter test sort based on name field in ascending order
        controller.filter(filterStrNameContains, Columns.SPEED, true);
        filterList = controller.getFilteredList();
        animalArray = filterList.toArray();
        ar1 = (AnimalRecord) animalArray[0];
        assertEquals(ar1.name(), "Sloth");

        //filter test sort based on name field in ascending order
        controller.filter(filterStrNameContains, Columns.SPEED, false);
        filterList = controller.getFilteredList();
        animalArray = filterList.toArray();
        ar1 = (AnimalRecord) animalArray[0];
        assertEquals(ar1.name(), "Jaguar");
     
    }

            
    @Test
    public void testSortByAvgWeight() {
        List<AnimalRecord> animalList;
        //Collection<AnimalRecord> favList;
        Collection<AnimalRecord> filterList;
        recordList = controller.getCollection();
        animalList = recordList.stream().toList();
        AnimalRecord ar1;

        String filterStrNameContains = "";
        Object[] animalArray;
        
        //filter test sort based on name field in ascending order
        controller.filter(filterStrNameContains, Columns.AVG_WEIGHT, true);
        filterList = controller.getFilteredList();
        animalArray = filterList.toArray();
        ar1 = (AnimalRecord) animalArray[0];
        assertEquals(ar1.name(), "Indigo bunting");

        //filter test sort based on name field in ascending order
        controller.filter(filterStrNameContains, Columns.AVG_WEIGHT, false);
        filterList = controller.getFilteredList();
        animalArray = filterList.toArray();
        ar1 = (AnimalRecord) animalArray[0];
        assertEquals(ar1.name(), "Blue whale");
     
    }

    @Test
    public void testFilterBadString() {
        List<AnimalRecord> animalList;
        //Collection<AnimalRecord> favList;
        Collection<AnimalRecord> filterList;
        recordList = controller.getCollection();
        animalList = recordList.stream().toList();
        AnimalRecord ar1 = animalList.get(0);
        AnimalRecord ar2 = animalList.get(1);
        AnimalRecord ar3 = animalList.get(2);
        AnimalRecord badAr = new AnimalRecord("Chuppacabra", 5, 20, 30, "human", "Americas");
        
        String filterStrEmpty = "";
        String filterStrBadCol = "na ~= Indian leopard";
        String filterStrBadOp = "name =~ Indian leopard";
        String filterStrNoValue = "name ~=";
        
        //filter test with name field and contains operation
        controller.filter(filterStrEmpty);
        filterList = controller.getFilteredList();
        assertEquals(16, filterList.size());

        //filter test with name field and Not Equals operation
        controller.filter(filterStrBadOp);
        filterList = controller.getFilteredList();
        assertEquals(16, filterList.size());
        
        //filter test with name field and Equals operation
        controller.filter(filterStrBadCol);
        filterList = controller.getFilteredList();
        assertEquals(16, filterList.size());
    }
}
