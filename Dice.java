/*=======================================================================
|   Source code:  Dice.java
|
|         Class:  Dice
|
|            Author:  Daniel Martinez
|
|    Language: Java
|	Compile/Run:
|	             javac Analyzer.java Craps.java Dice.java
|	             java  Analyzer
|
|        Purpose:  simulate the roll or rolls of die
|
|  Inherits From:  none
|
|     Interfaces:  none
|
|  +-----------------------------------------------------------------------
|
|      Constants:
|                 MIN_SIDES = 3 
|                 MAX_SIDES = 100 
|
|  +-----------------------------------------------------------------------
|
|   Constructors:  none
|
|  Class Methods:  none
|
|  Instance Methods:
|                    int rollDie(int die, int sides)
|
|*===========================================================================*/

import java.util.Random;

public class Dice {

        private Random random = new Random();
        private final int MIN_SIDES = 3;
        private final int MAX_SIDES = 100;

    /*---------------------------- rollDie ----------------------------
    |  Method int rollDie(int die, int sides)
    |
    |  Purpose:  simulates the rolling of die
    |
    |  @param  die is the amount of die being rolled, sides is the amount
    |          of sides the die has
    |
    |  @return the sum of the results of all rolled die
    *-------------------------------------------------------------------*/
        public int rollDie(int die, int sides)
        {
                int rollSum = 0;

                if(sides < MIN_SIDES)
                {
                        sides = MIN_SIDES;
                }
                else if(sides > MAX_SIDES)
                {
                        sides = MAX_SIDES;
                }

                for(int numDie = 0; numDie < die; numDie++)
                {
                        rollSum += random.nextInt(sides) + 1; // acommodates for the return of random
                }

                return rollSum;
        }
}

