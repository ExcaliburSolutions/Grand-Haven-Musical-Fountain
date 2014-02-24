/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package choreography.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import choreography.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBuilder;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBoxBuilder;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

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
    	quitMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
<<<<<<< HEAD
            	    Stage stage = new Stage();
            	    Parent root = null;
					try {
						root = FXMLLoader.load(
						    DialogController.class.getResource("Dialog.fxml"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            	    stage.setScene(new Scene(root));
            	    stage.setTitle("My modal window");
            	    stage.initModality(Modality.WINDOW_MODAL);
            	    stage.initOwner(
            	        ((Node)t.getSource()).getScene().getWindow() );
            	    stage.show();
=======
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
>>>>>>> 48e2111cbcfba5fbe1e53da7c857552c41b3055b
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
