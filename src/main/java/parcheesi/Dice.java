package parcheesi;

import java.util.ArrayList;

public class Dice {
    /* **************************************************************** */
    /* Class Scope Variables                                            */
    /*                                                                  */
    /*                                                                  */
    /*                                                                  */
    /*                                                                  */
    /* **************************************************************** */
    private ArrayList<NSidedDie> dice = new ArrayList<>();

    /* **************************************************************** */
    /* Constructors                                                     */
    /*                                                                  */
    /*                                                                  */
    /*                                                                  */
    /*                                                                  */
    /* **************************************************************** */
    public Dice()
    {

    }

    /* **************************************************************** */
    /* Getters                                                          */
    /*                                                                  */
    /*                                                                  */
    /*                                                                  */
    /*                                                                  */
    /* **************************************************************** */

    public ArrayList<NSidedDie> getDice() {
        return dice;
    }

    /* **************************************************************** */
    /* Setters                                                          */
    /*                                                                  */
    /*                                                                  */
    /*                                                                  */
    /*                                                                  */
    /* **************************************************************** */
    public void setDice(ArrayList<NSidedDie> dice) {
        this.dice = dice;
    }

    /* **************************************************************** */
    /* Public Methods                                                   */
    /*                                                                  */
    /*                                                                  */
    /*                                                                  */
    /*                                                                  */
    /* **************************************************************** */
    public void roll()
    {
        for( NSidedDie singleDie : this.getDice() )
        {
            singleDie.roll();
        }
    }

    public void print()
    {
        ArrayList<String[]> diceAsStringArrays = new ArrayList<>();

        // Gather all of the die as String arrays
        for( NSidedDie singleDie : this.getDice() )
        {
            diceAsStringArrays.add(singleDie.getDieAsStringArray());
        }

        int numberOfRows = this.getDice().get(0).getDieAsStringArray().length;

        for ( int i = 0; i < numberOfRows; i++ ) {
            for (String[] singleDie : diceAsStringArrays)
            {
                System.out.print(singleDie[i]);
            }
            System.out.println();
        }
    }

    /* **************************************************************** */
    /* Public Static Methods                                            */
    /*                                                                  */
    /*                                                                  */
    /*                                                                  */
    /*                                                                  */
    /* **************************************************************** */
    public static Dice getNGenericDice(int numberOfDice, int numberOfSides)
    {
        Dice dice = new Dice();

        for ( int i = 0; i < numberOfDice; i++ )
        {
            NSidedDie nSidedDie = new NSidedDie(numberOfSides);
            dice.getDice().add(nSidedDie);
        }

        return dice;
    }
    public static Dice getNGenericDice(int numberOfDice)
    {
        return Dice.getNGenericDice(numberOfDice, 6);
    }
}
