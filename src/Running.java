/*
* The subclass RUNNING  will inherit from its superclass Sport and will concrete abstract methods and
*  other methods which  are related with RUNNING sport only will be implemented in this class.
*  For example, compete method will be invoked in Running class based on the time that will randomly
*  generate  between 10 to 20 seconds 
*  
*   
*  @version 3 04 April 2017 
*  @author TURKI ALJANDAL
*  @ reviewed by bdulaziz Bazuhayr 
*   * Copyright  2017  All Rights Reserved. *
* the proprietary information of this program is confidential . You shall not
* use it only if you have written permission from Turki al jandal & Abdulaziz Bazuhayr
* we will not be liable any damage suffered as result of using this program */

import java.util.ArrayList;

class Running extends Game {

    Participant[] sprintersArray;

	// takes only one variable to enter
	public Running() {
        this.gameID = DatabaseOperations.getInstance().createNewGame(GameType.Running);
        official = DatabaseOperations.getInstance().getOfficialForSport(GameType.Running);
    }

    @Override
    public int compete() {
        return GeneralFunctions.getInstance().getRandomNumber(10,20);
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
		for (Participant athlete : sprintersArray) {
//			System.out.println(i + ".  " + addWhitespaces(athlete.getName()) + "       " + athlete.getId());
			i++;
		}
		return sprintersArray;
	}

    @Override
    public Athlete getRandomAthlete() {
        ArrayList<Athlete> athletes = DatabaseOperations.getInstance().getAthletesForSport(GameType.Running,true);
        int random = GeneralFunctions.getInstance().getRandomNumber(0,athletes.size()-1);
        return athletes.get(random);
    }

}
