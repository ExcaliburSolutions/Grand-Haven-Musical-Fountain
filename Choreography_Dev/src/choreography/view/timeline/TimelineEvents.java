/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package choreography.view.timeline;

import java.util.ArrayList;
import choreography.model.Event;

/**
 *
 * @author elementsking
 */
public class TimelineEvents extends ArrayList<Event>{
    
    private static ArrayList<Event> events;
    
    public static synchronized ArrayList<Event> getInstance() {
        if(events == null)
            events = new ArrayList<>();
        return events;
    }
    
}
