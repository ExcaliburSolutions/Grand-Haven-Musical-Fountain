/**
 * Sample Skeleton for "Choreography.fxml" Controller Class
 * You can copy and paste this code into your favorite IDE
 **/

package choreography;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import lib.events.StatusTextEvent;
import org.bushe.swing.event.EventBus;
import org.bushe.swing.event.annotation.AnnotationProcessor;
import org.bushe.swing.event.annotation.EventSubscriber;

/**
 *
 * @author elementsking
 */
public class ChoreographyController {
    
    boolean eventReady = false;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="csGUI"
    private VBox csGUI; // Value injected by FXMLLoader

    @FXML // fx:id="statusText"
    private Label statusText; // Value injected by FXMLLoader


    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert csGUI != null : "fx:id=\"csGUI\" was not injected: check your FXML file 'Choreography.fxml'.";
        assert statusText != null : "fx:id=\"statusText\" was not injected: check your FXML file 'Choreography.fxml'.";

        // Initialize your logic here: all @FXML variables will have been injected
        AnnotationProcessor.process(this);
        EventBus.publish(new StatusTextEvent(("GUI has finished loading!")));
    }
    
    /**
     *
     * @param s
     */
    @EventSubscriber(eventClass=StatusTextEvent.class)
    public void updateStatusText(StatusTextEvent s){
        if(eventReady)
            statusText.textProperty().setValue(s.getStatus());
    }
}
