/* The subclass Swimming  will inherit from its superclass Sport and will concrete abstract methods and
 *  other methods which  are related with Swimming sport only will be implemented in this class.
 *  For example, compete method will be invoked in Cycling class based on the time that will randomly
 *  generate  between 100 to 200 seconds  * 
*  @version 1 01 April 2017 
*  @author ABdulaziz Bazuhayr
*  @ reviewed by Turki Aljandal 
*  
*   Copyright  2017  All Rights Reserved. *
* the proprietary information of this program is confidential . You shall not
* use it only if you have written permission from Turki al jandal & Abdulaziz Bazuhayr
* we will not be liable any damage suffered as result of using this program */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Swimming extends Game {

	Participant[] swimmersArray;

	// takes only one variable to enter
	public Swimming() {
        this.gameID = DatabaseOperations.getInstance().createNewGame(GameType.Swimming);
        official = DatabaseOperations.getInstance().getOfficialForSport(GameType.Swimming);
	}

    @Override
    public int compete() {
        return GeneralFunctions.getInstance().getRandomNumber(100,200);
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
        for (Participant athlete : swimmersArray) {
//			System.out.println(i + ".  " + addWhitespaces(athlete.getName()) + "       " + athlete.getId());
            i++;
        }
        return swimmersArray;
    }

    @Override
    public Athlete getRandomAthlete() {
        ArrayList<Athlete> athletes = DatabaseOperations.getInstance().getAthletesForSport(GameType.Swimming,true);
        int random = GeneralFunctions.getInstance().getRandomNumber(0,athletes.size()-1);
        return athletes.get(random);
    }

}
