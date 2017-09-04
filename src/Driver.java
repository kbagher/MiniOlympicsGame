
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

    private Scanner scan;
    private Athlete userPrediction;
    private ArrayList<Game> games;

    public Driver() {
        scan = new Scanner(System.in);
        games = new ArrayList<>();
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
                    displayAllResults();
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
            return Integer.parseInt(scan.nextLine());
        } catch (Exception e) {
            scan.next();
            return -1;
        }
    }

    private void pressToContinue() {
        System.out.println("Press return to continue...\n\n");
        scan.nextLine();
    }

    private void displayOutOfRangeOptionMessage(@SuppressWarnings("SameParameterValue") int min, int max) {
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

    private Game getLastGame() {
        if (games.size() > 0) {
            return games.get(games.size() - 1);
        } else {
            return null;
        }
    }

    private void displayNoGamesPlayed() {
        System.out.println("\nNO games played yet.\n\n");
        pressToContinue();
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
        }
        else if (status == GameStatus.Completed) {
            System.out.println();
            game.displayResults();
            pressToContinue();
        }
    }

    private void displayAllResults() {
        if (games.size()==0){
            displayNoGamesPlayed();
            return;
        }

        System.out.println("================================");
        for (Game game:games) {
            game.displayResults();
        }
        System.out.println("================================\n");
        pressToContinue();
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
        Official bigBoss = new Official();
        ArrayList<Athlete> athletes= DatabaseOperations.getInstance().getAllAthletes();
        bigBoss.summerizeResultsByScore(athletes);

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
