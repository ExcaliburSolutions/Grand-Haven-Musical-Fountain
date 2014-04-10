package choreography.view.music;

import SimpleJavaFXPlayer.AudioWaveformCreator;
import SimpleJavaFXPlayer.Music;
import choreography.io.FilePayload;
import choreography.view.ChoreographyController;
import choreography.view.sim.FountainSimController;
import choreography.view.sliders.SlidersController;
import choreography.view.timeline.TimelineController;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.stage.FileChooser;
import javafx.util.Duration;

/**
 * This class coordinates the mediaPlayer and integrates it into the application.
 * It also controls the syncing between all Timeline panes and the SONGTIME.
 * 
 * @author Steven Merdzinski
 * @author Frank Madrid II
 * @author Max Morell
 */
public class MusicPaneController {
    
    private int timeFactor;
	
    private static MusicPaneController instance;

    private MediaPlayer mediaPlayer;
    private double time, roundedTime;
    private Duration duration;
    Music music2;
    private boolean notFirst = false;
    final DecimalFormat f = new DecimalFormat("0.0");
    private File musicFileLocation;

    /**
     * 
     */
    public static final int H_PIXEL_SIZE = 15;

    /**
     *
     */
    public static final int V_PIXEL_SIZE = 15;
    //public static final double SONG_TIME = 10;

    /**
     *
     */
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
    
    @FXML
    private ScrollPane waterTimeline, timeLabel;
    
    @FXML
    private LineChart labelChart;
    
    @FXML
    private NumberAxis labelAxis, numberLine;
    
    @FXML
    private Button playButton, resetButton;
    
    

    /**
     * Controls access to instance and constructor
     * 
     * @return the MusicPaneController instance
     */
    public static MusicPaneController getInstance() {
    	if(instance == null)
    		instance = new MusicPaneController();
    	return instance;
    }

    public MusicPaneController() {
        this.timeFactor = 10;
    }

    /**
     * Pauses music, kills all timers, pauses sweeps, and tells sliders to change.
     * 
     * @param event 
     */
    @FXML
    public void pauseSong(ActionEvent event) {
    	mediaPlayer.pause();
        ChoreographyController.getInstance().stopTimelineTimer();
        ChoreographyController.getInstance().stopSliderTimer();
        TimelineController.getInstance().fireSliderChangeEvent();
        FountainSimController.getInstance().pauseLeftSweep();
        FountainSimController.getInstance().pauseRightSweep();
//        FountainSimController.getInstance().disposeBuffer();
    }

    /**
     * Plays music, starts sweeps, starts polling algorithms, and resets sliders.
     * 
     * @param event 
     */
    @FXML
    public void playSong(ActionEvent event) {
    	if (mediaPlayer.statusProperty().getValue()==Status.PAUSED || 
    			mediaPlayer.statusProperty().getValue()==Status.STOPPED ||
    			mediaPlayer.statusProperty().getValue()==Status.READY){
            SlidersController.getInstance().resetAllSliders();
            mediaPlayer.play();
            FountainSimController.getInstance().playLeftSweep();
            FountainSimController.getInstance().playRightSweep();
            playButton.setText("Pause");
            ChoreographyController.getInstance().startPollingTimeSliderAlgorithm();
            //ChoreographyController.getInstance().startPollingSlidersAlgorithm();
            ChoreographyController.getInstance().startPollingSimAlgorithm();
            ChoreographyController.getInstance().startPollingColorAlgorithm();
            //ChoreographyController.getInstance().startPlayingSim();
                
    	}
    	
    	if (mediaPlayer.statusProperty().getValue()==Status.PLAYING){
            pauseSong(event);
            playButton.setText("Play");

//            ChoreographyController.getInstance().stopSliderTimer();
//            ChoreographyController.getInstance().stopTimelineTimer();
    	}
    }

    /**
     * Kills the music, sets slider and music time to 0, 
     * and clears the sim, sliders, and sweeps.
     * 
     * @param event 
     */
    @FXML
    public void stopSong(ActionEvent event) {
    	mediaPlayer.stop();
        mediaPlayer.seek(Duration.ZERO);
        timeSlider.setValue(0.0);
        TimelineController.getInstance().fireSimClearEvent();
//        FountainSimController.getInstance().disposeBuffer();
        SlidersController.getInstance().resetAllSliders();
        FountainSimController.getInstance().clearSweeps();
        playButton.setText("Play");
    }
    
//    /**
//     * Loads music from file into memory and initializes songLabel
//     * @param fileChosen 
//     */
//    private void getAllMusic(File fileChosen) {
//        
//    }
    
    /** 
     * @return the media player
     */
    public MediaPlayer getMediaPlayer(){
    	return mediaPlayer;
    }
    
    /**
     * 
     * @return the waterTimeline pane
     */
    public ScrollPane getWaterPane(){
    	return waterTimeline;
    }
    
    /**
     * 
     * @return the time label
     */
    public ScrollPane getTimeLabel(){
    	return timeLabel;
    }
    
    /**
     * Throws up FileChooser and loads music into mediaPlayer
     */
    public void selectMusic() {
    	if (notFirst){
            mediaPlayer.dispose();    		
    	}
    	FileChooser fc = new FileChooser();
    	fc.setInitialDirectory(new File(System.getProperty("user.dir")));
        fc.getExtensionFilters().setAll(new FileChooser.ExtensionFilter(
                "Music Files", "*.wav", "*.flac"));
    	File file2 = fc.showOpenDialog(null);
    	openMusicFile(file2);
        
        
    }
    	
    /**
     * Initializes the music pane with music length, time, name, and number line after load.
     * @param file2 the music file
     */
    public void loadMusicFile(File file2) {
    	URL url = null;
        try {
                url = file2.toURI().toURL();
        } catch (MalformedURLException ec) {
                ec.printStackTrace();
        }
		
        try {
            AudioWaveformCreator awc = new AudioWaveformCreator(url, "/resources/out.png");

            setTime(awc.getTime());
            DecimalFormat f = new DecimalFormat("0.0");
            roundedTime = Double.parseDouble(f.format(getTime()));
            setTime(getTimeFactor() * Double.parseDouble(f.format(getTime())));
            SONG_TIME = (int) getTime();
            TimelineController.getInstance().getTimeline().setTime(SONG_TIME);
            TimelineController.getInstance().setTimelineGridPane();
            TimelineController.getInstance().setWaterGridPane();
            ChoreographyController.getInstance().setBeatMarkGridPane();
            numberLine.setMinWidth(getTime()*26);
            numberLine.setPrefWidth(getTime()*26);
            numberLine.setUpperBound(roundedTime);
            numberLine.setVisible(true);
            songProgress.setText("0/"+roundedTime);
            
            music2.setName(file2.getName());
            music2.setDirectoryFile(file2.getAbsolutePath());
            songName.setText(music2.getName());
        } catch (Exception ex) {

                ex.printStackTrace();
        }
        notFirst = true;
    }

    /**
     * Loads music file and enables play button.
     * @param file2 
     */
    public void openMusicFile(File file2) {
        musicFileLocation = file2.getAbsoluteFile();
        music2 = new Music();
//        getAllMusic(file2);
        music2.setDirectoryFile(file2.getAbsolutePath());
        
        loadMusicFile(file2);
        
        String source = new File(music2.getDirectoryFile()).toURI().toString();
        Media media = new Media(source);
        mediaPlayer = new MediaPlayer(media);
        //mediaPlayer.setVolume(volume.getValue());
        songName.setText(music2.getName());
        mediaPlayer.play();
        mediaPlayer.pause();
        playButton.setDisable(false);
        resetButton.setDisable(false);
//        updateProgressTimer();
//        ChoreographyController.getInstance().startPollingTimeSliderAlgorithm();
    }

    /**
     * Sets song progress, duration, timeline, time label, and time slider value.
     */
    public void updateProgress() {
        final DecimalFormat f = new DecimalFormat("0.0");
        try {
            //songProgress.setText(time + "s");
//                    songProgress.setText( f.format((mediaPlayer.getTotalDuration().toSeconds() - mediaPlayer.getCurrentTime().toSeconds())) + "s");
            songProgress.setText( f.format(mediaPlayer.getCurrentTime().toSeconds()) + "/"+ f.format(mediaPlayer.getTotalDuration().toSeconds()));
                duration = mediaPlayer.getMedia().getDuration();
//                int currTime = (int)mediaPlayer.getCurrentTime().toSeconds()*10;
//                FountainSimController.getInstance().updateColors(currTime);
            TimelineController.getInstance().getScrollPane().setHvalue( (mediaPlayer.getCurrentTime().toSeconds()/mediaPlayer.getTotalDuration().toSeconds())*100);
            timeSlider.setValue((mediaPlayer.getCurrentTime().toSeconds()/mediaPlayer.getTotalDuration().toSeconds())*100);
            timeSlider.setValue( (mediaPlayer.getCurrentTime().toSeconds()/mediaPlayer.getTotalDuration().toSeconds())*100);
            waterTimeline.setHvalue( (mediaPlayer.getCurrentTime().toSeconds()/mediaPlayer.getTotalDuration().toSeconds())*100);
            ChoreographyController.getInstance().getBeatMarkScrollPane().setHvalue( (mediaPlayer.getCurrentTime().toSeconds()/mediaPlayer.getTotalDuration().toSeconds())*100);
            timeLabel.setHvalue( (mediaPlayer.getCurrentTime().toSeconds()/mediaPlayer.getTotalDuration().toSeconds())*100);
        } catch (Exception e) {
            System.out.println("Error updating song progress " + e);
        }

    }
    
    

    @FXML // This method is called by the FXMLLoader when initialization is complete
    public void initialize() {
        assert colorPicker != null : "fx:id=\"colorPicker\" was not injected: check your FXML file 'MusicPane.fxml'.";
        assert musicPane != null : "fx:id=\"musicPane\" was not injected: check your FXML file 'MusicPane.fxml'.";
        assert songName != null : "fx:id=\"songName\" was not injected: check your FXML file 'MusicPane.fxml'.";
        assert songProgress != null : "fx:id=\"songProgress\" was not injected: check your FXML file 'MusicPane.fxml'.";
        //assert volume != null : "fx:id=\"volume\" was not injected: check your FXML file 'MusicPane.fxml'.";
        
        timeSlider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable ov) {
                if (timeSlider.isValueChanging()) {
                    // multiply duration by percentage calculated by slider position
                	mediaPlayer.pause();
                	playButton.setText("Play");
                    mediaPlayer.seek(duration.multiply(timeSlider.getValue() / 100.0));
                    //mediaPlayer.play();
                }
            }
        });
        this.timeFactor = 10;
        instance = this;
    }

    /**
     * @return the timeFactor
     */
    public int getTimeFactor() {
        return timeFactor;
    }

    /**
     * @param timeFactor the timeFactor to set
     */
    public void setTimeFactor(int timeFactor) {
        this.timeFactor = timeFactor;
    }

    /**
     * @return the time in seconds
     */
    public double getTime() {
        return time;
    }
    
    /**
     * 
     * @param time in seconds
     */
    public void setTime(double time) {
        this.time = time;
    }
    
    /**
     * 
     * @return the current time in tenths of a second
     */
    public int getTenthsTime() {
        double wholeTime = timeSlider.getValue() /100 * time;
//        double inter = wholeTime * 10;
//        int seconds = (int) inter;
        int tenths = (int) wholeTime;
//        System.out.println(wholeTime + " " + tenths);
        return tenths;
    }
    
    /**
     * 
     * @return the song name without extension or trailing numbers
     */
    public String getMusicName() {
        String name = music2.getName().substring(0, music2.getName().length() - 4);
        String sansNumbers = name.replaceAll("\\d*$", "");
        return sansNumbers;
    }

    /**
     * 
     * @return a FilePayload(songName, songBytes)
     */
    public FilePayload createFilePayload() {
        try {
            FileInputStream input = new FileInputStream(musicFileLocation);
            int length = (int)musicFileLocation.length();
            byte[] musicFileBytes = new byte[length];
            input.read(musicFileBytes);
            return new FilePayload(music2.getName(), musicFileBytes);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MusicPaneController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MusicPaneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        throw new IllegalArgumentException("Unable to create music FilePayload");
    }

    /**
     * Kills mediaPlayer, sets notFirst, resets songName, songProgress, 
     * waterTimeline, timeSlider to 0, and disables playButton.
     */
    public void resetAll() {
        mediaPlayer.dispose();
        notFirst = false;
        songName.setText("No Music Selected");
        songProgress.setText("");
        waterTimeline.setContent(null);
        timeSlider.setValue(0);
        playButton.setDisable(true);
        resetButton.setDisable(true);
    }
}
