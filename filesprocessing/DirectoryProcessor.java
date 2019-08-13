package filesprocessing;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class is a platform for handling files.
 * The program receives a path to a folder and a instruction file,
 * and displays the desired files in the correct order.
 */
public class DirectoryProcessor {

    /**  Constants. Represents a title in the instructions document         */
    private final static String FILTER = "FILTER";
    private final static String ORDER = "ORDER";


    /**  An array that contains all the files in the target folder             */
    private static ArrayList<File> filesArr;

    /**
     * Main function.
     * Receives a path to the destination folder and path to the instructions file, and runs the software.
     * @param args An array of strings of 2, which contains paths to files.
     */
    public static void main(String[] args){
        try {
            if (args.length != 2) // Checks that you have received exactly two arguments from the user
                throw new TypeTwoException.Parmrter(args.length);

            setFileArr(args[0]); // Initializing the file list

            Scanner scnCommanFile = getScanner(args[1]); // Initializes an object to read the instruction file

            // Creates a list containing all the instructions, each line in the cell.
            ArrayList<String> allCommand = new ArrayList<>();
            while (scnCommanFile.hasNextLine())
                allCommand.add(scnCommanFile.nextLine());

            // Creates a list of all sections and activates them one by one.
            for (Section oneSec: getSections(allCommand))
                oneSec.operateSection(filesArr);

        }catch (TypeTwoException ex) {
            System.err.println(ex.toString());
        }
    }

    /**
     * A function that creates a scanner object, to read the instructions file.
     * @param filePath Path to instruction file
     * @return Object Scanner, which leads to the instruction file
     * @throws TypeTwoException If there is a problem reading the file, the error will contain the description.
     */
    private static Scanner getScanner(String filePath ) throws TypeTwoException {
        Scanner scnCommanFile;
        try {
            scnCommanFile = new Scanner(new File(filePath));
        } catch (IOException IOEx){
            throw new  TypeTwoException(IOEx);
        }
        return scnCommanFile;
    }

    /**
     * A function that creates all the Sections and saves them in the array.
     * To check that the file is valid, first create all the Sections, before activation.
     * @param allCommand An array of strings containing all the commands in the file
     * @return An array of sections for all file commands
     * @throws TypeTwoException In case of file structure error or lack of sub-section.
     */
    private static ArrayList<Section> getSections(ArrayList<String> allCommand) throws TypeTwoException {
        ArrayList<Section> allSection= new ArrayList<>();
        int i = 0;
        try {
            while (i < allCommand.size()) {
                Section newSec = new Section();

                // Checks that there is a FILTER sub-section  and if it is add it to the section object
                if (!allCommand.get(i).equals(FILTER))
                    throw new TypeTwoException.FilterMissing();
                newSec.setFilterString(allCommand.get(i + 1), i + 2);

                 //// Checks that there is a ORDER sub-section
                if (!allCommand.get(i + 2).equals(ORDER))
                    throw new TypeTwoException.OrderMissing();

                // Checks for information for ORDER, and in any case adds it to the object
                if ((i + 3 >= allCommand.size()) || (allCommand.get(i + 3).equals(FILTER))) {
                    newSec.setOrderString("", -1);
                    i += 3; // There is no information, so the  sub-section length is 3
                } else {
                    newSec.setOrderString(allCommand.get(i + 3), i + 4);
                    i += 4;
                }
                allSection.add(newSec);
            }
        } catch (IndexOutOfBoundsException ex){
            throw new TypeTwoException.Structure();
        }
        return allSection;
    }

    /**
     * A function that initializes the file list that has a resulting path
     * @param folderPath Path to the files folder
     */
    private static void setFileArr(String folderPath) {
        File sourceDir = new File(folderPath);
        filesArr = new ArrayList<>();
        for (File oneFile : sourceDir.listFiles())
            if (oneFile.isFile()) // Checks that the file is a file, not a folder
                filesArr.add(oneFile);
    }
}
