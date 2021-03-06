
import java.util.ArrayList;

/**
 * Swimming game class contains implementation for abstract
 * methods in game class.
 *
 * @author Khaled
 */
public class Swimming extends Game {

    /**
     * The Swimmers array.
     */
    Participant[] swimmersArray;

    /**
     * Instantiates a new Swimming.
     */
    public Swimming() {
        super(DatabaseOperations.getInstance().createNewGame(SportType.Swimming), DatabaseOperations.getInstance().getOfficialForSport(SportType.Swimming));
    }

    @Override
    public int compete() {
        return GeneralFunctions.getInstance().getRandomNumber(100, 200);
    }

    @Override
    public String getSportName() {
        return "Swimming";
    }

    @Override
    public Athlete getRandomAthlete() {
        ArrayList<Athlete> athletes = DatabaseOperations.getInstance().getAthletesForSport(SportType.Swimming);
        int random = GeneralFunctions.getInstance().getRandomNumber(0, athletes.size() - 1);
        return athletes.get(random);
    }

}
