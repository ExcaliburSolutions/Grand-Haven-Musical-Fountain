
package choreography.view.timeline;

import choreography.io.FCWLib;
import choreography.model.fcw.FCW;
import choreography.view.colorPalette.ColorPaletteController;
import choreography.view.music.MusicPaneController;
import customChannel.CustomChannel;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.SortedMap;
import java.util.concurrent.ConcurrentSkipListMap;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * FXML Controller class
 *
 * @author elementsking
 */
public class TimelineController implements Initializable {
	
    private static TimelineController instance;

    /**
     * 
     * @return
     */
    public static TimelineController getInstance() {
        if (instance == null)
            instance = new TimelineController();
        return instance;
    }

    @FXML
    private GridPane timelineLabelPane;
    @FXML
    private ScrollPane timelineScrollPane, labelScrollPane;
    // NonFXML
    private int time;
    private NumberAxis numLine;
    private SortedMap<Integer, ArrayList<FCW>> timeline;
    private SortedMap<Integer, ArrayList<FCW>> waterTimeline;
    private SortedMap<Integer, ArrayList<FCW>> lightTimeline;
    int startRow = 0;
    final int rowNumber = 14;
    GridPane gridpaneLight;
    GridPane gridpaneWater;
    Rectangle[] waterRecArray;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        setTimelineGridPane();
        setLabelGridPane();
        // setWaterGridPane();
        timeline = new ConcurrentSkipListMap<>();
        waterTimeline = new ConcurrentSkipListMap<>();
        lightTimeline = new ConcurrentSkipListMap<>();
        instance = this;

    }

    public void setLabelGridPane() {
        // 57 lines long
        final String[] labelNames = new String[] {"Custom Channel", "Module 1", "Module 2",
                        "Module 3", "Module 4", "Module 5", "Module 6", "Module 7",
                        "A Modules", "B Modules", "Front Curtain", "Back Curtain",
                        "Peacock", "Voice", "ALL LEDs", "L1-A Mod 1 Front Left",
                        "L2-A Mod 1 Front Right", "L6-A Mod 2 Front Left",
                        "L7-A Mod 2 Front Right", "L11-A Mod 3 Front Left",
                        "L12-A Mod 3 Front Right", "L19-A Mod 4 Front Left",
                        "L16-A Mod 4 Front Left Center", "L18-A Mod Front Center",
                        "L17-A Mod 4 Front Right Center", "L20-A Mod 4 Front Right",
                        "L21-A Mod 5 Front Left", "L22-A Mod 5 Front Right",
                        "L26-A Mod 6 Front Left", "L27-A Mod 6 Front Right",
                        "L31-A Mod 7 Front Left", "L32-A Mod 7 Front Right",
                        "L4-A Mod 1 Back Left", "L3-A Mod 1 Back Center",
                        "L5-A Mod 1 Back Right", "L9-A Mod 2 Back Left",
                        "L8-A Mod 2 Back Center", "L10-A Mod 2 Back Right",
                        "L14-A Mod 3 Back Left", "L13-A Mod 3 Back Center",
                        "L15-A Mod 3 Back Right", "L37-Peacock 1, Left",
                        "L38-Peacock 2, Left Center", "L39-Peacock 3, Right Center",
                        "L40-Peacock 4- Right", "L24-A Mod 5 Back Left",
                        "L23-A Mod 5 Back Center", "L25-A Mod 5 Back Right",
                        "L29-A Mod 6 Back Left", "L28-A Mod 6 Back Center",
                        "L30-A Mod 6 Back Right", "L34-A Mod 7 Back Left",
                        "L33-A Mod 7 Back Center", "L35-A Mod 7 Back Right",
                        "L36-Voice 1", "Space for Voice 2", "Custom Channel" };

        // need to be changed to 57 for full length, no scroll pane so takes up
        // whole screen,
        //the first custom channel is hter only for testing!!!!!!!!
        final Label[] labelArray = new Label[57];
        for (int i = 0; i < 13; i++) {
            timelineLabelPane.getRowConstraints().add(new RowConstraints(26));
        }

        for (int i = 0; i < 14; i++) {
            labelArray[i] = new Label(labelNames[i]);
            timelineLabelPane.add(labelArray[i], 0, i);

            if (labelNames[i].equals("Custom Channel")) {
                labelArray[i].setOnMousePressed((MouseEvent me) -> {
                    CustomChannel newChannel = new CustomChannel();
//						newChannel.start(primaryStage);
                    System.out.println("WOWOWOWOWOWOW");
                });
            }
        }
    }
    
//    public void setLabelGridPane(){
//    	timelineLabelPane = new GridPane();
//    	final String[] labelNames = new String[]{"Module 1", "Module 2", "Module 3", 
//            "Module 4", "Module 5", "Module 6", "Module 7", "A Modules", "B Modules", 
//            "Front Curtain", "Back Curtain", "Peacock", "Voice", "ALL LEDs"};
//    	
//    	timelineLabelPane.setGridLinesVisible(true);
//    	timelineLabelPane.setStyle("-fx-background-color: #4CC552;");
//    	
//    	final Label[] labelArray = new Label[14];
//    	for(int i=0; i<14;i++){
//        timelineLabelPane.getRowConstraints().add(new RowConstraints(26));
//    	}
//    	timelineLabelPane.getColumnConstraints().add(new ColumnConstraints(98));
//    	
//    	for(int i=0; i<14;i++){
//            labelArray[i] = new Label(labelNames[i]);
//            timelineLabelPane.add(labelArray[i], 0, i);
//    	}
//        timelineScrollPane.valueProperty().addListener(new ChangeListener<>() {
//            public void changed(ObservableValue o,Object oldVal, 
//                     Object newVal){
//                 labelScrollPane.setVvalue(timelineScrollPane.getVvalue());
//            }
//          });
//    	 labelScrollPane.valueProperty().addListener(new ChangeListener() {
//            public void changed(ObservableValue o,Object oldVal, 
//                     Object newVal){
//                    timelineScrollPane.setVvalue(labelScrollPane.getVvalue());
//            }
//          });
//    	labelScrollPane.setContent(timelineLabelPane);
//    }
    
    /**
     * 
     */
    public void setTimelineGridPane() {
        gridpaneLight = new GridPane();

        time = MusicPaneController.SONG_TIME;

        gridpaneLight.setGridLinesVisible(true);

        final Rectangle[][] lightRecArray = new Rectangle[time][rowNumber];
        for (int i = 0; i < time; i++) {
            gridpaneLight.getColumnConstraints().add(new ColumnConstraints(26));
            if (i < rowNumber) { // because the array is not square this needs to be
                                            // here
                    gridpaneLight.getRowConstraints().add(new RowConstraints(26));
            }

            for (int j = 0; j < rowNumber; j++) {
                // if (i == 0){
                // recArray[i][j] = new Rectangle(50,25, Color.RED);
                // continue;
                // }
                lightRecArray[i][j] = new Rectangle(25, 25, Color.LIGHTGREY);
                gridpaneLight.add(lightRecArray[i][j], i, j);
                // these are needed to talk to the mouse pressed events
                final int testI = i;
                final int testJ = j;

                lightRecArray[i][j].setOnMousePressed((MouseEvent me) -> {
                    System.out.println("Col " + (testI) + " Row "
                            + (testJ + 1));
                    startRow = testJ;
                    lightRecArray[testI][testJ]
                            .setFill(ColorPaletteController
                                    .getInstance()
                                    .getSelectedColor());
                });

                lightRecArray[i][j].setOnDragDetected((MouseEvent me) -> {
                    lightRecArray[testI][testJ].startFullDrag();
                });
                // continues and ends the drag event
                lightRecArray[i][j].setOnMouseDragOver((MouseEvent me) -> {
                    if (startRow == testJ) {
                        lightRecArray[testI][testJ]
                                .setFill(ColorPaletteController
                                        .getInstance()
                                        .getSelectedColor());
                    }
                });
            }
        }

        timelineScrollPane.setContent(gridpaneLight);
    }

    public void setWaterGridPane() {
        gridpaneWater = new GridPane();
        // NumberAxis valueAxis = new NumberAxis();

        time = MusicPaneController.SONG_TIME;
        numLine = new NumberAxis((double) 0, (double) time, 1);

        gridpaneWater.setGridLinesVisible(true);

        waterRecArray = new Rectangle[time];
        for (int i = 0; i < time; i++) {
        	 final int testI = i;
                gridpaneWater.getColumnConstraints().add(new ColumnConstraints(26));
                if (i < 1) { // because the array is not square this needs to be
                                              // here
                        gridpaneWater.getRowConstraints().add(new RowConstraints(26));
                }
                
                waterRecArray[i] = new Rectangle(25, 25, Color.LIGHTGREY);
                gridpaneWater.add(waterRecArray[i], i, 0);
                
                waterRecArray[i].setOnMousePressed((MouseEvent me) -> {
                    System.out.println("Col " + (testI));
                });
        }
        // ValueAxis axis = new ValueAxis();

        // scrollpane.setPrefSize(600, 250);
        MusicPaneController.getInstance().getWaterPane()
                        .setContent(gridpaneWater);
        MusicPaneController.getInstance().getLabelPane().setContent(numLine);
    }

    /**
     * 
     * @return
     */
    public ScrollPane getScrollPane() {
            return timelineScrollPane;
    }

    public SortedMap<Integer, ArrayList<FCW>> getTimeline() {
            return timeline;
    }

    /**
     * @param timeline the timeline to set
     */
    public void setTimeline(SortedMap<Integer, ArrayList<FCW>> timeline) {
        this.timeline = timeline;
        for(Integer i: timeline.keySet()) {
            for(FCW f: timeline.get(i)) {
                if(f.getIsWater()) {
                    if(waterTimeline.containsKey(i))
                        waterTimeline.get(i).add(f);
                    else {
                        ArrayList<FCW> newList = new ArrayList<>();
                        newList.add(f);
                        waterTimeline.put(i, newList);
                    }
                }
                else {
                    if(lightTimeline.containsKey(i))
                        lightTimeline.get(i).add(f);
                    else {
                        ArrayList<FCW> newList = new ArrayList<>();
                        newList.add(f);
                        lightTimeline.put(i, newList);
                    }
                        
                }
            }
        }
        System.out.println("Water timeline: " + waterTimeline);
        System.out.println("Light timeline: " + lightTimeline);
        rePaint();
    }
    
    /**
     *
     */
    public void rePaint() {
        for(Integer i: waterTimeline.keySet()){
            for(FCW f: waterTimeline.get(i)) {
                String name = FCWLib.getInstance().reverseLookupAddress(f);
                String[] actions = FCWLib.getInstance().reverseLookupData(f);
                int tenthOfSec = i % 10;
                int secondsOnly = i /10;
                //simple rounding to the half second for testing purposes
                if(tenthOfSec < 5){
                	tenthOfSec = 0;
                }
                else{
                	tenthOfSec = 5;
                }
                
                double newTime = secondsOnly + (tenthOfSec / 10);
                int colAtTime = (int) (newTime *2);
                if(colAtTime != 0){
                	colAtTime = colAtTime - 1;
                }
                waterRecArray[colAtTime].setFill(Color.BLACK);
                //TODO paint water timeline with info
            }
        }
        for(Integer i: lightTimeline.keySet()) {
            for(FCW f: lightTimeline.get(i)) {
                String name = FCWLib.getInstance().reverseLookupAddress(f);
                String[] actions = FCWLib.getInstance().reverseLookupData(f);
                //TODO paint light timeline with info
            }
        }
    }
}

///**             
// * for(int j=0; j<1; j++){
// * // if (i == 0){
// * // recArray[i][j] = new Rectangle(50,25, Color.RED);
// * // continue;
// * // }
// * recArray[i][j] = new Rectangle(25,25, Color.LIGHTGREY);
// * gridpaneRec.add(recArray[i][j], i, j);
// * //these are needed to talk to the mouse pressed events
// * final int testI = i;
// * final int testJ = j;
// * 
// * recArray[i][j].setOnMousePressed(new EventHandler<MouseEvent>() {
// * @Override
// public void handle(MouseEvent me) {
// System.out.println("Col " + (testI) + " Row " + (testJ+1));
// recArray[testI][testJ].setFill(ColorPaletteController.getInstance()
// .getSelectedColor());
// }
// });
//
// recArray[i][j].setOnDragDetected(new EventHandler<MouseEvent>() {
// @Override
// public void handle(MouseEvent me) {
// //starts the drag event
// recArray[testI][testJ].startFullDrag();
// }
//
// });
// //continues and ends the drag event
// recArray[i][j].setOnMouseDragOver(new EventHandler<MouseEvent>()
// {
// @Override
// public void handle(MouseEvent me) {
// recArray[testI][testJ].setFill(ColorPaletteController.getInstance()
// .getSelectedColor());
// }
// });
// } 
// **/


//        timelineScrollPane.hvalueProperty().addListener(new ChangeListener<Number>() {
//            public void changed(ObservableValue<? extends Number> ov,
//                    Number old_val, Number new_val) {
//                //System.out.println(volume.getValue());
//                //mediaPlayer.setVolume(volume.getValue());
//            	//System.out.println("works");
//            	timelineScrollPane.setHvalue((Double) new_val);
//            	Duration duration = new Duration((timelineScrollPane.getHvalue()/100)*(MusicPaneController.getInstance().getMediaPlayer().getTotalDuration().toSeconds()));
//            	MusicPaneController.getInstance().getMediaPlayer().seek(duration);
//            }
//        }); 