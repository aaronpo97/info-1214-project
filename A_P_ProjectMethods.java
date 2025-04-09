/**
 * Program Name: A_P_ProjectMethods.java
 * Purpose: A helper class consisting of static methods for use in A_P_LotteryPrizes.java
 * Date: 09-April-2025
 * Name: Aaron Po
 */

import java.util.*;

public class A_P_ProjectMethods
{
	/* ----------------------------------------------------------------------------- */
	/* ----------------------------------------------------------------------------- */

	/**
	 * Method Name: printAppDescription() <br>
	 * Purpose: This method prints the app name, and a random description. <br>
	 * Accepts: The app name, an array of random lottery descriptions, and a line separator string. <br>
	 * Returns: N/A <br>
	 * Coder: Aaron Po <br>
	 * Date: 09-April-2025 <br>
	 */
	public static void printAppDescription(final String APP_NAME,
			final String[] LOTTERY_DESCRIPTIONS, final String LINE_SEPARATOR)
	{
		// choose a random index out of LOTTERY_DESCRIPTIONS
		int rand = (int) (Math.random() * (LOTTERY_DESCRIPTIONS.length));

		System.out.println(LINE_SEPARATOR + "\n");
		System.out.println(APP_NAME);
		// choose random description
		System.out.println(LOTTERY_DESCRIPTIONS[rand] + "\n");
		System.out.println(LINE_SEPARATOR + "\n");
	} // END METHOD

	/* ----------------------------------------------------------------------------- */
	/* ----------------------------------------------------------------------------- */

	/**
	 * Method Name: getNextSeries() <br>
	 * Purpose: This method takes a Scanner object and gets each series in the list
	 * Accepts: A Scanner object to read the file. <br>
	 * Returns: An int array of ticket numbers. <br>
	 * Coder: Aaron Po <br>
	 * Date: 09-April-2025 <br>
	 */
	public static int[] getNextSeries(Scanner fileScnr)
	{
		// read each line
		String line = fileScnr.nextLine();
		// Create a string array using .split(), with the delim. set to a comma
		String[] split = line.split(",");
		// Create an int array to store the numbers
		//
		int[] series = new int[split.length];
		// Iterate through the string array
		for (int i = 0; i < split.length; i++)
		{
			// parse the integer from the value in the split arr.
			// if the parse doesn't work (invalid integer in the csv file, then catch
			try
			{
			series[i] = Integer.parseInt(split[i].trim());
			}
			catch (NumberFormatException e)
			{
				// give a warning
				System.out.println("\tWARNING: NumberFormatException thrown, using -1 as value");
				System.out.printf("\tInvalid line: %s", line);
				series[i] = -1; // if the value is invalid, then just put a -1
			} // end try catch
		} // END FOR

		// return the int array
		return series;
	} // END METHOD

	/* ----------------------------------------------------------------------------- */
	/* ----------------------------------------------------------------------------- */

	/**
	 * Method Name: formatTicketNumbers() <br>
	 * Purpose: A static class method that formats the ticket numbers into a
	 * 					string. <br>
	 * Accepts: An int array of ticket numbers. <br>
	 * Returns: A string representation of the ticket numbers, separated by commas.
	 * <br>
	 * Coder: Aaron Po <br>
	 * Date: 09-April-2025 <br>
	 */
	public static String formatTicketNumbers(int[] ticketNumbers)
	{
		// create a string builder instance for our formatted string
		StringBuilder formattedTicketNumbers = new StringBuilder();

		// iterate through the ticketNumbers array
		for (int i = 0; i < ticketNumbers.length; i++)
		{
			formattedTicketNumbers
					.append(ticketNumbers[i]) // append the ticket number to the string builder
					.append(i == ticketNumbers.length - 1
							? ""
							: ", "); // add a comma to all but the last entry
		} // END FOR

		// return the formatted ticket numbers as a string
		return formattedTicketNumbers.toString();
	} // END METHOD


	/* ----------------------------------------------------------------------------- */
	/* ----------------------------------------------------------------------------- */

	/**
	 * Method Name: countMatchingNumbers() <br>
	 * Purpose: A static class method that counts the number of matching numbers
	 * 					between a ticket and winning numbers. <br>
	 * Accepts: Two int arrays, one for the ticket numbers and one for the winning
	 * 					numbers. <br>
	 * Returns: The number of matching numbers. <br>
	 * Coder: Aaron Po <br>
	 * Date: 09-April-2025 <br>
	 */
	public static int countMatchingNumbers(int[] ticketNumbers, int[] winningNumbers)
	{
		// Check if both arrays are the same length, if they're not return -1 to indicate error
		if (ticketNumbers.length != winningNumbers.length)
		{
			System.out.println("\tWARNING: One ticket has a different amount of ticket numbers than the winning numbers. " +
												"This is invalid. Returning -1 as matching numbers count.");

			System.out.println("\n\tINVALID TICKET NUMBER: ");
			System.out.print("\t");
			for (int number: ticketNumbers)
			{
				System.out.print(number + " ");
			}
			System.out.println("\n\n\tWINNING NUMBERS: ");
			System.out.print("\t");
			for (int number: winningNumbers)
			{
				System.out.print(number + " ");
			}
			return -1; // return a negative integer, this would not be possible in normal circumstances
		} // END IF

		// create a variable to store the count of matching numbers
		int matchingNumbersCount = 0;

		/*
		 * Iterate through the ticketNumbers array, and compare each value with the winningNumbers array.
		 */
		for (int ticketNumber : ticketNumbers)
		{
			for (int winningNumber : winningNumbers)
			{
				if (ticketNumber == winningNumber)
				{
					// if there is a match between this number and one of the winning numbers, stop
					// this inner loop
					matchingNumbersCount += 1;
					break;
				} // end if
			} // end inner for
		} // end outer for

		return matchingNumbersCount;
	} // END METHOD

	/* ----------------------------------------------------------------------------- */
	/* ----------------------------------------------------------------------------- */

	/**
	 * Method Name: printTierStats() <br>
	 * Purpose: A static class method that prints statistical information for each prize tier.<br>
	 * Accepts: The number of winners, the percentage of the prize pool, the total
	 * 					prize value, and the prize per ticket. <br>
	 * Returns: N/A <br>
	 * Coder: Aaron Po <br>
	 * Date: 09-April-2025 <br>
	 */
	public static void printTierStats(int numWinners, double prizePoolPercent,
			double totalPrizeValue, double prizePerTicket)
	{
		System.out.printf("\nNumber of winners:\t\t%d\n", numWinners);
		System.out.printf("Percent of prize pool:\t%.2f%%\n", prizePoolPercent * 100.0);
		System.out.printf("Total prize value:\t\t$%,.2f\n", totalPrizeValue);
		System.out.printf("Prize per ticket:\t\t$%,.2f\n\n", prizePerTicket);
	} // END METHOD

	/* ----------------------------------------------------------------------------- */
	/* ----------------------------------------------------------------------------- */

	/**
	 * Method Name: listWinners() <br>
	 * Purpose: This method takes an array list of winning numbers and creates a formatted output.<br>
	 * Accepts: Takes an ArrayList of int arrays which represent the winners
	 * Returns: N/A <br>
	 * Coder: Aaron Po <br>
	 * Date: 09-April-2025 <br>

	 */
	public static void listWinners(ArrayList<int[]> winners)
	{
		String temp = "";
		System.out.print("Ticket Numbers:\t");
		for (int i = 0; i < winners.size(); i += 1)
		{
			if (i == 0)
			{
				// Print a tab for the first item
				System.out.print("\t\t");
			} // END IF

			// Print an additional tab if the index is odd
			if (i % 2 != 0)
			{
				System.out.print("\t");
			} // END IF

			/// Format the ticket numbers using formatTicketNumbers and then print it with printf()
			temp = A_P_ProjectMethods.formatTicketNumbers(winners.get(i));
			System.out.printf("%-30s", temp); // adding padding whitespace

			// Add extra tabs if the index is odd
			if (i % 2 != 0)
			{
				System.out.println();
				System.out.print("\t\t\t\t\t\t");
			} // END IF
		} // END FOR LOOP
	}// END METHOD

} // END CLASS
