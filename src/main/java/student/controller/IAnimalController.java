package student.controller;

import java.io.OutputStream;
import java.util.Collection;
import java.util.stream.Stream;

import student.model.IAnimalModel.AnimalRecord;
import student.model.formatters.Formats;

/**
 * Interface for the Controller.
 */
public interface IAnimalController {
   /** Method to get all records from database. */ 
   Collection<AnimalRecord> getCollection();

   /** Method to get all records from Favourite List. */ 
   Collection<AnimalRecord> getFavList();

   /** Method to get all records from Filered List. */ 
   Collection<AnimalRecord> getFilteredList();

   /** Method to write list to a file  */
   void writeFile(Collection<AnimalRecord> records, Formats format);

   /** Method to add record to favourite list */
   void addToFavList(String str);
   
   /** Method to remove record from favourite list */
   void removeFromFavList(String str);

   void resetFilter();
   void filter(String str);
   void filter(String str, Columns sortOn);
   void filter(String str, Columns sortOn, boolean ascending);

}
