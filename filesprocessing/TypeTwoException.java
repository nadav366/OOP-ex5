package filesprocessing;

/**
 * Exception of the second type..
 */
public class TypeTwoException extends Throwable {
    /**  The basic error message        */
    private final static String ERROR = "ERROR: ";

    /**  External Exception Object Error Message.   */
    private String originalMsg;

    /**
     * Constructor with external exception.
     * @param orgEx external exception.
     */
    TypeTwoException(Exception orgEx){
        this.originalMsg = orgEx.toString();
    }

    /**
     * Default constructor. Without external exception.
     */
    TypeTwoException(){
        this.originalMsg = "";
    }

    /**
     * A function that prints the complete error message.
     * @return Error Message
     */
    @Override
    public String toString(){
        return ERROR + originalMsg;
    }

    /**
     * Internal object for error for filter sub-section lack
     */
    public static class FilterMissing extends TypeTwoException {
        private final static String FILTER_MISSING_ERROR = "FILTER sub-section missing";

        /**
         * A function that prints the complete error message.
         * @return Error Message
         */
        @Override
        public String toString(){
            return super.toString() + FILTER_MISSING_ERROR;
        }

    }

    /**
     * Internal object for error for order sub-section lack
     */
    public static class OrderMissing extends TypeTwoException {
        private final static String ORDER_MISSING_ERROR = "ORDER sub-section missing";

        /**
         * A function that prints the complete error message.
         * @return Error Message
         */
        @Override
        public String toString(){
            return super.toString() + ORDER_MISSING_ERROR;
        }
    }

    /**
     * Internal object for an error for some wrong parameters.
     */
    public static class Parmrter extends TypeTwoException {
        private final static String PARMETER_ERROR = "Two parameters are required, received ";
        private int numOfPar;

        /**
         * constructor. Sears the number of parameters received from the user.
         * @param NumOfParmeters number of parameters received from the user.
         */
        Parmrter(int NumOfParmeters){
            numOfPar = NumOfParmeters;
        }

        /**
         * A function that prints the complete error message.
         * @return Error Message
         */
        @Override
        public String toString(){
            return super.toString() + PARMETER_ERROR + numOfPar;
        }
    }

    /**
     * Internal object for Error in command structure
     */
    public static class Structure extends TypeTwoException {
        private final static String FILTER_MISSING_ERROR = "FILTER sub-section missing";

        /**
         * A function that prints the complete error message.
         * @return Error Message
         */
        @Override
        public String toString(){
            return super.toString() + FILTER_MISSING_ERROR;
        }
    }
}

