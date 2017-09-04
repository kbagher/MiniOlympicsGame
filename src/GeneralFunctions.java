import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Waiting by kassem on 1/9/17.
 */
public class GeneralFunctions {
    private static GeneralFunctions instance = new GeneralFunctions();
    private Scanner in = new Scanner(System.in);
    private GeneralFunctions(){}

    public static GeneralFunctions getInstance(){
        return instance;
    }

    public int getRandomNumber(int min,int max){

        assert min <= max;

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean isInRange(@SuppressWarnings("SameParameterValue") int min, int max, int num){
        return !(num > max || num < min);
    }

    // User Interaction
    public void displayCannotRunGameMessage(){
        System.out.println("\n\n========================");
        System.out.println("No enough players to run the game");
        System.out.println("========================\n\n");
    }

    public void pressToContinue(){
        System.out.println("Press return to continue...\n\n");
        in.nextLine();
    }
}
