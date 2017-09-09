
import java.util.ArrayList;

/**
 * Running game class contains implementation for abstract
 * methods in game class.
 *
 * @author Khaled
 */
class Running extends Game {

    /**
     * Instantiates a new Running.
     */
    public Running() {
        super(DatabaseOperations.getInstance().createNewGame(SportType.Running), DatabaseOperations.getInstance().getOfficialForSport(SportType.Running));
    }

    @Override
    public int compete() {
        return GeneralFunctions.getInstance().getRandomNumber(10, 20);
    }

    @Override
    public String getSportName() {
        return "Running";
    }

    @Override
    public Athlete getRandomAthlete() {
        ArrayList<Athlete> athletes = DatabaseOperations.getInstance().getAthletesForSport(SportType.Running);
        int random = GeneralFunctions.getInstance().getRandomNumber(0, athletes.size() - 1);
        return athletes.get(random);
    }

}
