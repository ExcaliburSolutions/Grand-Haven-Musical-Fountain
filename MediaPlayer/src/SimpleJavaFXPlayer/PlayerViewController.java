package SimpleJavaFXPlayer;

//import Model.Music;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import javax.swing.JFileChooser;

/**
 *
 * @author Steven Merdzinski
 */
public class PlayerViewController implements Initializable {
    
    @FXML
    private ListView musicList;
    @FXML
    private Label songProgress, songName;
    @FXML
    private Slider volume;
    @FXML
    private ScrollPane timelineScrollPane;
    @FXML
    private ColorPicker colorPicker;
    /**/
    private MediaPlayer mediaPlayer;
    private int musicId = -1;
    
    private void getAllMusic(File directory) {
        if (directory.isDirectory()) {
            File[] listFiles = directory.listFiles();
            for (File file : listFiles) {
                getAllMusic(file);
            }
        } else {
            if (directory.getName().toLowerCase().endsWith(".mp3") || directory.getName().toLowerCase().endsWith(".wav")) {
                
                Music music = new Music();
                music.setName(directory.getName());
                music.setDirectoryFile(directory.getAbsolutePath());
                
                musicList.getItems().add(music);
            }
        }
    }
    
    public void selectMusic() {
        JFileChooser jf = new JFileChooser();
        jf.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int i = jf.showSaveDialog(null);
        if (i == JFileChooser.APPROVE_OPTION) {
            getAllMusic(jf.getSelectedFile());
            saveDirectory(jf.getSelectedFile().getAbsolutePath());
        }
//        play((Music) musicList.getFocusModel().getFocusedItem());
//        musicId = musicList.getFocusModel().getFocusedIndex();
//        mediaPlayer.play();
//        mediaPlayer.stop();
        
        createTimeline();
       
        
    }
    
    public void updateProgress() {
        
        try {
            songProgress.setText("0s");
            ChangeListener<Duration> changeListener = new ChangeListener<Duration>() {
                @Override
                public void changed(ObservableValue<? extends Duration> ov, Duration t, Duration t1) {
                    songProgress.setText( (mediaPlayer.getTotalDuration().toSeconds() - mediaPlayer.getCurrentTime().toSeconds()) + "s");
                }
            };
            mediaPlayer.currentTimeProperty().addListener(changeListener);
        } catch (Exception e) {
            System.out.println("Error updating song progress " + e);
        }
        
    }
    
    public void play(Music music) {
        String source = new File(music.getDirectoryFile()).toURI().toString();
        if (musicId != -1) {
            mediaPlayer.dispose();
        }
        Media media = new Media(source);
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(volume.getValue());
        songName.setText(music.getName());
        updateProgress();
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                nextSong();
            }
        });
        
        
        mediaPlayer.play();
        
    }
    
    public void playByDoubleClick(MouseEvent evt) {
        if (evt.getButton().equals(MouseButton.PRIMARY)) {
            if (evt.getClickCount() == 2) {
                play((Music) musicList.getFocusModel().getFocusedItem());
                musicId = musicList.getFocusModel().getFocusedIndex();
            }
            if (evt.getClickCount() == 1) {
                play((Music) musicList.getFocusModel().getFocusedItem());
                musicId = musicList.getFocusModel().getFocusedIndex();
                mediaPlayer.pause();
                System.out.println(mediaPlayer.getTotalDuration().toSeconds() - mediaPlayer.getCurrentTime().toSeconds());
                
            }
        }
    }
    
    public void previousSong() {
        if (musicId > 0) {
            musicId--;
            play((Music) musicList.getItems().get(musicId));
            musicList.getFocusModel().focus(musicId);
            musicList.getSelectionModel().select(musicId);
        }
    }
    
    public void createTimeline(){
    	//double songLength = (mediaPlayer.getTotalDuration().toSeconds())*10;
    	GridPane gridpaneRec = new GridPane();
//    	final ColorPicker colorPicker = new ColorPicker();
//    	
//    	colorPicker.setLayoutX(1000);
//        colorPicker.setLayoutY(1000);
    	
    	final Rectangle[][] recArray = new Rectangle[101][70];
   	 for(int i=0; i<101; i++){
     		  gridpaneRec.getColumnConstraints().add(new ColumnConstraints(30));
     		  if (i < 70){ //because the array is not square this needs to be here
     			 gridpaneRec.getRowConstraints().add(new RowConstraints(30));
     		  }
     		  
      	  for(int j=0; j<70; j++){
      		  if (i == 0){
      			 recArray[i][j] = new Rectangle(50,25, Color.RED);
      			 continue;
      		  }
      		  recArray[i][j] = new Rectangle(25,25, Color.LIGHTGREY);
      		  gridpaneRec.add(recArray[i][j], i, j);
      		  //these are needed to talk to the mouse pressed events
      		  final int testI = i;
      		  final int testJ = j;
      		  
      		recArray[i][j].setOnMousePressed(new EventHandler<MouseEvent>() {
      			  public void handle(MouseEvent me) {
      	        System.out.println("Col " + (testI) + " Row " + (testJ+1));
      	     recArray[testI][testJ].setFill(colorPicker.getValue());
      	    }
      	});
      		
      		recArray[i][j].setOnDragDetected(new EventHandler<MouseEvent>() {
    			  public void handle(MouseEvent me) {
    				  //starts the drag event
    				 recArray[testI][testJ].startFullDrag();
    				 
    	    }
    			  
    	});
      		//continues and ends the drag event
      		recArray[i][j].setOnMouseDragOver(new EventHandler<MouseEvent>() {
  			  public void handle(MouseEvent me) {
  				  recArray[testI][testJ].setFill(colorPicker.getValue());
  	    }
      	});
 
      	  }
        }
   	 timelineScrollPane.setContent(gridpaneRec);
   	 
    }
    
    public void playSong() {
    	try{
        mediaPlayer.play();
    	}
    	catch (Exception e){
    	play((Music) musicList.getFocusModel().getFocusedItem());
        musicId = musicList.getFocusModel().getFocusedIndex();
    	}
    }
    
    public void pauseSong() {
        mediaPlayer.pause();
    }
    
    public void stopSong() {
        mediaPlayer.stop();
    }
    
    public void nextSong() {
        if (musicId < musicList.getItems().size() - 1) {
            musicId++;
            play((Music) musicList.getItems().get(musicId));
            musicList.getFocusModel().focus(musicId);
            musicList.getSelectionModel().select(musicId);
        }
    }
    
    private void loadDirectory() {
        try (
                FileReader fr = new FileReader("lastDir.txt")) {
            BufferedReader br = new BufferedReader(fr);
            String content;
            while ((content = br.readLine()) != null) {
                System.out.println(content);
                getAllMusic(new File(content));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private void saveDirectory(String directory) {
        FileOutputStream out;
        
        PrintStream p;
        try {
            out = new FileOutputStream("lastDir.txt");
            p = new PrintStream(out);
            p.println(directory);
            p.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
    public void close() {
        SimpleJavaFXPlayer.getInstance().close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadDirectory();
        volume.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                    Number old_val, Number new_val) {
                System.out.println(volume.getValue());
                mediaPlayer.setVolume(volume.getValue());
            }
        });
        

    }
}