/**
 * Sample Skeleton for "MusicPane.fxml" Controller Class
 * You can copy and paste this code into your favorite IDE
 **/

package choreography.view.music;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import choreography.view.timeline.TimelineController;
import SimpleJavaFXPlayer.AudioWaveformCreator;
import SimpleJavaFXPlayer.Music;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.util.Duration;

public class MusicPaneController {
	
	private static MusicPaneController instance;
	
	private MediaPlayer mediaPlayer;
    private double time, roundedTime;
    private Duration duration;
    Music music2;
    private boolean notFirst = false;
	final DecimalFormat f = new DecimalFormat("#.0");

    
    public static final int H_PIXEL_SIZE = 15;
    public static final int V_PIXEL_SIZE = 15;
    //public static final double SONG_TIME = 10;
    public static int SONG_TIME = 0;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="colorPicker"
    private ColorPicker colorPicker; // Value injected by FXMLLoader

    @FXML // fx:id="musicPane"
    private VBox musicPane; // Value injected by FXMLLoader

    @FXML // fx:id="songName"
    private Label songName; // Value injected by FXMLLoader

    @FXML // fx:id="songProgress"
    private Label songProgress; // Value injected by FXMLLoader

    @FXML // fx:id="volume"
    private Slider volume, timeSlider; // Value injected by FXMLLoader

    public static MusicPaneController getInstance() {
    	if(instance == null)
    		instance = new MusicPaneController();
    	return instance;
    }

    // Handler for Button[Button[id=null, styleClass=button]] onAction
    @FXML
    void pauseSong(ActionEvent event) {
    	mediaPlayer.pause();
    }

    // Handler for Button[Button[id=null, styleClass=button]] onAction
    @FXML
    void playSong(ActionEvent event) {
    	try{
    		mediaPlayer.play();
    	}
    	catch (Exception e){
    		
    	}
    }

    // Handler for Button[Button[id=null, styleClass=button]] onAction
    @FXML
    void stopSong(ActionEvent event) {
    	mediaPlayer.stop();
    }
    
    private void getAllMusic(File fileChosen) {

        if (fileChosen.getName().toLowerCase().endsWith(".mp3") || fileChosen.getName().toLowerCase().endsWith(".wav")) {
            
            music2.setName(fileChosen.getName());
            music2.setDirectoryFile(fileChosen.getAbsolutePath());
            songName.setText(music2.getName());
        }
}
    
    public MediaPlayer getMediaPlayer(){
    	return mediaPlayer;
    }
    
    public void selectMusic() {
    	if (notFirst){
    		mediaPlayer.dispose();    		
    	}
    	
    	FileChooser fc = new FileChooser();
    	fc.setInitialDirectory(new File(System.getProperty("user.home")));
    	File file2 = fc.showOpenDialog(null);
    	music2 = new Music();
    	if (file2 != null){
    		getAllMusic(file2);
    		music2.setDirectoryFile(file2.getAbsolutePath());
    	}
    	
    	String source = new File(music2.getDirectoryFile()).toURI().toString();
    	Media media = new Media(source);
    	mediaPlayer = new MediaPlayer(media);
    	mediaPlayer.setVolume(volume.getValue());
    	songName.setText(music2.getName());
    	updateProgress(); 
    	
    	
    	mediaPlayer.currentTimeProperty().addListener(new InvalidationListener() {
            public void invalidated(Observable ov) {
                updateProgress();
            }
        });

    	URL url = null;
		try {
			url = file2.toURI().toURL();
			
		} catch (MalformedURLException ec) {
			ec.printStackTrace();
		}
		
        try {
			AudioWaveformCreator awc = new AudioWaveformCreator(url, "out.png");

			time = awc.getTime();
			DecimalFormat f = new DecimalFormat("#.0");
			roundedTime = Double.parseDouble(f.format(time));
        	time = 2*Double.parseDouble(f.format(time));
        	SONG_TIME = (int) time;
        	TimelineController.getInstance().setGridPane();
			
		} catch (Exception ex) {
			
			ex.printStackTrace();
		}
        System.out.println(roundedTime);
    }
    
    
    public void updateProgress() {
    	final DecimalFormat f = new DecimalFormat("#.0");
        
        try {
            //songProgress.setText(time + "s");
            ChangeListener<Duration> changeListener = new ChangeListener<Duration>() {
                @Override
                public void changed(ObservableValue<? extends Duration> ov, Duration t, Duration t1) {
                    songProgress.setText( f.format((mediaPlayer.getTotalDuration().toSeconds() - mediaPlayer.getCurrentTime().toSeconds())) + "s");
                    duration = mediaPlayer.getMedia().getDuration();
                    TimelineController.getInstance().getScrollPane().setHvalue( (mediaPlayer.getCurrentTime().toSeconds()/mediaPlayer.getTotalDuration().toSeconds())*100);
                    timeSlider.setValue( (mediaPlayer.getCurrentTime().toSeconds()/mediaPlayer.getTotalDuration().toSeconds())*100);
                }
            };
            mediaPlayer.currentTimeProperty().addListener(changeListener);
            
        } catch (Exception e) {
            System.out.println("Error updating song progress " + e);
        }
        
    }
    
    

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert colorPicker != null : "fx:id=\"colorPicker\" was not injected: check your FXML file 'MusicPane.fxml'.";
        assert musicPane != null : "fx:id=\"musicPane\" was not injected: check your FXML file 'MusicPane.fxml'.";
        assert songName != null : "fx:id=\"songName\" was not injected: check your FXML file 'MusicPane.fxml'.";
        assert songProgress != null : "fx:id=\"songProgress\" was not injected: check your FXML file 'MusicPane.fxml'.";
        assert volume != null : "fx:id=\"volume\" was not injected: check your FXML file 'MusicPane.fxml'.";

        // Initialize your logic here: all @FXML variables will have been injected
        instance = this;
        
        timeSlider.valueProperty().addListener(new InvalidationListener() {
            public void invalidated(Observable ov) {
                if (timeSlider.isValueChanging()) {
                    // multiply duration by percentage calculated by slider position
                    mediaPlayer.seek(duration.multiply(timeSlider.getValue() / 100.0));
                }
            }
        });
        
    }

}
