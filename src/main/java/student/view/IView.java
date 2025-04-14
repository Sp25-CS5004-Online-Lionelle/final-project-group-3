package student.view;

import java.util.Collection;

public interface IView {
    
    /*
     * This method is used to display a collection of items and its subsets(Including the favorite list, sorted and filtered lists)
     * @param collection The collection of items to be displayed
     */
    void displayCollection(Collection collection);

    /*
     * This method is used to display a message to the user(e.g., error message, success message)
     * @param item The item to be displayed
     */
    void displayMessage(String Message, boolean isError);

    /*
     * This method is used to display a show Filter JOptionPane
     */
    void displayFilterPopup();

    /*
     * This method is used to display a show Sort JOptionPane
     */
    void displaySortPopup();

    /*
     * This method is used to display a show Favorite JOptionPane
     */
    void displaySavePopup();
}
