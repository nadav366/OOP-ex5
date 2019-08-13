package filesprocessing.filters;

import java.io.File;

/**
 * An object that is a filter that returns If the file size is among the data values
 */
public class BetweenFilter implements MyFilter {
    private double smallPar;
    private double bigPar;

    private static final double ToKB = 1024;  //Final variable converted to KB

    /**
     * Constructor, saves the filter data.
     * @param small Lower limit for file size
     * @param big Upper limit for file size
     */
    BetweenFilter (double small, double big){
        smallPar = small;
        bigPar = big;
    }

    /**
     * A function that accepts a file, and checks to insert it according to filter conditions
     * @param fileToCheck Check file
     * @return true if it should be entered, otherwise false
     */
    @Override
    public boolean insertFile(File fileToCheck) {
        return (((fileToCheck.length()/ToKB) >= smallPar) && ((fileToCheck.length()/ToKB) <= bigPar));
    }
}
