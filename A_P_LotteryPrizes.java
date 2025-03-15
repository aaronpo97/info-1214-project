
/**
 * Program Name: A_P_LotteryPrizes
 *
 * Purpose: This is an application that analyzes ticket number data contained
 * ina a data file to determine which ticket holders have won a prize, how many
 * will have to share a prize, and how much money they will receive.
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
        /**
         * Set up a Scanner object to read input from the console.
         */
        Scanner input = new Scanner(System.in);

        /**
         * Set up variables to store the title and description of the lottery
         * application.
         * 
         * Print the title and description to the console.
         */
        final String APP_NAME = "AARON'S LOTTERY PRIZE ANALYZER";
        final String LOTTERY_DESCRIPTION = "Remember, it's not about winning. It's about the thrill of almost winning!";
        final String LINE_SEPARATOR = "*************************************************"
                + "******************************************"
                + "******************************************";

        System.out.printf("%s\n\n%s\n%s\n\n%s\n\n", LINE_SEPARATOR, APP_NAME,
                LOTTERY_DESCRIPTION, LINE_SEPARATOR);

        String tempString;
        int tempInt;
        String lotteryName;
        int prizePoolMoney;

        int numTickets = 0;

        while (true)
        {
            /**
             * Prompt the user to enter the name of the lottery.
             * 
             * If the name is less than 5 characters, prompt the user to enter a name that
             * is 5 characters or more.
             */

            System.out.print("Please enter the name of the lottery: ");
            tempString = input.nextLine();
            if (tempString.length() < 5)
            {
                System.out.println(
                        "\tThe name of the lottery must be 5 characters or more.");
                continue;
            }

            lotteryName = tempString;
            break;
        }

        while (true)
        {
            /**
             * Prompt the user to enter the amount of money in the prize pool.
             * 
             * If the user enters a non-integer value, prompt the user to enter a valid
             * number.
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
            }

            tempInt = input.nextInt();
            // flush the input buffer
            input.nextLine();

            if (tempInt < 1000)
            {
                System.out.println("\tThe prize pool must be at least $1000.");
                continue;
            }

            prizePoolMoney = tempInt;
            break;
        }

        /**
         * Prompt the user to enter the name of the data file.
         */
        String fileName;

        System.out.print("Please enter the path for the data file: ");
        fileName = input.nextLine();
        File file = new File(fileName);

        Scanner fileInput = null;
        try
        {
            fileInput = new Scanner(file);
        }
        catch (FileNotFoundException e)
        {
            System.out.printf("The file %s does not exist.\n", fileName);
            e.printStackTrace();
            System.exit(0);
        }

        int[] winningNumbers = A_P_ProjectMethods.getNextSeries(fileInput);

        System.out.printf("\n%s\n\nLottery Prize Report\n\n%s\n\n", LINE_SEPARATOR,
                LINE_SEPARATOR);

        System.out.printf("Lottery Name:\t\t%s\n", lotteryName);
        System.out.printf("Total prize pool:\t$%,d\n", prizePoolMoney);
        System.out.printf("Winning Numbers:\t%s\n\n",
                A_P_ProjectMethods.formatTicketNumbers(winningNumbers));

        input.close();
    } // end main
}
// end class