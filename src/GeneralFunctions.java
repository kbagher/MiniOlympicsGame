import java.util.Random;

/**
 * General functions used across the program
 *
 * @author Khaled
 */
public class GeneralFunctions {
    /**
     * Static object following singleton pattern
     */
    private static final GeneralFunctions instance = new GeneralFunctions();
    /**
     * Private constructor to disable instantiation
     */
    private GeneralFunctions() {
    }

    /**
     * Gets object instance.
     *
     * @return the instance
     */
    public static GeneralFunctions getInstance() {
        return instance;
    }

    /**
     * <p>Gets random number.</p>
     *
     * Min and max are included.
     *
     * @param min minimum number
     * @param max maximum number
     *
     * @return the random number
     */
    public int getRandomNumber(int min, int max) {

        // insure min is always smaller than max
        assert min <= max;

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    /**
     * Check if a given number is within the specified range
     *
     * @param min range start
     * @param max range end
     * @param num number to check
     *
     * @return true if number is within range
     */
    public boolean isInRange(@SuppressWarnings("SameParameterValue") int min, int max, int num) {
        return !(num > max || num < min);
    }

}
