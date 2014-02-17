/**
 * 
 */
package lib;

/**
 * An event which describes a resource which has been made available to the eventBus.
 * 
 * @author madridf
 *
 */
public class ResourceAvailable {

	private Object resource;
	
	/**
	 * 
	 */
	public ResourceAvailable(Object resource) {
		this.resource = resource;
	}
	
	public Object getResource() {
		return resource;
	}

}
