/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package choreography.view;

import java.net.URL;
import java.util.ResourceBundle;

import choreography.view.music.MusicPaneController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author elementsking
 */
public class ChoreographyController implements Initializable {
    
    private static ChoreographyController cc;
    
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
            	final Stage dialogStage = new Stage();
            	Parent root = new GridPane();
            	Label message = new Label("Are you sure you want to quit?");
            	Button yes = new Button("Yes");
            	yes.addEventHandler(new EventType<MouseEvent>(), 
            			new EventHandler<MouseEvent>(){
					@Override
					public void handle(MouseEvent event) {
						System.exit(0);					
					}
            	});
            	Button no = new Button("No");
            	no.addEventHandler(new EventType<MouseEvent>(), 
            			new EventHandler<MouseEvent>(){
					@Override
					public void handle(MouseEvent event) {
						dialogStage.close();				
					}
            	});
            	GridPane.setConstraints(message, 0, 1);
            	GridPane.setConstraints(yes, 1, 2);
            	GridPane.setConstraints(no, 2, 2);
            	dialogStage.initModality(Modality.WINDOW_MODAL);
            	dialogStage.setScene(new Scene(root));
            	dialogStage.showAndWait();
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
