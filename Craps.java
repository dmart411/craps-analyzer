/*=======================================================================
|	Source code:  Craps.java
|
|             Class:  Craps
|            Author:  Daniel Martinez
|  
|   Language:     Java
|	  Compile/Run:
|	                javac Analyzer.java Craps.java Dice.java
|	                java  Analyzer
| 
|+-----------------------------------------------------------------------------
|
|        Purpose:  Purpose of this class is simulate a game of Craps
|
|  Inherits From:  None.
|
|     Interfaces:  None.
|
|  +-----------------------------------------------------------------------
|
|      Constants:
|                  The following are all terminology from the game of Craps:
|
|                  NATURAL
|                  YO_LEVEN
|                  SNAKE_EYES
|                  ACE_DEUCE
|                  BOX_CAR
|
|                  WIN,  the value of true represents that the game was won
|                  LOSE, the value of false represents that the game was lost
|
| +-----------------------------------------------------------------------
|
|   Constructors:  None.
|
|  Class Methods:  None.
|
|  Instance Methods:
|
|                     boolean playGame()
|                     void resetGame()
|                     int getNumberOfRolls()
|
|*===========================================================================*/

public class Craps {

        private final int NATURAL    = 7;
        private final int YO_LEVEN   = 11;
        private final int SNAKE_EYES = 2;
        private final int ACE_DEUCE  = 3;
        private final int BOX_CAR    = 12;
        private final boolean WIN    = true;
        private final boolean LOSE   = false;

        private int point = 0;          // stores the "point" for the game of craps
        private int numberOfRolls = 0;  // stores the number of rolls a game lasts
        private Dice die = new Dice();  // initializes a die object to simulate rolling die in the game

    /*---------------------------- playGame ----------------------------
    |  Method playGame()
    |
    |  Purpose:  simulates a game of Craps
    |
    |  @param  none
    |  @return true if game was won, false if game was loss
    *-------------------------------------------------------------------*/
        public boolean playGame()
        {
                point = die.rollDie(2, 6); // 6 represents the amount of sides on a standard die
                numberOfRolls++;

                if(point == NATURAL || point == YO_LEVEN)
                {
                        return WIN;
                }
                else if(point == SNAKE_EYES || point == ACE_DEUCE || point == BOX_CAR)
                {
                        return LOSE;
                }

                int value = die.rollDie(2, 6);
                numberOfRolls++;

                while((value != point) && (value != NATURAL))
                {
                        value = die.rollDie(2, 6);
                        numberOfRolls++;
                }

                if(value == point)
                {
                        return WIN;
                }
                else
                {
                        return LOSE;
                }
        }
        
    /*---------------------------- resetGame ----------------------------
    |  Method resetGame()
    |
    |  Purpose:  resets all the object's private values
    *-------------------------------------------------------------------*/
        public void resetGame()
        {
                numberOfRolls = 0;
                point = 0;
        }

	  /*---------------------------- getNumberOfRolls----------------------------
    |  Method getNumberOfRolls()
    |
    |  Purpose:  retrieve amount of rolls the game lasted
    |  @param   none
    |  @return  the number of rolls the game lasted
    *-------------------------------------------------------------------*/
        public int getNumberOfRolls()
        {
                return numberOfRolls;
        }
}













