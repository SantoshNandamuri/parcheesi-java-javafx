package parcheesi;

import java.util.Random;

public class NSidedDie
{
    /* **************************************************************** */
    /* Class Scope Variables                                            */
    /*                                                                  */
    /*                                                                  */
    /*                                                                  */
    /*                                                                  */
    /* **************************************************************** */

    private int numberOfSides = 6;
    private int currentValue;

    /* **************************************************************** */
    /* Constructors                                                     */
    /*                                                                  */
    /*                                                                  */
    /*                                                                  */
    /*                                                                  */
    /* **************************************************************** */
    public NSidedDie()
    {

    }
    public NSidedDie(int numberOfSides)
    {
        this.setNumberOfSides(numberOfSides);
    }

    /* **************************************************************** */
    /* Getters                                                          */
    /*                                                                  */
    /*                                                                  */
    /*                                                                  */
    /*                                                                  */
    /* **************************************************************** */

    public int getNumberOfSides() {
        return numberOfSides;
    }

    public int getCurrentValue() {
        return currentValue;
    }
    /* **************************************************************** */
    /* Setters                                                          */
    /*                                                                  */
    /*                                                                  */
    /*                                                                  */
    /*                                                                  */
    /* **************************************************************** */

    public void setNumberOfSides(int numberOfSides) {
        if ( numberOfSides >= 2 )
        {
            this.numberOfSides = numberOfSides;
        }
    }

    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
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
        Random random = new Random();

        currentValue = random.nextInt(numberOfSides) + 1;
    }
    public void print()
    {
        String[] diePrint = getDieAsStringArray();

        for ( int i = 0; i < diePrint.length; i++ )
        {
            System.out.println(diePrint[i]);
        }
    }
    public String[] getDieAsStringArray()
    {
        String[] diePrint = {
                "      _________________________ ",
                "    /                          │",
                "   /                          /│",
                "  /                          / │",
                " /                          /  │",
                "┌─────────────────────────┐/   │",
                "│                         │    │",
                "│                         │    │",
                "│                         │    │",
                "│                         │    /",
                "│                         │   / ",
                "│                         │  /  ",
                "│                         │ /   ",
                "│                         │/    ",
                "└─────────────────────────┘     "
        };

        // Add numeric value to top
        diePrint[6] = diePrint[6].charAt(0)
                + String.format("%-3d", this.getCurrentValue())
                + diePrint[6].substring(5, 24)
                + String.format("%3d", this.getCurrentValue())
                + diePrint[6].substring(26);

        // Add numeric value to bottom
        diePrint[13] = diePrint[13].charAt(0)
                + String.format("%-3d", this.getCurrentValue())
                + diePrint[13].substring(5, 24)
                + String.format("%3d", this.getCurrentValue())
                + diePrint[13].substring(26);


        return diePrint;
    }
}
