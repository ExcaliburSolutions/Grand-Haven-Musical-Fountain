/**
 * 
 */
package lib.events;

/**
 * An event which describes a resource which has been made available to the eventBus.
 * 
 * @author madridf
 *
 */
public class ResourceAvailable<T> {

	private T resource;
	
    /**
     * 
     * @param resource
     */
     public ResourceAvailable(T resource) {
           this.resource = resource;
     }

    public T getResource() {
           return resource;
    }

}
