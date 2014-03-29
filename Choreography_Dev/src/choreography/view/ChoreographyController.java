/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package choreography.view;

import choreography.Main;
import choreography.io.CtlLib;
import choreography.io.LagTimeLibrary;
import choreography.io.MapLib;
import choreography.model.fcw.FCW;
import choreography.view.lagtime.LagTimeGUIController;
import choreography.view.music.MusicPaneController;
import choreography.view.timeline.Timeline;
import choreography.view.timeline.TimelineController;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.SortedMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentSkipListMap;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog.Actions;
import org.controlsfx.dialog.Dialogs;

import customChannel.CustomChannel;

/**
 * FXML Controller class
 *
 * @author elementsking
 */
public class ChoreographyController implements Initializable {
    
    private static ChoreographyController cc;
    
    private ConcurrentSkipListMap<Integer, ArrayList<FCW>> events;
    
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
    private MenuItem advancedCheckMenuItem;
    @FXML
    private MenuItem quitMenuItem;
    @FXML
    private Menu editMenu;
    @FXML
    private MenuItem addChannelsMenuItem;
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
    @FXML
    private ToggleButton selectionButton;
    @FXML
    private Pane simPane;
//    @FXML
//    private ProgressIndicator progressIndicator;
    
    private File saveLocation;
    private boolean isSaved;
    private boolean isAdvanced;
    private boolean isSlidersLoaded;
    private boolean isSelected = false;
	boolean isFirst = true;
    Timer timelineTimer = new Timer("progressTimer", true);
    Timer sliderTimer = new Timer("progressTimer", true);


    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        progressIndicator = new ProgressIndicator();
    	
    	openMusicMenuItem.setOnAction(new EventHandler<ActionEvent> (){

            @Override
            public void handle(ActionEvent arg0) {
//                progressIndicator.setProgress(-1);
                MusicPaneController.getInstance().selectMusic();
//                progressIndicator.setProgress(1);
                openCTLMenuItem.setDisable(false);
            }
    		
    	});
    	
    	selectionButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
//				selectButton.isPressed();
				if (isSelected){
					isSelected = false;
					System.out.println("Off");
					TimelineController.getInstance().clearAllAL();
					TimelineController.getInstance().disableCopyPaste();
				}
				else {
					isSelected = true;
					System.out.println("On");

				}
				
			}
    		
		});
        
    	copyMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				
			}
    		
		});
    	
    	pasteMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				
			}
    		
		});
    	
        openCTLMenuItem.setOnAction(new EventHandler<ActionEvent> () {

                @Override
                public void handle(ActionEvent t) {
                    CtlLib.getInstance().openCtl();  
                    cc.setfcwOutput("CTL file has loaded!");
                }
            
        });
        
        advancedCheckMenuItem.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
//                    ColorPaletteController.getInstance().setAdvancedFunction(true);
                }
            });
    	
    	quitMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                Action result = Dialogs.create()
                        .title("Quit?")
                        .masthead("")
                        .message( "Are you sure you want to quit?")
                        .showConfirm();
                if(result != Actions.YES) {
//                    System.out.println(result);
                } else {
                    if(isSaved) {
                        Platform.exit();
                    }
                    else {
                        Action saveResult = Dialogs.create().title("Save?")
                                .masthead("You haven't saved before exiting.")
                                .message("Would you like to save before quiting?")
                                .showConfirm();
                        if(saveResult == Actions.YES) {
                            saveAsMenuItem.getOnAction().handle(t);
                        }
                        else if(saveResult == Actions.NO) {
                            Platform.exit();
                        }
                    }
                }
            }
        });
        
        saveMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                
                @Override
                public void handle(ActionEvent t) {
                    if(isSaved) {
                        buildFcwOutputAndSave();
                    } else {
                        saveAsMenuItem.getOnAction().handle(t);
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
//                    openLagTimeDialog();
                }
            });
        
        events = new ConcurrentSkipListMap<>();
        fcwOutput.setText("Choreographer has loaded!");
        openCTLMenuItem.setDisable(true);
        cc = this;
        Platform.runLater(new Runnable() {

                @Override
                public void run() {
                    MapLib.openMap(new File("src/choreography/model/color/dmx.map"));
                    MusicPaneController.getInstance().openMusicFile(new File("src/choreography/Reflections of Earth.wav"));
                    CtlLib.getInstance().openCtl(new File("src/choreography/Reflections of Earth.ctl"));
                }
            });
        isSlidersLoaded = true;
    }

    private void buildFcwOutputAndSave() {
        isSaved = CtlLib.getInstance().saveFile(saveLocation,
                Timeline.getInstance().getTimeline());
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
    
    public void addChannels(){
//    	if (isFirst){
    	Stage primaryStage = new Stage();
    	CustomChannel.start(primaryStage);
//    	isFirst = false;
//    	}
//    	else{
//    		CustomChannel.showStage();
//    	}
    }
    
    /**
     *
     * @return
     */
    public static ChoreographyController getInstance() {
        return cc;
    }

    /**
     * Sets the 
     * @param parsedCTL
     */
    public void setEventTimeline(SortedMap<Integer, ArrayList<FCW>> parsedCTL) {
        events.putAll(parsedCTL);
        TimelineController.getInstance().setTimeline(parsedCTL);
        TimelineController.getInstance().setLabelGridPaneWithCtl();
        TimelineController.getInstance().rePaint();
    }
    
    /**
     * Returns the event Timeline
     * @return
     */
    public SortedMap<Integer, ArrayList<FCW>> getEventTimeline() {
        return events;
    }

    public void setAdvanced(boolean b) {
        isAdvanced = b;
    }

    public boolean getAdvanced() {
        return isAdvanced;
    }
    
    /**
     * Updates timeSlider in MusicPaneController every 1/8th of a second
     */
    public void startPollingTimeSliderAlgorithm() {
        
        sliderTimer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                Platform.runLater(() -> {
                    MusicPaneController.getInstance().updateProgress();
                });
            }
        }, 0l, 125l);
    }
    
    /**
     * Draws SIM and sets sliders every 10th of a second
     */
    
    public void startPollingSlidersAlgorithm() {
       
        timelineTimer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                Platform.runLater(() -> {
                    TimelineController.getInstance().fireSliderChangeEvent();
                    Timeline.getInstance().drawSim(MusicPaneController.getInstance().getTenthsTime());
                });
            }
        }, 0l, 100l);
    }

    public void setSlidersLoaded(boolean b) {
        isSlidersLoaded = b;
    }
 
    public boolean getIsSelected(){
	return isSelected;
    }

    public void stopTimelineTimer() {
       timelineTimer.purge();
    }

    public void stopSliderTimer() {
        sliderTimer.purge();
    }
    
    public void openMapFileMenuItemHandler() {
        MapLib.openMap();
    }
    
}
