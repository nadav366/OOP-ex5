package filesprocessing;

/**
 * Exception of the first type.
 * When there is an invalid command within one of the rows.
 */
public class TypeOneException extends Throwable {
    /**  Error printing message    */
    private final static String WARNING = "Warning in line ";

    /**   A variable that keeps the line number of the problem  */
    private int lineNum;

    /**
     * Default constructor, no line number.
     */
    public TypeOneException(){

    }
    /**
     * constructor. Saves the resulting line number
     * @param lineNum problem line number
     */
    public TypeOneException(int lineNum){
        this.lineNum = lineNum;
    }


    /**
     * Function that edits the row number
     * @param lineIndex problem line number
     */
    public void setLineIndex(int lineIndex){
        this.lineNum = lineIndex;
    }

    /**
     * A function that prints the complete error message.
     * @return Error Message
     */
    @Override
    public String toString(){
        return WARNING + this.lineNum;
    }
}
