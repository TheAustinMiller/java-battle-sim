/*
 * Course: CS1011-011
 * Fall 2020
 * Lab 7 - Battle Simulator 3000 - Die
 * Name: Austin Miller
 * Created: 10/20/2020
 */
import java.util.Random;

/**
 * This method creates a die that allows user
 * to define the number of sides
 */
public class Die {
    private int numSides;
    private int currentValue;
    private Random generator = new Random();
    private static final int DEFAULT_SIDES = 6;
    private static final int NO_MORE_THAN_HUNDRED = 100;

    /**
     * Sets up die with appropriate amount of sides
     *
     * @param numSides - number of sides
     */
    public Die(int numSides){
        this.currentValue = generator.nextInt(numSides) + 1;
        this.numSides = numSides;
        if (this.numSides < 2 || this.numSides > NO_MORE_THAN_HUNDRED){
            this.numSides = DEFAULT_SIDES;
        }
    }

    public int getCurrentValue(){
        return currentValue;
    }

    /**
     *  Rolls the die
     */
    public void roll(){
        currentValue = generator.nextInt(numSides) + 1;
    }
}
