package filesprocessing.filters;

import java.io.File;

public class PrefixFilter implements MyFilter {
    private String preName;

    /**
     * Constructor, saves the filter data.
     * @param preName
     */
    PrefixFilter(String preName) {
        this.preName = preName;
    }

    /**
     * A function that accepts a file, and checks to insert it according to filter conditions
     * @param fileToCheck Check file
     * @return true if it should be entered, otherwise false
     */
    @Override
    public boolean insertFile(File fileToCheck) {
        return fileToCheck.getName().startsWith(preName);
    }
}
