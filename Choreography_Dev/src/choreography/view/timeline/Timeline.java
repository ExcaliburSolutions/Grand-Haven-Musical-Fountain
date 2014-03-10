/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package choreography.view.timeline;

import java.util.ArrayList;
import choreography.model.Event;
import choreography.model.fcw.FCW;
import java.util.SortedMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 *
 * @author elementsking
 */
public class Timeline extends ArrayList<Event>{
    
    /**
    * 
    */
   private static final long serialVersionUID = 7242109851591362314L;
   private static Timeline instance;
   private SortedMap<Integer, ArrayList<FCW>> timeline;
   private SortedMap<Integer, ArrayList<FCW>> waterTimeline;
   private SortedMap<Integer, ArrayList<FCW>> lightTimeline;
   
    
    /**
     *
     * @return
     */
    public static synchronized Timeline getInstance() {
        if(instance == null)
            instance = new Timeline();
        return instance;
        
    }
    
    private Timeline() {
        timeline = new ConcurrentSkipListMap<>();
        waterTimeline = new ConcurrentSkipListMap<>();
        lightTimeline = new ConcurrentSkipListMap<>();
    }
    
    /**
     * @param timeline the timeline to set
     */
    public void setTimeline(SortedMap<Integer, ArrayList<FCW>> timeline) {
        this.timeline = timeline;
        for(Integer i: timeline.keySet()) {
            for(FCW f: timeline.get(i)) {
                if(f.getIsWater()) {
                    if(getWaterTimeline().containsKey(i))
                        getWaterTimeline().get(i).add(f);
                    else {
                        ArrayList<FCW> newList = new ArrayList<>();
                        newList.add(f);
                        getWaterTimeline().put(i, newList);
                    }
                }
                else {
                    if(getLightTimeline().containsKey(i))
                        getLightTimeline().get(i).add(f);
                    else {
                        ArrayList<FCW> newList = new ArrayList<>();
                        newList.add(f);
                        getLightTimeline().put(i, newList);
                    }
                        
                }
            }
        }
        System.out.println("Water timeline: " + getWaterTimeline());
        System.out.println("Light timeline: " + getLightTimeline());
        TimelineController.getInstance().rePaint();
    }

    /**
     * @return the timeline
     */
    public SortedMap<Integer, ArrayList<FCW>> getTimeline() {
        return timeline;
    }

    /**
     * @return the waterTimeline
     */
    public SortedMap<Integer, ArrayList<FCW>> getWaterTimeline() {
        return waterTimeline;
    }

    /**
     * @return the lightTimeline
     */
    public SortedMap<Integer, ArrayList<FCW>> getLightTimeline() {
        return lightTimeline;
    }
    
}
