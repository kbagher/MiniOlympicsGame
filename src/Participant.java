
public abstract class Participant {
	String state;
	String name;
	int age;
	int id;
	GameType sportType;

	public String getName(){
	    return name;
    }

    public GameType getSportType(){return  sportType;}

	public String getState(){
	    return state;
    }

	public int getAge(){
	    return  age;
    }

	public int getId(){
	    return  id;
    }

}
