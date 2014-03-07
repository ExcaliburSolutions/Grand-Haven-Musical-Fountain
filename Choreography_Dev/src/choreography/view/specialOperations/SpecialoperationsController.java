/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package choreography.view.specialOperations;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author elementsking
 */
public class SpecialoperationsController implements Initializable {
    @FXML
    private TitledPane sweepControlsPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        RangeSlider aSweeps = new RangeSlider(0, 4, 1, 3);
        RangeSlider bSweeps = new RangeSlider(0, 4, 1, 3);
        AnchorPane sweepsAnchor = new AnchorPane(aSweeps, bSweeps);
        AnchorPane.setLeftAnchor(aSweeps, 30.0);
        AnchorPane.setRightAnchor(aSweeps, 30.0);
        AnchorPane.setTopAnchor(aSweeps, 30.0);
        AnchorPane.setTopAnchor(bSweeps, 140.0);
        AnchorPane.setLeftAnchor(bSweeps, 30.0);
        AnchorPane.setRightAnchor(bSweeps, 30.0);
        
        aSweeps.setSnapToTicks(true); 
        aSweeps.setMajorTickUnit(6); aSweeps.setShowTickMarks(true); 
        aSweeps.setMinorTickCount(0); aSweeps.setBlockIncrement(1);
        
        bSweeps.setSnapToTicks(true); 
        bSweeps.setMajorTickUnit(6); bSweeps.setShowTickMarks(true); 
        bSweeps.setMinorTickCount(0); bSweeps.setBlockIncrement(1);
        sweepControlsPane.setContent(sweepsAnchor);
    }    
    
}
