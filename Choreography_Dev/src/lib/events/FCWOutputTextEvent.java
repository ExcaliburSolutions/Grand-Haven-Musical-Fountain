/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lib.events;
    
import lib.FCW;

/**
 *
 * @author elementsking
 */
public class FCWOutputTextEvent {
    private FCW fcwOutput;
    
    public FCWOutputTextEvent(FCW f) {
        fcwOutput = f;
    }
    
    public FCW getFcwOutput() {
        return fcwOutput;
    }
}
