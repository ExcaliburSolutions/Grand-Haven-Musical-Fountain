package choreography;
	
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lib.Fountain;
import org.bushe.swing.event.EventServiceExistsException;
import org.bushe.swing.event.EventServiceLocator;
import org.bushe.swing.event.ThreadSafeEventService;



public class Main extends Application {
	
	private static Fountain fountain;
	
	
	public static void main(String[] args) {
		launch(args);
	}

	public static Fountain getFountain() {
		return fountain;
	}
	private VBox root;
	private Stage primaryStage;
	

        @Override
	public void start(Stage primaryStage) {
            try {
                System.setProperty(EventServiceLocator.SERVICE_NAME_EVENT_BUS, 
                        ThreadSafeEventService.class.getName());
                EventServiceLocator.setEventService(
                        EventServiceLocator.SERVICE_NAME_SWING_EVENT_SERVICE, 
                        new ThreadSafeEventService());
                this.setPrimaryStage(primaryStage);
                primaryStage.setTitle("Grand Haven Musical Fountain Choreographer");
                fountain = Fountain.getInstance();
//                        EventBus.publish(new ResourceAvailable<Fountain>(fountain));
//			EventBus.publish(new ResourceAvailable(fountain));
                FXMLLoader fxml = new FXMLLoader(getClass().getResource("Choreography.fxml"));
                root = (VBox)fxml.load();
//			HBox sliders = (HBox)FXMLLoader.load(getClass().getResource("sliders/Sliders.fxml"));
                Scene scene = new Scene(root);
                scene.getStylesheets().add(getClass().getResource("sliders/application.css").toExternalForm());
                primaryStage.setScene(scene);
                
                primaryStage.show();
            } catch (IOException | EventServiceExistsException e) {
                System.out.println("DIE!");
            }
        }
	
	/**
     * @return the primaryStage
     */
    	public Stage getPrimaryStage() {
            return primaryStage;
        }

	/**
     * @param primaryStage the primaryStage to set
     */
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
}
