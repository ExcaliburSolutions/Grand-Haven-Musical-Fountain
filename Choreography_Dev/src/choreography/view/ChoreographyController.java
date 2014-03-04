/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package choreography.view;

import choreography.view.lagtime.LagTimeGUIController;
import choreography.Main;
import choreography.io.CtlLib;
import choreography.io.FCWLib;
import choreography.io.LagTimeLibrary;
import choreography.model.Event;
import choreography.model.fcw.FCW;
import java.net.URL;
import java.util.ResourceBundle;

import choreography.view.music.MusicPaneController;
import choreography.view.timeline.TimelineController;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
    private MenuItem openMusicMenuItem;
    @FXML
    private MenuItem openCTLMenuItem;
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
    @FXML
    private MenuItem setLagTimesMenuItem;
    private File saveLocation;
    private boolean isSaved;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	
    	openMusicMenuItem.setOnAction(new EventHandler<ActionEvent> (){

            @Override
            public void handle(ActionEvent arg0) {
                MusicPaneController.getInstance().selectMusic();
                cc.setfcwOutput("Music has loaded!");
            }
    		
    	});
        
        openCTLMenuItem.setOnAction(new EventHandler<ActionEvent> () {

                @Override
                public void handle(ActionEvent t) {
                    CtlLib.getInstance().openCtl();  
                    cc.setfcwOutput("CTL file has loaded!");
                }
            
        });
    	
    	quitMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
            	try {
                    // Load the fxml file and create a new stage for the popup
                    FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/Dialog.fxml"));
                    Pane page = (Pane) loader.load();
                    Stage dialogStage = new Stage();
                    dialogStage.setTitle("Quit?");
                    dialogStage.initModality(Modality.WINDOW_MODAL);
                    dialogStage.initOwner(Main.getPrimaryStage());
                    Scene scene = new Scene(page);
                    dialogStage.setScene(scene);

                    // Set the lagtimes into the controller
                    DialogController controller = loader.getController();
                    controller.setDialogStage(dialogStage);
                    controller.setMessage("Are you sure you want to Quit?");

                    // Show the dialog and wait until the user closes it
                    dialogStage.showAndWait();

                } catch (IOException e) {
                    // Exception gets thrown if the fxml file could not be loaded
                    e.printStackTrace();
                }
            }
        });
        
        saveMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                
                @Override
                public void handle(ActionEvent t) {
                    if(isSaved) {
                        buildFcwOutputAndSave();
                    } else {
                        saveLocation  = selectSaveLocation();
                    }
                }
            });
        
        saveAsMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    saveLocation  = selectSaveLocation();
                    buildFcwOutputAndSave();
                }
            });
        
        setLagTimesMenuItem.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent t) {
                    openLagTimeDialog();
                }
            });
        
        events = new ArrayList<>();
        fcwOutput.setText("Choreographer has loaded!");
        cc = this;
    }

    private void buildFcwOutputAndSave() {
        CtlLib.getInstance().saveFile(saveLocation, events);
    }

    private File selectSaveLocation() {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(System.getProperty("user.home")));
        saveLocation = fc.showSaveDialog(null);
        isSaved = true;
        return saveLocation;
    }
    
    /**
     *
     * @return
     */
    public boolean openLagTimeDialog() {
        try {
            // Load the fxml file and create a new stage for the popup
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/lagtime/LagTimeGUI.fxml"));
            GridPane page = (GridPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Lag Times");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(Main.getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the lagtimes into the controller
            LagTimeGUIController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setDelays(LagTimeLibrary.getInstance().getLagTimes());

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
            return true;

            } catch (IOException e) {
              // Exception gets thrown if the fxml file could not be loaded
              e.printStackTrace();
              return false;
            }
    }
    
    /**
     *
     * @param s
     */
    public void setfcwOutput(String s) {
        fcwOutput.setText(s);
    }
    
    /**
     *
     * @return
     */
    public static ChoreographyController getInstance() {
        return cc;
    }

    /**
     *
     * @param parseCTL
     */
    public void setEventTimeline(ArrayList<Event> parseCTL) {
        events.addAll(parseCTL);
        rePaint();
    }
    
    /**
     *
     * @return
     */
    public ArrayList<Event> getEventTimeline() {
        return events;
    }

    /**
     *
     */
    public void rePaint() {
        for(Event e : events) {
            for(FCW f : e.getCommands()){
                FCWLib.getInstance().reverseLookup(f);
                //TODO Send event to Timeline Controller to setup 
//                TimelineController.getInstance()
            }
        }
    }
    
}
