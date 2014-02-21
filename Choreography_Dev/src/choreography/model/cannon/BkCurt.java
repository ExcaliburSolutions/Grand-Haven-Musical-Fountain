/**
 * 
 */
package choreography.model.cannon;

import choreography.model.cannon.IndependentCannon;

/**
 * @author madridf
 *
 */
public class BkCurt extends IndependentCannon {

	/**
	 * @param name
	 * @param AB
	 * @param level
	 */
	public BkCurt(int level) {
		super(level);
	}

    BkCurt(int level, String name) {
        super(level, name);
    }

}
