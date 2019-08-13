package filesprocessing.order;

import java.io.File;

/**
 * An object that envelops an object of order, and turns its answers
 */
public class ReverseDecorator implements Order {
    /**  The original order object  */
    private Order orgOrder;

    /**
     * constructor. Saves the original object
     * @param order The original order object
     */
    public ReverseDecorator(Order order){
        orgOrder = order;
    }

    /**
     * A method that determines the order between two data files.
     * @param file1 file 1 For comparison
     * @param file2 file 2 For comparison
     * @return a negative integer, zero, or a positive integer as the first argument is
     *          less than, equal to, or greater than the second.
     */
    @Override
    public int getOrder(File file1, File file2) {
        return orgOrder.getOrder(file1,file2)*(-1);
    }
}
