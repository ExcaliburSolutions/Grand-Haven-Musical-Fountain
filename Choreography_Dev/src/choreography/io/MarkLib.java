/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package choreography.io;

import choreography.view.ChoreographyController;
import choreography.view.music.MusicPaneController;
import java.io.InputStream;

/**
 *
 * @author elementsking
 */
public class MarkLib {
    private static Integer[] marks;

    static void readMarks(InputStream input) {
        
    }
    
//    static void writeMarks(Integer[] output) {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }

    public static FilePayload createFilePayload() {
        marks = ChoreographyController.getInstance().getBeatmarks();
        return new FilePayload(MusicPaneController.getInstance().getMusicName() + ".marks", marks.toString().getBytes());
    }
    
    
}
