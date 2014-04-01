/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package choreography.view.specialOperations;

import choreography.io.FCWLib;
import choreography.model.fcw.FCW;
import choreography.view.ChoreographyController;
import choreography.view.music.MusicPaneController;
import choreography.view.sliders.SlidersController;
import choreography.view.timeline.Timeline;
import choreography.view.timeline.TimelineController;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author elementsking
 */
public class SpecialoperationsController implements Initializable {
    
    private static SpecialoperationsController instance;
    
    public static SpecialoperationsController getInstance() {
        if(instance == null) {
            return new SpecialoperationsController();
        }
        return instance;
    }
    
    @FXML
    private TitledPane sweepControlsPane;
    

    @FXML
    private CheckBox voiceCheckbox;

    @FXML
    private AnchorPane bSweepsPane;


    @FXML
    private ToggleGroup SpecialOps;


    @FXML
    private AnchorPane aSweepsPane;

    @FXML
    private ToggleButton resetButton;
    @FXML
    private ToggleGroup sweepGroup;
    @FXML
    private ToggleButton opposedSweeps;
    @FXML
    private ToggleButton parallelSweeps;
    @FXML
    private ToggleButton deleteButton;
    @FXML
    private CheckBox strobeCheckbox;
    @FXML
    private ToggleButton fadeUpButton;
    @FXML
    private ToggleGroup fadeGroup;
    @FXML
    private ToggleButton fadeDownButton;
    @FXML
    private ToggleButton independentSweeps;
    
    private RangeSlider aSweeps;
    private RangeSlider bSweeps;
    private String cannon;
    private SweepsEventHandlerImpl aSweepsEventHandler;
    private SweepsEventHandlerImpl bSweepsEventHandler;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        aSweeps = new RangeSlider(0.0, 4.0, 1.0, 3.0);
        bSweeps = new RangeSlider(0.0, 4.0, 1.0, 3.0);
        
        aSweeps.setSnapToTicks(true);
        aSweeps.setMajorTickUnit(1); aSweeps.setShowTickMarks(true); 
        aSweeps.setMinorTickCount(0); aSweeps.setBlockIncrement(1);
        
        bSweeps.setSnapToTicks(true);
        bSweeps.setMajorTickUnit(1); bSweeps.setShowTickMarks(true); 
        bSweeps.setMinorTickCount(0); bSweeps.setBlockIncrement(1);
        
        aSweepsPane.getChildren().add(aSweeps); 
        bSweepsPane.getChildren().add(bSweeps); 
        aSweeps.setVisible(false);
        bSweeps.setVisible(false);
        
        opposedSweeps.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                aSweeps.setVisible(true);
                bSweeps.setVisible(false);
                aSweepsEventHandler.setOpposed(true);
                bSweepsEventHandler.setOpposed(true);
            }
        });
        
        independentSweeps.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                aSweeps.setVisible(true);
                bSweeps.setVisible(true);
                aSweepsEventHandler.setOpposed(false);
                bSweepsEventHandler.setOpposed(false);
            }
        });
        
        parallelSweeps.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                aSweeps.setVisible(true);
                bSweeps.setVisible(false);
                aSweepsEventHandler.setOpposed(true);
                bSweepsEventHandler.setOpposed(true);
            }
        });
        
        aSweepsEventHandler = new SweepsEventHandlerImpl("SWEEPLIMITA", aSweeps);
        bSweepsEventHandler = new SweepsEventHandlerImpl("SWEEPLIMITB", bSweeps);
        
        aSweeps.setOnMouseReleased(aSweepsEventHandler);
        bSweeps.setOnMouseReleased(bSweepsEventHandler);
        
        voiceCheckbox.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if(voiceCheckbox.isSelected()) {
                    setVoiceOnOff("ON");
                }
                else {
                    setVoiceOnOff("OFF");
                }
            }

            public void setVoiceOnOff(String onOff) {
                ArrayList<String> AL = new ArrayList<>(3);
                AL.add(onOff);
                String[] actions = AL.toArray(new String[1]);
                FCW f = FCWLib.getInstance().getFCW("VOICE", actions);
                int tenths = MusicPaneController.getInstance().getTenthsTime();
                Timeline.getInstance().setWaterFcwAtPoint(tenths, f);
                TimelineController.getInstance().rePaintWaterTimeline();
                ChoreographyController.getInstance().setfcwOutput(f.toString());
            }
        });
        
        resetButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                ArrayList<String> AL = new ArrayList<>(3);
                AL.add("RESETALL");
                String[] actions = AL.toArray(new String[1]);
                FCW f = FCWLib.getInstance().getFCW("OFF", actions);
                int tenths = MusicPaneController.getInstance().getTenthsTime();
                Timeline.getInstance().setWaterFcwAtPoint(tenths, f);
                TimelineController.getInstance().rePaintWaterTimeline();
                ChoreographyController.getInstance().setfcwOutput(f.toString());
            }
        });
        
        //TODO Add Fadeup
        //TODO Add Fadedown
        //TODO Add Strobe
        //TODO Modify 
    }    

    public void setSweeps(FCW f) {
        String[] actions = FCWLib.getInstance().reverseLookupData(f);
        System.out.println(Arrays.toString(actions));
        switch(f.getAddr()) {
            case 35:
//                aSweeps.setMin(value);
            case 36:
            case 37:
        }
    }

    private class SweepsEventHandlerImpl implements EventHandler<MouseEvent> {
        
        String sweeps;
        RangeSlider slider;
        boolean opposed;

        public SweepsEventHandlerImpl(String sweeps, RangeSlider slider) {
            this.sweeps = sweeps;
            this.slider = slider;
        }
        
        public boolean isOpposed() {
            return opposed;
        }
        
        public void setOpposed(boolean opposed) {
            this.opposed = opposed;
        }

        @Override
        public void handle(MouseEvent event) {
            String[] actions = new String[2];
            String action;
            FCW f = null;
            int tenths = MusicPaneController.getInstance().getTenthsTime();
            if(opposedSweeps.isSelected()) {
                f = createFcw(actions);
                FCW opposed = FCWLib.getInstance().getFCW("SWEEPTYPE", new String[]{"OPPOSED"});
                Timeline.getInstance().setWaterFcwAtPoint(tenths, opposed);
            } else if(parallelSweeps.isSelected()) {
                f = createFcw(actions);
                FCW opposed = FCWLib.getInstance().getFCW("SWEEPTYPE", new String[]{"TOGETHER"});
                Timeline.getInstance().setWaterFcwAtPoint(tenths, opposed);
            } else if(independentSweeps.isSelected()) {
                f = createFcw(actions);
                FCW opposed = FCWLib.getInstance().getFCW("SWEEPTYPE", new String[]{"INDEPENDENT"});
                Timeline.getInstance().setWaterFcwAtPoint(tenths, opposed);
            }
//            else { f = new FCW(0, 0); }
            ChoreographyController.getInstance().setfcwOutput(f.toString());
            Timeline.getInstance().setWaterFcwAtPoint(tenths, f);
            TimelineController.getInstance().rePaintWaterTimeline();
        }

        public FCW createFcw(String[] actions) {
            String action;
            FCW f;
            int low = (int)slider.getLowValue();
            int high = (int)slider.getHighValue();
            System.out.println(low + " " + high);
            action = buildSweepLimitString(low, actions, high);
            System.out.println(action);
            if(opposed) {
                f = FCWLib.getInstance().getFCW("SWEEPLIMITAB", new String[]{action});
            } else
                f = FCWLib.getInstance().getFCW(sweeps, new String[]{action});
            return f;
        }

            public String buildSweepLimitString(int low, String[] actions, int high) {
                String action;
                switch(low) {
                    case 0:
                        actions[0] = "LEFTLONG";break;
                    case 1:
                        actions[0] = "LEFTSHORT";break;
                    case 2:
                        actions[0] = "CENTER";break;
                    case 3:
                        actions[0] = "RIGHTSHORT";break;
                    case 4:
                        actions[0] = "RIGHTLONG";break;
                }
                switch(high) {
                    case 0:
                        actions[1] = "LEFTLONG";break;
                    case 1:
                        actions[1] = "LEFTSHORT";break;
                    case 2:
                        actions[1] = "CENTER";break;
                    case 3:
                        actions[1] = "RIGHTSHORT";break;
                    case 4:
                        actions[1] = "RIGHTLONG";break;
                }
                if(low == high) {
                    action = "HOLD" + actions[0];
                }
                else {
                    action = actions[1] + actions[0];
                }
                return action;
            }
    }    
    
}
