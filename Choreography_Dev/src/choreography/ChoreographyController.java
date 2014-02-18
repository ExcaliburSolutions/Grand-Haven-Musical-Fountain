/**
 * 
 */
package choreography;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import lib.events.StatusText;

import org.bushe.swing.event.annotation.EventSubscriber;

/**
 * @author madridf
 *
 */
public class ChoreographyController {

	@FXML private Label fcwOutput;

	/**
	 * 
	 */
	public ChoreographyController() {
		
	}
	
	/**
	 * 
	 */
	@EventSubscriber(eventClass=StatusText.class)
	public void setFCWOutput(StatusText status) {
		fcwOutput.setText(status.getStatus());
	}

}
