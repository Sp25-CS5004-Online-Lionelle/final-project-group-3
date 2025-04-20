package student.controller;

import java.util.Comparator;
import student.model.IAnimalModel.AnimalRecord;

/**
 * Class implementing sort methods for different columns
 */
public final class Sort {

    /**
     * Sort class private empty constructor.
     */
    private Sort() { }
    
    /**
     * Method to sort based on column and ascending.
     * @param sortOn column enum
     * @param asc ascending or descending
     * @return result of the compare method
     */
    public static Comparator<AnimalRecord> getSortType(Columns sortOn, boolean asc) {
        switch (sortOn) {
            case NAME:
                return (o1, o2) -> {
                    int compare = o1.name().toLowerCase().compareTo(o2.name().toLowerCase());
                    return asc ? compare : -compare;
                };
            case POPULATION:
                return (o1, o2) -> {
                    int compare = ((Double) o1.population()).compareTo((Double) (o2.population()));
                    return asc ? compare : -compare;
                };
            case SPEED:
                return (o1, o2) -> {
                    int compare = ((Double) o1.speed()).compareTo((Double) (o2.speed()));
                    return asc ? compare : -compare;
                };
            case AVG_WEIGHT:
                return (o1, o2) -> {
                    int compare = ((Double) o1.averageWeight()).compareTo((Double) (o2.averageWeight()));
                    return asc ? compare : -compare;
                };
            case DIET:
                return (o1, o2) -> {
                    int compare = o1.diet().toLowerCase().compareTo(o2.diet().toLowerCase());
                    return asc ? compare : -compare;
                };
            case LOCATION:
                return (o1, o2) -> {
                    int compare = o1.location().toLowerCase().compareTo(o2.location().toLowerCase());
                    return asc ? compare : -compare;
                };
            default:
                return (o1, o2) -> 0;
        }
    }    
}
