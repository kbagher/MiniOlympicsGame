
public class Athlete extends Participant {

	private int score;
	private int time;

	public Athlete(int id, String name, GameType type, String state, int age) {
        super.sportType = type;
	    super.name = name;
        super.state = state;
		super.id = id;
		super.age = age;
	}

	public void setScore(int score) {
		// Add new score to the old one
		this.score += score;
	}

    public int getTime() {
        return time;
    }
	
	public void setTime(int time) {
		this.time = time;
	}

	public int getScore() {
		return score;
	}
}