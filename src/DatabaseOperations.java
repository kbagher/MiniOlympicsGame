//the data input will be read from this class as a hard coded data collection.

/*  @version 2 01 April 2017 
*  @author Abdulaziz Bazuhayr & Turki Ajandal
*   * Copyright  2017  All Rights Reserved. *
* the proprietary information of this program is confidential . You shall not
* use it only if you have written permission from Turki al jandal & Abdulaziz Bazuhayr
* we will not be liable any damage suffered as result of using this program */

import java.util.ArrayList;

public class DatabaseOperations {

    private boolean isPopulated = false;
    private static final DatabaseOperations instance = new DatabaseOperations();
    private ArrayList<Athlete> athletes = new ArrayList<>();
    private ArrayList<Official> officials = new ArrayList<>();
    private int swimmingID = 0;
    private int runningID = 0;
    private int cyclingID = 0;

    private DatabaseOperations(){}

    public static DatabaseOperations getInstance() {
        return instance;
    }

    private void populateData() {
        if (isPopulated)
            return;

        populateOfficials();
        populateAthletes();
        isPopulated = true;
    }

    private void populateOfficials() {
        officials.add(new Official(1, "Jon", GameType.Swimming, "Victoria", 34));
        officials.add(new Official(2, "Khaled", GameType.Running, "New South Wales", 45));
        officials.add(new Official(3, "Kassem", GameType.Cycling, " Queensland", 51));
    }

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

    public ArrayList<Athlete> getAllAthletes() {
        populateData();
        return athletes;
    }

    public ArrayList<Athlete> getAthletesForSport(GameType sport, boolean superAthlete) {
        populateData();

        ArrayList<Athlete> list = new ArrayList<>();

        for (Athlete a : athletes) {
            if (a.getSportType() == sport || (superAthlete ? (a.getSportType() == GameType.SuperAthlete) : false))
                list.add(a);
        }

        return list;
    }

    public String createNewGame(GameType type) {
        // perform necessary database operations later
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


    public Official getOfficialForSport(GameType sport) {
        populateData();
        if (officials.size() == 0)
            populateOfficials();

        // Should be replaced by database call
        for (Official o : officials) {
            if (o.getSportType() == sport)
                return o;
        }
        return null;
    }

}
