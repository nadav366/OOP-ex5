package filesprocessing.filters;

import java.io.File;

/**
 * An object that surrounds a filter, and turns its socket, in case the user requested "NOT"
 */
public class DecoratorNotFilter implements MyFilter {
    private MyFilter orgFilter;  // A variable that holds the original filter object

    /**
     * constructor. Gets a filter object and saves it.
     * @param filter original filter object
     */
    DecoratorNotFilter(MyFilter filter){
        orgFilter = filter;
    }

    /**
     * A function that accepts a file, and checks to insert it according to filter conditions
     * @param fileToCheck Check file
     * @return true if it should be entered, otherwise false
     */
    @Override
    public boolean insertFile(File fileToCheck) {
        return !orgFilter.insertFile(fileToCheck);
    }
}
