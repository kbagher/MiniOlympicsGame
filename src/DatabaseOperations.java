

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
        officials.add(new Official(1, "Jon", GameType.Swimming, "Victoria", 34));
        officials.add(new Official(2, "Khaled", GameType.Running, "New South Wales", 45));
        officials.add(new Official(3, "Kassem", GameType.Cycling, " Queensland", 51));
    }

    /**
     * populate athletes data
     */
    private void populateAthletes() {
        // Swimmers
        athletes.add(new Athlete(1, "David", GameType.Swimming, "Victoria", 23));
        athletes.add(new Athlete(2, "Adel", GameType.Swimming, "Victoria", 18));
        athletes.add(new Athlete(3, "Oliver", GameType.Swimming, "Queensland", 29));
        athletes.add(new Athlete(4, "William", GameType.Swimming, "New South Wales", 31));
        athletes.add(new Athlete(5, "Jack", GameType.Swimming, "Victoria", 23));
        athletes.add(new Athlete(6, "Noah", GameType.Swimming, "New South Wales", 24));

        // Runners
        athletes.add(new Athlete(7, "Thomas", GameType.Running, "Queensland", 25));
        athletes.add(new Athlete(8, "James", GameType.Running, "New South Wales", 28));
        athletes.add(new Athlete(9, "Jackson", GameType.Running, "Victoria", 26));
        athletes.add(new Athlete(10, "Ethan", GameType.Running, "New South Wales", 20));
        athletes.add(new Athlete(11, "Lucas", GameType.Running, "Victoria", 31));

        // Cyclists
        athletes.add(new Athlete(12, "Liam", GameType.Cycling, "Queensland", 32));
        athletes.add(new Athlete(13, "Charlie", GameType.Cycling, "Victoria", 23));
        athletes.add(new Athlete(14, "Henry", GameType.Cycling, "New South Wales", 22));
        athletes.add(new Athlete(15, "Mason", GameType.Cycling, "Victoria", 27));

        // Super Athletes
        athletes.add(new Athlete(16, "Alexander", GameType.SuperAthlete, "Queensland", 29));
        athletes.add(new Athlete(17, "Cooper", GameType.SuperAthlete, "Victoria", 23));
        athletes.add(new Athlete(18, "Samuel", GameType.SuperAthlete, "New South Wales", 24));
        athletes.add(new Athlete(19, "Jacob", GameType.SuperAthlete, "Victoria", 21));
        athletes.add(new Athlete(20, "Leo", GameType.SuperAthlete, "New South Wales", 20));

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
    public ArrayList<Athlete> getAthletesForSport(GameType sport) {

        // populate the data first
        populateData();

        ArrayList<Athlete> list = new ArrayList<>();

        // return superAthletes also
        for (Athlete a : athletes) {
            if (a.getSportType() == sport || ((a.getSportType() == GameType.SuperAthlete))) {
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
    public String createNewGame(GameType type) {
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
     * @param id athlete ID
     * @return athlete reference or null if not found
     */
    public Athlete getAthleteByID(int id){
        for (Athlete a:getAllAthletes()) {
            if (a.getId() == id){
                return a;
            }
        }
        return null;
    }

    /**
     * A
     * @param athlete athlete object to add the score to
     * @param score score
     */
    public void addScoreToAthlete(Athlete athlete,int score){
        for (Athlete a:getAllAthletes()) {
            if (athlete.getId() == a.getId()){
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
    public Official getOfficialForSport(GameType sport) {

        // populate the data first
        populateData();

        for (Official o : officials) {
            if (o.getSportType() == sport)
                return o;
        }
        return null;
    }

}
