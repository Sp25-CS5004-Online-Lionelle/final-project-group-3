package student.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import student.model.IAnimalModel.AnimalRecord;

/**
 * Java Bean storage class for Animal information.
 */
public class AnimalInfo {
    /** Store animal name. */
    private String name;
    /** Store animal population size. */
    private double population;
    /** Store animal speed. */
    private double speed;
    /** Store average weight of animal. */
    private double averageWeight;
    /** Store animal diet info. */
    private String diet;
    /** Store animal location info. */
    private String location;
   

    /**
     * Emptry constructor.
     */
    public AnimalInfo() {

    }

    /**
     * Constructor with all data for Java bean class.
     * @param name String name info
     * @param population double population size
     * @param spped double speed
     * @param averageWeight double average weight
     * @param diet String diet info
     * @param location double location info
     */
    public AnimalInfo(String name, double population, double speed, double averageWeight, 
                        String diet, String location) {
        this.name = name;
        this.population = population;
        this.speed = speed;
        this.averageWeight = averageWeight;
        this.diet = diet;
        this.location = location;
    }

    /**
     * Getter for Animal name.
     * @return String data for name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for Animal population size.
     * @return double value of population size
     */
    public double getPopulaton() {
        return population;
    }

    /**
     * Getter for Animal speed.
     * @return double value of speed
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * Getter for Animal Average weight.
     * @return double value of average weight
     */
    public double getAverageWeight() {
        return averageWeight;
    }

    /**
     * Getter for Animal diet.
     * @return String data of diet info
     */
    public String getDiet() {
        return diet;
    }

    /**
     * Getter for Animal location.
     * @return String data of location info
     */
    public String getLocation() {
        return location;
    }


    /**
     * Setter for Animla name.
     * @param name String data for name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter for Population Size.
     * @param pop_size double data for population size
     */
    public void setPopulation(double pop_size) {
        this.population = pop_size;
    }

    /**
     * Setter for speed.
     * @param speed double data for speed
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    /**
     * Setter for Average weight.
     * @param avg_weight double data for average weight
     */
    public void setAverageWeigt(double avg_weight) {
        this.averageWeight = avg_weight;
    }

    /**
     * Setter for diet.
     * @param diet String data for diet
     */
    public void setDiet(String diet) {
        this.diet = diet;
    }

    /**
     * Setter for location.
     * @param loc String data for location 
     */
    public void setLocation(String loc) {
        this.location = loc;
    }

    @Override
    public String toString() {
        return "AnimalInfo [name=" +  name + ", population_size=" + population + ", speed=" + speed
                + ", average_weight=" + averageWeight + ", diet=" + diet + ", location=" + location + "]";
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    /**
     * Method to create Java Record class for Animal information.
     * @return Java record
     */
    public AnimalRecord toRecord() {
        return new AnimalRecord(name, population, speed, averageWeight, diet, location);
    }    
}
