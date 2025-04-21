package student.controller;

/**
 * Enum for the columns in Animal Record.
 * This is to make it easier to access the column names
 * from the CSV file, without knowing
 * the names of the specific columns anywhere else in the program.
 */
public enum Columns {
    /**
     * Enums matching CODE(cvsname) pattern.
     * 
     */
    NAME("name"), POPULATION("population"),
    SPEED("speed"), AVG_WEIGHT("averageWeight"),
    DIET("diet"), LOCATION("location");

    /** stores the original csv name in the enum. */
    private final String columnName;

    /**
     * Constructor for the enum.
     * 
     * @param columnName the name of the column in the CSV file.
     */
    Columns(String columnName) {
        this.columnName = columnName;
    }

    /**
     * Getter for the column name.
     * 
     * @return the name of the column in the CSV file.
     */
    public String getColumnName() {
        return columnName;
    }

    /**
     * Get the enum from the column name.
     * 
     * @param columnName the name of the column in the CSV file.
     * @return the enum that matches the column name.
     */
    public static Columns fromColumnName(String columnName) {
        for (Columns col : Columns.values()) {
            if (col.getColumnName().equals(columnName)) {
                return col;
            }
        }
        throw new IllegalArgumentException("No column with name " + columnName);
    }

    /**
     * Get the enum from the enum name.
     * 
     * Can use the enum name or the column name. Useful for filters and sorts
     * as they can use both.
     * 
     * @param name the name of the enum.
     * @return the enum that matches the name.
     */
    public static Columns fromString(String name) {
        for (Columns col : Columns.values()) {
            if (col.name().equalsIgnoreCase(name) || col.getColumnName().equalsIgnoreCase(name)) {
                return col;
            }
        }
        throw new IllegalArgumentException("No column with name " + name);
    }
    
}
