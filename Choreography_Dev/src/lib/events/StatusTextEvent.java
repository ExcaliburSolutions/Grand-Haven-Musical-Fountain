/**
 * 
 */
package lib.events;

/**
 * @author madridf
 *
 */
public class StatusTextEvent {

	private final String status;

	/**
	 * 
     * @param status
	 */
	public StatusTextEvent(String status) {
		this.status = status;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

}
