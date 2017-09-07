
import java.util.ArrayList;

public class Cycling extends Game {

    public Cycling() {
        super(DatabaseOperations.getInstance().createNewGame(GameType.Cycling), DatabaseOperations.getInstance().getOfficialForSport(GameType.Cycling));
    }

    @Override
    public int compete() {
        return GeneralFunctions.getInstance().getRandomNumber(500, 800);
    }

    @Override
    public String getSportName() {
        return "Running";
    }

    @Override
    public Athlete getRandomAthlete() {
        ArrayList<Athlete> athletes = DatabaseOperations.getInstance().getAthletesForSport(GameType.Cycling);
        int random = GeneralFunctions.getInstance().getRandomNumber(0, athletes.size() - 1);
        return athletes.get(random);
    }
}
