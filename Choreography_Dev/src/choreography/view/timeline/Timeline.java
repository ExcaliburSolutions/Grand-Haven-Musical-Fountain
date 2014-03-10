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
public class Timeline extends ArrayList<Event>{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 7242109851591362314L;
	private static ArrayList<Event> events;
    
    /**
     *
     * @return
     */
    public static synchronized ArrayList<Event> getInstance() {
        if(events == null)
            events = new ArrayList<>();
        return events;
        
    }
    
}
