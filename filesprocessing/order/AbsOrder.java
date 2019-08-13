package filesprocessing.order;

import java.io.File;

/**
 * A singleton object that compares two files, by their full name
 */
public class AbsOrder implements Order {
    /**  The only instance of the class */
    private static final AbsOrder order = new AbsOrder();

    /**
     * Private Constructor for the object.
     */
    private AbsOrder(){ }

    /**
     * A function that is access to the single instance of the class
     * @return The single AbsOrder instance
     */
    public static AbsOrder getAbsOrder(){
        return order;
    }

    /**
     * A method that determines the order between two files.
     * @param file1 file 1 For comparison
     * @param file2 file 2 For comparison
     * @return a negative integer, zero, or a positive integer as the first argument is
     *          less than, equal to, or greater than the second.
     */
    @Override
    public int getOrder(File file1, File file2) {
        return file1.getAbsolutePath().compareTo(file2.getAbsolutePath());
    }
}
