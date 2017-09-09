

import java.util.ArrayList;

/**
 * <p>Maintain database operations and connections.</p>
 * <p>
 * The implementation of this class will be changed for assignment 2.
 *
 * @author Kassem
 */
public class DatabaseOperations {

    /**
     * Determine if data is already populated or not
     */
    private boolean isPopulated = false;
    /**
     * Static object following singleton pattern
     */
    private static final DatabaseOperations instance = new DatabaseOperations();
    /**
     * List of all available athletes in the database
     */
    private final ArrayList<Athlete> athletes = new ArrayList<>();
    /**
     * List of all available officials in the database
     */
    private final ArrayList<Official> officials = new ArrayList<>();
    /**
     * Maintains swimming game id
     */
    private int swimmingID = 0;
    /**
     * Maintains running game id
     */
    private int runningID = 0;
    /**
     * Maintains cycling game id
     */
    private int cyclingID = 0;

    /**
     * Disable instantiation following the singleton pattern
     */
    private DatabaseOperations() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static DatabaseOperations getInstance() {
        return instance;
    }

    /**
     * populate dummy data
     */
    private void populateData() {
        if (isPopulated)
            return;

        populateOfficials();
        populateAthletes();
        isPopulated = true;
    }

    /**
     * populate officials data
     */
    private void populateOfficials() {
        officials.add(new Official(1, "Jon", SportType.Swimming, "Victoria", 34));
        officials.add(new Official(2, "Khaled", SportType.Running, "New South Wales", 45));
        officials.add(new Official(3, "Kassem", SportType.Cycling, " Queensland", 51));
    }

    /**
     * populate athletes data
     */
    private void populateAthletes() {
        // Swimmers
        athletes.add(new Athlete(1, "David", SportType.Swimming, "Victoria", 23));
        athletes.add(new Athlete(2, "Adel", SportType.Swimming, "Victoria", 18));
        athletes.add(new Athlete(3, "Oliver", SportType.Swimming, "Queensland", 29));
        athletes.add(new Athlete(4, "William", SportType.Swimming, "New South Wales", 31));
        athletes.add(new Athlete(5, "Jack", SportType.Swimming, "Victoria", 23));
        athletes.add(new Athlete(6, "Noah", SportType.Swimming, "New South Wales", 24));

        // Runners
        athletes.add(new Athlete(7, "Thomas", SportType.Running, "Queensland", 25));
        athletes.add(new Athlete(8, "James", SportType.Running, "New South Wales", 28));
        athletes.add(new Athlete(9, "Jackson", SportType.Running, "Victoria", 26));
        athletes.add(new Athlete(10, "Ethan", SportType.Running, "New South Wales", 20));
        athletes.add(new Athlete(11, "Lucas", SportType.Running, "Victoria", 31));

        // Cyclists
        athletes.add(new Athlete(12, "Liam", SportType.Cycling, "Queensland", 32));
        athletes.add(new Athlete(13, "Charlie", SportType.Cycling, "Victoria", 23));
        athletes.add(new Athlete(14, "Henry", SportType.Cycling, "New South Wales", 22));
        athletes.add(new Athlete(15, "Mason", SportType.Cycling, "Victoria", 27));

        // Super Athletes
        athletes.add(new Athlete(16, "Alexander", SportType.SuperAthlete, "Queensland", 29));
        athletes.add(new Athlete(17, "Cooper", SportType.SuperAthlete, "Victoria", 23));
        athletes.add(new Athlete(18, "Samuel", SportType.SuperAthlete, "New South Wales", 24));
        athletes.add(new Athlete(19, "Jacob", SportType.SuperAthlete, "Victoria", 21));
        athletes.add(new Athlete(20, "Leo", SportType.SuperAthlete, "New South Wales", 20));

    }

    /**
     * Gets all athletes in the database.
     *
     * @return list of available athletes
     */
    public ArrayList<Athlete> getAllAthletes() {
        populateData();
        return athletes;
    }

    /**
     * Gets athletes for a specific sport.
     *
     * @param sport sport type
     *
     * @return the athletes for sport
     */
    public ArrayList<Athlete> getAthletesForSport(SportType sport) {

        // populate the data first
        populateData();

        ArrayList<Athlete> list = new ArrayList<>();

        // return superAthletes also
        for (Athlete a : athletes) {
            if (a.getSportType() == sport || ((a.getSportType() == SportType.SuperAthlete))) {
                list.add(a);
            }
        }
        return list;
    }

    /**
     * <p>Create a new game.</p>
     * <p>
     * <p>This will add a new record in the database and return the game ID.</p>
     *
     * @param type game type to be created
     *
     * @return game ID
     */
    public String createNewGame(SportType type) {
        switch (type) {
            case Swimming:
                return String.format("S%02d", ++swimmingID);
            case Running:
                return String.format("R%02d", ++runningID);
            case Cycling:
                return String.format("C%02d", ++cyclingID);
            default:
                return "";
        }
    }

    /**
     * Gets reference to athlete object by ID
     *
     * @param id athlete ID
     *
     * @return athlete reference or null if not found
     */
    public Athlete getAthleteByID(int id) {
        for (Athlete a : getAllAthletes()) {
            if (a.getId() == id) {
                return a;
            }
        }
        return null;
    }

    /**
     * Adds score to the total athlete's score
     *
     * @param athlete athlete object to add the score to
     * @param score   score
     */
    public void addScoreToAthlete(Athlete athlete, int score) {
        for (Athlete a : getAllAthletes()) {
            if (athlete.getId() == a.getId()) {
                a.setScore(score);
            }
        }
    }

    /**
     * Gets official for a specific sport.
     *
     * @param sport sport type
     *
     * @return game official
     */
    public Official getOfficialForSport(SportType sport) {

        // populate the data first
        populateData();

        for (Official o : officials) {
            if (o.getSportType() == sport)
                return o;
        }
        return null;
    }

}
