/**
 * 
 */
package lib.cannons;


/**
 * @author madridf
 *
 */
public class Ring extends Cannon {
	int size;
	/**
	 * @param module
	 * @param level
	 * @param size
	 */
	public Ring(String module, int level, int size) {
		super(module, level);
		this.size = size;
	}

}
