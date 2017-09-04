/*
* THE subclass OficialsInformation  will inherit  4 variables from its superclass and will concrete the 4 abstract methods
*  and it include the official method to be able to conduct summarize Final results By Time and By Score.
*   These two methods are only done by Official  * 

*  @version 3 04 April 2017 
*  @author TURKI ALJANDAL
*  @ reviewd by Abdulaziz Bazuhayr 
*   * Copyright  2017  All Rights Reserved. *
* the proprietary information of this program is confidential . You shall not
* use it only if you have written permission from Turki al jandal & Abdulaziz Bazuhayr
* we will not be liable any damage suffered as result of using this program */

import java.util.ArrayList;
import java.util.Collections;

public class Official extends Participant {


	public Official(int id, String name, GameType type , String state, int age) {
		super.sportType = type;
	    super.name = name;
		super.id = id;
		super.age = age;
		super.state = state;
	}

    public Official() {
    }

    public ArrayList<Athlete> summerizeResultsByTime(ArrayList<Athlete> athletes) {

	    Athlete tmp;
        for (int i = 1; i < athletes.size(); i++) {
            for(int j = i ; j > 0 ; j--){
                if(athletes.get(j).getTime() > athletes.get(j-1).getTime()){
                    Collections.swap(athletes,j,j-1);
                }
            }
        }
        return athletes;
    }

    public ArrayList<Athlete> summerizeResultsByScore(ArrayList<Athlete> athletes) {

        Athlete tmp;
        for (int i = 1; i < athletes.size(); i++) {
            for(int j = i ; j > 0 ; j--){
                if(athletes.get(j).getScore() > athletes.get(j-1).getScore()){
                    Collections.swap(athletes,j,j-1);
                }
            }
        }
        return athletes;
    }


    // Selection sort method to rearange the array depends on the time or score
	// for each athlete
	public Participant[] summerizeResultsByTime(Participant[] athletesArray) {

		for (int i = 0; i < athletesArray.length; i++) {
			int tempAddress = i;
			int tempTime = ((Athlete) athletesArray[i]).getTime();
			for (int j = i + 1; j < athletesArray.length; j++) {
				if (tempTime > ((Athlete) athletesArray[j]).getTime()) {
					tempTime = ((Athlete) athletesArray[j]).getTime();
					tempAddress = j;
				}
			}
			Participant temp = athletesArray[i];
			athletesArray[i] = athletesArray[tempAddress];
			athletesArray[tempAddress] = temp;
		}
		return athletesArray;
	}

	public Participant[] summerizeFinalresultsByScore(Participant[] athletesArray) {

		for (int i = 0; i < athletesArray.length; i++) {
			int tempAddress = i;
			int tempTime = ((Athlete) athletesArray[i]).getScore();
			for (int j = i + 1; j < athletesArray.length; j++) {
				if (tempTime < ((Athlete) athletesArray[j]).getScore()) {
					tempTime = ((Athlete) athletesArray[j]).getScore();
					tempAddress = j;
				}
			}
			Participant temp = athletesArray[i];
			athletesArray[i] = athletesArray[tempAddress];
			athletesArray[tempAddress] = temp;
		}
		return athletesArray;
	}

}
