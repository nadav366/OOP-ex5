package filesprocessing.filters;

import java.io.File;

public class SuffixFilter implements MyFilter {
    private String sufName;

    /**
     * Constructor, saves the filter data.
     * @param sufName
     */
    SuffixFilter(String sufName) {
        this.sufName = sufName;
    }

    /**
     * A function that accepts a file, and checks to insert it according to filter conditions
     * @param fileToCheck Check file
     * @return true if it should be entered, otherwise false
     */
    @Override
    public boolean insertFile(File fileToCheck) {
        return fileToCheck.getName().endsWith(sufName);
    }
}
