package filesprocessing.filters;

import java.io.File;

/**
 * An object that is a filter that checks whether the file name contains a given string
 */
public class ContainsFilter implements MyFilter {
    private String contName;

    /**
     * Constructor, saves the filter data.
     * @param contName String to check whether it appears in the file name
     */
    ContainsFilter(String contName) {
        this.contName = contName;
    }

    /**
     * A function that accepts a file, and checks to insert it according to filter conditions
     * @param fileToCheck Check file
     * @return true if it should be entered, otherwise false
     */
    @Override
    public boolean insertFile(File fileToCheck) {
        return fileToCheck.getName().contains(contName);
    }
}
