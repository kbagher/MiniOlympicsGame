/**
 * Participant class will hold participants information
 *
 * @author Kassem
 */
public abstract class Participant {
	/**
	 * Participant state.
	 */
	private String state;
	/**
	 * Participant name.
	 */
    private String name;
	/**
	 * Participant age.
	 */
    private int age;
	/**
	 * Participant ID.
	 */
    private int id;
	/**
	 * Participant sport type
	 */
    private SportType sportType;


    /**
     * Instantiates a new Participant.
     *
     * @param id    participant's id
     * @param name  participant's name
     * @param type  participant's type
     * @param state participant's state
     * @param age   participant's age
     */
    public Participant(int id, String name, SportType type, String state, int age){
        this.sportType = type;
        this.name = name;
        this.id = id;
        this.age = age;
        this.state = state;
    }

	/**
	 * Get the participant's name.
	 *
	 * @return participant's name string
	 */
	public String getName(){
	    return name;
    }

	/**
	 * Get the participant's sport type.
	 *
	 * @return game type
	 */
	public SportType getSportType(){return  sportType;}

	/**
	 * Get the participant's state.
	 *
	 * @return participant's state
	 */
	public String getState(){
	    return state;
    }

	/**
	 * Get the participant's age.
	 *
	 * @return participant's age
	 */
	public int getAge(){
	    return  age;
    }

	/**
	 * Get the participant's ID.
	 *
	 * @return participant's ID
	 */
	public int getId(){
	    return  id;
    }

}
