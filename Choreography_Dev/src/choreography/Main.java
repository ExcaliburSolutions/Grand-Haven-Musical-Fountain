package choreography;
	
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import choreography.model.*;



public class Main extends Application {
	
	private static Fountain fountain;
	private VBox root;
	private Stage primaryStage;
	
	public static void main(String[] args) {
		launch(args);
	}

	public static Fountain getFountain() {
		return fountain;
	}
	
        @Override
	public void start(Stage primaryStage) {
            try {
                this.setPrimaryStage(primaryStage);
                primaryStage.setTitle("Grand Haven Musical Fountain Choreographer");
                fountain = Fountain.getInstance();
//                        EventBus.publish(new ResourceAvailable<Fountain>(fountain));
//			EventBus.publish(new ResourceAvailable(fountain));
                FXMLLoader fxml = new FXMLLoader(getClass().getResource("view/Choreography.fxml"));
                root = (VBox)fxml.load();
//			HBox sliders = (HBox)FXMLLoader.load(getClass().getResource("sliders/Sliders.fxml"));
                Scene scene = new Scene(root);
                scene.getStylesheets().add(getClass().getResource("view/application.css").toExternalForm());
                primaryStage.setScene(scene);
                
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
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
