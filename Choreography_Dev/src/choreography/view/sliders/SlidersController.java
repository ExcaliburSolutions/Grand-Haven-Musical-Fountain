/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package choreography.view.sliders;
/**
 * Sample Skeleton for "Sliders.fxml" Controller Class
 * You can copy and paste this code into your favorite IDE
 **/



import choreography.model.cannon.FtCurt;
import choreography.model.cannon.Peacock;
import choreography.model.cannon.Spout;
import choreography.model.cannon.Bazooka;
import choreography.model.cannon.BkCurt;
import choreography.model.cannon.Sweep;
import choreography.model.cannon.Multi;
import choreography.model.cannon.Ring;
import choreography.model.cannon.Cannon;
import choreography.model.cannon.Candelabra;
import choreography.Main;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import choreography.model.cannon.CannonEnum;
import choreography.model.Fountain;
import choreography.model.ModuleGroup;


public class SlidersController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="bkC"
    private Slider bkC; // Value injected by FXMLLoader

    @FXML // fx:id="bz"
    private Slider bz; // Value injected by FXMLLoader

    @FXML // fx:id="candleA"
    private Slider candleA; // Value injected by FXMLLoader

    @FXML // fx:id="candleB"
    private Slider candleB; // Value injected by FXMLLoader

    @FXML // fx:id="ftC"
    private Slider ftC; // Value injected by FXMLLoader

    @FXML // fx:id="mxA"
    private Slider mxA; // Value injected by FXMLLoader

    @FXML // fx:id="mxB"
    private Slider mxB; // Value injected by FXMLLoader

    @FXML // fx:id="pk"
    private Slider pk; // Value injected by FXMLLoader

    @FXML // fx:id="r1A"
    private Slider r1A; // Value injected by FXMLLoader

    @FXML // fx:id="r1B"
    private Slider r1B; // Value injected by FXMLLoader

    @FXML // fx:id="r2A"
    private Slider r2A; // Value injected by FXMLLoader

    @FXML // fx:id="r2B"
    private Slider r2B; // Value injected by FXMLLoader

    @FXML // fx:id="r3A"
    private Slider r3A; // Value injected by FXMLLoader

    @FXML // fx:id="r3B"
    private Slider r3B; // Value injected by FXMLLoader

    @FXML // fx:id="r4A"
    private Slider r4A; // Value injected by FXMLLoader

    @FXML // fx:id="r4B"
    private Slider r4B; // Value injected by FXMLLoader

    @FXML // fx:id="r5A"
    private Slider r5A; // Value injected by FXMLLoader

    @FXML // fx:id="r5B"
    private Slider r5B; // Value injected by FXMLLoader

    @FXML // fx:id="sp"
    private Slider sp; // Value injected by FXMLLoader

    @FXML // fx:id="swA"
    private Slider swA; // Value injected by FXMLLoader

    @FXML // fx:id="swB"
    private Slider swB; // Value injected by FXMLLoader
    private Fountain fountain;
    private ModuleGroup A;
    private ModuleGroup B;
    private ArrayList<Ring> rings1A, rings1B, rings2A, rings2B, rings3A, 
            rings3B, rings4A, rings4B, rings5A, rings5B;
    private ArrayList<Multi> multisA, multisB;
    private ArrayList<Candelabra> candlesA, candlesB;
    private ArrayList<Sweep> sweepsA, sweepsB;
    private Peacock peacock;
    private Bazooka bazooka;
    private Spout spout;
    private BkCurt bkCurt;
    private FtCurt ftCurt;


    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert bkC != null : "fx:id=\"bkC\" was not injected: check your FXML file 'Sliders.fxml'.";
        assert bz != null : "fx:id=\"bz\" was not injected: check your FXML file 'Sliders.fxml'.";
        assert candleA != null : "fx:id=\"candleA\" was not injected: check your FXML file 'Sliders.fxml'.";
        assert candleB != null : "fx:id=\"candleB\" was not injected: check your FXML file 'Sliders.fxml'.";
        assert ftC != null : "fx:id=\"ftC\" was not injected: check your FXML file 'Sliders.fxml'.";
        assert mxA != null : "fx:id=\"mxA\" was not injected: check your FXML file 'Sliders.fxml'.";
        assert mxB != null : "fx:id=\"mxB\" was not injected: check your FXML file 'Sliders.fxml'.";
        assert pk != null : "fx:id=\"pk\" was not injected: check your FXML file 'Sliders.fxml'.";
        assert r1A != null : "fx:id=\"r1A\" was not injected: check your FXML file 'Sliders.fxml'.";
        assert r1B != null : "fx:id=\"r1B\" was not injected: check your FXML file 'Sliders.fxml'.";
        assert r2A != null : "fx:id=\"r2A\" was not injected: check your FXML file 'Sliders.fxml'.";
        assert r2B != null : "fx:id=\"r2B\" was not injected: check your FXML file 'Sliders.fxml'.";
        assert r3A != null : "fx:id=\"r3A\" was not injected: check your FXML file 'Sliders.fxml'.";
        assert r3B != null : "fx:id=\"r3B\" was not injected: check your FXML file 'Sliders.fxml'.";
        assert r4A != null : "fx:id=\"r4A\" was not injected: check your FXML file 'Sliders.fxml'.";
        assert r4B != null : "fx:id=\"r4B\" was not injected: check your FXML file 'Sliders.fxml'.";
        assert r5A != null : "fx:id=\"r5A\" was not injected: check your FXML file 'Sliders.fxml'.";
        assert r5B != null : "fx:id=\"r5B\" was not injected: check your FXML file 'Sliders.fxml'.";
        assert sp != null : "fx:id=\"sp\" was not injected: check your FXML file 'Sliders.fxml'.";
        assert swA != null : "fx:id=\"swA\" was not injected: check your FXML file 'Sliders.fxml'.";
        assert swB != null : "fx:id=\"swB\" was not injected: check your FXML file 'Sliders.fxml'.";

        // Initialize your logic here: all @FXML variables will have been injected
        configureModules();
    }
    private void configureModules(){
        fountain = Main.getFountain();
        A = fountain.getA();
        B = fountain.getB();
        rings1A = new ArrayList<>();
        setupAModule();
        setupBModule();
        peacock = fountain.getPeacock();
        bazooka = fountain.getBazooka();
        spout = fountain.getSpout();
        bkCurt = fountain.getBkCurt();
        ftCurt = fountain.getFtCurt();
    }
    
    public void setupCannonSliderChangeListener(ArrayList<? extends Cannon> list, 
            ModuleGroup aB, CannonEnum ce, Slider s){
        
        list = aB.getCannonGroup(ce);
        CannonSliderChangeListener cs = new CannonSliderChangeListener(list, aB.toString());
        s.valueProperty().addListener(cs);

    }

    private void setupAModule() {
        setupCannonSliderChangeListener(rings1A, A, CannonEnum.RING1, r1A);

        setupCannonSliderChangeListener(rings2A, A, CannonEnum.RING2, r2A);

        setupCannonSliderChangeListener(rings3A, A, CannonEnum.RING3, r3A);

        setupCannonSliderChangeListener(rings4A, A, CannonEnum.RING4, r4A);

        setupCannonSliderChangeListener(rings5A, A, CannonEnum.RING5, r5A);

        setupCannonSliderChangeListener(multisA, A, CannonEnum.MULTI, mxA);

        setupCannonSliderChangeListener(candlesA, A, CannonEnum.CANDELABRA, 
                candleA);

        setupCannonSliderChangeListener(sweepsA, A, CannonEnum.SWEEP, swA);
    }


    /**
     * 
     */
    private void setupBModule() {
        setupCannonSliderChangeListener(rings1B, B, CannonEnum.RING1, r1B);

        setupCannonSliderChangeListener(rings2B, B, CannonEnum.RING2, r2B);

        setupCannonSliderChangeListener(rings3B, B, CannonEnum.RING3, r3B);

        setupCannonSliderChangeListener(rings4B, B, CannonEnum.RING4, r4B);

        setupCannonSliderChangeListener(rings5B, B, CannonEnum.RING5, r5B);

        setupCannonSliderChangeListener(multisB, B, CannonEnum.MULTI, mxB);

        setupCannonSliderChangeListener(candlesB, B, CannonEnum.CANDELABRA, candleB);

        setupCannonSliderChangeListener(sweepsB, B, CannonEnum.SWEEP, swB);
    }


    /**
     * @return the fountain
     */
    public Fountain getFountain() {
            return fountain;
    }

    /**
     * @param fountain the fountain to set
     */
    public void setFountain(Fountain fountain) {
            this.fountain = fountain;
    }
}

