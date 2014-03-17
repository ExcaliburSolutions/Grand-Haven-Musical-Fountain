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
import javafx.scene.control.CheckBox;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author elementsking
 */
public class SpecialoperationsController implements Initializable {
    @FXML
    private TitledPane sweepControlsPane;
    
    @FXML
    private ToggleGroup CopyButtons;

    @FXML
    private CheckBox voiceCheckbox;

    @FXML
    private AnchorPane bSweepsPane;

    @FXML
    private ToggleButton copyWater;

    @FXML
    private CheckBox operatorCheckbox;

    @FXML
    private ToggleButton copyLight;

    @FXML
    private ToggleGroup SpecialOps;

    @FXML
    private ToggleButton copyAtoB;

    @FXML
    private ToggleButton copyBtoA;

    @FXML
    private ToggleButton eraseButton;

    @FXML
    private AnchorPane aSweepsPane;

    @FXML
    private ToggleButton resetButton;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        RangeSlider aSweeps = new RangeSlider(0.0, 4.0, 1.0, 3.0);
        RangeSlider bSweeps = new RangeSlider(0.0, 4.0, 1.0, 3.0);
       
        //= new AnchorPane(aSweeps);
        
        //= new AnchorPane(bSweeps);
        //AnchorPane sweepsAnchor = new AnchorPane(aSweeps, bSweeps);
//        AnchorPane.setLeftAnchor(aSweeps, 5.0);
//        AnchorPane.setRightAnchor(aSweeps, 5.0);
//        AnchorPane.setTopAnchor(aSweeps, 10.0);
//        AnchorPane.setTopAnchor(bSweeps, 25.0);
//        AnchorPane.setLeftAnchor(bSweeps, 5.0);
//        AnchorPane.setRightAnchor(bSweeps, 5.0);
        
        aSweeps.setSnapToTicks(true);
        aSweeps.setMajorTickUnit(1); aSweeps.setShowTickMarks(true); 
        aSweeps.setMinorTickCount(0); aSweeps.setBlockIncrement(1);
        
        bSweeps.setSnapToTicks(true);
        bSweeps.setMajorTickUnit(1); bSweeps.setShowTickMarks(true); 
        bSweeps.setMinorTickCount(0); bSweeps.setBlockIncrement(1);
        aSweepsPane.getChildren().add(aSweeps); 
        bSweepsPane.getChildren().add(bSweeps); 
        //sweepControlsPane.setContent(sweepsAnchor);
    }    
    
}
