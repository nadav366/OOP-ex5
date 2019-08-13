package filesprocessing.order;

import java.io.File;

/**
 * interface that is the basis for an object that determines the order of files.
 */
public interface Order {

    /**
     * A method that determines the order between two data files.
     * @param file1 file 1 For comparison
     * @param file2 file 2 For comparison
     * @return a negative integer, zero, or a positive integer as the first argument is
     *          less than, equal to, or greater than the second.
     */
    int getOrder(File file1, File file2);
}
