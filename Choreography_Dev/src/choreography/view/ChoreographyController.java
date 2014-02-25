/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package choreography.view;

import choreography.io.CtlLib;
import choreography.model.Event;
import java.net.URL;
import java.util.ResourceBundle;

import choreography.view.music.MusicPaneController;
import java.io.File;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author elementsking
 */
public class ChoreographyController implements Initializable {
    
    private static ChoreographyController cc;
    
    private ArrayList<Event> events;
    
    @FXML
    private VBox csGUI;
    @FXML
    private Label fcwOutput;
    @FXML
    private Menu fileMenu;
    @FXML
    private MenuItem newItemMenuItem;
    @FXML
    private MenuItem openWizardMenuItem;
    @FXML
    private Menu openRecentMenuItemItem;
    @FXML
    private MenuItem closeMenuItem;
    @FXML
    private MenuItem saveMenuItem;
    @FXML
    private MenuItem saveAsMenuItem;
    @FXML
    private MenuItem revertMenuItem;
    @FXML
    private MenuItem preferencesMenuItem;
    @FXML
    private MenuItem quitMenuItem;
    @FXML
    private Menu editMenu;
    @FXML
    private MenuItem undoMenuItem;
    @FXML
    private MenuItem redoMenuItem;
    @FXML
    private MenuItem cutMenuItem;
    @FXML
    private MenuItem copyMenuItem;
    @FXML
    private MenuItem pasteMenuItem;
    @FXML
    private MenuItem deleteMenuItem;
    @FXML
    private MenuItem selectAllMenuItem;
    @FXML
    private MenuItem unselectAllMenuItem;
    @FXML
    private Menu helpMenu;
    @FXML
    private MenuItem aboutMenuItem;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	
    	openWizardMenuItem.setOnAction(new EventHandler<ActionEvent> (){

            @Override
            public void handle(ActionEvent arg0) {
                    MusicPaneController.getInstance().selectMusic();
                    
            }
    		
    	});
    	
    	quitMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
            	
            }
        });
        
        saveMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    FileChooser fc = new FileChooser();
                    fc.setInitialDirectory(new File(System.getProperty("user.home")));
                    File saveLocation = fc.showOpenDialog(null);
                    CtlLib saver = new CtlLib();
                    
                    StringBuilder fcwOut = new StringBuilder();
                    
                    for(Event e : events) {
                        fcwOut.append(e);
                    }
                    
                    saver.saveFile(saveLocation, fcwOut.toString());
                }
            });
        
        fcwOutput.setText("Choreographer has loaded!");
        cc = this;
    }    
    
    public void setfcwOutput(String s) {
        fcwOutput.setText(s);
    }
    
    public static ChoreographyController getInstance() {
        return cc;
    }
    
}
