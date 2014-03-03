/**
 * 
 */
package choreography.view;

/**
 * @author madridf
 *
 */

	import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 *
 * @author elementsking
 */
public class DialogController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="actionButton"
    private Button actionButton; // Value injected by FXMLLoader

    @FXML // fx:id="actionParent"
    private HBox actionParent; // Value injected by FXMLLoader

    @FXML // fx:id="cancelButton"
    private Button cancelButton; // Value injected by FXMLLoader

    @FXML // fx:id="detailsLabel"
    private Label detailsLabel; // Value injected by FXMLLoader

    @FXML // fx:id="messageLabel"
    private Label messageLabel; // Value injected by FXMLLoader

    @FXML // fx:id="okButton"
    private Button okButton; // Value injected by FXMLLoader

    @FXML // fx:id="okParent"
    private HBox okParent; // Value injected by FXMLLoader


    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert actionButton != null : "fx:id=\"actionButton\" was not injected: check your FXML file 'Untitled 1'.";
        assert actionParent != null : "fx:id=\"actionParent\" was not injected: check your FXML file 'Untitled 1'.";
        assert cancelButton != null : "fx:id=\"cancelButton\" was not injected: check your FXML file 'Untitled 1'.";
        assert detailsLabel != null : "fx:id=\"detailsLabel\" was not injected: check your FXML file 'Untitled 1'.";
        assert messageLabel != null : "fx:id=\"messageLabel\" was not injected: check your FXML file 'Untitled 1'.";
        assert okButton != null : "fx:id=\"okButton\" was not injected: check your FXML file 'Untitled 1'.";
        assert okParent != null : "fx:id=\"okParent\" was not injected: check your FXML file 'Untitled 1'.";

        // Initialize your logic here: all @FXML variables will have been injected
        okButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				System.out.println(0);
				
			}
		});
        
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				
			}
		});
    }
    
    /**
     *
     * @param message
     */
    public void setMessage(String message) {
    	messageLabel.setText(message);
	}

    

}
