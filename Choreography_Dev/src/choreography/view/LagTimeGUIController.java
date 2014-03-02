/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package choreography.view;

import choreography.io.LagTimeLibrary;
import choreography.model.lagtime.LagTimeTable;
import java.net.URL;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author elementsking
 */
public class LagTimeGUIController implements Initializable {
    @FXML
    private TableView<?> lagTimeTable;
    @FXML
    private TableColumn<String, String> delayName;
    @FXML
    private TableColumn<String, Double> delayTime;
    @FXML
    private Button cancelButton;
    @FXML
    private Button okButton;
    private Stage dialogStage;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        LagTimeTable ltt = LagTimeLibrary.getInstance().getLagTimes();
        SimpleListProperty<Entry> slp = new SimpleListProperty<>();
        slp.addAll(ltt.getDelays().entrySet());
        
        delayName.setCellValueFactory(
            new PropertyValueFactory<String,String>("delayName"));
        delayTime.setCellValueFactory(
            new PropertyValueFactory<String,Double>("delayTime"));
        
        okButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                HashMap<String, Double> delayTable = new HashMap();
                
            }
        });
        
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                cancel(t);
            }
        });
    }    

    @FXML
    private void cancel(ActionEvent event) {
    }

    @FXML
    private void saveLagTimes(ActionEvent event) {
    }
    
    public void setDialogStage(Stage dialog) {
        this.dialogStage = dialog;
    }

    void setDelays(LagTimeTable lagTimes) {
        
    }
    
}
