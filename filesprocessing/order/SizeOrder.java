package filesprocessing.order;

import java.io.File;

/**
 * A singleton object that compares two files, according to their size in the case of equality, by name
 */
public class SizeOrder implements Order {
    /**  The only instance of the class */
    private static final SizeOrder order = new SizeOrder();

    /**
     * Private Constructor for the object.
     */
    private SizeOrder(){ }

    /**
     * A function that is access to the single instance of the class
     * @return The single SizeOrder instance
     */
    public static SizeOrder getTypeOrder(){
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
        int a;
        if ((a = Long.compare(file1.length(), file2.length())) != 0) // A case of inequality
            return a;
        return AbsOrder.getAbsOrder().getOrder(file1,file2);
    }
}
