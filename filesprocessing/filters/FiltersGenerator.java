package filesprocessing.filters;

import filesprocessing.TypeOneException;

/**
 * A singleton object is a generator that generates the filter that corresponds to the user's request
 */
public class FiltersGenerator {
    /**  The only instance of the class */
    private final static FiltersGenerator instance = new FiltersGenerator();

    private final static String NOT = "NOT";

    /**  Flag save if user requested "NOT" on the filter */
    private static boolean notFlag;

    /**
     * Private Constructor for the object.
     */
    private FiltersGenerator(){ }

    /**
     * A function that returns the only instance of the class
     * @return instance of the class
     */
    public static FiltersGenerator getInstance() {
        return instance;
    }

    /**
     * A function that runs the generator, receives the parameters and
     * returns the appropriate generator, if the parameters are normal...
     * @param splitString A string array containing the commands for making the filter
     * @param lineInx Line number of commands (for reporting in case of error)
     * @return A filter type object that matches commands
     * @throws TypeOneException If the commands are not valid
     */
    public MyFilter getCorFilter(String[] splitString, int lineInx) throws TypeOneException {
        try {
            notFlag = splitString[splitString.length -1].equals(NOT); // Whether user requested NOT

            switch (splitString[0]) {
                case "between":
                    return DecoratorCheck(getBetweenFilter(splitString));

                case "all":
                    validNumParameters(1, splitString);
                    return DecoratorCheck(new AllFilter());
            }
            validNumParameters(2, splitString);
            String arg = splitString[1];
            switch (splitString[0]){
                case "file":        return DecoratorCheck(new FileFilter(arg));
                case "contains":    return DecoratorCheck(new ContainsFilter(arg));
                case "prefix":      return DecoratorCheck(new PrefixFilter(arg));
                case "suffix":      return DecoratorCheck(new SuffixFilter(arg));
                case "greater_than":return DecoratorCheck(new GreaterThanFilter(convertDouble(arg)));
                case "smaller_than":return DecoratorCheck(new SmallerThanFilter(convertDouble(arg)));
                case "writable":    return DecoratorCheck(new WritableFilter(convertWord(arg)));
                case "executable":  return DecoratorCheck(new ExecutableFilter(convertWord(arg)));
                case "hidden":      return DecoratorCheck(new HiddenFilter(convertWord(arg)));
            }

            throw new TypeOneException(); // The name does not match any of the valid dieters

        } catch (TypeOneException ex){
            ex.setLineIndex(lineInx);
            throw ex;
        } catch (ArrayIndexOutOfBoundsException ex){
            throw new TypeOneException(lineInx);
        }
    }

    /**
     * A function that checks whether the decorator should be activated
     * @param orgFilter The original filter
     * @return Object filter, wrapped in NOT if needed
     */
    private MyFilter DecoratorCheck(MyFilter orgFilter){
        if (notFlag)
            return new DecoratorNotFilter(orgFilter);
        return orgFilter;
    }

    /**
     * Generator default, to run without parameters (in case of error)
     * @return All filter
     */
    public MyFilter getCorFilter(){
        return new AllFilter();
    }

    /**
     * Metoda handles all the tests and conversions in order to get a between filter
     * @param splitString A string array containing the commands for making the filter
     * @return Filter object, according to the parameters received
     * @throws TypeOneException If the commands are not valid
     */
    private MyFilter getBetweenFilter(String[] splitString) throws TypeOneException {
        validNumParameters(3, splitString); //Number of parameters

        // Conversion to number
        double smallPar = convertDouble(splitString[1]);
        double bigPar = convertDouble(splitString[2]);

        // Check the correct order
        if (smallPar > bigPar)
            throw new TypeOneException();
        return new BetweenFilter(smallPar, bigPar);
    }

    /**
     * A function that checks the number of parameters received is valid
     * @param parNum Nlegal parameters number
     * @param splitString A string array containing the commands for making the filter
     * @throws TypeOneException If the number of parameters received does not match the desired
     */
    private void validNumParameters(int parNum, String[] splitString) throws TypeOneException{
        if (splitString[splitString.length-1].equals(NOT)) {
            if (splitString.length != parNum + 1)
                throw new TypeOneException();
        } else
            if (splitString.length != parNum)
                throw new TypeOneException();

    }

    /**
     * A method that converts a string to a non-negative double number
     * @param parm String to be converted
     * @return A non-negative double number
     * @throws TypeOneException If the string does not have a non-negative number
     */
    private double convertDouble(String parm) throws TypeOneException{
        double digit = Double.parseDouble(parm);
        if (digit < 0)
            throw new TypeOneException();
        return digit;
    }

    /**
     * A method that converts from "Yes" - "No" String to Boolean
     * @param parm String to be converted
     * @return Boolean value
     * @throws TypeOneException If the string does not have "Yes" or "No"
     */
    private static Boolean convertWord(String parm) throws TypeOneException{
        if (parm.equals("YES"))
            return true;
        if (parm.equals("NO"))
            return false;
        throw new TypeOneException();
    }
}
