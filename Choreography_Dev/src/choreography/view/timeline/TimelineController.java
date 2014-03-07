
package choreography.view.timeline;

import choreography.io.FCWLib;
import choreography.model.fcw.FCW;

import java.net.URL;
import java.util.ResourceBundle;

import choreography.view.colorPalette.ColorPaletteController;
import choreography.view.music.MusicPaneController;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author elementsking
 */
public class TimelineController implements Initializable {
	
	int startRow = 0;
    private static TimelineController instance;

    /**
     *
     * @return
     */
    public static TimelineController getInstance() {
		if(instance == null)
			return instance;
		return instance;
	}
	
    @FXML
    private GridPane timelineLabelPane;
    @FXML
    private ScrollPane timelineScrollPane, labelScrollPane;
    //NonFXML
    private int time;
    //private NumberAxis numLine;
    private HashMap<Integer, ArrayList<FCW>> timeline;
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        setTimelineGridPane();
        setLabelGridPane();
        //setWaterGridPane();
        instance = this;
        
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
        
    }
    
    public void setLabelGridPane(){
    	timelineLabelPane = new GridPane();
    	final String[] labelNames = new String[]{"Module 1", "Module 2", "Module 3", "Module 4", "Module 5",
"Module 6", "Module 7", "A Modules", "B Modules", "Front Curtain", "Back Curtain", "Peacock", "Voice", "ALL LEDs"};
    	
    	timelineLabelPane.setGridLinesVisible(true);
    	timelineLabelPane.setStyle("-fx-background-color: #4CC552;");
    	
    	final Label[] labelArray = new Label[14];
    	for(int i=0; i<14;i++){
        timelineLabelPane.getRowConstraints().add(new RowConstraints(26));
    	}
    	timelineLabelPane.getColumnConstraints().add(new ColumnConstraints(98));
    	
    	for(int i=0; i<14;i++){
            labelArray[i] = new Label(labelNames[i]);
            timelineLabelPane.add(labelArray[i], 0, i);
    	}
    	 timelineScrollPane.vvalueProperty().addListener(new ChangeListener(){
    	        @Override public void changed(ObservableValue o,Object oldVal, 
    	                 Object newVal){
    	             labelScrollPane.setVvalue(timelineScrollPane.getVvalue());
    	        }
    	      });
    	 labelScrollPane.vvalueProperty().addListener(new ChangeListener(){
 	        @Override public void changed(ObservableValue o,Object oldVal, 
 	                 Object newVal){
 	        	timelineScrollPane.setVvalue(labelScrollPane.getVvalue());
 	        }
 	      });
    	labelScrollPane.setContent(timelineLabelPane);
    }
    
    /**
     * 
     */
    public void setTimelineGridPane() {
        GridPane gridpaneRec = new GridPane();
        
        time = MusicPaneController.SONG_TIME;
    
	gridpaneRec.setGridLinesVisible(true);

	final Rectangle[][] recArray = new Rectangle[time][14];
        for(int i=0; i<time; i++){
            gridpaneRec.getColumnConstraints().add(new ColumnConstraints(26));
            if (i < 14){ //because the array is not square this needs to be here
                   gridpaneRec.getRowConstraints().add(new RowConstraints(26));
            }
  		  
        for(int j=0; j<14; j++){
//   		  if (i == 0){
//   			 recArray[i][j] = new Rectangle(50,25, Color.RED);
//   			 continue;
//   		  }
   		  recArray[i][j] = new Rectangle(25,25, Color.LIGHTGREY);
   		  gridpaneRec.add(recArray[i][j], i, j);
   		  //these are needed to talk to the mouse pressed events
   		  final int testI = i;
   		  final int testJ = j;
   		  
   		recArray[i][j].setOnMousePressed(new EventHandler<MouseEvent>() {
                             @Override
   			  public void handle(MouseEvent me) {
   	        System.out.println("Col " + (testI) + " Row " + (testJ+1));
   	        startRow = testJ;
   	     recArray[testI][testJ].setFill(ColorPaletteController.getInstance()
                     .getSelectedColor());
   	    }
   	});
   		
   		recArray[i][j].setOnDragDetected(new EventHandler<MouseEvent>() {
                           @Override
 			  public void handle(MouseEvent me) {
 				  //starts the drag event
 				 recArray[testI][testJ].startFullDrag();
 	    }
 			  
 	});
        //continues and ends the drag event
        recArray[i][j].setOnMouseDragOver(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
            	if(startRow == testJ){
            		recArray[testI][testJ].setFill(ColorPaletteController.getInstance()
                    .getSelectedColor());
            	}
                    
	    }
   	});
   	  }
     }
    
	 timelineScrollPane.setContent(gridpaneRec);
	}
    
    
    public void setWaterGridPane() {
		GridPane gridpaneRec = new GridPane();
		//NumberAxis valueAxis = new NumberAxis();
        
        time = MusicPaneController.SONG_TIME;
        //numLine = new NumberAxis((double)0,(double) time, 1);
    
	gridpaneRec.setGridLinesVisible(true);

	final Rectangle[][] recArray = new Rectangle[time][1];
	 for(int i=0; i<time; i++){
  		  gridpaneRec.getColumnConstraints().add(new ColumnConstraints(26));
  		  if (i < 1){ //because the array is not square this needs to be here
  			 gridpaneRec.getRowConstraints().add(new RowConstraints(26));
  		  }
  		  
//   	  for(int j=0; j<1; j++){
////   		  if (i == 0){
////   			 recArray[i][j] = new Rectangle(50,25, Color.RED);
////   			 continue;
////   		  }
//   		  recArray[i][j] = new Rectangle(25,25, Color.LIGHTGREY);
//   		  gridpaneRec.add(recArray[i][j], i, j);
//   		  //these are needed to talk to the mouse pressed events
//   		  final int testI = i;
//   		  final int testJ = j;
//   		  
//   		recArray[i][j].setOnMousePressed(new EventHandler<MouseEvent>() {
//                             @Override
//   			  public void handle(MouseEvent me) {
//   	        System.out.println("Col " + (testI) + " Row " + (testJ+1));
//   	     recArray[testI][testJ].setFill(ColorPaletteController.getInstance()
//                     .getSelectedColor());
//   	    }
//   	});
//   		
//   		recArray[i][j].setOnDragDetected(new EventHandler<MouseEvent>() {
//                           @Override
// 			  public void handle(MouseEvent me) {
// 				  //starts the drag event
// 				 recArray[testI][testJ].startFullDrag();
// 	    }
// 			  
// 	});
//   		//continues and ends the drag event
//   		recArray[i][j].setOnMouseDragOver(new EventHandler<MouseEvent>() {
//                          @Override
//			  public void handle(MouseEvent me) {
//				  recArray[testI][testJ].setFill(ColorPaletteController.getInstance()
//                     .getSelectedColor());
//	    }
//   	});
//   	  }
     }
//    ValueAxis axis = new ValueAxis();
	 
//	 scrollpane.setPrefSize(600, 250);
	 MusicPaneController.getInstance().getWaterPane().setContent(gridpaneRec);
	 //MusicPaneController.getInstance().getLabelPane().setContent(numLine);
	}
    

    /**
     *
     * @return
     */
    public ScrollPane getScrollPane(){
		return timelineScrollPane;
    }
    
    public HashMap<Integer, ArrayList<FCW>> getTimeline() {
        return timeline;
    }
}
