import java.util.*;

public class A_P_ProjectMethods
{

	/**
	 * @param fileScnr - The instance of the scanner object that is used to read the
	 *                 lottery .csv file.
	 * @return An array that contains the numbers of the next series as read from the
	 * file.
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
		/*
		 * Create an empty string to store the formatted ticket numbers.
		 *
		 * Iterate through the ticket numbers array and add each number to the
		 * string. If the current number is not the last number in the array, add a
		 * comma and a space after the number.
		 */
		StringBuilder formattedTicketNumbers = new StringBuilder();
		boolean isLastIndex;
		for (int i = 0; i < ticketNumbers.length; i++)
		{
			isLastIndex = i == ticketNumbers.length - 1;
			formattedTicketNumbers.append(ticketNumbers[i])
					.append(isLastIndex ? "" : ", ");
		}
		return formattedTicketNumbers.toString();
	}

	/**
	 * @return The amount of matching numbers
	 */
	public static int countMatchingNumbers(int[] ticketNumbers, int[] winningNumbers)
	{
		// @todo Sanity check, ticket numbers and winning numbers should be the same length.

		// Create a variable to store the count of matching numbers.
		int matchingNumbersCount = 0;

		for (int ticketNumber : ticketNumbers)
		{
			for (int winningNumber : winningNumbers)
			{
				if (ticketNumber == winningNumber) {
					// if there is a match between this number and one of the winning numbers, stop this inner loop
					matchingNumbersCount += 1;
					break;
				} // end if
			} // end inner for
		} // end outer for

		return matchingNumbersCount;
	}

}
