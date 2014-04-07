/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package choreography.io;

import choreography.view.music.MusicPaneController;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;
import javafx.stage.FileChooser;

/**
 *
 * @author elementsking
 */
public class GhmfLibrary {
    
    public static void openGhmfFile() {
        try {
            FileChooser fc = new FileChooser();
            fc.setTitle("Open GHMF Directory");
            fc.setInitialFileName(System.getProperty("user.dir"));
//            fc.getExtensionFilters().add(new ExtensionFilter("GHMF Folders", "*.ghmf"));
            ZipFile ghmfFile = new ZipFile(fc.showOpenDialog(null));
            readGhmfZip(ghmfFile);
        } catch (IOException ex) {
            Logger.getLogger(GhmfLibrary.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void readGhmfZip(ZipFile ghmfFile) throws IOException {
        Enumeration<? extends ZipEntry> entries = ghmfFile.entries();
        InputStream ctl = null, map = null, music = null, marks = null;
        File musicFile = null;
        
        while(entries.hasMoreElements()) {
            ZipEntry entry = entries.nextElement();
            if(entry != null) {
                if(entry.getName().endsWith(".ctl")) ctl = ghmfFile.getInputStream(entry);
                if(entry.getName().endsWith(".map")) map = ghmfFile.getInputStream(entry);
                if(entry.getName().endsWith(".wav")) {
                    music = ghmfFile.getInputStream(entry);
                    String name = entry.getName().substring(entry.getName().lastIndexOf("/")+1, entry.getName().length());
                    String suffix = name.substring(name.length() - 4, name.length());
                    Path out = Files.createTempFile(name, suffix);
                    Files.copy(music, out, REPLACE_EXISTING);
                    musicFile = new File(out.toUri());
                }
                if(entry.getName().endsWith(".mark")) marks = ghmfFile.getInputStream(entry);
            }
        }
        
        try {
            MusicPaneController.getInstance().openMusicFile(musicFile);    

            MapLib.openMap(map);
            CtlLib.getInstance().openCtl(ctl);

            MarkLib.readMarks(marks);
        } catch (NullPointerException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    public static boolean writeGhmfZip(File zipLoc, FilePayload... buffers) throws IOException {
        try (ZipOutputStream output = new ZipOutputStream(new FileOutputStream(zipLoc))) {
            for(FilePayload buffer: buffers) {
                buffer.setName(buffer.getName().replaceAll("\\d*$", ""));
                ZipEntry entry = new ZipEntry(buffer.getName());
                output.putNextEntry(entry);
                output.write(buffer.getPayload());
                output.closeEntry();
            }
        }
        return true;
    }
}
