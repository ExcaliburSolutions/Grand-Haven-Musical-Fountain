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
import choreography.model.fountain.Fountain;
import choreography.model.fountain.ModuleGroup;
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
import java.util.Iterator;
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
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Slider bkC;

    @FXML
    private Slider bz;

    @FXML
    private Slider candleA;

    @FXML
    private Slider candleB;

    @FXML
    private Slider ftC;

    @FXML
    private Slider mxA;

    @FXML
    private Slider mxB;

    @FXML
    private Slider pk;

    @FXML
    private Slider r1A;

    @FXML
    private Slider r1B;

    @FXML
    private Slider r2A;

    @FXML
    private Slider r2B;

    @FXML
    private Slider r3A;

    @FXML
    private Slider r3B;

    @FXML
    private Slider r4A;

    @FXML
    private Slider r4B;

    @FXML
    private Slider r5A;

    @FXML
    private Slider r5B;

    @FXML
    private Slider sp;

    @FXML
    private Slider swA;

    @FXML
    private Slider swB;
    private Slider[] allSliders;


    @FXML
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
        configureModules();
        allSliders = new Slider[]{r1A, r1B, r2A, r2B, r3A, r3B, r4A, r4B, r5A, 
           r5B, mxA, mxB, candleA, candleB, swA, swB, ftC, bkC, pk, bz, sp};
        instance = this;
    }
    private void configureModules(){
        fountain = Main.getFountain();
        A = fountain.getA();
        B = fountain.getB();
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
    
    public synchronized void setSlidersWithFcw(ArrayList<FCW> fcws) {
        for(FCW f: fcws) {
            if(!f.getIsWater()) {
                return;
            }
        }
        resetAllSliders();
        Iterator<FCW> it = fcws.iterator();
        while(it.hasNext()) {
            FCW f = it.next();
            String[] actions = FCWLib.getInstance().reverseLookupData(f);
//            String module = findModule(actions);
            
            for(String action: actions) {
            switch(f.getAddr()) {
                case 1:
                    if(action.equalsIgnoreCase("modulea")){
                        int level = findLevel(actions);
                        r5A.setValue(level);
                    }
                    else if(action.equalsIgnoreCase("moduleb")) {
                        int level = findLevel(actions);
                        r5B.setValue(level);
                    }
                break;
                case 2:
                      if(action.equalsIgnoreCase("modulea")){
                        int level = findLevel(actions);
                        r4A.setValue(level);
                    }
                    else if(action.equalsIgnoreCase("moduleb")) {
                        int level = findLevel(actions);
                        r4B.setValue(level);
                    }
                break;
                case 3:
                    if(action.equalsIgnoreCase("modulea")){
                        int level = findLevel(actions);
                        r3A.setValue(level);
                    }
                    else if(action.equalsIgnoreCase("moduleb")) {
                        int level = findLevel(actions);
                        r3B.setValue(level);
                    }
                break;
                case 4:
                    if(action.equalsIgnoreCase("modulea")){
                        int level = findLevel(actions);
                        r2A.setValue(level);
                    }
                    else if(action.equalsIgnoreCase("moduleb")) {
                        int level = findLevel(actions);
                        r2B.setValue(level);
                    }
                break;
                case 5:
                    if(action.equalsIgnoreCase("modulea")){
                        int level = findLevel(actions);
                        r1A.setValue(level);
                    }
                    else if(action.equalsIgnoreCase("moduleb")) {
                        int level = findLevel(actions);
                        r1B.setValue(level);
                    }
                break;
                case 6:
                    if(action.equalsIgnoreCase("modulea")){
                        int level = findLevel(actions);
                        swA.setValue(level);
                    }
                    else if(action.equalsIgnoreCase("moduleb")) {
                        int level = findLevel(actions);
                        swB.setValue(level);
                    }
                break; 
                case 7:
                    if(action.equalsIgnoreCase("spout")) {
                        int level = findLevel(actions);
                        sp.setValue(level);
                    }
                    else if(action.equalsIgnoreCase("bazooka")) {
                        int level = findLevel(actions);
                        bz.setValue(level);
                    }
                break;
                case 8:
                    if(action.equalsIgnoreCase("modulea")){
                        int level = findLevel(actions);
                        candleA.setValue(level);
                    }
                    else if(action.equalsIgnoreCase("moduleb")) {
                        int level = findLevel(actions);
                        candleB.setValue(level);
                    }
                break;
                case 9:
                    if(action.equalsIgnoreCase("peacock")){
                        int level = findLevel(actions);
                        pk.setValue(level);
                    }
                    else if(action.equalsIgnoreCase("ftcurt")) {
                        int level = findLevel(actions);
                        ftC.setValue(level);
                    }
                    else if(action.equalsIgnoreCase("bkcurt")) {
                        int level = findLevel(actions);
                        bkC.setValue(level);
                    }
                    break;
                case 48:
                    if(action.equalsIgnoreCase("modulea")){
                        int level = findLevel(actions);
                        mxA.setValue(level);
                    }
                    else if(action.equalsIgnoreCase("moduleb")) {
                        int level = findLevel(actions);
                        mxB.setValue(level);
                    }
                    break;
                case 33:
                case 34:
                    if(action.equalsIgnoreCase("offreset")) {
                        
                    }
                    else if(action.equalsIgnoreCase("short")) {
                        
                    }
                    else if(action.equalsIgnoreCase("long")) {
                        
                    }
                    else if(action.equalsIgnoreCase("largo")) {
                        
                    }
                    else if(action.equalsIgnoreCase("adagio")) {
                        
                    }
                    else if(action.equalsIgnoreCase("andante")) {
                        
                    }
                    else if(action.equalsIgnoreCase("moderato")) {
                        
                    }
                    else if(action.equalsIgnoreCase("allegreto")) {
                        
                    }
                    else if(action.equalsIgnoreCase("allegro")) {
                        
                    }
                    else if(action.equalsIgnoreCase("presto")) {
                        
                    }
                    else if(action.equalsIgnoreCase("playpause")) {
                        
                    }
                    break;
            }
            }
        }
    }
//    
//    public String findModule(String[] input) {
//        for(String action: input) {
//            if(action.equalsIgnoreCase("modulea")){
//                return "A";
//            }
//            else if(action.equalsIgnoreCase("moduleb")){
//                return "B";
//            }
//            else if(action.equalsIgnoreCase("ftcurt")) {
//                return "A";
//            }
//        }
//        return null;
//    }
    
    public int findLevel(String[] input) {
        for(String action: input) {
            switch(action){
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
        return 0;
    }

    private void resetAllSliders() {
       for(Slider s: allSliders) {
           s.setValue(0);
       }
    }
}

