
package choreography.view.timeline;

import java.net.URL;
import java.util.ResourceBundle;


import choreography.view.colorPalette.ColorPaletteController;
//import SimpleJavaFXPlayer.ColumnConstraints;
//import SimpleJavaFXPlayer.EventHandler;
//import SimpleJavaFXPlayer.MouseEvent;
//import SimpleJavaFXPlayer.Rectangle;
//import SimpleJavaFXPlayer.RowConstraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
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
    @FXML
    private GridPane labelGridpane;
    @FXML
    private Label timelineLabel;
    @FXML
    private ScrollPane timelineScrollPane;
    @FXML
    private ListView<?> timelineListView;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        GridPane gridpaneRec = new GridPane();

        final ColorPaletteController color = ColorPaletteController.getInstance();
    
	gridpaneRec.setGridLinesVisible(true);

	final Rectangle[][] recArray = new Rectangle[101][17];
	 for(int i=0; i<101; i++){
  		  gridpaneRec.getColumnConstraints().add(new ColumnConstraints(26));
  		  if (i < 17){ //because the array is not square this needs to be here
  			 gridpaneRec.getRowConstraints().add(new RowConstraints(26));
  		  }
  		  
   	  for(int j=0; j<17; j++){
   		  if (i == 0){
   			 recArray[i][j] = new Rectangle(50,25, Color.RED);
   			 continue;
   		  }
   		  recArray[i][j] = new Rectangle(25,25, Color.LIGHTGREY);
   		  gridpaneRec.add(recArray[i][j], i, j);
   		  //these are needed to talk to the mouse pressed events
   		  final int testI = i;
   		  final int testJ = j;
   		  
   		recArray[i][j].setOnMousePressed(new EventHandler<MouseEvent>() {
                             @Override
   			  public void handle(MouseEvent me) {
   	        System.out.println("Col " + (testI) + " Row " + (testJ+1));
//   	     recArray[testI][testJ].setFill(ColorPaletteController.getInstance()
//                     .getSelectedColor());
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
//				  recArray[testI][testJ].setFill(ColorPaletteController.getInstance()
//                     .getSelectedColor());
	    }
   	});
   	  }
     }
    
//	 scrollpane.setPrefSize(600, 250);
	 timelineScrollPane.setContent(gridpaneRec);
	 
    }
}
