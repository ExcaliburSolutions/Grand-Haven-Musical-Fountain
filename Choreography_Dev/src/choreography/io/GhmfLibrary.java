/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package choreography.io;

import choreography.view.music.MusicPaneController;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

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
        
        while(entries.hasMoreElements()) {
            ZipEntry entry = entries.nextElement();
            if(entry.getName().endsWith(".ctl")) {
                InputStream input = ghmfFile.getInputStream(entry);
                CtlLib.getInstance().openCtl(input);
            }
            else if(entry.getName().endsWith(".map")) {
                InputStream input = ghmfFile.getInputStream(entry);
                MapLib.openMap(input);
            }
            else if(entry.getName().endsWith(".wav")) {
                InputStream input = ghmfFile.getInputStream(entry);
                File musicFile = new File(ghmfFile.getName() + entry.getName());
                MusicPaneController.getInstance().openMusicFile(musicFile);
            }
            else if(entry.getName().endsWith(".mark")) {
                InputStream input = ghmfFile.getInputStream(entry);
                MarkLib.readMarks(input);
            }
        }
    }
    
    public static boolean writeGhmfZip(File zipLoc, FilePayload... buffers) throws IOException {
        try (ZipOutputStream output = new ZipOutputStream(new FileOutputStream(zipLoc))) {
            for(FilePayload buffer: buffers) {
                ZipEntry entry = new ZipEntry(buffer.getName());
                output.putNextEntry(entry);
                output.write(buffer.getPayload());
                output.closeEntry();
            }
        }
        return true;
    }
}
