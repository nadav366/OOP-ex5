package filesprocessing.filters;

import java.io.File;

/**
 * interface that unifies all filters
 */
public interface MyFilter {

    /**
     * A function that accepts a file, and checks to insert it according to filter conditions
     * @param fileToCheck
     * @return
     */
    boolean insertFile(File fileToCheck);
}
