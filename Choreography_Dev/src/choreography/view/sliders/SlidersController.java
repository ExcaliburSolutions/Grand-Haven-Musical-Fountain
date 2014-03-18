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
import choreography.Main;
import choreography.io.FCWLib;
import choreography.model.Fountain;
import choreography.model.ModuleGroup;
import choreography.model.cannon.Candelabra;
import choreography.model.cannon.Cannon;
import choreography.model.cannon.CannonEnum;
import choreography.model.cannon.IndependentCannon;
import choreography.model.cannon.Multi;
import choreography.model.cannon.Ring;
import choreography.model.cannon.Sweep;
import choreography.model.fcw.FCW;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;

/**
 *
 * @author elementsking
 */
public class SlidersController {
    
    private static SlidersController instance;
    
    public static SlidersController getInstance() {
        if(instance == null) {
            return instance = new SlidersController();
        }
        return instance;
    }

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
    private IndependentCannon peacock;
    private IndependentCannon bazooka;
    private IndependentCannon spout;
    private IndependentCannon bkCurt;
    private IndependentCannon ftCurt;


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
        setupIndependentCannons();
    }
    
    /**
     *
     * @param list
     * @param aB
     * @param ce
     * @param s
     */
    public void setupCannonSliderChangeListener(ArrayList<? extends Cannon> list, 
            ModuleGroup aB, CannonEnum ce, Slider s){
        
        list = aB.getCannonGroup(ce);
        CannonSliderChangeListener<? extends Cannon> cs = new CannonSliderChangeListener<>(list, aB.toString());
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
    
    private void setupIndependentCannons() {
    	bz.valueProperty().addListener(new IndependentCannonSliderChangeListener(bazooka));
    	sp.valueProperty().addListener(new IndependentCannonSliderChangeListener(spout));
    	pk.valueProperty().addListener(new IndependentCannonSliderChangeListener(peacock));
    	bkC.valueProperty().addListener(new IndependentCannonSliderChangeListener(bkCurt));
    	ftC.valueProperty().addListener(new IndependentCannonSliderChangeListener(ftCurt));
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
    
    public void setSlidersWithFcw(ArrayList<FCW> fcws) {
        for(FCW f: fcws) {
            String[] actions = FCWLib.getInstance().reverseLookupData(f);
            String module = findModule(actions);
            int level = findLevel(actions);
            switch(f.getAddr()) {
                case 1:
                    if(module.equalsIgnoreCase("A")){
                        r5A.setValue(level);
                    }
                    else if(module.equalsIgnoreCase("B")) {
                        r5B.setValue(level);
                    }
                break;
                case 2:
                     if(module.equalsIgnoreCase("A")){
                        r4A.setValue(level);
                    }
                    else if(module.equalsIgnoreCase("B")) {
                        r4B.setValue(level);
                    }
                break;
                case 3:
                     if(module.equalsIgnoreCase("A")){
                        r3A.setValue(level);
                    }
                    else if(module.equalsIgnoreCase("B")) {
                        r3B.setValue(level);
                    }
                break;
                case 4:
                     if(module.equalsIgnoreCase("A")){
                        r2A.setValue(level);
                    }
                    else if(module.equalsIgnoreCase("B")) {
                        r2B.setValue(level);
                    }
                break;
                case 5:
                     if(module.equalsIgnoreCase("A")){
                        r1A.setValue(level);
                    }
                    else if(module.equalsIgnoreCase("B")) {
                        r1B.setValue(level);
                    }
                break;
                case 6:
                    if(module.equalsIgnoreCase("A")){
                        swA.setValue(level);
                    }
                    else if(module.equalsIgnoreCase("B")) {
                        swB.setValue(level);
                    }
                break; 
                case 7:
                     if(module.equalsIgnoreCase("A")){
                        sp.setValue(level);
                    }
                    else if(module.equalsIgnoreCase("B")) {
                        bz.setValue(level);
                    }
                break;
                case 8:
                     if(module.equalsIgnoreCase("A")){
                        candleA.setValue(level);
                    }
                    else if(module.equalsIgnoreCase("B")) {
                        candleB.setValue(level);
                    }
                break;
                case 9:
                     if(module.equalsIgnoreCase("A")){
                        ftC.setValue(level);
                    }
                    else if(module.equalsIgnoreCase("B")) {
                        for(String action: actions) {
                            if(action.equalsIgnoreCase("peacock")) {
                                pk.setValue(level);
                            }
                            else if(action.equalsIgnoreCase("bkcurt")) {
                                bkC.setValue(level);
                            }
                        }
                    }
                break;
                case 48:
                    if(module.equalsIgnoreCase("A")){
                        r1A.setValue(level);
                        r2A.setValue(level);
                        r3A.setValue(level);
                        r4A.setValue(level);
                        r5A.setValue(level);
                        swA.setValue(level);
                    }
                    else if(module.equalsIgnoreCase("B")) {
                        r1B.setValue(level);
                        r2B.setValue(level);
                        r3B.setValue(level);
                        r4B.setValue(level);
                        r5B.setValue(level);
                        swB.setValue(level);
                    }
                break;
            }
        }
    }
    
    public String findModule(String[] input) {
        for(String action: input) {
            if(action.equalsIgnoreCase("modulea")){
                return "A";
            }
            else if(action.equalsIgnoreCase("moduleb")){
                return "B";
            }
            else if(action.equalsIgnoreCase("ftcurt")) {
                return "A";
            }
        }
        return null;
    }
    
    public int findLevel(String[] input) {
        for(String action: input) {
            switch(action){
                case "0":
                    return 0;
                case "1":
                    return 1;
                case "2":
                    return 2;
                case "3":
                    return 3;
                case "4":
                    return 4;
                case "5":
                    return 5;
                case "6":
                    return 6;
            }
        }
        throw new IllegalArgumentException("Illegal level found..." + input);
    }
}

