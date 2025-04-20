package student.controller;

import student.model.IAnimalModel.AnimalRecord;

public final class Filter {
    /**
    * Filter class private empty constructor.
    */
    private Filter() { }

    /**
    * Method to be called to find the result of filter operation.
    * @param ar Animal Record 
    * @param col Column to filter upon
    * @param op Operation for filter
    * @param value Value to compare against
    * @return true or false based on filter operation
    */
    public static boolean filter(AnimalRecord ar, Columns col, Operations op, String value) {
        switch (col) {
            case NAME:
                return filterString(ar.name(), op, value);
            case POPULATION:
                return filterNumber(ar.population(), op, value);
            case SPEED:
                return filterNumber(ar.speed(), op, value);
            case AVG_WEIGHT:
                return filterNumber(ar.averageWeight(), op, value);
            case DIET:
                return filterString(ar.diet(), op, value);
            case LOCATION:
                return filterString(ar.location(), op, value);
            default:
                return false;
        }

    }

    /**
    * Method to filter based on String values.
    * @param bgValue String value of BoardGame object field
    * @param op Operation for filter
    * @param value Value to compare against
    * @return true or false based on filter operation
    */
    private static boolean filterString(String arValue, Operations op, String value) {
        arValue = arValue.toLowerCase().replaceAll(" ", "");
        value = value.toLowerCase();
        switch (op) {
            case EQUALS :
                return arValue.equals(value);
            case NOT_EQUALS:
                return !arValue.equals(value);
            case CONTAINS:
                return arValue.contains(value);
            case GREATER_THAN:
                return arValue.compareTo(value) > 0;
            case LESS_THAN:
                return arValue.compareTo(value) < 0;
            case GREATER_THAN_EQUALS:
                return arValue.compareTo(value) >= 0;
            case LESS_THAN_EQUALS:
                return arValue.compareTo(value) <= 0;
            default:
                return false;
        }
    }

    /**
    * Method to filter based on Number values.
    * @param bgValue double value of BoardGame object field
    * @param op Operation for filter
    * @param value Value to compare against
    * @return true or false based on filter operation
    */
    private static boolean filterNumber(double arValue, Operations op, String value) {
        double filterValue;
        try {
            filterValue = Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return false;
        }

        switch (op) {
            case EQUALS :
                return arValue == filterValue;
            case NOT_EQUALS:
                return arValue != filterValue;
            case GREATER_THAN:
                return arValue > filterValue;
            case LESS_THAN:
                return arValue < filterValue;
            case GREATER_THAN_EQUALS:
                return arValue >= filterValue;
            case LESS_THAN_EQUALS:
                return arValue <= filterValue;
            default:
                return false;
        }
    }    
}
