
/*
 * Program Name: A_P_LotteryPrizes
 *
 * Purpose: This is an application that analyzes ticket number data
 * 					contained in a data file to determine which ticket
 * 					holders have won a prize, how many will have to share a
 * 					prize, and how much money they will receive.
 *
 * Coder: Aaron Po - 1305057
 *
 * Date: April 9, 2025
 */

import java.util.*;
import java.io.*;

public class A_P_LotteryPrizes
{
	public static void main(String[] args)
	{
		// Setup the scanner object
		Scanner input = new Scanner(System.in);

		/*
		 * Set up variables to store the title and a set of descriptions for the app.
		 */
		final String APP_NAME = "AARON'S LOTTERY PRIZE ANALYZER";

		final String[] LOTTERY_DESCRIPTIONS =
				{
					"Remember, it's not about winning. It's about the thrill of almost winning!",
					"99% of people quit gambling before their big win. Don't let it be you.",
					"Gambling is the only way to get nothing for something.",
					"WINNER! GAGNANT!",
					"Gambling: As seen on Roblox!",
					"Gambling: At least you're not doing it on CS:GO.",
				}; // end array

		final String LINE_SEPARATOR = "*************************************************"
				+ "******************************************";

		// Create container variables for user input
		String lotteryName;
		int prizePoolMoney;

		// print out app description:
		A_P_ProjectMethods.printAppDescription(APP_NAME, LOTTERY_DESCRIPTIONS,
				LINE_SEPARATOR);

		// Create temp variables to store unsanitized user input
		String tempString;
		int tempInt;

		// Validation loop for lottery name:
		while (true)
		{
			// Prompt the user for the lottery name, store value in tempString.
			System.out.print("Please enter the name of the lottery: ");
			tempString = input.nextLine();

			if (tempString.length() < 5)
			{
				// Print out the warning, go back to the start of the loop.
				System.out.println(
						"\tThe name of the lottery must be 5 characters or more.");
				continue;
			} // end if

			// If the tempString is validated, then store it in lotteryName.
			lotteryName = tempString;
			break;
		} // end while

		while (true)
		{
			/*
			 * Prompt the user to enter the amount of money in the prize pool.
			 *
			 * If the user enters a non-integer value, prompt the user to enter a
			 * valid number.
			 *
			 * If the user enters a value less than $1000, prompt the user to enter a
			 * number that is at least $1000.
			 */
			System.out.print(
					"Please enter the amount of money in the prize pool (no cents): $");

			// If the input is not a valid integer:
			if (!input.hasNextInt())
			{
				tempString = input.nextLine();
				System.out.printf("\tYou entered %s. Please enter a valid number.\n",
						tempString);
				continue;
			} // end if

			// Hold the input buffer into the temporary int container.
			tempInt = input.nextInt();

			// Flush the input buffer
			input.nextLine();

			// If the user provided int is less than 1000:
			if (tempInt < 1000)
			{
				// Give warning and then set user back to the start.
				System.out.println("\tThe prize pool must be at least $1000.");
				continue;
			} // end if

			prizePoolMoney = tempInt;
			break;
		} // end while

		// Create a container variable for file name.
		String fileName;

		// Prompt the user for the file.
		System.out.print("Please enter the path for the data file: ");
		fileName = input.nextLine();

		// Initialize a Scanner object for the fileInput. Set it to null for now.
		Scanner fileInput = null;

		// Try to find the file and set the fileInput scanner from null
		// to an instance of a file scanner.

		try
		{
			File file = new File(fileName);
			fileInput = new Scanner(file);
		}
		catch (FileNotFoundException e)
		{
			System.out.printf("The file %s does not exist. Exiting now.\n", fileName);
			System.exit(1);
		} // end try catch

		/*
		 * Using the getNextSeries method from A_P_ProjectMethods, get the winning
		 * numbers from the file.
		 */
		int[] winningNumbers = A_P_ProjectMethods.getNextSeries(fileInput);


		/*
		 * Create constants for the percentage of the prize pool each are prize tier
		 * is awarded.
		 *
		 * First place: 85% of prize pool.
		 *
		 * Second place: 7% of prize pool.
		 *
		 * Third place: 8% of prize pool.
		 */
		final double FIRST_PLACE_ALLOCATION = 0.85;
		final double SECOND_PLACE_ALLOCATION = 0.07;
		final double THIRD_PLACE_ALLOCATION = 0.08;

		/*
		 * Calculate the prize money for each prize tier.
		 */
		final double FIRST_PLACE_AMOUNT = prizePoolMoney * FIRST_PLACE_ALLOCATION;
		final double SECOND_PLACE_AMOUNT = prizePoolMoney * SECOND_PLACE_ALLOCATION;
		final double THIRD_PLACE_AMOUNT = prizePoolMoney * THIRD_PLACE_ALLOCATION;

		/*
		 * Create constants for the number of matches needed to win each prize tier.
		 */
		final int MATCHES_NEEDED_FOR_FIRST_PLACE = winningNumbers.length;
		final int MATCHES_NEEDED_FOR_SECOND_PLACE = MATCHES_NEEDED_FOR_FIRST_PLACE - 1;
		final int MATCHES_NEEDED_FOR_THIRD_PLACE = MATCHES_NEEDED_FOR_FIRST_PLACE - 2;

		// Create a running total for the number of first place winners
		int firstPlaceWinnersCount = 0;
		// create an array list to store the second place and third place winners
		ArrayList<int[]> secondPlaceWinners = new ArrayList<>(); // 5 numbers match
		ArrayList<int[]> thirdPlaceWinners = new ArrayList<>(); // 4 numbers match

		/*
		 * Create container variables to store the parsed nextSeries and the lottery
		 * match count.
		 */
		int[] nextSeries;
		int matchCount;

		/*
		 * Create a loop that will continue iterating as long as there is a next line
		 * in the file.
		 */
		while (fileInput.hasNextLine())
		{
			// Get the next line of lottery numbers using .getNextSeries()
			nextSeries = A_P_ProjectMethods.getNextSeries(fileInput);
			// Compare the nextSeries array with the winning numbers array by calculating match count
			matchCount = A_P_ProjectMethods.countMatchingNumbers(nextSeries,
					winningNumbers);

			if (matchCount == MATCHES_NEEDED_FOR_FIRST_PLACE)
			{
				firstPlaceWinnersCount += 1;
			}
			else if (matchCount == MATCHES_NEEDED_FOR_SECOND_PLACE)
			{
				secondPlaceWinners.add(nextSeries);
			}
			else if (matchCount == MATCHES_NEEDED_FOR_THIRD_PLACE)
			{
				thirdPlaceWinners.add(nextSeries);
			}
		} // end while

		/* --------------------------------------------------------------------------- */
		/* PROCESSING: Calculate the prize allocation for each ticket in each tier.		 */
		/* --------------------------------------------------------------------------- */

		double firstPrizePerTicket = FIRST_PLACE_AMOUNT / firstPlaceWinnersCount;
		double secondPrizePerTicket = SECOND_PLACE_AMOUNT / secondPlaceWinners.size();
		double thirdPrizePerTicket = THIRD_PLACE_AMOUNT / thirdPlaceWinners.size();

		/* --------------------------------------------------------------------------- */
		/*                               REPORT                                        */
		/* --------------------------------------------------------------------------- */
		/*  - Print information about the lottery being analyzed:                      */
		/*      - Lottery name                                                         */
		/*      - Total prize pool                                                     */
		/*      - Winning numbers                                                      */
		/*                                                                             */
		/*  - Print the number of winners for each prize tier                          */
		/*      and the winning ticket numbers.                                        */
		/* --------------------------------------------------------------------------- */

		System.out.println("\n" + LINE_SEPARATOR + "\n");
		System.out.println("Lottery Prize Report\n");
		System.out.println(LINE_SEPARATOR + "\n");
		System.out.println("Lottery Name:\t\t\t" + lotteryName);

		System.out.println(
				"Total prize pool:\t\t$" + String.format("%,d", prizePoolMoney));

		System.out.println(
				"Winning Numbers:\t\t" + A_P_ProjectMethods.formatTicketNumbers(
						winningNumbers));

		System.out.print("\n");
		System.out.println(LINE_SEPARATOR);

		/* --------------------------------------------------------------------------- */
		/* FIRST PLACE  																															 */
		/* - Print out the stats for the second place winners.												 */
		/* - Iterate through the ArrayList of secondPlaceWinners.											 */
		/* --------------------------------------------------------------------------- */

		System.out.printf("\nFirst place winners:\t\t(%d of %d match)\n",
				MATCHES_NEEDED_FOR_FIRST_PLACE, MATCHES_NEEDED_FOR_FIRST_PLACE);

		System.out.println("\nWINNER! GAGNANT!");
		// Print out the stats for first place winners:
		A_P_ProjectMethods.printTierStats(firstPlaceWinnersCount, FIRST_PLACE_ALLOCATION,
				FIRST_PLACE_AMOUNT, firstPrizePerTicket);

		System.out.println(LINE_SEPARATOR);

		/* --------------------------------------------------------------------------- */
		/* SECOND PLACE 																															 */
		/* - Print out the stats for the second place winners.												 */
		/* - Iterate through the ArrayList of secondPlaceWinners.											 */
		/* --------------------------------------------------------------------------- */

		System.out.printf("\nSecond place winners:\t\t(%d of %d match)\n",
				MATCHES_NEEDED_FOR_SECOND_PLACE, MATCHES_NEEDED_FOR_FIRST_PLACE);

		// Print out the stats for second place winners:
		A_P_ProjectMethods.printTierStats(secondPlaceWinners.size(),
				SECOND_PLACE_ALLOCATION, SECOND_PLACE_AMOUNT, secondPrizePerTicket);

		// Iterate through the ArrayList of secondPlaceWinners using the listWinners method.
		A_P_ProjectMethods.listWinners(secondPlaceWinners);

		System.out.println();

		/* --------------------------------------------------------------------------- */
		/* THIRD PLACE  																															 */
		/* - Print out the stats for the second place winners.												 */
		/* - Iterate through the ArrayList of secondPlaceWinners.											 */
		/* --------------------------------------------------------------------------- */

		System.out.println(LINE_SEPARATOR);
		System.out.printf("\nThird place winners:\t\t(%d of %d match)\n",
				MATCHES_NEEDED_FOR_THIRD_PLACE, MATCHES_NEEDED_FOR_FIRST_PLACE);

		// Print out the stats for third place winners:
		A_P_ProjectMethods.printTierStats(thirdPlaceWinners.size(),
				THIRD_PLACE_ALLOCATION, THIRD_PLACE_AMOUNT, thirdPrizePerTicket);

		// Iterate through the ArrayList of thirdPlaceWinners using the listWinners method.
		A_P_ProjectMethods.listWinners(thirdPlaceWinners);

		System.out.println();

		/* --------------------------------------------------------------------------- */
		/* --------------------------------------------------------------------------- */
		/* --------------------------------------------------------------------------- */

		// Housekeeping
		input.close();
		fileInput.close();

	} // end main

}
// end class