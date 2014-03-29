package choreography.view.sim;


import java.net.URL;
import java.util.ResourceBundle;

import choreography.view.timeline.TimelineController;
import customChannel.CustomChannel;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.Slider;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.QuadCurve;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;


public class FountainSimController implements Initializable {
	
	private static FountainSimController instance;


	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Pane fountainPane;

	@FXML
    private Group mod5;

    

	@FXML
    private Rectangle mod3ring4;

    @FXML
    private Group mod4;

    @FXML
    private Group mod3;

    @FXML
    private Rectangle mod3ring2;

    @FXML
    private Rectangle mod6ring4;

    @FXML
    private Group mod2;

    @FXML
    private Rectangle mod6ring5;

    @FXML
    private Slider ring4Slider;

    @FXML
    private Group mod7;

    @FXML
    private Group mod6;

    @FXML
    private MenuButton ring5Level;

    @FXML
    private Slider ring1Slider;

    @FXML
    private Group mod1;

    @FXML
    private Rectangle mod6ring2;

    @FXML
    private Rectangle mod6ring3;

    @FXML
    private Slider ring3Slider;

    @FXML
    private Rectangle mod6ring1;

    @FXML
    private Rectangle mod3ring3;

    @FXML
    private Slider ring5Slider;

    @FXML
    private Rectangle mod3ring1;

    @FXML
    private Rectangle mod3ring5;

    @FXML
    private Rectangle mod4ring1;

    @FXML
    private Rectangle mod7ring4;

    @FXML
    private Rectangle mod7ring3;

    @FXML
    private Rectangle mod2ring5;

    @FXML
    private Rectangle mod7ring2;

    @FXML
    private Rectangle mod2ring4;

    @FXML
    private Rectangle mod7ring1;

    @FXML
    private Rectangle mod2ring3;

    @FXML
    private Rectangle mod5ring4;

    @FXML
    private Rectangle mod2ring2;

    @FXML
    private Rectangle mod5ring3;

    @FXML
    private Rectangle mod2ring1;

    @FXML
    private Rectangle mod5ring5;

    @FXML
    private Rectangle mod5ring2;

    @FXML
    private Rectangle mod5ring1;

    @FXML
    private Rectangle mod7ring5;

    @FXML
    private Slider ring2Slider;

    @FXML
    private Rectangle mod1ring1;

    @FXML
    private Rectangle mod4ring2;

    @FXML
    private Rectangle mod1ring2;

    @FXML
    private Rectangle mod4ring3;

    @FXML
    private Rectangle mod4ring4;

    @FXML
    private Rectangle mod4ring5;

    @FXML
    private Rectangle mod1ring5;

    @FXML
    private Rectangle mod1ring3;

    @FXML
    private Rectangle mod1ring4;
    
    @FXML
    private QuadCurve bazookaB;
    
    @FXML
    private Line bazooka1;
    
    @FXML
    private Line bazooka2;
    
    @FXML
    private Line bazooka3;
    
    @FXML
    private Line bazooka4;
    
    @FXML
    private Line bazooka5;
    
    @FXML
    private Line peacock1;
    
    @FXML
    private Line peacock2;
    
    @FXML
    private Line peacock3;
    
    @FXML
    private Line peacock4;
    
    @FXML
    private Line peacock5;
    
    @FXML
    private Line peacock6;
    
    @FXML
    private Line peacock7;
    
    @FXML
    private Line peacock8;
    
    @FXML
    private Line peacock9;
    
    @FXML
    private Rectangle frontCurtain;
    
    @FXML
    private Rectangle backCurtain;
    
    @FXML
    private Line mod1sweep1;
    
    @FXML
    private Line mod1sweep2;
    
    @FXML
    private Line mod1candle1;
    
    @FXML
    private Line mod1candle2;
    
    @FXML
    private Line mod1candle3;
    
    @FXML
    private Line mod1candle4;
    
    @FXML
    private Line mod1candle5;
    
    @FXML
    private Line mod1candle6;    
    
    
    @FXML
    private Line mod2sweep1;
    
    @FXML
    private Line mod2sweep2;
    
    @FXML
    private Line mod2candle1;
    
    @FXML
    private Line mod2candle2;
    
    @FXML
    private Line mod2candle3;
    
    @FXML
    private Line mod2candle4;
    
    @FXML
    private Line mod2candle5;
    
    @FXML
    private Line mod2candle6;
    
    @FXML
    private Line mod3sweep1;
    
    @FXML
    private Line mod3sweep2;
    
    @FXML
    private Line mod3candle1;
    
    @FXML
    private Line mod3candle2;
    
    @FXML
    private Line mod3candle3;
    
    @FXML
    private Line mod3candle4;
    
    @FXML
    private Line mod3candle5;
    
    @FXML
    private Line mod3candle6;
    
    @FXML
    private Line mod4sweep1;
    
    @FXML
    private Line mod4sweep2;
    
    @FXML
    private Line mod4candle1;
    
    @FXML
    private Line mod4candle2;
    
    @FXML
    private Line mod4candle3;
    
    @FXML
    private Line mod4candle4;
    
    @FXML
    private Line mod4candle5;
    
    @FXML
    private Line mod4candle6;
	
    @FXML
    private Line mod5sweep1;
    
    @FXML
    private Line mod5sweep2;
    
    @FXML
    private Line mod5candle1;
    
    @FXML
    private Line mod5candle2;
    
    @FXML
    private Line mod5candle3;
    
    @FXML
    private Line mod5candle4;
    
    @FXML
    private Line mod5candle5;
    
    @FXML
    private Line mod5candle6;
    
    @FXML
    private Line mod6sweep1;
    
    @FXML
    private Line mod6sweep2;
    
    @FXML
    private Line mod6candle1;
    
    @FXML
    private Line mod6candle2;
    
    @FXML
    private Line mod6candle3;
    
    @FXML
    private Line mod6candle4;
    
    @FXML
    private Line mod6candle5;
    
    @FXML
    private Line mod6candle6;
    
    @FXML
    private Line mod7sweep1;
    
    @FXML
    private Line mod7sweep2;
    
    @FXML
    private Line mod7candle1;
    
    @FXML
    private Line mod7candle2;
    
    @FXML
    private Line mod7candle3;
    
    @FXML
    private Line mod7candle4;
    
    @FXML
    private Line mod7candle5;
    
    @FXML
    private Line mod7candle6;
    
    @FXML
    private Rectangle spoutRec;
    
	private static Stage principalStage;
	Timeline timelingRing1;

	
//	@Override
//	public void start(Stage primaryStage) throws Exception {
//		
//		
//		Parent root = FXMLLoader.load(getClass().getResource("fountainSim.fxml"));		
//        
//        Scene scene = new Scene(root);
//        principalStage = primaryStage;
//        principalStage.setScene(scene);
//        principalStage.setTitle("Sim");
//        principalStage.show();
//
//	}
	
	public static void main(String[] args) {
        Application.launch(args);
    }
	
	public static FountainSimController getInstance() {
        if (instance == null)
            instance = new FountainSimController();
        return instance;
    }
	
	public void updateColors(int colNum){
		choreography.view.timeline.Timeline.getInstance().getGtfoMap();
		//lookup colors for for all existing channels at given time...
		
		//update the sim objects to those colors
	}


	@Override
    public void initialize(URL url, ResourceBundle rb) {
		assert fountainPane != null : "fx:id=\"fountainPane\" was not injected: check your FXML file 'fountainSim.fxml'.";
		//assert ring1Rec != null : "fx:id=\"ring1Rec\" was not injected: check your FXML file 'fountainSim.fxml'.";
		assert ring1Slider != null : "fx:id=\"ring1Slider\" was not injected: check your FXML file 'fountainSim.fxml'.";
		//assert ring2Rec != null : "fx:id=\"ring2Rec\" was not injected: check your FXML file 'fountainSim.fxml'.";
		assert ring2Slider != null : "fx:id=\"ring2Slider\" was not injected: check your FXML file 'fountainSim.fxml'.";
		//assert ring3Rec != null : "fx:id=\"ring3Rec\" was not injected: check your FXML file 'fountainSim.fxml'.";
		assert ring3Slider != null : "fx:id=\"ring3Slider\" was not injected: check your FXML file 'fountainSim.fxml'.";
		//assert ring4Rec != null : "fx:id=\"ring4Rec\" was not injected: check your FXML file 'fountainSim.fxml'.";
		assert ring4Slider != null : "fx:id=\"ring4Slider\" was not injected: check your FXML file 'fountainSim.fxml'.";
		assert ring5Level != null : "fx:id=\"ring5Level\" was not injected: check your FXML file 'fountainSim.fxml'.";
		//assert ring5Rec != null : "fx:id=\"ring5Rec\" was not injected: check your FXML file 'fountainSim.fxml'.";
		assert ring5Slider != null : "fx:id=\"ring5Slider\" was not injected: check your FXML file 'fountainSim.fxml'.";

        instance = this;
		
		// Listen for Slider value changes
		ring5Slider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable,
					Number oldValue, Number newValue) {
				final Timeline timeline = new Timeline();
				timeline.setCycleCount(1);
				//timeline.setAutoReverse(true);
				final KeyValue kv7 = new KeyValue(getMod7ring5().heightProperty(), ((35*newValue.doubleValue())));
				final KeyValue kv6 = new KeyValue(mod6ring5.heightProperty(), ((35*newValue.doubleValue())));
				final KeyValue kv5 = new KeyValue(mod5ring5.heightProperty(), ((35*newValue.doubleValue())));
				final KeyValue kv4 = new KeyValue(mod4ring5.heightProperty(), ((35*newValue.doubleValue())));
				final KeyValue kv3 = new KeyValue(mod3ring5.heightProperty(), ((35*newValue.doubleValue())));
				final KeyValue kv2 = new KeyValue(mod2ring5.heightProperty(), ((35*newValue.doubleValue())));
				final KeyValue kv1 = new KeyValue(mod1ring5.heightProperty(), ((35*newValue.doubleValue())));



				final KeyFrame kf = new KeyFrame(Duration.millis(1000), kv1, kv2, kv3, kv4, kv4, kv5, kv6, kv7);
				timeline.getKeyFrames().add(kf);
				timeline.play();
				
				System.out.println(newValue);
				//outputTextArea.appendText("Slider Value Changed (newValue: " + newValue.intValue() + ")\n");

			}
		});
		
		
		ring4Slider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable,
					Number oldValue, Number newValue) {
				final Timeline timeline = new Timeline();
				timeline.setCycleCount(1);
				//timeline.setAutoReverse(true);
				final KeyValue kv7 = new KeyValue(mod7ring4.heightProperty(), ((35*newValue.doubleValue())));
				final KeyValue kv6 = new KeyValue(mod6ring4.heightProperty(), ((35*newValue.doubleValue())));
				final KeyValue kv5 = new KeyValue(mod5ring4.heightProperty(), ((35*newValue.doubleValue())));
				final KeyValue kv4 = new KeyValue(mod4ring4.heightProperty(), ((35*newValue.doubleValue())));
				final KeyValue kv3 = new KeyValue(mod3ring4.heightProperty(), ((35*newValue.doubleValue())));
				final KeyValue kv2 = new KeyValue(mod2ring4.heightProperty(), ((35*newValue.doubleValue())));
				final KeyValue kv1 = new KeyValue(mod1ring4.heightProperty(), ((35*newValue.doubleValue())));



				final KeyFrame kf = new KeyFrame(Duration.millis(1000), kv1, kv2, kv3, kv4, kv4, kv5, kv6, kv7);
				timeline.getKeyFrames().add(kf);
				timeline.play();
				
				System.out.println(newValue);
				//outputTextArea.appendText("Slider Value Changed (newValue: " + newValue.intValue() + ")\n");

			}
		});
		
		ring3Slider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable,
					Number oldValue, Number newValue) {
				final Timeline timeline = new Timeline();
				timeline.setCycleCount(1);
				//timeline.setAutoReverse(true);
				final KeyValue kv7 = new KeyValue(mod7ring3.heightProperty(), ((35*newValue.doubleValue())));
				final KeyValue kv6 = new KeyValue(mod6ring3.heightProperty(), ((35*newValue.doubleValue())));
				final KeyValue kv5 = new KeyValue(mod5ring3.heightProperty(), ((35*newValue.doubleValue())));
				final KeyValue kv4 = new KeyValue(mod4ring3.heightProperty(), ((35*newValue.doubleValue())));
				final KeyValue kv3 = new KeyValue(mod3ring3.heightProperty(), ((35*newValue.doubleValue())));
				final KeyValue kv2 = new KeyValue(mod2ring3.heightProperty(), ((35*newValue.doubleValue())));
				final KeyValue kv1 = new KeyValue(mod1ring3.heightProperty(), ((35*newValue.doubleValue())));



				final KeyFrame kf = new KeyFrame(Duration.millis(1000), kv1, kv2, kv3, kv4, kv4, kv5, kv6, kv7);
				timeline.getKeyFrames().add(kf);
				timeline.play();
				
				System.out.println(newValue);
				//outputTextArea.appendText("Slider Value Changed (newValue: " + newValue.intValue() + ")\n");

			}
		});
		
		ring2Slider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable,
					Number oldValue, Number newValue) {
				final Timeline timeline = new Timeline();
				timeline.setCycleCount(1);
				//timeline.setAutoReverse(true);
				final KeyValue kv7 = new KeyValue(mod7ring2.heightProperty(), ((35*newValue.doubleValue())));
				final KeyValue kv6 = new KeyValue(mod6ring2.heightProperty(), ((35*newValue.doubleValue())));
				final KeyValue kv5 = new KeyValue(mod5ring2.heightProperty(), ((35*newValue.doubleValue())));
				final KeyValue kv4 = new KeyValue(mod4ring2.heightProperty(), ((35*newValue.doubleValue())));
				final KeyValue kv3 = new KeyValue(mod3ring2.heightProperty(), ((35*newValue.doubleValue())));
				final KeyValue kv2 = new KeyValue(mod2ring2.heightProperty(), ((35*newValue.doubleValue())));
				final KeyValue kv1 = new KeyValue(mod1ring2.heightProperty(), ((35*newValue.doubleValue())));



				final KeyFrame kf = new KeyFrame(Duration.millis(1000), kv1, kv2, kv3, kv4, kv4, kv5, kv6, kv7);
				timeline.getKeyFrames().add(kf);
				timeline.play();
				
				System.out.println(newValue);
				//outputTextArea.appendText("Slider Value Changed (newValue: " + newValue.intValue() + ")\n");

			}
		});
		
		ring1Slider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable,
					Number oldValue, Number newValue) {
				final Timeline timeline = new Timeline();
				timeline.setCycleCount(1);
				//timeline.setAutoReverse(true);
				final KeyValue kv7 = new KeyValue(mod7ring1.heightProperty(), ((35*newValue.doubleValue())));
				final KeyValue kv6 = new KeyValue(mod6ring1.heightProperty(), ((35*newValue.doubleValue())));
				final KeyValue kv5 = new KeyValue(mod5ring1.heightProperty(), ((35*newValue.doubleValue())));
				final KeyValue kv4 = new KeyValue(mod4ring1.heightProperty(), ((35*newValue.doubleValue())));
				final KeyValue kv3 = new KeyValue(mod3ring1.heightProperty(), ((35*newValue.doubleValue())));
				final KeyValue kv2 = new KeyValue(mod2ring1.heightProperty(), ((35*newValue.doubleValue())));
				final KeyValue kv1 = new KeyValue(mod1ring1.heightProperty(), ((35*newValue.doubleValue())));



				final KeyFrame kf = new KeyFrame(Duration.millis(1000), kv1, kv2, kv3, kv4, kv4, kv5, kv6, kv7);
				timeline.getKeyFrames().add(kf);
				timeline.play();
				
				System.out.println(newValue);
				//outputTextArea.appendText("Slider Value Changed (newValue: " + newValue.intValue() + ")\n");

			}
		});
		
		
	}

	public Rectangle getMod7ring5() {
		return mod7ring5;
	}

	public void setMod7ring5(Rectangle mod7ring5) {
		this.mod7ring5 = mod7ring5;
	}
	
	public Group getMod5() {
		return mod5;
	}

	public void setMod5(Group mod5) {
		this.mod5 = mod5;
	}

	public Rectangle getMod3ring4() {
		return mod3ring4;
	}

	public void setMod3ring4(Rectangle mod3ring4) {
		this.mod3ring4 = mod3ring4;
	}

	public Group getMod4() {
		return mod4;
	}

	public void setMod4(Group mod4) {
		this.mod4 = mod4;
	}

	public Group getMod3() {
		return mod3;
	}

	public void setMod3(Group mod3) {
		this.mod3 = mod3;
	}

	public Rectangle getMod3ring2() {
		return mod3ring2;
	}

	public void setMod3ring2(Rectangle mod3ring2) {
		this.mod3ring2 = mod3ring2;
	}

	public Rectangle getMod6ring4() {
		return mod6ring4;
	}

	public void setMod6ring4(Rectangle mod6ring4) {
		this.mod6ring4 = mod6ring4;
	}

	public Group getMod2() {
		return mod2;
	}

	public void setMod2(Group mod2) {
		this.mod2 = mod2;
	}

	public Rectangle getMod6ring5() {
		return mod6ring5;
	}

	public void setMod6ring5(Rectangle mod6ring5) {
		this.mod6ring5 = mod6ring5;
	}

	public Group getMod7() {
		return mod7;
	}

	public void setMod7(Group mod7) {
		this.mod7 = mod7;
	}

	public Group getMod6() {
		return mod6;
	}

	public void setMod6(Group mod6) {
		this.mod6 = mod6;
	}

	public Group getMod1() {
		return mod1;
	}

	public void setMod1(Group mod1) {
		this.mod1 = mod1;
	}

	public Rectangle getMod6ring2() {
		return mod6ring2;
	}

	public void setMod6ring2(Rectangle mod6ring2) {
		this.mod6ring2 = mod6ring2;
	}

	public Rectangle getMod6ring3() {
		return mod6ring3;
	}

	public void setMod6ring3(Rectangle mod6ring3) {
		this.mod6ring3 = mod6ring3;
	}

	public Rectangle getMod6ring1() {
		return mod6ring1;
	}

	public void setMod6ring1(Rectangle mod6ring1) {
		this.mod6ring1 = mod6ring1;
	}

	public Rectangle getMod3ring3() {
		return mod3ring3;
	}

	public void setMod3ring3(Rectangle mod3ring3) {
		this.mod3ring3 = mod3ring3;
	}

	public Rectangle getMod3ring1() {
		return mod3ring1;
	}

	public void setMod3ring1(Rectangle mod3ring1) {
		this.mod3ring1 = mod3ring1;
	}

	public Rectangle getMod3ring5() {
		return mod3ring5;
	}

	public void setMod3ring5(Rectangle mod3ring5) {
		this.mod3ring5 = mod3ring5;
	}

	public Rectangle getMod4ring1() {
		return mod4ring1;
	}

	public void setMod4ring1(Rectangle mod4ring1) {
		this.mod4ring1 = mod4ring1;
	}

	public Rectangle getMod7ring4() {
		return mod7ring4;
	}

	public void setMod7ring4(Rectangle mod7ring4) {
		this.mod7ring4 = mod7ring4;
	}

	public Rectangle getMod7ring3() {
		return mod7ring3;
	}

	public void setMod7ring3(Rectangle mod7ring3) {
		this.mod7ring3 = mod7ring3;
	}

	public Rectangle getMod2ring5() {
		return mod2ring5;
	}

	public void setMod2ring5(Rectangle mod2ring5) {
		this.mod2ring5 = mod2ring5;
	}

	public Rectangle getMod7ring2() {
		return mod7ring2;
	}

	public void setMod7ring2(Rectangle mod7ring2) {
		this.mod7ring2 = mod7ring2;
	}

	public Rectangle getMod2ring4() {
		return mod2ring4;
	}

	public void setMod2ring4(Rectangle mod2ring4) {
		this.mod2ring4 = mod2ring4;
	}

	public Rectangle getMod7ring1() {
		return mod7ring1;
	}

	public void setMod7ring1(Rectangle mod7ring1) {
		this.mod7ring1 = mod7ring1;
	}

	public Rectangle getMod2ring3() {
		return mod2ring3;
	}

	public void setMod2ring3(Rectangle mod2ring3) {
		this.mod2ring3 = mod2ring3;
	}

	public Rectangle getMod5ring4() {
		return mod5ring4;
	}

	public void setMod5ring4(Rectangle mod5ring4) {
		this.mod5ring4 = mod5ring4;
	}

	public Rectangle getMod2ring2() {
		return mod2ring2;
	}

	public void setMod2ring2(Rectangle mod2ring2) {
		this.mod2ring2 = mod2ring2;
	}

	public Rectangle getMod5ring3() {
		return mod5ring3;
	}

	public void setMod5ring3(Rectangle mod5ring3) {
		this.mod5ring3 = mod5ring3;
	}

	public Rectangle getMod2ring1() {
		return mod2ring1;
	}

	public void setMod2ring1(Rectangle mod2ring1) {
		this.mod2ring1 = mod2ring1;
	}

	public Rectangle getMod5ring5() {
		return mod5ring5;
	}

	public void setMod5ring5(Rectangle mod5ring5) {
		this.mod5ring5 = mod5ring5;
	}

	public Rectangle getMod5ring2() {
		return mod5ring2;
	}

	public void setMod5ring2(Rectangle mod5ring2) {
		this.mod5ring2 = mod5ring2;
	}

	public Rectangle getMod5ring1() {
		return mod5ring1;
	}

	public void setMod5ring1(Rectangle mod5ring1) {
		this.mod5ring1 = mod5ring1;
	}

	public Rectangle getMod1ring1() {
		return mod1ring1;
	}

	public void setMod1ring1(Rectangle mod1ring1) {
		this.mod1ring1 = mod1ring1;
	}

	public Rectangle getMod4ring2() {
		return mod4ring2;
	}

	public void setMod4ring2(Rectangle mod4ring2) {
		this.mod4ring2 = mod4ring2;
	}

	public Rectangle getMod1ring2() {
		return mod1ring2;
	}

	public void setMod1ring2(Rectangle mod1ring2) {
		this.mod1ring2 = mod1ring2;
	}

	public Rectangle getMod4ring3() {
		return mod4ring3;
	}

	public void setMod4ring3(Rectangle mod4ring3) {
		this.mod4ring3 = mod4ring3;
	}

	public Rectangle getMod4ring4() {
		return mod4ring4;
	}

	public void setMod4ring4(Rectangle mod4ring4) {
		this.mod4ring4 = mod4ring4;
	}

	public Rectangle getMod4ring5() {
		return mod4ring5;
	}

	public void setMod4ring5(Rectangle mod4ring5) {
		this.mod4ring5 = mod4ring5;
	}

	public Rectangle getMod1ring5() {
		return mod1ring5;
	}

	public void setMod1ring5(Rectangle mod1ring5) {
		this.mod1ring5 = mod1ring5;
	}

	public Rectangle getMod1ring3() {
		return mod1ring3;
	}

	public void setMod1ring3(Rectangle mod1ring3) {
		this.mod1ring3 = mod1ring3;
	}

	public Rectangle getMod1ring4() {
		return mod1ring4;
	}

	public void setMod1ring4(Rectangle mod1ring4) {
		this.mod1ring4 = mod1ring4;
	}

	public QuadCurve getBazookaB() {
		return bazookaB;
	}

	public void setBazookaB(QuadCurve bazookaB) {
		this.bazookaB = bazookaB;
	}

	public Line getBazooka1() {
		return bazooka1;
	}

	public void setBazooka1(Line bazooka1) {
		this.bazooka1 = bazooka1;
	}

	public Line getBazooka2() {
		return bazooka2;
	}

	public void setBazooka2(Line bazooka2) {
		this.bazooka2 = bazooka2;
	}

	public Line getBazooka3() {
		return bazooka3;
	}

	public void setBazooka3(Line bazooka3) {
		this.bazooka3 = bazooka3;
	}

	public Line getBazooka4() {
		return bazooka4;
	}

	public void setBazooka4(Line bazooka4) {
		this.bazooka4 = bazooka4;
	}

	public Line getBazooka5() {
		return bazooka5;
	}

	public void setBazooka5(Line bazooka5) {
		this.bazooka5 = bazooka5;
	}

	public Line getPeacock1() {
		return peacock1;
	}

	public void setPeacock1(Line peacock1) {
		this.peacock1 = peacock1;
	}

	public Line getPeacock2() {
		return peacock2;
	}

	public void setPeacock2(Line peacock2) {
		this.peacock2 = peacock2;
	}

	public Line getPeacock3() {
		return peacock3;
	}

	public void setPeacock3(Line peacock3) {
		this.peacock3 = peacock3;
	}

	public Line getPeacock4() {
		return peacock4;
	}

	public void setPeacock4(Line peacock4) {
		this.peacock4 = peacock4;
	}

	public Line getPeacock5() {
		return peacock5;
	}

	public void setPeacock5(Line peacock5) {
		this.peacock5 = peacock5;
	}

	public Line getPeacock6() {
		return peacock6;
	}

	public void setPeacock6(Line peacock6) {
		this.peacock6 = peacock6;
	}

	public Line getPeacock7() {
		return peacock7;
	}

	public void setPeacock7(Line peacock7) {
		this.peacock7 = peacock7;
	}

	public Line getPeacock8() {
		return peacock8;
	}

	public void setPeacock8(Line peacock8) {
		this.peacock8 = peacock8;
	}

	public Line getPeacock9() {
		return peacock9;
	}

	public void setPeacock9(Line peacock9) {
		this.peacock9 = peacock9;
	}

	public Rectangle getFrontCurtain() {
		return frontCurtain;
	}

	public void setFrontCurtain(Rectangle frontCurtain) {
		this.frontCurtain = frontCurtain;
	}

	public Rectangle getBackCurtain() {
		return backCurtain;
	}

	public void setBackCurtain(Rectangle backCurtain) {
		this.backCurtain = backCurtain;
	}

	public Line getMod1sweep1() {
		return mod1sweep1;
	}

	public void setMod1sweep1(Line mod1sweep1) {
		this.mod1sweep1 = mod1sweep1;
	}

	public Line getMod1sweep2() {
		return mod1sweep2;
	}

	public void setMod1sweep2(Line mod1sweep2) {
		this.mod1sweep2 = mod1sweep2;
	}

	public Line getMod1candle1() {
		return mod1candle1;
	}

	public void setMod1candle1(Line mod1candle1) {
		this.mod1candle1 = mod1candle1;
	}

	public Line getMod1candle2() {
		return mod1candle2;
	}

	public void setMod1candle2(Line mod1candle2) {
		this.mod1candle2 = mod1candle2;
	}

	public Line getMod1candle3() {
		return mod1candle3;
	}

	public void setMod1candle3(Line mod1candle3) {
		this.mod1candle3 = mod1candle3;
	}

	public Line getMod1candle4() {
		return mod1candle4;
	}

	public void setMod1candle4(Line mod1candle4) {
		this.mod1candle4 = mod1candle4;
	}

	public Line getMod1candle5() {
		return mod1candle5;
	}

	public void setMod1candle5(Line mod1candle5) {
		this.mod1candle5 = mod1candle5;
	}

	public Line getMod1candle6() {
		return mod1candle6;
	}

	public void setMod1candle6(Line mod1candle6) {
		this.mod1candle6 = mod1candle6;
	}

	public Line getMod2sweep1() {
		return mod2sweep1;
	}

	public void setMod2sweep1(Line mod2sweep1) {
		this.mod2sweep1 = mod2sweep1;
	}

	public Line getMod2sweep2() {
		return mod2sweep2;
	}

	public void setMod2sweep2(Line mod2sweep2) {
		this.mod2sweep2 = mod2sweep2;
	}

	public Line getMod2candle1() {
		return mod2candle1;
	}

	public void setMod2candle1(Line mod2candle1) {
		this.mod2candle1 = mod2candle1;
	}

	public Line getMod2candle2() {
		return mod2candle2;
	}

	public void setMod2candle2(Line mod2candle2) {
		this.mod2candle2 = mod2candle2;
	}

	public Line getMod2candle3() {
		return mod2candle3;
	}

	public void setMod2candle3(Line mod2candle3) {
		this.mod2candle3 = mod2candle3;
	}

	public Line getMod2candle4() {
		return mod2candle4;
	}

	public void setMod2candle4(Line mod2candle4) {
		this.mod2candle4 = mod2candle4;
	}

	public Line getMod2candle5() {
		return mod2candle5;
	}

	public void setMod2candle5(Line mod2candle5) {
		this.mod2candle5 = mod2candle5;
	}

	public Line getMod2candle6() {
		return mod2candle6;
	}

	public void setMod2candle6(Line mod2candle6) {
		this.mod2candle6 = mod2candle6;
	}

	public Line getMod3sweep1() {
		return mod3sweep1;
	}

	public void setMod3sweep1(Line mod3sweep1) {
		this.mod3sweep1 = mod3sweep1;
	}

	public Line getMod3sweep2() {
		return mod3sweep2;
	}

	public void setMod3sweep2(Line mod3sweep2) {
		this.mod3sweep2 = mod3sweep2;
	}

	public Line getMod3candle1() {
		return mod3candle1;
	}

	public void setMod3candle1(Line mod3candle1) {
		this.mod3candle1 = mod3candle1;
	}

	public Line getMod3candle2() {
		return mod3candle2;
	}

	public void setMod3candle2(Line mod3candle2) {
		this.mod3candle2 = mod3candle2;
	}

	public Line getMod3candle3() {
		return mod3candle3;
	}

	public void setMod3candle3(Line mod3candle3) {
		this.mod3candle3 = mod3candle3;
	}

	public Line getMod3candle4() {
		return mod3candle4;
	}

	public void setMod3candle4(Line mod3candle4) {
		this.mod3candle4 = mod3candle4;
	}

	public Line getMod3candle5() {
		return mod3candle5;
	}

	public void setMod3candle5(Line mod3candle5) {
		this.mod3candle5 = mod3candle5;
	}

	public Line getMod3candle6() {
		return mod3candle6;
	}

	public void setMod3candle6(Line mod3candle6) {
		this.mod3candle6 = mod3candle6;
	}

	public Line getMod4sweep1() {
		return mod4sweep1;
	}

	public void setMod4sweep1(Line mod4sweep1) {
		this.mod4sweep1 = mod4sweep1;
	}

	public Line getMod4sweep2() {
		return mod4sweep2;
	}

	public void setMod4sweep2(Line mod4sweep2) {
		this.mod4sweep2 = mod4sweep2;
	}

	public Line getMod4candle1() {
		return mod4candle1;
	}

	public void setMod4candle1(Line mod4candle1) {
		this.mod4candle1 = mod4candle1;
	}

	public Line getMod4candle2() {
		return mod4candle2;
	}

	public void setMod4candle2(Line mod4candle2) {
		this.mod4candle2 = mod4candle2;
	}

	public Line getMod4candle3() {
		return mod4candle3;
	}

	public void setMod4candle3(Line mod4candle3) {
		this.mod4candle3 = mod4candle3;
	}

	public Line getMod4candle4() {
		return mod4candle4;
	}

	public void setMod4candle4(Line mod4candle4) {
		this.mod4candle4 = mod4candle4;
	}

	public Line getMod4candle5() {
		return mod4candle5;
	}

	public void setMod4candle5(Line mod4candle5) {
		this.mod4candle5 = mod4candle5;
	}

	public Line getMod4candle6() {
		return mod4candle6;
	}

	public void setMod4candle6(Line mod4candle6) {
		this.mod4candle6 = mod4candle6;
	}

	public Line getMod5sweep1() {
		return mod5sweep1;
	}

	public void setMod5sweep1(Line mod5sweep1) {
		this.mod5sweep1 = mod5sweep1;
	}

	public Line getMod5sweep2() {
		return mod5sweep2;
	}

	public void setMod5sweep2(Line mod5sweep2) {
		this.mod5sweep2 = mod5sweep2;
	}

	public Line getMod5candle1() {
		return mod5candle1;
	}

	public void setMod5candle1(Line mod5candle1) {
		this.mod5candle1 = mod5candle1;
	}

	public Line getMod5candle2() {
		return mod5candle2;
	}

	public void setMod5candle2(Line mod5candle2) {
		this.mod5candle2 = mod5candle2;
	}

	public Line getMod5candle3() {
		return mod5candle3;
	}

	public void setMod5candle3(Line mod5candle3) {
		this.mod5candle3 = mod5candle3;
	}

	public Line getMod5candle4() {
		return mod5candle4;
	}

	public void setMod5candle4(Line mod5candle4) {
		this.mod5candle4 = mod5candle4;
	}

	public Line getMod5candle5() {
		return mod5candle5;
	}

	public void setMod5candle5(Line mod5candle5) {
		this.mod5candle5 = mod5candle5;
	}

	public Line getMod5candle6() {
		return mod5candle6;
	}

	public void setMod5candle6(Line mod5candle6) {
		this.mod5candle6 = mod5candle6;
	}

	public Line getMod6sweep1() {
		return mod6sweep1;
	}

	public void setMod6sweep1(Line mod6sweep1) {
		this.mod6sweep1 = mod6sweep1;
	}

	public Line getMod6sweep2() {
		return mod6sweep2;
	}

	public void setMod6sweep2(Line mod6sweep2) {
		this.mod6sweep2 = mod6sweep2;
	}

	public Line getMod6candle1() {
		return mod6candle1;
	}

	public void setMod6candle1(Line mod6candle1) {
		this.mod6candle1 = mod6candle1;
	}

	public Line getMod6candle2() {
		return mod6candle2;
	}

	public void setMod6candle2(Line mod6candle2) {
		this.mod6candle2 = mod6candle2;
	}

	public Line getMod6candle3() {
		return mod6candle3;
	}

	public void setMod6candle3(Line mod6candle3) {
		this.mod6candle3 = mod6candle3;
	}

	public Line getMod6candle4() {
		return mod6candle4;
	}

	public void setMod6candle4(Line mod6candle4) {
		this.mod6candle4 = mod6candle4;
	}

	public Line getMod6candle5() {
		return mod6candle5;
	}

	public void setMod6candle5(Line mod6candle5) {
		this.mod6candle5 = mod6candle5;
	}

	public Line getMod6candle6() {
		return mod6candle6;
	}

	public void setMod6candle6(Line mod6candle6) {
		this.mod6candle6 = mod6candle6;
	}

	public Line getMod7sweep1() {
		return mod7sweep1;
	}

	public void setMod7sweep1(Line mod7sweep1) {
		this.mod7sweep1 = mod7sweep1;
	}

	public Line getMod7sweep2() {
		return mod7sweep2;
	}

	public void setMod7sweep2(Line mod7sweep2) {
		this.mod7sweep2 = mod7sweep2;
	}

	public Line getMod7candle1() {
		return mod7candle1;
	}

	public void setMod7candle1(Line mod7candle1) {
		this.mod7candle1 = mod7candle1;
	}

	public Line getMod7candle2() {
		return mod7candle2;
	}

	public void setMod7candle2(Line mod7candle2) {
		this.mod7candle2 = mod7candle2;
	}

	public Line getMod7candle3() {
		return mod7candle3;
	}

	public void setMod7candle3(Line mod7candle3) {
		this.mod7candle3 = mod7candle3;
	}

	public Line getMod7candle4() {
		return mod7candle4;
	}

	public void setMod7candle4(Line mod7candle4) {
		this.mod7candle4 = mod7candle4;
	}

	public Line getMod7candle5() {
		return mod7candle5;
	}

	public void setMod7candle5(Line mod7candle5) {
		this.mod7candle5 = mod7candle5;
	}

	public Line getMod7candle6() {
		return mod7candle6;
	}

	public void setMod7candle6(Line mod7candle6) {
		this.mod7candle6 = mod7candle6;
	}

	public Rectangle getSpoutRec() {
		return spoutRec;
	}

	public void setSpoutRec(Rectangle spoutRec) {
		this.spoutRec = spoutRec;
	}


//	public void initialize(URL location, ResourceBundle resources) {
//		// TODO Auto-generated method stub
//		
//	}

	
}
