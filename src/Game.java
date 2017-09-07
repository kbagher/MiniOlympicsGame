import java.util.ArrayList;

/**
 * Game class holds game information and operations
 *
 * @author Kassem
 */
abstract class Game {

    /**
     * Game official
     */
    private final Official official;
    /**
     * Game ID
     */
    private final String gameID;
    /**
     * Game status
     */
    private GameStatus status;
    /**
     * Game participated athletes
     */
    private ArrayList<Athlete> participatedAthletes;

    /**
     * Athlete compete in game.
     * <p>
     * Depending on game type, the method will return a random number which represents
     * the athletes time in this game.
     * </p>
     *
     * @return random time
     */
    protected abstract int compete();

    /**
     * Returns a random athlete from the database.
     * <p> the athlete type will be determined by the subclass</p>
     *
     * @return the random athlete
     */
    protected abstract Athlete getRandomAthlete();

    /**
     * Return sport name
     *
     * @return sport name
     */
    public abstract String getSportName();

    /**
     * Instantiates a new Game.
     *
     * @param gameID   game id
     * @param official game official
     */
    public Game(String gameID, Official official) {
        this.official = official;
        this.gameID = gameID;
        this.status = GameStatus.Waiting;
        this.participatedAthletes = new ArrayList<>();
    }

    /**
     * Gets game's official
     *
     * @return game official
     */
    public Official getOfficial() {
        return official;
    }

    /**
     * Gets game id.
     *
     * @return the game id
     */
    public String getGameID() {
        return gameID;
    }

    /**
     * Gets game status.
     *
     * @return game status
     */
    public GameStatus getStatus() {
        return status;
    }

    /**
     * Gets participated athletes in the game.
     *
     * @return game participated athletes
     */
    public ArrayList<Athlete> getParticipatedAthletes() {
        return participatedAthletes;
    }

    /**
     * <p>Displays game results.</p>
     *
     * Depending on the game, the method will either display:
     * <ol>
     * <li>Participated athletes (if game is canceled or not run yet)</li>
     * <li>Athletes results (if game is completed)</li>
     * </ol>
     *
     * @param extraMessage extra message to be displayed
     */
    public void displayResults(String extraMessage) {
        displayGameInfo(); // display game information

        if (!extraMessage.isEmpty())
            System.out.println(extraMessage);

        if (status == GameStatus.Canceled || status == GameStatus.Waiting)
            displayParticipatedAthletes();
        else // game completed
            displayAthletesRankByTime();
    }

    /**
     * Displays general information about the game
     */
    private void displayGameInfo() {
        System.out.println(); // new line as separator
        System.out.println("Game ID: " + gameID);
        System.out.println("Game type: " + getSportName());
        System.out.println("Game status: " + status.toString());
        System.out.println("Referee ID: " + official.getId());
        System.out.println("Referee name: " + official.getName() + "\n");

    }

    /**
     * Run game.
     *
     * Running a game will results in on of the following status:
     * <ol>
     *     <li>(Canceled) if the number of participant below 5 </li>
     *     <li>(Completed) if the game run successfully</li>
     * </ol>
     *
     * @return game status after running
     */
    public GameStatus run() {
        if (!haveEnoughParticipants()) { // no enough participants
            status = GameStatus.Canceled; // set status as canceled
            return status;
        }

        status = GameStatus.Running; // game is running

        // random compete time for participated athletes
        for (Athlete athlete : participatedAthletes) {
            athlete.setTime(compete());
        }

        // rank (sort) athletes based on time
        participatedAthletes = official.summarizeResultsByTime(participatedAthletes);
        // set athletes score based on rank
        setAthletesScores();


        status = GameStatus.Completed;
        return status;
    }

    /**
     * Check if game has enough participants or not
     * @return tru if 5 or above
     */
    private boolean haveEnoughParticipants() {
        int minParticipants = 5;
        return participatedAthletes.size() >= minParticipants;
    }

    /**
     * Display athletes information based on their compete time in the game
     */
    private void displayAthletesRankByTime() {
        // insuring athletes are sorted based on time
        official.summarizeResultsByTime(participatedAthletes);

        System.out.printf("%-5s %-20s %-5s %n", "Rank", "Name", "Time");
        int index = 0;
        for (Athlete athlete : participatedAthletes) {
            System.out.printf("%-5s %-20s %-5s %n", index + 1, athlete.getName(), athlete.getTime());
            index++;
        }
        System.out.println();
    }


    /**
     * Display participated athletes information.
     */
    public void displayParticipatedAthletes() {
        System.out.printf("%-5s %-20s %-5s %n", "#", "Name", "ID");
        int index = 0;
        for (Athlete athlete : participatedAthletes) {
            System.out.printf("%-5s %-20s %-5s %n", index + 1, athlete.getName(), athlete.getId());
            index++;
        }
        System.out.println();
    }

    /**
     * Add random athletes to the game.
     */
    public void addAthletes() {
        // random number of athletes between 1 and 8
        int numOfParticipants = GeneralFunctions.getInstance().getRandomNumber(1, 8);
        while (participatedAthletes.size() != numOfParticipants) {
            Athlete a = getRandomAthlete();
            if (!participatedAthletes.contains(a))
                participatedAthletes.add(a);
        }
    }

    /**
     * Gets a participated athlete by id.
     *
     * @param athleteID athlete id
     *
     * @return a participated athlete
     */
    public Athlete getParticipatedAthleteByID(int athleteID) {
        for (Athlete a : participatedAthletes) {
            if (a.getId() == athleteID)
                return a;
        }
        return null;
    }

    /**
     * Gets the winner athlete of the game.
     *
     * @return the winner
     */
    public Athlete getWinner() {
        // insure participated athletes are sorted by time
        official.summarizeResultsByTime(participatedAthletes);
        return participatedAthletes.get(0); // top athlete
    }

    /**
     * Give scores to the first 3 athletes
     */
    private void setAthletesScores() {
        // must be sorted first
        official.summarizeResultsByTime(participatedAthletes);

        participatedAthletes.get(0).setScore(5);
        participatedAthletes.get(1).setScore(2);
        participatedAthletes.get(2).setScore(1);
    }
}
