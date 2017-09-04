import java.util.ArrayList;

abstract class Game {

    Official official;
    String gameID;
    GameStatus status;
    private ArrayList<Athlete> participatedAthletes;

    protected abstract int compete();

    protected abstract Athlete getRandomAthlete();


    public abstract String getSportName();

    Game() {
        status = GameStatus.Waiting;
        participatedAthletes = new ArrayList<>();
    }

    public ArrayList<Athlete> getParticipatedAthletes() {
        return participatedAthletes;
    }

    public void displayResults() {
        displayGameInfo();
        if (status == GameStatus.Canceled || status == GameStatus.Waiting)
            displayParticipatedAthletes();
        else
            displayAthletesRankByTime();
    }


    private void displayGameInfo(){
        System.out.println("\nGame ID: " + gameID);
        System.out.println("Game type: " + getSportName());
        System.out.println("Game status: " + status.toString());
        System.out.println("Referee ID: " + official.getId());
        System.out.println("Referee name: " + official.getName() + "\n");

    }

    public GameStatus run() {
        if (!haveEnoughParticipants()) {
            status = GameStatus.Canceled;
            return status;
        }

        status = GameStatus.Running;

        for (Athlete athlete : participatedAthletes) {
            athlete.setTime(compete());
        }

        participatedAthletes = official.summerizeResultsByTime(participatedAthletes);

        setAthletesScores();

        status = GameStatus.Completed;

        return status;
    }

    private boolean haveEnoughParticipants() {
        int minParticipants = 5;
        return participatedAthletes.size() >= minParticipants;
    }

    private void displayAthletesRankByTime() {
        displayGameInfo();
        official.summerizeResultsByTime(participatedAthletes);
        System.out.printf("%-5s %-20s %-5s %n", "Rank", "Name", "Time",
                "total");
        int index = 0;
        for (Athlete athlete : participatedAthletes) {
            System.out.printf("%-5s %-20s %-5s %n", index + 1, athlete.getName(), athlete.getTime());
            index++;
        }
        System.out.println();
    }


    public void displayParticipatedAthletes() {
        System.out.printf("%-5s %-20s %-5s %n", "#", "Name", "ID",
                "total");
        int index = 0;
        for (Athlete athlete : participatedAthletes) {
            System.out.printf("%-5s %-20s %-5s %n", index + 1, athlete.getName(), athlete.getId());
            index++;
        }
        System.out.println();
    }

    public void addAthletes() {
        int numOfParticipants = GeneralFunctions.getInstance().getRandomNumber(1, 8);
        while (participatedAthletes.size() != numOfParticipants) {
            Athlete a = getRandomAthlete();
            if (!participatedAthletes.contains(a))
                participatedAthletes.add(a);
        }
    }


    public Athlete getParticipatedAthleteByID(int athleteID) {
        for (Athlete a : participatedAthletes) {
            if (a.getId() == athleteID)
                return a;
        }
        return null;
    }

    public Athlete getWinner() {
        // must be sorted first
        official.summerizeResultsByTime(participatedAthletes);
        return participatedAthletes.get(0);
    }

    // must be sorted
    private void setAthletesScores() {
        // must be sorted first
        official.summerizeResultsByTime(participatedAthletes);

        participatedAthletes.get(0).setScore(5);
        participatedAthletes.get(1).setScore(2);
        participatedAthletes.get(2).setScore(1);
    }
}
