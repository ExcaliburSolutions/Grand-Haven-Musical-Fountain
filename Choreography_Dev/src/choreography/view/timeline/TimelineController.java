/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package choreography.view.timeline;
/**
 * Sample Skeleton for "Timeline.fxml" Controller Class
 * You can copy and paste this code into your favorite IDE
 **/

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;


public class TimelineController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="timelineChannelLabel"
    private Label timelineChannelLabel; // Value injected by FXMLLoader

    @FXML // fx:id="timelineGridPane"
    private GridPane timelineGridPane; // Value injected by FXMLLoader

    @FXML // fx:id="timelineLabelPane"
    private GridPane timelineLabelPane; // Value injected by FXMLLoader


    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert timelineChannelLabel != null : "fx:id=\"timelineChannelLabel\" was not injected: check your FXML file 'Timeline.fxml'.";
        assert timelineGridPane != null : "fx:id=\"timelineGridPane\" was not injected: check your FXML file 'Timeline.fxml'.";
        assert timelineLabelPane != null : "fx:id=\"timelineLabelPane\" was not injected: check your FXML file 'Timeline.fxml'.";

        // Initialize your logic here: all @FXML variables will have been injected

    }

}
