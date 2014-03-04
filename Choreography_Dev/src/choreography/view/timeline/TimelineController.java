
package choreography.view.timeline;

import choreography.model.fcw.FCW;
import java.net.URL;
import java.util.ResourceBundle;
import choreography.view.colorPalette.ColorPaletteController;
import choreography.view.music.MusicPaneController;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.event.EventHandler;
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
    private ScrollPane timelineScrollPane;
    //NonFXML
    private int time;
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
//    	labelGridpane.setGridLinesVisible(true);
    	final Label[] labelArray = new Label[18];
    	
    	for(int i=0; i<17;i++){
            timelineLabelPane.getRowConstraints().add(new RowConstraints(26));
            labelArray[i] = new Label(""+i);
            timelineLabelPane.add(labelArray[i], 0, i);
    	}
    }
    
    /**
     * 
     */
    public void setTimelineGridPane() {
        GridPane gridpaneRec = new GridPane();
        
        time = MusicPaneController.SONG_TIME;
    
	gridpaneRec.setGridLinesVisible(true);

	final Rectangle[][] recArray = new Rectangle[time][17];
        for(int i=0; i<time; i++){
            gridpaneRec.getColumnConstraints().add(new ColumnConstraints(26));
            if (i < 17){ //because the array is not square this needs to be here
                   gridpaneRec.getRowConstraints().add(new RowConstraints(26));
            }
  		  
        for(int j=0; j<17; j++){
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
        //tesdfsdha; dnjasbnd jasndj bsnadkj asnda 
    
	 timelineScrollPane.setContent(gridpaneRec);
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
