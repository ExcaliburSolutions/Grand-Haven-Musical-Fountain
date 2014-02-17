/**
 * 
 */
package lib;

import java.util.ArrayList;

import org.bushe.swing.event.EventBus;

import lib.cannons.Cannon;

/**
 * @author madridf
 *
 */
public class CannonGroup<T extends Cannon> {

	ArrayList<T> cannons;
	
	/**
	 * 
	 */
	public CannonGroup(ArrayList<T> cannons) {
		this.cannons = cannons;
		EventBus.publish(new ResourceAvailable(cannons));
	}

	/**
	 * @return the cannons
	 */
	public ArrayList<T> getCannons() {
		return cannons;
	}

	/**
	 * @param cannons the cannons to set
	 */
	public void setCannons(ArrayList<T> cannons) {
		this.cannons = cannons;
	}

}
