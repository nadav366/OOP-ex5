package filesprocessing;

import filesprocessing.order.*;

/**
 * A singleton object is a generator that generates the Sort that corresponds to the user's request
 */
class SortGenerator {
    /**  The only instance of the class */
    private final static SortGenerator instants = new SortGenerator();

    private final static String REVERSE = "REVERSE";

    /**  Flag save if user requested "REVERSE" on the order */
    private boolean revOrder;

    /**
     * Private Constructor for the object.
     */
    private SortGenerator(){ }

    /**
     * A function that returns the only instance of the class
     * @return instance of the class
     */
    static SortGenerator getInstans(){
        return instants;
    }

    /**
     * A function that activates the generator and returns the
     * appropriate sorter to the commands of the operator
     * @param splitString A string array containing the commands for making the sorter
     * @param lineInx Line number of commands (for reporting in case of error)
     * @return A sorter type object that matches commands
     * @throws TypeOneException If the commands are not valid
     */
    Sorter getSorter(String[] splitString, int lineInx) throws TypeOneException {
        revOrder = splitString[splitString.length - 1].equals(REVERSE);// Whether user requested REVERSE

        switch (splitString[0]){
            case "":     return new Sorter(AbsOrder.getAbsOrder());
            case "abs":  return new Sorter(DecoratorCheck(AbsOrder.getAbsOrder()));
            case "type": return new Sorter(DecoratorCheck(TypeOrder.getTypeOrder()));
            case "size": return new Sorter(DecoratorCheck(SizeOrder.getTypeOrder()));
        }
        throw new TypeOneException(lineInx); // The name does not match any of the valid dieters
    }

    /**
     * A function that checks whether the decorator should be activated
     * @param order The original order obj
     * @return Object filter, wrapped in REVERSE if needed
     */
    private Order DecoratorCheck(Order order){
        if (revOrder)
            return new ReverseDecorator(order);
        return order;
    }

    /**
     * Generator default, to run without parameters (in case of error)
     * @return Sorter by abs order
     */
    Sorter getSorter() {
        return new Sorter(AbsOrder.getAbsOrder());
    }
}
