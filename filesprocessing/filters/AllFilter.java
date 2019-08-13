package filesprocessing.filters;

import java.io.File;

/**
 * An object that is a filter that returns everything
 */
public class AllFilter implements MyFilter {

    /**
     * A function that accepts a file, and checks to insert it according to filter conditions
     * @param fileToCheck Check file
     * @return true if it should be entered, otherwise false
     */
    @Override
    public boolean insertFile(File fileToCheck) {
        return true;
    }
}
