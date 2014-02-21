/**
 * 
 */
package choreography.model.cannon;

/**
 * @author madridf
 *
 */
public class Spout extends IndependentCannon {

    /**
     * @param name
     * @param AB
     * @param level
     */
    public Spout(int level) {
            super(level);
    }

    public Spout(int level, String name){
        super(level, name);
    }

}
