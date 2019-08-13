package filesprocessing;

import filesprocessing.filters.FiltersGenerator;
import filesprocessing.filters.MyFilter;

import java.io.File;
import java.util.ArrayList;

/**
 * An object is a Section in the command file.
 * Saves the commands and knows to execute them on a list of files.
 */
class Section {

    /**  A variable that preserves the character that separates parameters from each line  */
    private final static String SEPARATOR = "#";


    /** Variables that keep the commands in the Section     */
    private String filterString;
    private String orderString;

    /** Variables that keep the command line numbers     */
    private int filterIndex;
    private int orderIndex;

    /**
     * A method that keeps the command which filter is required, and its row number.
     * @param filterString String, filter command.
     * @param lineNum The row number of the filter command
     */
    void setFilterString(String filterString, int lineNum){
        this.filterString = filterString;
        this.filterIndex = lineNum;
    }

    /**
     * A method that keeps the command which order is required, and its row number.
     * @param orderString String, order command. (Empty string for default)
     * @param lineNum The row number of the order command (-1 for default)
     */
    void setOrderString(String orderString, int lineNum){
        this.orderString = orderString;
        this.orderIndex = lineNum;
    }

    /**
     * Function that activates the section.
     * That is, run the filter, sort by order and print the result.
     * @param filesArr An array of files to sort and print.
     */
    void operateSection(ArrayList<File> filesArr) {
        ArrayList<File> filterArr = new ArrayList<>();

        MyFilter filterObj = getFilterObj();

        // Check each file whether it should be inserted according to the filter
        for(File oneFile : filesArr)
            if (filterObj.insertFile(oneFile))
                filterArr.add(oneFile);

        // Sorts the files in the order saved in the order object
        filterArr = getSorterObj().mySort(filterArr);

        // Prints the file names in order
        for (File oneFile : filterArr)
            System.out.println(oneFile.getName());
    }

    /**
     * A function that returns a filter object, according to the saved command.
     * If the command is invalid, prints an error and returns a default object
     * @return A suitable object filter
     */
    private MyFilter getFilterObj(){
        // Separates the command into an array of strings
        String[] splitString = this.filterString.split(SEPARATOR);

        MyFilter filterObj;
        try {
            filterObj = FiltersGenerator.getInstance().getCorFilter(splitString, filterIndex);
        } catch (TypeOneException ex){
            filterObj = FiltersGenerator.getInstance().getCorFilter();
            System.err.println(ex.toString());
        }
        return filterObj;
    }

    /**
     * A function that returns a Sort object, according to the saved command.
     * If the command is invalid, prints an error and returns a default object
     * @return A suitable object Sort
     */
    private Sorter getSorterObj() {
        // Separates the command into an array of strings
        String[] splitString = this.orderString.split(SEPARATOR);

        Sorter SorterObj;
        try {
            SorterObj = SortGenerator.getInstans().getSorter(splitString, orderIndex);
        } catch (TypeOneException ex){
            SorterObj = SortGenerator.getInstans().getSorter();
            System.err.println(ex.toString());
        }
        return SorterObj;
    }
}
