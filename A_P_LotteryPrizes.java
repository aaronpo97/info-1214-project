
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
		/*
		 * Set up a Scanner object to read input from the console.
		 */
		Scanner input = new Scanner(System.in);

		String tempString;
		int tempInt;
		String lotteryName;
		int prizePoolMoney;

		// print out app description:
		A_P_ProjectMethods.printAppDescription();

		while (true)
		{
			/*
			 * Prompt the user to enter the name of the lottery.
			 *
			 * If the name is less than 5 characters, prompt the user to enter a name
			 * that is 5 characters or more.
			 */

			System.out.print("Please enter the name of the lottery: ");
			tempString = input.nextLine();

			if (tempString.length() < 5)
			{
				System.out.println(
						"\tThe name of the lottery must be 5 characters or more.");
				continue;
			} // end if

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

			if (!input.hasNextInt())
			{
				tempString = input.nextLine();
				System.out.printf("\tYou entered %s. Please enter a valid number.\n",
						tempString);
				continue;
			} // end if

			tempInt = input.nextInt();
			// flush the input buffer
			input.nextLine();

			if (tempInt < 1000)
			{
				System.out.println("\tThe prize pool must be at least $1000.");
				continue;
			} // end if

			prizePoolMoney = tempInt;
			break;
		} // end while

		/*
		 * Prompt the user to enter the name of the data file.
		 */
		String fileName;

		System.out.print("Please enter the path for the data file: ");
		fileName = input.nextLine();

		Scanner fileInput = null;
		try
		{
			File file = new File(fileName);
			fileInput = new Scanner(file);
		}
		catch (FileNotFoundException e)
		{
			System.out.printf("The file %s does not exist. Exiting now.\n",
					fileName);
			// e.printStackTrace();
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
		 * First place: 85% of prize pool Second place: 7% of prize pool Third place:
		 * 8% of prize pool
		 */
		final double FIRST_PLACE_PRIZE_ALLOC_PERCENT = 0.85;
		final double SECOND_PLACE_PRIZE_ALLOC_PERCENT = 0.07;
		final double THIRD_PLACE_PRIZE_ALLOC_PERCENT = 0.08;

		/*
		 * Calculate the prize money for each prize tier.
		 */
		final double FIRST_PLACE_PRIZE = prizePoolMoney
				* FIRST_PLACE_PRIZE_ALLOC_PERCENT;
		final double SECOND_PLACE_PRIZE = prizePoolMoney
				* SECOND_PLACE_PRIZE_ALLOC_PERCENT;
		final double THIRD_PLACE_PRIZE = prizePoolMoney
				* THIRD_PLACE_PRIZE_ALLOC_PERCENT;

		/*
		 * Create the report for the lottery prize.
		 *
		 * Print the lottery name, total prize pool, and winning numbers to the
		 * console.
		 */

		System.out.println("\n" + A_P_ProjectMethods.LINE_SEPARATOR + "\n");
		System.out.println("Lottery Prize Report\n");
		System.out.println(A_P_ProjectMethods.LINE_SEPARATOR + "\n");

		System.out.println("Lottery Name:\t\t" + lotteryName);

		System.out.println(
				"Total prize pool:\t$" + String.format("%,d", prizePoolMoney));

		System.out.println("Winning Numbers:\t"
				+ A_P_ProjectMethods.formatTicketNumbers(winningNumbers) + "\n");

		// create a running total for the number of first place winners
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
		 * Create constants for the number of matches needed to win each prize tier.
		 */
		final int MATCHES_NEEDED_FOR_FIRST_PLACE = winningNumbers.length;
		final int MATCHES_NEEDED_FOR_SECOND_PLACE = MATCHES_NEEDED_FOR_FIRST_PLACE
				- 1;
		final int MATCHES_NEEDED_FOR_THIRD_PLACE = MATCHES_NEEDED_FOR_FIRST_PLACE
				- 2;
		/*
		 * Create a loop that will continue iterating as long as there is a next line
		 * in the file.
		 */
		while (fileInput.hasNextLine())
		{
			/*
			 * Use the methods getNextSeries and countMatchingNumbers
			 */
			nextSeries = A_P_ProjectMethods.getNextSeries(fileInput);
			matchCount = A_P_ProjectMethods.countMatchingNumbers(nextSeries,
					winningNumbers);

			if (matchCount == MATCHES_NEEDED_FOR_FIRST_PLACE)
			{
				firstPlaceWinnersCount += 1;
				continue;
			}
			if (matchCount == MATCHES_NEEDED_FOR_SECOND_PLACE)
			{
				secondPlaceWinners.add(nextSeries);
				continue;
			}
			if (matchCount == MATCHES_NEEDED_FOR_THIRD_PLACE)
			{
				thirdPlaceWinners.add(nextSeries);
			}
		} // end while

		double FIRST_PLACE_PRIZE_PER_TICKET = FIRST_PLACE_PRIZE /firstPlaceWinnersCount;
		double SECOND_PLACE_PRIZE_PER_TICKET = SECOND_PLACE_PRIZE / secondPlaceWinners.size();
		double THIRD_PLACE_PRIZE_PER_TICKET = THIRD_PLACE_PRIZE / thirdPlaceWinners.size();

		/*
		 * Print the number of winners for each prize tier and the winning ticket
		 * numbers.
		 */

		// GRAND PRIZE WINNERS
		System.out.println(A_P_ProjectMethods.LINE_SEPARATOR);
		System.out.printf("\nGrand prize winners: (all %d numbers match)\n",
				MATCHES_NEEDED_FOR_FIRST_PLACE);
		System.out.printf("\nNumber of winners: %d\n", firstPlaceWinnersCount);
		System.out.printf("Percent of prize pool: %.2f%%\n", FIRST_PLACE_PRIZE_ALLOC_PERCENT * 100.0);
		System.out.printf("Total prize value: $%,.2f\n", FIRST_PLACE_PRIZE);
		System.out.printf("Prize per ticket: $%,.2f\n\n", FIRST_PLACE_PRIZE_PER_TICKET);



		// SECOND PLACE WINNERS
		System.out.println(A_P_ProjectMethods.LINE_SEPARATOR);
		System.out.printf("\nSecond place winners: (%d of %d match)\n",
				MATCHES_NEEDED_FOR_SECOND_PLACE, MATCHES_NEEDED_FOR_FIRST_PLACE);
		System.out.printf("\nNumber of winners: %d\n", secondPlaceWinners.size());
		System.out.printf("Percent of prize pool: %.2f%%\n", SECOND_PLACE_PRIZE_ALLOC_PERCENT * 100.0);
		System.out.printf("Total prize value: $%,.2f\n", SECOND_PLACE_PRIZE);
		System.out.printf("Prize per ticket: $%,.2f\n\n", SECOND_PLACE_PRIZE_PER_TICKET);
		String temp;
		for (int[] winner : secondPlaceWinners)
		{
			temp = A_P_ProjectMethods.formatTicketNumbers(winner);
			System.out.println(temp);
		}
		System.out.println();
		// THIRD PLACE WINNERS

		System.out.println(A_P_ProjectMethods.LINE_SEPARATOR);
		System.out.printf("\nThird place winners: (%d of %d match)",
				MATCHES_NEEDED_FOR_THIRD_PLACE, MATCHES_NEEDED_FOR_FIRST_PLACE);
		System.out.printf("\nNumber of winners: %d\n", thirdPlaceWinners.size());
		System.out.printf("Percent of prize pool: %.2f%%\n", THIRD_PLACE_PRIZE_ALLOC_PERCENT * 100.0);
		System.out.printf("Total prize value: $%,.2f\n", THIRD_PLACE_PRIZE);
		System.out.printf("Prize per ticket: $%,.2f\n\n", THIRD_PLACE_PRIZE_PER_TICKET);
		for (int[] winner : thirdPlaceWinners)
		{
			temp = A_P_ProjectMethods.formatTicketNumbers(winner);
			System.out.println(temp);
		}
System.out.println();

		input.close();
	} // end main

}
// end class