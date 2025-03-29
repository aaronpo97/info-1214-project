import java.util.*;

public class A_P_ProjectMethods
{
	/* ----------------------------------------------------------------------------- */
	/* ----------------------------------------------------------------------------- */

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
	}

	/* ----------------------------------------------------------------------------- */
	/* ----------------------------------------------------------------------------- */

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
			series[i] = Integer.parseInt(split[i].trim());
		} // END FOR

		// return the int array
		return series;
	}

	/* ----------------------------------------------------------------------------- */
	/* ----------------------------------------------------------------------------- */

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
		} // END FOR
		return formattedTicketNumbers.toString();
	}

	/* ----------------------------------------------------------------------------- */
	/* ----------------------------------------------------------------------------- */

	/**
	 * @return The amount of matching numbers
	 */
	public static int countMatchingNumbers(int[] ticketNumbers, int[] winningNumbers)
	{
		// Sanity check, ticket numbers and winning numbers should be the same length.

		if (ticketNumbers.length != winningNumbers.length)
		{
			return -1; // return a negative integer, this would not be possible in normal circumstances
		} // END IF

		// Create a variable to store the count of matching numbers.
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
	 * Method Name:
	 * Purpose:
	 * Accepts:
	 * Returns:
	 * Date: 26-March-2025
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
	 * Purpose: A static class method that iterates through an array list of winning
	 * 					numbers and prints formatted output. <br>
	 * Accepts: <br>
	 * Returns: <br>
	 * Date: 26-March-2025 <br>
	 */
	public static void listWinners(ArrayList<int[]> winners)
	{
		String temp = "";
		System.out.print("Ticket Numbers:\t");
		for (int i = 0; i < winners.size(); i += 1)
		{
			if (i == 0)
			{
				// Print initial tab only for the first item.
				System.out.print("\t\t");
			} // END IF

			// If it's an odd index, print an additional tab for alignment.
			if (i % 2 != 0)
			{
				System.out.print("\t");
			} // END IF

			// Format the ticket number for the winner.
			temp = A_P_ProjectMethods.formatTicketNumbers(winners.get(i));
			System.out.printf("%-30s", temp);

			// If it's an even index, add a tab after the ticket number for alignment.
			if (i % 2 != 0)
			{
				// Print a newline for odd index after each ticket number.
				System.out.println();
				System.out.print("\t\t\t\t\t\t");
			} // END IF
		} // END FOR LOOP
	}// END METHOD

} // END CLASS
