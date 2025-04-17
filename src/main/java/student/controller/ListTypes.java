package student.controller;

import student.model.formatters.Formats;

public enum ListTypes {
    /** Different Lists options. */
    COLLECTION, FAVOURITE, FILTERED;

    /**
    * Helper function to check if a value is in the list of list types.
    * 
    * @param value the value to check
    * @return the format if found, null otherwise
    */
    public static ListTypes containsValues(String value) {
        for (ListTypes listType : ListTypes.values()) {
           if (listType.toString().equalsIgnoreCase(value)) {
               return listType;
           }
       }
       return null;
    } 
}
