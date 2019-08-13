package filesprocessing.filters;

import java.io.File;

/**
 * An object that is a filter if the file size is above a certain number
 */
public class GreaterThanFilter implements MyFilter {

    private static final double ToKB = 1024;  //Final variable converted to KB
    private double greaterPar;

    /**
     * Constructor, saves the filter data.
     * @param par Compare file size (in KB)
     */
    GreaterThanFilter (double par){
        greaterPar = par;
    }

    /**
     * A function that accepts a file, and checks to insert it according to filter conditions
     * @param fileToCheck Check file
     * @return true if it should be entered, otherwise false
     */
    @Override
    public boolean insertFile(File fileToCheck) {
        return (fileToCheck.length()/ToKB) > greaterPar;
    }
}
