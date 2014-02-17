package choreography;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lib.Fountain;
import lib.ResourceAvailable;

import org.bushe.swing.event.EventBus;



public class Main extends Application {
	
	private static Fountain fountain;
	private VBox root;
	private Stage primaryStage;
	
	
	public static void main(String[] args) {
		launch(args);
		
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				
				fountain = new Fountain();
				EventBus.publish(new ResourceAvailable(fountain));
				
			}
			
		});
		
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			this.primaryStage = primaryStage;
			primaryStage.setTitle("Grand Haven Musical Fountain Choreographer");
			FXMLLoader fxml = new FXMLLoader(getClass().getResource("Choreography.fxml"));
			root = (VBox)fxml.load();
//			HBox sliders = (HBox)FXMLLoader.load(getClass().getResource("sliders/Sliders.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("sliders/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Fountain getFountain() {
		return fountain;
	}
}
