
import java.util.ArrayList;
import java.util.Collections;

/**
 * Official class holds official's information and operations
 *
 * @author Kassem
 */
public class Official extends Participant {


    /**
     * Instantiates a new Official.
     *
     * @param id    official ID
     * @param name  official name
     * @param type  official sport type
     * @param state official state
     * @param age   official age
     */
    public Official(int id, String name, GameType type, String state, int age) {
        super(id, name, type, state, age);
    }

    /**
     * Summarize athletes results by competition time.
     *
     * @param athletes athletes list
     *
     * @return summarized athletes
     */
    public ArrayList<Athlete> summarizeResultsByTime(ArrayList<Athlete> athletes) {

        // loop through athletes and sort them by time
        for (int i = 1; i < athletes.size(); i++) {
            for (int j = i; j > 0; j--) {
                if (athletes.get(j).getTime() > athletes.get(j - 1).getTime()) {
                    Collections.swap(athletes, j, j - 1);
                }
            }
        }
        return athletes;
    }

    /**
     * Summarize athletes results by overall score
     *
     * @param athletes athletes list
     *
     * @return summarized athletes
     */
    public ArrayList<Athlete> summerizeResultsByScore(ArrayList<Athlete> athletes) {
        // loop through athletes and sort them by score
        for (int i = 1; i < athletes.size(); i++) {
            for (int j = i; j > 0; j--) {
                if (athletes.get(j).getScore() > athletes.get(j - 1).getScore()) {
                    Collections.swap(athletes, j, j - 1);
                }
            }
        }
        return athletes;
    }
}
