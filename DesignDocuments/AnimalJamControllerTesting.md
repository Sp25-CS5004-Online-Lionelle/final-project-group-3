# Unit level Testing of Controller components

This document outlines the unit level testing that was done on all Controller components.  The tests can be viewed in the file [TestAnimalJamController.java](../src/test/java/TestAnimalJamController.java).

## Unit level tests

* Test for method getCollection() to verify all records are returned.
* Test for method getFavList() to verify all records are returned.
* Test for method getFilteredList() to verify all records are returned.
* Test for method addToFavList() by adding multiple records.
* Test for method addToFavList() by adding duplicate records
* Test for method addToFavList() by record that does not exist in the collection
* Test for method removeFromFavList() by removing multiple records.
* Test for method removeFromFavList() by removing already removed records
* Test for method removeFromFavList() by removing non existing records
* Test for method removeFromFavList() by removing record from empty list
* Test for method resetFilter() when filtered list has items
* Test for method resetFilter() when filtered list is empty
* Test for method filter() using filter by name for contains and equals operations
* Test for method filter() using filter by diet for contains and equals operations
* Test for method filter() using filter by location for contains and equals operations
* Test for method filter() using filter by population for operations (==, !=, >, <, >=, <=)
* Test for method filter() using filter by speed for operations (==, !=, >, <, >=, <=)
* Test for method filter() using filter by averageWeight for operations (==, !=, >, <, >=, <=)
* Test for method filter with non existant column name filter string
* Test for method filter with non existant operation filter string
* Test for method filter with non existant value filter string
* Test for method filter with sorting by name field in ascending and descending order
* Test for method filter with sorting by diet field in ascending and descending order
* Test for method filter with sorting by location field in ascending and descending order
* Test for method filter with sorting by population field in ascending and descending order
* Test for method filter with sorting by speed field in ascending and descending order
* Test for method filter with sorting by averageWeight field in ascending and descending order


