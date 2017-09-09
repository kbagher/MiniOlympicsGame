
import java.util.ArrayList;
import java.util.Scanner;

/**
 * <p>Ozlympic driver class.</p>
 * <p>
 * This class will start, manage games and interact with the user
 *
 * @author Khaled
 */
public class Driver {

    /**
     * Used to get user's input
     */
    private final Scanner scan;
    /**
     * Holds user's game prediction
     */
    private Athlete userPrediction;
    /**
     * Holds all ran games and their information
     */
    private final ArrayList<Game> games;

    /**
     * Instantiates a new Driver.
     */
    public Driver() {
        scan = new Scanner(System.in);
        games = new ArrayList<>();
    }

    /**
     * Displays the main menu to the user.
     */
    public void displayMainMenu() {

        int userInput = -1;

        // exits the main menu if the user enters 6
        while (userInput != 6) {
            System.out.println("\n\nOzlympic Game\n" +
                    "===================================\n" +
                    "1. Select a game to run\n" +
                    "2. Predict the winner of the game\n" +
                    "3. Start the game\n" +
                    "4. Display the final results of all games\n" +
                    "5. Display the points of all athletes\n" +
                    "6. Exit\n");

            userInput = getUserInput();

            if (!GeneralFunctions.getInstance().isInRange(1, 6, userInput)) {
                displayOutOfRangeOptionMessage(1, 6);
                continue; // wrong user input, display the menu again
            }
            switch (userInput) {
                case 1:
                    selectGame();
                    break;
                case 2:
                    makePrediction();
                    break;
                case 3:
                    startGame();
                    break;
                case 4:
                    displayAllResults();
                    break;
                case 5:
                    displayAthletesPoints();
                    break;
            }
        }
    }

    /**
     * Get the users input
     *
     * @return users option, -1 if any error occurred
     */
    private int getUserInput() {
        try {
            System.out.print("Enter an option: ");
            return Integer.parseInt(scan.nextLine());
        } catch (Exception e) {
            scan.next();
            return -1;
        }
    }

    /**
     * Display press to continue message
     */
    private void pressToContinue() {
        System.out.println("Press return to continue...\n\n");
        scan.nextLine();
    }

    /**
     * Displays input is out of range message to the user
     *
     * @param min minimum input (included in range)
     * @param max maximum input (included in range)
     */
    private void displayOutOfRangeOptionMessage(int min, int max) {
        System.out.println("\nPlease enter a number between " + min + " and " + max + "\n");
        pressToContinue();
    }

    /**
     * Displays select a game menu to the user
     */
    private void selectGame() {

        if (games.size() > 0) {
            if (getLastGame().getStatus() == GameStatus.Waiting) {
                displayGameAlreadySelected();
                return; // game already selected
            }
        }

        System.out.println("\nPlease Choose a game from the list below\n" +
                "1. Running\n" +
                "2. Cycling\n" +
                "3. Swimming\n");

        int userInput = getUserInput();
        if (!GeneralFunctions.getInstance().isInRange(1, 3, userInput)) {
            displayOutOfRangeOptionMessage(1, 3);
            return; // user's input out of range
        }

        // reset user's prediction
        userPrediction = null;

        switch (userInput) {
            case 1:
                Running r = new Running();
                r.addAthletes();
                games.add(r);
                break;
            case 2:
                Cycling c = new Cycling();
                c.addAthletes();
                games.add(c);
                break;
            case 3:
                Swimming s = new Swimming();
                s.addAthletes();
                games.add(s);
                break;
        }

    }

    /**
     * <p>Gets last game</p>
     * <p>
     * This method will return the last game object in the games list.
     *
     * @return game object and null if no games selected yet.
     */
    private Game getLastGame() {
        if (games.size() > 0) {
            return games.get(games.size() - 1);
        } else {
            return null;
        }
    }

    /**
     * Displays no games selected yet message to the user
     */
    private void displayNoGamesPlayed() {
        System.out.println("\nNO games played yet.\n\n");
        pressToContinue();
    }

    /**
     * Displays you need to select a game first message to the user
     */
    private void displaySelectGame() {
        System.out.println("\nYou need to select a game first\n\n");
        pressToContinue();
    }

    /**
     * Displays invalid athlete ID message to the user
     */
    private void displayInvalidID() {
        System.out.println("\nInvalid athlete ID\n\n");
        pressToContinue();
    }

    /**
     * Displays a game is already selected message to the user
     */
    private void displayGameAlreadySelected() {
        System.out.println("\nA " + games.get(games.size() - 1).getSportName() + " game is already selected\n");
        pressToContinue();
    }

    /**
     * Displays you need to make a prediction first message to the user
     */
    private void displayMakeAPrediction() {
        System.out.println("\nYou need to make a prediction first\n");
        pressToContinue();
    }

    /**
     * Start the game
     */
    private void startGame() {

        Game game = getLastGame();

        if (game != null) { // game is available
            if (game.getStatus() != GameStatus.Waiting) {
                displaySelectGame();
                return;  // game is available but either completed or canceled
            }
            if (userPrediction == null) {
                displayMakeAPrediction();
                return; // no prediction has been made
            }
        } else { // no game has been selected yet
            displaySelectGame();
            return;
        }

        GameStatus status = game.run();

        if (status == GameStatus.Canceled) { // canceled due to low participants
            System.out.println("\nNo enough players! The game has been canceled.");
            game.displayResults("");
            pressToContinue();
        } else if (status == GameStatus.Completed) { // game ran successfully
            System.out.println();

            String msg = "Your prediction is wrong :(\n";

            // compare by id to avoid problems with different objects
            if (userPrediction.getId() == game.getWinner().getId())
                msg = "Your prediction is right :)\n";

            game.displayResults(msg);

            pressToContinue();
        }
    }

    /**
     * Displays all games with results
     */
    private void displayAllResults() {
        if (games.size() == 0) {
            displayNoGamesPlayed();
            return; // no game has been ran
        }

        System.out.println("================================");
        for (Game game : games) {
            game.displayResults("");
        }
        System.out.println("================================\n");
        pressToContinue();
    }

    /**
     * Make a winner prediction
     */
    private void makePrediction() {

        if (games.size() > 0) {
            if (getLastGame().getStatus() != GameStatus.Waiting) {
                displaySelectGame();
                return; // game is available but either completed or canceled
            }
        } else {
            displaySelectGame();
            return; // no game has been selected
        }

        Game game = getLastGame();

        while (userPrediction == null) {
            System.out.println("Select an athlete by entering the ID below\n");

            game.displayParticipatedAthletes();

            int athleteID = getUserInput();
            if ((userPrediction = game.getParticipatedAthleteByID(athleteID)) == null) {
                displayInvalidID();
                continue; // user did input an invalid athlete ID
            }
            break;
        }
    }

    /**
     * <p>Displays all athletes with their scores</p>
     */
    private void displayAthletesPoints() {
        // dummy official
        Official gameOfficial = DatabaseOperations.getInstance().getOfficialForSport(SportType.Running);

        ArrayList<Athlete> athletes = DatabaseOperations.getInstance().getAllAthletes();
        gameOfficial.summarizeResultsByScore(athletes);

        System.out.printf("\n%-5s %-20s %-5s %n", "Rank", "Name", "Score",
                "total");
        int index = 0;
        for (Athlete athlete : athletes) {
            System.out.printf("%-5s %-20s %-5s %n", index + 1, athlete.getName(), athlete.getScore());
            index++;
        }
        System.out.println();
        pressToContinue();
    }

}