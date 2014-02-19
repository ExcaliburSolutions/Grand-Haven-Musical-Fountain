package choreography.sliders;


import choreography.Main;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import lib.CannonEnum;
import lib.Fountain;
import lib.ModuleGroup;
import lib.cannons.Bazooka;
import lib.cannons.BkCurt;
import lib.cannons.Candelabra;
import lib.cannons.Cannon;
import lib.cannons.FtCurt;
import lib.cannons.Multi;
import lib.cannons.Peacock;
import lib.cannons.Ring;
import lib.cannons.Spout;
import lib.cannons.Sweep;
//import lib.events.ResourceAvailable;

public class OldSlidersController {
    @FXML private Slider r1A, r1B;
    @FXML private Slider r2A, r2B;
    @FXML private Slider r3A, r3B;
    @FXML private Slider r4A, r4B;
    @FXML private Slider r5A, r5B;
    @FXML private Slider mxA, mxB;
    @FXML private Slider candleA, candleB;
    @FXML private Slider swA, swB;
    @FXML private Slider ftC, bkC;
    @FXML private Slider pk, bz, sp;
    
    //Represents all cannons available on the fountain
    private Fountain fountain;
    private ArrayList<Ring> rings1A, rings2A, rings3A, rings4A, rings5A, rings1B, rings2B, rings3B, rings4B, rings5B;
    private ArrayList<Multi> multisA, multisB;
    private ArrayList<Candelabra> candlesA, candlesB;
    private ArrayList<Sweep> sweepsA, sweepsB;
    private FtCurt ftCurt;
    private BkCurt bkCurt;
    private Peacock peacock;
    private Bazooka bazooka;
    private Spout spout;
    
    //Represents the two groups of modules
    private ModuleGroup A;
    private ModuleGroup B;


    public OldSlidersController(){
        fountain = Main.getFountain();
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
        CannonSliderChangeListener cs = new CannonSliderChangeListener(list);
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

    /**
    *
    * @param res
    */
    //    @EventSubscriber(eventClass=ResourceAvailable.class)
//    public void fountainAvailableEventHFountainAvailableandler(ResourceAvailable<Fountain> res) {
//        this.setFountain(res.getResource());
//        configureModules();
//    }

    /**
     * @return the r1A
     */
    public Slider getR1A() {
            return r1A;
    }

    /**
     * @param r1a the r1A to set
     */
    public void setR1A(Slider r1a) {
            r1A = r1a;
    }

    /**
     * @return the r1B
     */
    public Slider getR1B() {
            return r1B;
    }

    /**
     * @param r1b the r1B to set
     */
    public void setR1B(Slider r1b) {
            r1B = r1b;
    }

    /**
     * @return the r2A
     */
    public Slider getR2A() {
            return r2A;
    }

    /**
     * @param r2a the r2A to set
     */
    public void setR2A(Slider r2a) {
            r2A = r2a;
    }

    /**
     * @return the r2B
     */
    public Slider getR2B() {
            return r2B;
    }

    /**
     * @param r2b the r2B to set
     */
    public void setR2B(Slider r2b) {
            r2B = r2b;
    }

    /**
     * @return the r3A
     */
    public Slider getR3A() {
            return r3A;
    }

    /**
     * @param r3a the r3A to set
     */
    public void setR3A(Slider r3a) {
            r3A = r3a;
    }

    /**
     * @return the r3B
     */
    public Slider getR3B() {
            return r3B;
    }

    /**
     * @param r3b the r3B to set
     */
    public void setR3B(Slider r3b) {
            r3B = r3b;
    }

    /**
     * @return the r4A
     */
    public Slider getR4A() {
            return r4A;
    }

    /**
     * @param r4a the r4A to set
     */
    public void setR4A(Slider r4a) {
            r4A = r4a;
    }

    /**
     * @return the r4B
     */
    public Slider getR4B() {
            return r4B;
    }

    /**
     * @param r4b the r4B to set
     */
    public void setR4B(Slider r4b) {
            r4B = r4b;
    }

    /**
     * @return the r5A
     */
    public Slider getR5A() {
            return r5A;
    }

    /**
     * @param r5a the r5A to set
     */
    public void setR5A(Slider r5a) {
            r5A = r5a;
    }

    /**
     * @return the r5B
     */
    public Slider getR5B() {
            return r5B;
    }

    /**
     * @param r5b the r5B to set
     */
    public void setR5B(Slider r5b) {
            r5B = r5b;
    }

    /**
     * @return the mxA
     */
    public Slider getMxA() {
            return mxA;
    }

    /**
     * @param mxA the mxA to set
     */
    public void setMxA(Slider mxA) {
            this.mxA = mxA;
    }

    /**
     * @return the mxB
     */
    public Slider getMxB() {
            return mxB;
    }

    /**
     * @param mxB the mxB to set
     */
    public void setMxB(Slider mxB) {
            this.mxB = mxB;
    }

    /**
     * @return the candleA
     */
    public Slider getCandleA() {
            return candleA;
    }

    /**
     * @param candleA the candleA to set
     */
    public void setCandleA(Slider candleA) {
            this.candleA = candleA;
    }

    /**
     * @return the candleB
     */
    public Slider getCandleB() {
            return candleB;
    }

    /**
     * @param candleB the candleB to set
     */
    public void setCandleB(Slider candleB) {
            this.candleB = candleB;
    }

    /**
     * @return the swA
     */
    public Slider getSwA() {
            return swA;
    }

    /**
     * @param swA the swA to set
     */
    public void setSwA(Slider swA) {
            this.swA = swA;
    }

    /**
     * @return the swB
     */
    public Slider getSwB() {
            return swB;
    }

    /**
     * @param swB the swB to set
     */
    public void setSwB(Slider swB) {
            this.swB = swB;
    }

    /**
     * @return the ftC
     */
    public Slider getFtC() {
            return ftC;
    }

    /**
     * @param ftC the ftC to set
     */
    public void setFtC(Slider ftC) {
            this.ftC = ftC;
    }

    /**
     * @return the bkC
     */
    public Slider getBkC() {
            return bkC;
    }

    /**
     * @param bkC the bkC to set
     */
    public void setBkC(Slider bkC) {
            this.bkC = bkC;
    }

    /**
     * @return the pk
     */
    public Slider getPk() {
            return pk;
    }

    /**
     * @param pk the pk to set
     */
    public void setPk(Slider pk) {
            this.pk = pk;
    }

    /**
     * @return the bz
     */
    public Slider getBz() {
            return bz;
    }

    /**
     * @param bz the bz to set
     */
    public void setBz(Slider bz) {
            this.bz = bz;
    }

    /**
     * @return the sp
     */
    public Slider getSp() {
            return sp;
    }

    /**
     * @param sp the sp to set
     */
    public void setSp(Slider sp) {
            this.sp = sp;
    }

    /**
     * @return the ftCurt
     */
    public FtCurt getFtCurt() {
            return ftCurt;
    }

    /**
     * @param ftCurt the ftCurt to set
     */
    public void setFtCurt(FtCurt ftCurt) {
            this.ftCurt = ftCurt;
    }

    /**
     * @return the bkCurt
     */
    public BkCurt getBkCurt() {
            return bkCurt;
    }

    /**
     * @param bkCurt the bkCurt to set
     */
    public void setBkCurt(BkCurt bkCurt) {
            this.bkCurt = bkCurt;
    }

    /**
     * @return the peacock
     */
    public Peacock getPeacock() {
            return peacock;
    }

    /**
     * @param peacock the peacock to set
     */
    public void setPeacock(Peacock peacock) {
            this.peacock = peacock;
    }

    /**
     * @return the bazooka
     */
    public Bazooka getBazooka() {
            return bazooka;
    }

    /**
     * @param bazooka the bazooka to set
     */
    public void setBazooka(Bazooka bazooka) {
            this.bazooka = bazooka;
    }

    /**
     * @return the spout
     */
    public Spout getSpout() {
            return spout;
    }

    /**
     * @param spout the spout to set
     */
    public void setSpout(Spout spout) {
            this.spout = spout;
    }

    /**
     * @return the a
     */
    public ModuleGroup getA() {
            return A;
    }

    /**
     * @param a the a to set
     */
    public void setA(ModuleGroup a) {
            A = a;
    }

    /**
     * @return the b
     */
    public ModuleGroup getB() {
            return B;
    }

    /**
     * @param b the b to set
     */
    public void setB(ModuleGroup b) {
            B = b;
    }

    }
