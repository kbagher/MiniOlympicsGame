
import java.util.ArrayList;

/**
 * Cycling game class contains implementation for abstract
 * methods in game class.
 *
 * @author Khalid
 */
public class Cycling extends Game {

    /**
     * Instantiates a new Cycling.
     */
    public Cycling() {
        super(DatabaseOperations.getInstance().createNewGame(GameType.Cycling), DatabaseOperations.getInstance().getOfficialForSport(GameType.Cycling));
    }

    @Override
    public int compete() {
        return GeneralFunctions.getInstance().getRandomNumber(500, 800);
    }

    @Override
    public String getSportName() {
        return "Cycling";
    }

    @Override
    public Athlete getRandomAthlete() {
        ArrayList<Athlete> athletes = DatabaseOperations.getInstance().getAthletesForSport(GameType.Cycling);
        int random = GeneralFunctions.getInstance().getRandomNumber(0, athletes.size() - 1);
        return athletes.get(random);
    }
}
