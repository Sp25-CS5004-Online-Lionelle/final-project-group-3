# Final Project Proposal


This document summarizes the final project deliverables.



## Project Proposal Details

Please refer to Powerpoint presentation in Desgin Documents folder: Final_Project_Proposal.ppt




## (INITIAL DESIGN): Class Diagram



```mermaid
---
title:  Animal Jam UML
---
classDiagram
    AnimalJamApp --> AnimalJamController : uses
    AnimalJamApp --> AnimalJamModel : uses
    AnimalJamApp --> AnimalJamView : uses
    IController <|-- AnimalJamController
    IView <|-- AnimalJamView
    IModel <|-- AnimalJamModel
    AnimalJamModel --> Formats : uses
    AnimalJamModel --> DataFormater : uses
    DataFormater --> AnimalJamXmlWrapper : uses
    AnimalJamView --> IController : has a
    AnimalJamController --> IModel : has a
    AnimalJamModel --> AnimalInfo : uses
    class AnimalJamApp {
        - DEFAULT_COLLECTION : String$
        - AnimalJamApp()
        + main(String[] args) : void$
    }
    class IModel {
        <<interface>>
        + DATABASE : String
        + getAnimals() : Collection~AnimalRecord~
        + getAnimal(String id) : AnimalRecord
        + wrtieRecords(Collection~AnimalRecord~ records, Formats format, OutputStream out) : void$
        + getInstance() : IModel$
        + getInstance(String database) : IModel$
        record AnimalRecord(String name, String location, int population, int speed, double weight)
    }
    class AnimalJamModel {
        - animalDB : HashMap~String, AnimalRecord~
        + AnimalJamModel(String database)
        - loadDB(InputStream in, Formats format) : void
        - getAnimalJamCollection() : Collection~AnimalRecord~
        - getAnimalJamFavorites() : Collection~AnimalRecord~
        + getAnimals() : Collection~AnimalRecord~
        + getAnimal(String id) : AnimalRecord
    }
    class AnimalInfo {
        - name : String
        - location : String
        - population : int
        - spped : int
        - weight : double
        - postal : String
        - latitude : double
        - longitude : double
        + AnimalInfo()
        + AnimalInfo(String name, String location, int population, int speed, double weight)
        + getname() : String
        + setname(String name) : void
        + getLocation() : String
        + setLocation(String location) : void
        + getPopulation() : int
        + setPopulation(int population) : void
        + getSpeed() : int
        + setSpeed(int speed) : void
        + getWeight() : double
        + setWeight(double weight) : void
        + toString() : String
        + equals(Object obj) : boolean
        + hashCode() : int
        + toRecord() : AnimalRecord
    }
    class Formats {
        <<enum>>
        + containsValues(String value) : Formats$
    }
    class DataFormater {
        - DataFormater()
        - prettyPrint(Collection~AnimalRecord~ records, OutputStream out) : void$
        - prettySingle(AnimalRecord record, PrintStream out) : void$
        - writeXmlData(Collection~AnimalRecord~ records, OutputStream out) : void$
        - writeJsonData(Collection~AnimalRecord~ records, OutputStream out) : void$
        - writeCsvData(Collection~AnimalRecord~ records, OutputStream out) : void$
        - write(Collection~AnimalRecord~ records, Formats format, OutputStream out) : void$
    }
    class AnimalJamXmlWrapper {
        - domain : Collections~AnimalRecord~
        + AnimalXmlWrapper(Collections~AnimalRecord~ records)
    }
    class IController {
        <<interface>>
        + processDisplayListCmd(String cmd) : void
        + processDisplayCollectionCmd(String cmd) : void
        + processAddToListCmd(String cmd) : void
        + processRemoveFromListCmd(String cmd) : void
        + processWriteOutputCmd(String cmd) : void
        + processSortCmd(String cmd) : void
        + processFilterCmd(String cmd) : void
    }
    class AnimalJamController {
        - DEFAULT_OUTPUT_LOCATION : String$
        - DEFAULT_INPUT_FILE : String$
        - OUTPUT_FLAG : String$
        - OUTPUT_FLAG_LONG : String$
        - INPUT_FLAG : String$
        - INPUT_FLAG_LONG : String$
        - OUTPUT_FORMAT_FLAG : String$
        - OUTPUT_FORMAT_FLAG_LONG : String$
        - model : AnimalJamModel
        - outputFormat : Formats
        - outputLocation : OutputStream
        - inputFile : String
        + AnimalJamController(IModel model)
        + processDisplayListCmd(String cmd) : void
        + processDisplayCollectionCmd(String cmd) : void
        + processAddToListCmd(String cmd) : void
        + processRemoveFromListCmd(String cmd) : void
        + processWriteOutputCmd(String cmd) : void
        + processSortCmd(String cmd) : void
        + processFilterCmd(String cmd) : void
    }
    class IView {
        <<interface>>
        + start() : void
    }
    class AnimalJamView {
        - controller : IController
        - animalJamDisplay : AnimalJamDisplay
        + AnimalJamView(Icontroller controller)
        + start() : void
        + displayCollection() : void
        + displayList() : void
    }

```

