package filesprocessing.order;

import java.io.File;

/**
 * A singleton object that compares two files, according to their file type in the case of equality, by name
 */
public class TypeOrder implements Order {
    /**  The only instance of the class */
    private static final TypeOrder order = new TypeOrder();

    /**
     * Private Constructor for the object.
     */
    private TypeOrder(){ }

    /**
     * A function that is access to the single instance of the class
     * @return The single TypeOrder instance
     */
    public static TypeOrder getTypeOrder(){
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
        if ((a = getType(file1).compareTo(getType(file2))) != 0) // A case of inequality
            return a;
        return AbsOrder.getAbsOrder().getOrder(file1,file2);
    }

    /**
     * A method that extracts a file from its type
     * @param file1 A file to check its file type
     * @return The resulting file type, A empty string if the file type is not recognized
     */
    private static String getType(File file1) {
        String fileName = file1.getName();
        StringBuffer typeName = new StringBuffer();
        for (int i = fileName.length()-1; i > 0; i--){
            if (fileName.charAt(i) == '.')
                return typeName.toString();
            typeName.insert(0,fileName.charAt(i));
        }
        return "";
    }
}
