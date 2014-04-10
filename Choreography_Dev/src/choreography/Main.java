package choreography;
	
import choreography.model.fountain.Fountain;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import choreography.model.*;
import javafx.scene.image.Image;

/**
 *
 * @author elementsking
 */
public class Main extends Application {
	
	private static Fountain fountain;
	private VBox root;
	private static Stage primaryStage;

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     *
     * @return
     */
    public static Fountain getFountain() {
		return fountain;
	}

    /**
     *
     * @param primaryStage
     */
    @Override
	public void start(Stage primaryStage) {
        try {
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/resources/ghmf_cs_logo.png")));
            primaryStage.setIconified(true);
            this.setPrimaryStage(primaryStage);
            
            Main.primaryStage = primaryStage;
            primaryStage.setTitle("Grand Haven Musical Fountain Choreographer");
            fountain = Fountain.getInstance();
            FXMLLoader fxml = new FXMLLoader(getClass().getResource("view/Choreography.fxml"));
            root = (VBox)fxml.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("view/application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	/**
     * @return the primaryStage
     */
	public static Stage getPrimaryStage() {
        return primaryStage;
    }

	/**
     * @param primaryStage the primaryStage to set
     */
    public void setPrimaryStage(Stage primaryStage) {
        Main.primaryStage = primaryStage;
    }
}
