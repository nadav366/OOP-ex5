package filesprocessing.filters;

import java.io.File;

/**
 * An object that is a filter if the file name is a given string
 */
public class FileFilter implements MyFilter {
    private String neme;

    /**
     * Constructor, saves the filter data.
     * @param name A string to compare is the name of the file
     */
    FileFilter(String name) {
        this.neme = name;
    }

    /**
     * A function that accepts a file, and checks to insert it according to filter conditions
     * @param fileToCheck Check file
     * @return true if it should be entered, otherwise false
     */
    @Override
    public boolean insertFile(File fileToCheck) {
        return fileToCheck.getName().equals(neme);
    }
}
