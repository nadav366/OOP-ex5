package filesprocessing.filters;

import java.io.File;

/**
 * An object that is a filter to check if the file is Executable
 */
public class ExecutableFilter implements MyFilter {
    private Boolean condition;

    /**
     * Constructor, saves the filter data.
     * @param condition Boolean hold if we interested in Executable files or not.
     */
    ExecutableFilter(Boolean condition) {
        this.condition = condition;
    }

    /**
     * A function that accepts a file, and checks to insert it according to filter conditions
     * @param fileToCheck Check file
     * @return true if it should be entered, otherwise false
     */
    @Override
    public boolean insertFile(File fileToCheck) {
        return condition.equals(fileToCheck.canExecute());
    }
}
