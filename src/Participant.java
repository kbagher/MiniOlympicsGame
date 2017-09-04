
public abstract class Participant {
	protected String state;
	protected String name;
	protected int age;
	protected int id;
	protected GameType sportType;

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
