/*=============================================================================
|	Source code:  Analyzer.java
|      Author:  Daniel Martinez
|
|    Language:  Java
|	Compile/Run:
|	              javac Analyzer.java Craps.java Dice.java
|	              java  Analyzer
|
|  +-----------------------------------------------------------------------------
|
|  Description:  This program prompts the user for an amount of games and then
|                simulates and analyzes the given amount of games of Craps.
|
|                Statistics for the games are then returned to the user.
|
|        Input:  The user is required to enter an integer value for the amount of
|                games they want simulated and analyzed.
|
|	      Output:  Output will provide the analysis of running a certain number of games of Craps – presented in clear, readable and attractive manner. The results of each game (win/lose) and the number of$
|
|                                (1) total number of games played,
|                                (2) total number of rolls for all games played,
|                                (3) average length (in rolls) of the games played (total rolls/total games),
|                                (4) longest game played (in rolls).
|
|                                (5) total number of games won,
|                                (6) expected probability of winning overall,
|                                (7) outcome of winning overall (total wins/total games),
|
|                                (8) total number of wins that occurred on the coming out roll,
|                                (9) total number of games that ended on the opening (coming out) roll,
|                                (10) expected probability of winning on the opening roll,
|                                (11) outcome of winning on the coming out roll(coming out wins/coming out games),
|                                (12) expected probability of the games ending on the coming out roll,
|                                (13) outcome of games ending on the coming out roll (coming out games/total games),
|
|                                (14) total number of games continuing after the coming out roll (wins & losses),
|                                (15) expected probability of the games continuing after the coming out roll,
|                                (16) outcome of games continuing after the coming out roll
|                                        ((total games - coming out games)/total games),
|
|                                (17) summary tally of the number of rolls for each game to finish (1 to 21+),
|
|
|      Process:
|                                The process consists of prompting the user then simulating the games.
|                                Methods from the Craps class are used to find how many rolls the game took
|                                to end, as well as weather the game was won or loss.
|
|
|   Known Bugs:  None; the program operates correctly.
|
|  *===========================================================================*/

import java.text.DecimalFormat;
import java.util.Scanner;

public class Analyzer 
{   
    public static void main(String[] args)
    {

        Craps crapsGame = new Craps();

        final double EXPECTED_PROB_OF_WINNING         = .4929; //https://www.mscs.dal.ca/~hoshino/book/ch20craps.pdf
        final double EXPECTED_WIN_ON_COMING_OUT       = .2222; //https://people.richland.edu/james/misc/simulation/craps.html
        final double EXPECTED_FINISH_ON_COMING_OUT    = .3333; //https://wizardofodds.com/ask-the-wizard/craps/probability/
        final double EXPECTED_PROB_OF_GAMES_CONTINUED = .6666; //https://people.richland.edu/james/misc/simulation/craps.html

        int totalGames = 0; // number of games simulated
        int totalWins  = 0; // number of games won
        int totalRolls = 0; // number of rolls for all games
        int totalWinsOnComingOut   = 0; // number of wins for the coming out roll
        int totalFinishOnComingOut = 0; // number of games that ended after one roll
        int totalGamesContinued    = 0; // number of games that lasted more than one roll
        int longestRolls = 0; // game that lasted the longest (in rolls)
        int gamesEndedAtRolls = 0; // used in for loop to store amount of games ending at a designated number of rolls
                                               
        int enteredValue = 0; // used to store the inputed value for validation
        double averageRolls = 0.0; // average number of rolls a game lasted, total rolls / total games
                                                                                                        
        DecimalFormat outputFormat = new DecimalFormat("0.0000"); // used to format the computed values
        Scanner input = new Scanner (System.in); // used to fetch value from user
        System.out.println("How many game of Craps do you want simulated and analayzed?");

        /*
         *  validates input
         *
         *  only accepts value if it's of the integer type and is between
         *  1 and 1,000,000 inclusive
         *
         *  value in the parameter is stored so that multiple credentials can be checked
         *
         *  1,000,000 was in specifications for highest number of games that can be simulated
         */
	      while(!input.hasNextInt() || (enteredValue = input.nextInt()) < 1 || enteredValue > 1000000)
        {
                input.next(); // clears stored value
        }
        
        totalGames = enteredValue;

        /*
         * an array that has a boolean value stored for each game simulated
         * where a value of true represents a win and a value of false
         * represents a loss
         */
	      boolean[] wins = new boolean[totalGames];

        /*
         * an array that stores the amount of rolls a game lasted, for all
         * the games that were simulated
         */
        int[] numberOfRolls	 = new int[totalGames];

        /*
         * an array that stores how many games ended
         */
        int[] rollsWhenGameEnded = new int[21]; /* 21 is given in the specifications
                                                   on cap on rolls when the game ended (1-21+) */

        /*
         * this loop calls the playGame() method from the Craps class
         * for each game requested by the user
         * values from the game are stored such as amount of rolls and
         * weather the game was won or loss
         *
         * at the end of the loop the resetGame() method is called to
         * reset the values of the Craps class
         */
        for(int game = 0; game < totalGames; game++)
        {
            if(crapsGame.playGame())
            {
                wins[game] = true;
                totalWins++;
            }
            else
            {
                wins[game] = false;
            }

            numberOfRolls[game] = crapsGame.getNumberOfRolls();
            totalRolls += numberOfRolls[game];

            if(numberOfRolls[game] == 1)
            {
                totalFinishOnComingOut++;
                if(wins[game])
                {
                    totalWinsOnComingOut++;
                }
            }

            if(numberOfRolls[game] > longestRolls)
            {
                longestRolls = numberOfRolls[game];
            }

            crapsGame.resetGame();
         }


         averageRolls = (double)totalRolls/totalGames;
         totalGamesContinued = totalGames - totalFinishOnComingOut;
         
         System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
         System.out.println("Summary of Game Statistics\n");
         System.out.println("(1) Total Games                                        " + totalGames);
         System.out.println("(2) Total Rolls                                        " + totalRolls);
         System.out.println("(3) Average Rolls                                      " + outputFormat.format(averageRolls));
         System.out.println("(4) Longest Rolls                                      " + longestRolls);
         System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
         System.out.println("Winning Statistics\n");
         System.out.println("(5) Total Games Won                                    " + totalWins);
         System.out.println("(6) Expected Probability of Winning Overall            " + EXPECTED_PROB_OF_WINNING);
         System.out.println("(7) Computed Probability of Winning Overall            " + outputFormat.format((double)totalWins/totalGames));
         System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
         System.out.println("Coming Out Statistics\n");
         System.out.println("(8)  Total Wins on Coming Out                          " + totalWinsOnComingOut);
         System.out.println("(9)  Total Games Ending on Coming Out                  " + totalFinishOnComingOut);
         System.out.println("(10) Expected Probability of Winning On Opening Roll   " + EXPECTED_WIN_ON_COMING_OUT);
         System.out.println("(11) Computed Probability of Winning On Opening Roll   " + outputFormat.format((double)totalWinsOnComingOut/totalGames));
         System.out.println("(12) Expected Probability of Finishing On Opening Roll " + EXPECTED_FINISH_ON_COMING_OUT);
         System.out.println("(13) Computed Probability of Finishing On Opening Roll " + outputFormat.format((double)totalFinishOnComingOut/totalGames));
         System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
         System.out.println("Summary of Ending Statistics\n");
         System.out.println("(14) Total Games Continued After Coming Out Roll	   " + totalGamesContinued);
         System.out.println("(15) Expected Probability of Games Continuing          " + EXPECTED_PROB_OF_GAMES_CONTINUED);
         System.out.println("(16) Computed Probability of Games Continuing          " + outputFormat.format((double)totalGamesContinued / totalGames));
         System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
         System.out.println("(17) Summary of Game Length in Rolls\n");
         
         /*
          * purpose of this double for loop is for each possible number of rolls (1-21+)
          * go through each game and add up all the games that end at the given number
          *
          * the counter variable (gamesEndedAtRolls is cleared at the beginning of the loop
          * and output for the rolls and amount of games that ended at the given number of rolls
          * is returned
          */
          for(int rolls = 0; rolls < rollsWhenGameEnded.length; rolls++)
          {
              gamesEndedAtRolls = 0;
              for(int games = 0; games < numberOfRolls.length; games++)
              {
                  if(numberOfRolls[games] == (rolls+1);
                      gamesEndedAtRolls++;
              }
              if((rolls+1) < 21)
                  System.out.println((rolls+1) + "\t|\t" + gamesEndedAtRolls );
              else
                  System.out.println("21+" + "\t|\t" + gamesEndedAtRolls );
           }
           System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
}





