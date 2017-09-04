
/*In this class  user will interact with the program from this class.
 *  User will enter the choice and the choice will be checked automatically by the program by inputValidation method.
 */

/*  @version 3 04 April 2017
*   @author Abdulaziz Bazuhayr & Turki Aljandal
*    * Copyright  2017  All Rights Reserved. *
* the proprietary information of this program is confidential . You shall not
* use it only if you have written permission from Turki al jandal & Abdulaziz Bazuhayr
* we will not be liable any damage suffered as result of using this program */


import java.util.ArrayList;
import java.util.Scanner;

public class Driver {

    Scanner scan;
    Athlete userPrediction;
    // remove
    int usrPrediction;

    Game[] sportArray;
    ArrayList<Game> games = new ArrayList<>();
    int sportsIndex;
    int swimmingCode;
    int cyclingCode;
    int runningCode;
    Participant[] athletesArray;

    public Driver() {
        scan = new Scanner(System.in);
        usrPrediction = -1;
        sportArray = new Game[20];
        sportsIndex = 0;
        swimmingCode = 1;
        cyclingCode = 1;
        runningCode = 1;
        athletesArray = Game.getAthletesArray();
    }

    public void displayMainMenu() {
        int userInput = -1;

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
                continue;
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
                    displayTheFinalResults();
                    break;
                case 5:
                    displayAthletesPoints();
                    break;
            }
        }
    }

    private int getUserInput() {
        try {
            System.out.print("Enter an option: ");
            int in = Integer.parseInt(scan.nextLine());
            return in;
        } catch (Exception e) {
            scan.next();
            return -1;
        }
    }

    public void pressToContinue() {
        System.out.println("Press return to continue...\n\n");
        scan.nextLine();
    }

    private void displayOutOfRangeOptionMessage(int min, int max) {
        System.out.println("\nPlease enter a number between " + min + " and " + max + "\n");
        pressToContinue();
    }

    private void selectGame() {

        if (games.size() > 0) {
            if (getLastGame().status == GameStatus.Waiting) {
                displayGameAlreadySelected();
                return;
            }
        }

        System.out.println("\nPlease Choose a game from the list below\n" +
                "1. Running\n" +
                "2. Cycling\n" +
                "3. Swimming\n");

        int userInput = getUserInput();
        if (!GeneralFunctions.getInstance().isInRange(1, 3, userInput)) {
            displayOutOfRangeOptionMessage(1, 3);
            return;
        }

        // reset prediction
        userPrediction = null;
        // remove
        usrPrediction = -1;
        switch (userInput) {
            case 1:
                Running r = new Running();
                r.addAthletes();
                games.add(r);
                sportArray[sportsIndex] = r;
                break;
            case 2:
                Cycling c = new Cycling();
                c.addAthletes();
                sportArray[sportsIndex] = c;
                games.add(c);
                break;
            case 3:
                Swimming s = new Swimming();
                s.addAthletes();
                sportArray[sportsIndex] = s;
                games.add(s);
                break;
        }

    }

    private Game getLastGame() {
        if (games.size() > 0) {
            return games.get(games.size() - 1);
        } else {
            return null;
        }
    }

    private void displaySelectGame() {
        System.out.println("\nYou need to select a game first\n\n");
        pressToContinue();
    }

    private void displayInvalidID() {
        System.out.println("\nInvalid athlete ID\n\n");
        pressToContinue();
    }

    private void displayGameAlreadySelected() {
        System.out.println("\nA " + games.get(games.size() - 1).getSportName() + " game is already selected\n");
        pressToContinue();
    }

    private void displayMakeAPrediction() {
        System.out.println("\nYou need to make a prediction first\n");
        pressToContinue();
    }

    private void startGame() {
        Game game = getLastGame();

        if (game != null) {
            if (game.status != GameStatus.Waiting) {
                displaySelectGame();
                return;
            }
            if (userPrediction == null) {
                displayMakeAPrediction();
                return;
            }
        } else {
            displaySelectGame();
            return;
        }

        GameStatus status = game.run();

        if (status == GameStatus.Canceled) {
            System.out.println("\nNo enough players! The game has been canceled.");
            game.displayResults();
            pressToContinue();
            return;
        }
        else if (status == GameStatus.Completed) {
            System.out.println();
            game.displayAthletesRankByTime();
            pressToContinue();
        }
    }

    private void displayTheFinalResults() {
        // To make sure there are games played
        if (sportsIndex > 0) {
            System.out.println("The final results of all games played today are:");
            // Check each index played not the whole array
            for (int i = 0; i < sportsIndex; i++) {
                // check each object in array and get its results
                sportArray[i].displayResults();
            }
        } else
            System.out.println("There is no games played today !!\n");

    }

    private void makePrediction() {

        if (games.size() > 0) {
            if (getLastGame().status != GameStatus.Waiting) {
                displaySelectGame();
                return;
            }
        } else {
            displaySelectGame();
            return;
        }

        Game game = getLastGame();

        while (userPrediction == null) {
            System.out.println("Select an athlete by entering the ID below\n");
            game.displayParticipatedAthletes();
            int athleteID = getUserInput();
            if ((userPrediction = game.getParticipatedAthleteByID(athleteID)) == null) {
                displayInvalidID();
                continue;
            }
            break;
        }
    }

    private void displayAthletesPoints() {
        // To declare referee to give the points
        Participant headOfficial = new Official();
        // Casting for headOfficial to use summerizeFinalresultsByScore method
        athletesArray = ((Official) headOfficial).summerizeFinalresultsByScore(athletesArray);
        System.out.println("Rank Name         Score\n");
        int i = 1;
        // for each loop to check all the array
        for (Participant athlete : athletesArray) {
            System.out.println(addWhitespaces(i + "." + athlete.getName()) + ""
                    + ((Athlete) athlete).getScore() + "\n");
            i++;
        }
    }

    // To increase the index of each game to have different GameId
    private void gameCodeIncrement(String sport) {
        switch (sport) {
            case "Swimming":
                swimmingCode++;
                break;
            case "Running":
                runningCode++;
                break;
            default:
                cyclingCode++;
                break;
        }

    }

    public String addWhitespaces(String name) {
        int nameLength = name.length();
        int NOWhitespaces = 18 - nameLength;
        String nameWithSpaces = name;
        for (int i = 0; i < NOWhitespaces; i++) {
            nameWithSpaces = nameWithSpaces + " ";
        }
        return nameWithSpaces;
    }

}
