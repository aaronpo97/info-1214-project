import java.util.*;

public class A_P_ProjectMethods
{
    /**
     * 
     * @param fileScnr - The instance of the scanner object that is used to read the
     *                 lottery .csv file.
     * @return An array of length 6 that contains the numbers of the next series as
     *         read from the file.
     */
    public static int[] getNextSeries(Scanner fileScnr)
    {
        // read each line
        String line = fileScnr.nextLine();
        String[] split = line.split(",");

        // convert the string array to an int array
        int[] series = new int[split.length];
        for (int i = 0; i < split.length; i++)
        {
            series[i] = Integer.parseInt(split[i]);
        }

        // return the int array
        return series;
    }

    /**
     * This method creates a formatted string that delimits the ticket numbers with
     * commas.
     * 
     * @param ticketNumbers - An array of length 6 that contains ticket numbers.
     * @return The formatted string.
     */
    public static String formatTicketNumbers(int[] ticketNumbers)
    {
        /**
         * Create an empty string to store the formatted ticket numbers.
         * 
         * Iterate through the ticket numbers array and add each number to the string.
         * If the current number is not the last number in the array, add a comma and a
         * space after the number.
         */
        String formattedTicketNumbers = "";
        boolean isLastIndex;
        for (int i = 0; i < ticketNumbers.length; i++)
        {
            isLastIndex = i == ticketNumbers.length - 1;
            formattedTicketNumbers += ticketNumbers[i] + (isLastIndex ? "" : ", ");
        }
        return formattedTicketNumbers;
    }

}
