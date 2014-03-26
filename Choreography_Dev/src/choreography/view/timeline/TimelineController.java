
package choreography.view.timeline;

import choreography.io.FCWLib;
import choreography.model.fcw.FCW;
import choreography.view.ChoreographyController;
import choreography.view.colorPalette.ColorPaletteController;
import choreography.view.colorPalette.ColorPaletteEnum;
import choreography.view.colorPalette.ColorPaletteModel;
import choreography.view.music.MusicPaneController;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author elementsking
 */
public class TimelineController implements Initializable {
	
    private static TimelineController instance;
    private final ColorPaletteEnum[] colorEnumArray;
    private Integer[] channelAddresses;
    boolean oldRecHasValue = false;
    Rectangle oldRec = new Rectangle();
    private String[] labelNames;
    Rectangle selectedRec = new Rectangle();
    int selectedCol = 0;
    int selectedRow = 0;
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

    int startRow = 0;
    int rowNumber;
    GridPane gridpaneLight;
    GridPane gridpaneWater;
    Rectangle[] waterRecArray;
    Rectangle[][] lightRecArray;
    final ArrayList<Rectangle> copyAL;
    final ArrayList<Integer> colAL;
    final ArrayList<Integer> rowAL;
    final SortedMap<Integer, Integer> colRow;
    	
    MenuItem copy = new MenuItem("copy");
    MenuItem paste = new MenuItem("paste");
    
    final ContextMenu cm = new ContextMenu();

    public TimelineController() {
        this.colorEnumArray = ColorPaletteEnum.values();
        this.colRow = new TreeMap<>();
        this.rowAL = new ArrayList<>();
        this.colAL = new ArrayList<>();
        this.copyAL = new ArrayList<>();
    }
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    @SuppressWarnings("Convert2Lambda")
    public void initialize(URL url, ResourceBundle rb) {
        // setWaterGridPane();
        instance = this;
        configureTimelines();
        cm.getItems().add(copy);
        cm.getItems().add(paste);
        
        copy.setDisable(true);
        paste.setDisable(true);
        
        copy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                paste.setDisable(false);
                System.out.println(copyAL.toString());
            }
    	});
    	
    	paste.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                int count = 0;
                for(Rectangle rec: copyAL){
                    lightRecArray[selectedCol + count][selectedRow ].setFill(rec.getFill());
                    System.out.println((selectedCol+count) +" " +(selectedRow));
                    count++;
                }
            }
    		
        });
    }

    public void configureTimelines() {
        setLabelNames(new String[]{"Module1", "Module2", "Module3", 
                "Module4", "Module5", "Module6", "Module7", "ModuleA", "ModuleB", 
                "FrontCurtain", "BackCurtain", "PeacockAB", "SpoutVoice", "AllLEDs"});
        setLabelGridPane(getLabelNames());
        setTimelineGridPane();
    }
    
    public void disableCopyPaste(){
    	copy.setDisable(true);
    	paste.setDisable(true);
    }
//    public void setLabelGridPane() {
//        // 57 lines long
//        final String[] labelNames = new String[] {"Custom Channel", "Module 1", "Module 2",
//                        "Module 3", "Module 4", "Module 5", "Module 6", "Module 7",
//                        "A Modules", "B Modules", "Front Curtain", "Back Curtain",
//                        "Peacock", "Voice", "ALL LEDs", "L1-A Mod 1 Front Left",
//                        "L2-A Mod 1 Front Right", "L6-A Mod 2 Front Left",
//                        "L7-A Mod 2 Front Right", "L11-A Mod 3 Front Left",
//                        "L12-A Mod 3 Front Right", "L19-A Mod 4 Front Left",
//                        "L16-A Mod 4 Front Left Center", "L18-A Mod Front Center",
//                        "L17-A Mod 4 Front Right Center", "L20-A Mod 4 Front Right",
//                        "L21-A Mod 5 Front Left", "L22-A Mod 5 Front Right",
//                        "L26-A Mod 6 Front Left", "L27-A Mod 6 Front Right",
//                        "L31-A Mod 7 Front Left", "L32-A Mod 7 Front Right",
//                        "L4-A Mod 1 Back Left", "L3-A Mod 1 Back Center",
//                        "L5-A Mod 1 Back Right", "L9-A Mod 2 Back Left",
//                        "L8-A Mod 2 Back Center", "L10-A Mod 2 Back Right",
//                        "L14-A Mod 3 Back Left", "L13-A Mod 3 Back Center",
//                        "L15-A Mod 3 Back Right", "L37-Peacock 1, Left",
//                        "L38-Peacock 2, Left Center", "L39-Peacock 3, Right Center",
//                        "L40-Peacock 4- Right", "L24-A Mod 5 Back Left",
//                        "L23-A Mod 5 Back Center", "L25-A Mod 5 Back Right",
//                        "L29-A Mod 6 Back Left", "L28-A Mod 6 Back Center",
//                        "L30-A Mod 6 Back Right", "L34-A Mod 7 Back Left",
//                        "L33-A Mod 7 Back Center", "L35-A Mod 7 Back Right",
//                        "L36-Voice 1", "Space for Voice 2", "Custom Channel" };
//
//        // need to be changed to 57 for full length, no scroll pane so takes up
//        // whole screen,
//        //the first custom channel is hter only for testing!!!!!!!!
//        final Label[] labelArray = new Label[57];
//        for (int i = 0; i < 13; i++) {
//            timelineLabelPane.getRowConstraints().add(new RowConstraints(26));
//        }
//
//        for (int i = 0; i < 14; i++) {
    
    public void setLabelGridPane(String[] input) {
    	setLabelNames(input);
    	Set<Integer> addresses = new HashSet<>();
    	for(String in: input) {
            Integer addr = FCWLib.getInstance().lookupAddress(in);
    		 addresses.add(addr);
    	}
    	channelAddresses = addresses.toArray(new Integer[1]);
    	setLabelGridPane(channelAddresses);
	}
    
    public void setLabelGridPaneWithCtl(){
    	Set<Integer> channelAddressesSet = Timeline.getInstance().getGtfoMap().keySet();
        channelAddresses = channelAddressesSet.toArray(channelAddresses);
        labelNames = new String[channelAddresses.length];
        for(int i = 0; i < channelAddresses.length; i++) {
   		 	labelNames[i] = FCWLib.getInstance().reverseLookupAddress(channelAddresses[i]);
        }
        setLabelGridPane(channelAddresses);
        
    }
    	
	public void setLabelGridPane(Integer[] input) {
        timelineLabelPane = new GridPane();
    	timelineLabelPane.setGridLinesVisible(true);
    	Label[] labelArray = new Label[input.length];
    	for(int i=0; i<input.length;i++){
            timelineLabelPane.getRowConstraints().add(new RowConstraints(26));
    	}
    	timelineLabelPane.getColumnConstraints().add(new ColumnConstraints(98));
    	
    	for(int i=0; i < input.length;i++){
            labelArray[i] = new Label(FCWLib.getInstance().reverseLookupAddress((input[i])));
            timelineLabelPane.add(labelArray[i], 0, i);
    	}
        
        timelineScrollPane.vvalueProperty().addListener(new ChangeListener<Object>() {
            @Override
            public void changed(ObservableValue<?> o,Object oldVal, 
                     Object newVal){
                 labelScrollPane.setVvalue(timelineScrollPane.getVvalue());
            }
          });
    	 labelScrollPane.vvalueProperty().addListener(new ChangeListener<Object>() {
            @Override
            public void changed(ObservableValue<?> o,Object oldVal, 
                     Object newVal){
                    timelineScrollPane.setVvalue(labelScrollPane.getVvalue());
            }
          });
    	labelScrollPane.setContent(timelineLabelPane);
//    	rePaint();
    }
    
    /**
     * 
     */
    public void setTimelineGridPane() {
        gridpaneLight = new GridPane();
        time = MusicPaneController.SONG_TIME;
        gridpaneLight.setGridLinesVisible(true);
        rowNumber =  labelNames.length;
        lightRecArray = new Rectangle[time][labelNames.length];
        
        for (int i = 0; i < time; i++) {
            gridpaneLight.getColumnConstraints().add(new ColumnConstraints(26));
            if (i < labelNames.length) { // because the array is not square this needs to be
                                            // here
                    gridpaneLight.getRowConstraints().add(new RowConstraints(26));
            }

            for (int j = 0; j < labelNames.length; j++) {
                // if (i == 0){
                // recArray[i][j] = new Rectangle(50,25, Color.RED);
                // continue;
                // }
                lightRecArray[i][j] = new Rectangle(25, 25, Color.LIGHTGREY);
                gridpaneLight.add(lightRecArray[i][j], i, j);
                // these are needed to talk to the mouse pressed events
                final int testI = i;
                final int testJ = j;

                lightRecArray[i][j].setOnMousePressed(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent me) {
                    	selectedRec = lightRecArray[testI][testJ];
                    	selectedCol = testI;
                    	selectedRow = testJ;
                    	
                        if(ChoreographyController.getInstance().getIsSelected()){
                        	if (me.getButton() == MouseButton.SECONDARY) {
                                cm.show(lightRecArray[testI][testJ], me.getScreenX(), me.getScreenY());
                            }
                        	
                        }
                    else{
                        		startRow = testJ;
                            lightRecArray[testI][testJ]
                                    .setFill(ColorPaletteController
                                            .getInstance()
                                            .getSelectedColor());
                    }
//                        Timeline.getInstance().setLightFcwAtPoint(testI, new FCW(testI, 
//                                ColorPaletteController.getInstance().getModel().getSelectedIndex()));
                    }
                });

                lightRecArray[i][j].setOnDragDetected((MouseEvent me) -> {
                	
                	for(Rectangle rec: copyAL){
                        rec.setOpacity(1);
                    }
                    copyAL.clear();
                    colAL.clear();
                    rowAL.clear();
                    colRow.clear();
                    
                	if(ChoreographyController.getInstance().getIsSelected()){
                		copy.setDisable(false);
                		lightRecArray[testI][testJ].startFullDrag();
                		lightRecArray[testI][testJ].setOpacity(50);
                        copyAL.add(lightRecArray[testI][testJ]);
                        colRow.put(testI, testJ);
                        
                        colAL.add(testI);
                        rowAL.add(testJ);
                	}
                	
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
                    
                    if (ChoreographyController.getInstance().getIsSelected()) {
                    	lightRecArray[testI][testJ].setOpacity(.50);
                        if (!copyAL.contains(lightRecArray[testI][testJ])){
                            copyAL.add(lightRecArray[testI][testJ]);
                            colRow.put(testI, testJ);
                            
                            colAL.add(testI);
                            rowAL.add(testJ);
                        }
                    } else {
                    	lightRecArray[testI][testJ].setFill(ColorPaletteController
                                .getInstance()
                                .getSelectedColor());
                    }
                });
            }
        }

        timelineScrollPane.setContent(gridpaneLight);
    }

    public void clearAllAL(){
    	for(Rectangle rec: copyAL){
            rec.setOpacity(1);
        }
    	copyAL.clear();
    	colAL.clear();
        rowAL.clear();
    }
    
    public void setWaterGridPane() {
        gridpaneWater = new GridPane();
        // NumberAxis valueAxis = new NumberAxis();

        time = MusicPaneController.SONG_TIME;
//        numLine = new NumberAxis((double) 0, (double) time, 1);

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
                                
                waterRecArray[i]
                        .setOnMousePressed(new EventHandler<MouseEvent>() {
                            public void handle(MouseEvent me) {
                            	
                    System.out.println("Col " + (testI));
                    Duration duration = MusicPaneController.getInstance().getMediaPlayer().getTotalDuration();
                    MusicPaneController.getInstance().getMediaPlayer().seek(Duration.seconds((((double)testI+1)/10)));
                    
                    waterRecArray[testI].setFill(Color.LIGHTBLUE);
                    System.out.println(Timeline.getInstance().getActionsAtTime(testI));
                    
                    if (!oldRecHasValue){ //aka oldRed does not have a value
                    	oldRec = waterRecArray[testI];
                    	oldRecHasValue = true;
                    }
                    else{
                    	if(Timeline.getInstance().getActionsAtTime(testI)){
                    		oldRec.setFill(Color.DARKBLUE);
                    	oldRec = waterRecArray[testI];
                    	waterRecArray[testI] = oldRec;
                    	}
                    	else{
                    		oldRec.setFill(Color.LIGHTGRAY);
                        	oldRec = waterRecArray[testI];
                        	waterRecArray[testI] = oldRec;
                    	}
                    	
                    	
                    }}
                });
        MusicPaneController.getInstance().getWaterPane().setContent(gridpaneWater);
    }
    }

    /**
     * 
     * @return
     */
    public ScrollPane getScrollPane() {
            return timelineScrollPane;
    }
    
    /**
     *
     */
    public void rePaint() {
        SortedMap<Integer, ArrayList<FCW>> waterTimeline = Timeline.getInstance().getWaterTimeline();
        SortedMap<Integer, ArrayList<FCW>> lightTimeline = Timeline.getInstance().getLightTimeline();
        for(Integer i: waterTimeline.keySet()){
            for(FCW f: waterTimeline.get(i)) {
                String name = FCWLib.getInstance().reverseLookupAddress(f);
                String[] actions = FCWLib.getInstance().reverseLookupData(f);
                StringBuilder actionList = new StringBuilder();
                for(String s: actions){
                	actionList.append(waterTLWords(s));
                	actionList.append(System.getProperty("line.separator"));
                }
                int tenthOfSec = i % 10;
                int secondsOnly = i /10; 
                double tenths = (double) tenthOfSec;
                double newTime = secondsOnly + (tenths / 10);
                int colAtTime = (int) (newTime * MusicPaneController.getInstance().getTimeFactor());
                if(colAtTime != 0){
                	colAtTime = colAtTime - 1;
                }
                waterRecArray[colAtTime].setFill(Color.ALICEBLUE);
                Tooltip t = new Tooltip(actionList.toString());
                Tooltip.install(waterRecArray[colAtTime], t);
                //TODO make mouse over info better
                //TODO update sliders
            }
        }
        SortedMap<Integer, SortedMap<Integer, Integer>> gtfoArray = Timeline.getInstance().getGtfoMap();
        for (int channel: gtfoArray.keySet()){
        	for (int timeIndex: gtfoArray.get(channel).keySet()){
//    			System.out.println(Timeline.getInstance().getGtfoMap().get(channel).get(timeIndex));
    			Integer gtfo = Timeline.getInstance().getGtfoMap().get(channel).get(timeIndex);
    			Paint color = ColorPaletteModel.getInstance().getColor(gtfo);
    			lightRecArray[timeIndex][lightRowLookupNumber(channel)].setFill(color);
    			
    		}
    		
    		}
    }
    
    /**
     * @param timeline the timeline to set
     */
    public void setTimeline(SortedMap<Integer, ArrayList<FCW>> timeline) {
       Timeline.getInstance().setTimeline(timeline);
    }

    private int lightRowLookupNumber(int channel){
    	for(int i = 0; i < channelAddresses.length; i++) {
    		if(channelAddresses[i] == channel) {
    			return i;
    		}
    	}
    	throw new IllegalArgumentException("Channel doesn't exist " + channel);
    //return 0;
    }
    
    private String waterTLWords(String badName){
    	String newName = "";
    	
    	switch(badName){
    	//From water address table
    	case "RING5":  newName = "Ring 5";
    	break;
    	case "RING4":  newName = "Ring 4";
		break;
    	case "RING3":  newName = "Ring 3";
		break;
    	case "RING2":  newName = "Ring 2";
		break;
    	case "RING1":  newName = "Ring 1";
		break;
    	case "SWEEP":  newName = "Sweeps";
    	break;
    	case "SPOUT":  newName = "Spout"; // TODO there are duplicate SPOUT in the FCW_DEF file one in the table A and one in WaterAddresses
    	break;
    	case "BAZOOKA": newName = "Bazooka"; // TODO Same issue as SPOUT.
    	break;
    	case "CANDELABRA": newName = "Candelabra";
    	break;
    	case "FTCURT": newName = "Front Curtain";
    	break;
    	case "BKCURT": newName = "Back Curtain";
    	break;
    	case "PEACOCK": newName = "Peacock";
    	break;
    	case "SWEEPA": newName = "Sweep A";
    	break;
    	case "SWEEPB": newName = "Sweep B";
    	break;
    	case "SWEEPLIMITAB": newName = "Sweep Limit A + B";
    	break;
    	case "SWEEPLIMITA": newName = "Sweep Limit A";
    	break;
    	case "SWEEPLIMITB": newName = "Sweep Limit B";
    	break;
    	case "MULTI": newName = "Wedding Cake Formation";
    	break;
    	case "PULSE": newName = "Repeat Jump @ 0.5sec";
    	break;
    	// From functions table
    	case "VOICE": newName = "Auto Cateloged Formations (water + light)"; //TODO needed? 
    	break;
    	case "INTERCHANGEAB": newName = "Interchange A+B module formations (water + light)"; //TODO needed?
    	break;
    	case "OFF": newName = "Off"; //TODO needed?
    	break;
    	case "MODULEA": newName = "Module A";
    	break;
    	case "MODULEB": newName = "Module B";
    	break;
    	case "CONNECTAB": newName = "Connect A + B";
    	break;
    	case "OFFRESET": newName = "Hold Center";
    	break;
    	case "SHORT": newName = "Short";
    	break;
    	case "LONG": newName = "Long";
    	break;
    	case "LARGO": newName = "Largo";
    	break;
    	case "ADAGIO": newName = "Slow Speed";
    	break;
    	case "ANDANTE": newName = "Andante";
    	break;
    	case "MODERATO": newName = "Medium Speed";
    	break;
    	case "ALLEGRETO": newName = "Allegreto";
    	break;
    	case "ALLEGRO": newName = "Fast Speed";
    	break;
    	case "PRESTO": newName = "Presto";
    	break;
    	case "PLAYPAUSE": newName = "Play/Pause Sweep";
    	break;
    	case "STOP": newName = "Stop Jumping";
    	break;
    	case "ADDRSWEEP": newName = "Sweep Formation";
    	break;
    	case "JUMPA": newName = "Jump A";
    	break;
    	case "JUMPB": newName = "Jump B";
    	break;
    	case "JUMP0OR1": newName = "Jump Phase";
    	break;
    	case "HOLDRIGHTOT": newName = "Hold Right OT";
    	break;
    	case "RIGHTOTRIGHTLONG": newName = "Rt OT - Rt Long";
    	break;
    	case "RIGHTOTRIGHTSHORT": newName = "Rt OT - Rt Short";
    	break;
    	case "RIGHTOTCENTER": newName = "Rt OT - C";
    	break;
    	case "RIGHTOTLEFTSHORT": newName = "Rt OT - Lt Short";
    	break;
    	case "RIGHTOTLEFTLONG": newName = "Rt OT - Lt Long";
    	break;
    	case "RIGHTOTLEFTOT": newName = "Rt OT - Lt OT";
    	break;
    	case "HOLDRIGHTLONG": newName = "Hold Right Long";
    	break;
    	case "RIGHTLONGRIGHTSHORT": newName = "Rt Long - Rt Short";
    	break;
    	case "RIGHTLONGCENTER": newName = "Rt Long - C";
    	break;
    	case "RIGHTLONGLEFTSHORT": newName = "Rt Long - Lt Short";
    	break;
    	case "RIGHTLONGLEFTLONG": newName = "Rt Long - Lt Long";
    	break;
    	case "RIGHTLONGLEFTOT": newName = "Rt Long - Lt OT";
    	break;
    	case "HOLDRIGHTSHORT": newName = "Hold Right Short";
    	break;
    	case "RIGHTSHORTCENTER": newName = "Right Short - C";
    	break;
    	case "RIGHTSHORTLEFTSHORT": newName = "Rt Short - Lt Short";
    	break;
    	case "RIGHTSHORTLEFTLONG": newName = "Rt Short - Lt Long";
    	break;
    	case "RIGHTSHORTLEFTOT": newName = "Rt Short - Left OT";
    	break;
    	case "HOLDCENTER": newName = "Sweep Hold C";
    	break;
    	case "CENTERLEFTSHORT": newName = "C - Lt Short";
    	break;
    	case "CENTERLEFTLONG": newName = "C - Lt Long";
    	break;
    	case "CENTERLEFTOT": newName = "C - Lt OT";
    	break;
    	case "HOLDLEFTSHORT": newName = "Hold Lt Short";
    	break;
    	case "LEFTSHORTLEFTLONG": newName = "Lt Short - Lt Long";
    	break;
    	case "LEFTSHORTLEFTOT": newName = "Lt Short - Lt OT";
    	break;
    	case "HOLDLEFTLONG": newName = "Hold Lt Long";
    	break;
    	case "LEFTLONGLEFTOT": newName = "Lt Long - Lt OT";
    	break;
    	case "HOLDLEFTOT": newName = "Hold Lt OT";
    	break;
    	case "SWEEPSPECIAL": newName = "Special";
    	break;
    	default: newName = badName;
    	
    	}
    	return newName;
		
    }

    public void fireSliderChangeEvent() {
        Timeline.getInstance().sendTimelineInstanceToSliders(MusicPaneController.getInstance().getTenthsTime());
    }

	public String[] getLabelNames() {
		return labelNames;
	}

	public void setLabelNames(String[] labelNames) {
		this.labelNames = labelNames;
	}
}
