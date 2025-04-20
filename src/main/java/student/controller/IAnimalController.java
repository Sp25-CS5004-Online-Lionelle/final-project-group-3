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
   
   /**
    *  Method to get all records from database. 
    * @return Collection of Animal Records
    */
   Collection<AnimalRecord> getCollection();


   /**
    * Method to get all records from Favourite List.
    * @return Collection of Animal Records
    */
   Collection<AnimalRecord> getFavList();

   
   /**
    * Method to get all records from Filered List.
    * @return Collection of Animal Records
    */
   Collection<AnimalRecord> getFilteredList();

   /**
    * Method to write list to a file.
    * @param listTypes Enum for type of list.
    * @param format Enum for output format.
    * @param filename String name of output file.
    */
   void writeFile(ListTypes listTypes, Formats format, String filename);

   
   /**
    * Method to add record to favourite list. 
    * @param str String of Animal Record name to be added
    */
   void addToFavList(String str);
   

   /**
    * Method to remove record from favourite list. 
    * @param str String of Animal Record name to be removed
    */
   void removeFromFavList(String str);


   /**
    * Method to clear filtered list.
    */
   void resetFilter();

   /**
    * Method to filter based on filter String, sort on Name column in ascending order
    * @param str String of filter operation
    * @return Collection of Animal Records after filter operation
    */
   Collection<AnimalRecord> filter(String str);

   /**
    * Method to filter based on filter String and sort column in ascending order
    * @param str String of filter operation
    * @param sortOn Enum of column name to sort on
    * @return Collection of Animal Records after filter operation
    */
   Collection<AnimalRecord> filter(String str, Columns sortOn);

   /**
    * Method to filter based on filter String, Sort column and value
    * @param str String of filter operation
    * @param sortOn Enum of column name to sort on
    * @param ascending Boolean for ascending or descending
    * @return Collection of Animal Records after filter operation
    */
   Collection<AnimalRecord> filter(String str, Columns sortOn, boolean ascending);

}
