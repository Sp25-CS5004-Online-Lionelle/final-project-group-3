# Unit level Testing of Model components

This document outlines the unit level testing that was done on all Model components.  The tests can be viewed in the file [TestAnimalJamModel.java](../src/test/java/TestAnimalJamModel.java).

## Unit level tests

* Test for method getInstance() using a happy path.
* Test for method getInstance() using a file not found path.
* Test for method getRecords() to verify all records are returned
* Test for method getRecord() using a valid key - name of animal
* Test for method getRecord() using a invalid key - name of animal
* Test for method writeRecord() using file format XML with good file path
* Test for method writeRecord() using file format XML with bad file path
* Test for method writeRecord() using file format JSON with good file path
* Test for method writeRecord() using file format JSON with bad file path
* Test for method writeRecord() using file format CSV with good file path
* Test for method writeRecord() using file format CSV with bad file path
* Test for method writeRecord() using file format TXT with good file path
* Test for method writeRecord() using file format TXT with bad file path
* Test for method getFavList() to verify all records in favorite list are returned
* Test for method addToFavList() by adding multiple records
* Test for method addToFavList() by adding multiple records and repeating same record again
* Test for method addToFavList() by adding record with name not in the list
* Test for method addToFavList() by adding record with null name
* Test for method addToFavList() by adding record with null record list
* Test for method addToFavList() by adding record with null name and null record list
* Test for method removeFromList() by removing multiple records and all records
* Test for method removeFromList() by removing records from empty list
* Test for method removeFromList() by removing records that does not exist in favorite list
* Test for method removeFromList() by removing records with null name
