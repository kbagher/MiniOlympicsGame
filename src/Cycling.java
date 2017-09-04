
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Cycling extends Game {
	Participant[] cyclistsArray;

	public Cycling() {
		this.gameID = DatabaseOperations.getInstance().createNewGame(GameType.Cycling);
		official = DatabaseOperations.getInstance().getOfficialForSport(GameType.Cycling);
	}

	@Override
	public int compete() {
		return GeneralFunctions.getInstance().getRandomNumber(500,800);
	}

	@Override
	public String getSportName() {
		return "Running";
	}

	@Override
	@Deprecated
	public Participant[] displayParticipants() {
		System.out.println("No  Name         id");
		int i = 1;
		for (Participant athlete : cyclistsArray) {
//			System.out.println(i + ".  " + addWhitespaces(athlete.getName()) + "       " + athlete.getId());
			i++;
		}
		return cyclistsArray;
	}

	@Override
	public Athlete getRandomAthlete() {
		ArrayList<Athlete> athletes = DatabaseOperations.getInstance().getAthletesForSport(GameType.Cycling,true);
		int random = GeneralFunctions.getInstance().getRandomNumber(0,athletes.size()-1);
		return athletes.get(random);
	}
}
