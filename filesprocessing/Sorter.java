package filesprocessing;

import filesprocessing.order.Order;
import java.io.File;
import java.util.ArrayList;

/**
 * An object that sorts a list of files according to an object that determines its order.
 * Based on a "quick sorting" algorithm.
 */
class Sorter {
    private Order ord;

    /**
     * constructor. Saves the order object.
     * @param ord
     */
    Sorter(Order ord){
        this.ord = ord;
    }

    /**
     * Function that sorts a file list
     * @param list Unlisted file list
     * @return Sorted list files
     */
    ArrayList<File> mySort(ArrayList<File> list){
        ArrayList<File> small = new ArrayList<>();
        ArrayList<File> big = new ArrayList<>();

        if (list.size() <= 1) // Stop conditions, the list is sorted
            return list;

        int pivotIndex = list.size()/2;  // Set pivot at the center of the list (arbitrarily)
        File pivotFile = list.get(pivotIndex);
        list.remove(pivotIndex);

        // Divides the list into two lists - large and small from the pivot
        for (File oneFile : list)
            if ((ord.getOrder(pivotFile, oneFile)) >= 0)
                small.add(oneFile);
             else
                big.add(oneFile);

        // Sorts both lists separately
        small = mySort(small);
        big = mySort(big);
        // connect the lists and the pivot
        small.add(pivotFile);
        small.addAll(big);
        return small;
    }
}