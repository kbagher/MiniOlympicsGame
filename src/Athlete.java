/**
 * Athlete class holds athlete information, competition score and time.
 *
 * @author Khalid
 */
public class Athlete extends Participant {

    /**
     * Athlete total score
     */
    private int score;
    /**
     * Athlete competition time for the game
     */
    private int time;

    /**
     * Instantiates a new Athlete object.
     *
     * @param id    athlete id
     * @param name  athlete  name
     * @param type  athlete game type
     * @param state athlete state
     * @param age   athlete age
     */
    public Athlete(int id, String name, GameType type, String state, int age) {
        super(id, name, type, state, age);
    }

    /**
     * Sets athlete score based on game rank
     *
     * @param score athlete score in the game
     */
    public void setScore(int score) {
        this.score += score; // total score across all games
    }

    /**
     * Gets the athletes time for last his competition
     *
     * @return competition time
     */
    public int getTime() {
        return time;
    }

    /**
     * Sets the athletes time for last his competition
     *
     * @param time competition time
     */
    public void setTime(int time) {
        this.time = time;
    }

    /**
     * Gets the athlete's total score.
     *
     * @return athlete's score
     */
    public int getScore() {
        return score;
    }
}