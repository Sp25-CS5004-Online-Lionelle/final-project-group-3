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
    private double population_size;
    /** Store animal speed. */
    private double speed;
    /** Store average weight of animal. */
    private double average_weight;
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
     * @param hostname String hostname info
     * @param ip String ip address info
     * @param city String city info
     * @param region String region info
     * @param postal String postal info
     * @param latitude double latitude info
     * @param longitude double longitude info
     */
    public AnimalInfo(String name, double population_size, double speed, double average_weight, 
                        String diet, String location) {
        this.name = name;
        this.population_size = population_size;
        this.speed = speed;
        this.average_weight = average_weight;
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
    public double getPopulatonSize() {
        return population_size;
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
        return average_weight;
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
    public void setPopulationSize(double pop_size) {
        this.population_size = pop_size;
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
        this.average_weight = avg_weight;
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
        return "AnimalInfo [name=" +  name + ", population_size=" + population_size + ", speed=" + speed
                + ", average_weight=" + average_weight + ", diet=" + diet + ", location=" + location + "]";
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
        return new AnimalRecord(name, population_size, speed, average_weight, diet, location);
    }    
}
