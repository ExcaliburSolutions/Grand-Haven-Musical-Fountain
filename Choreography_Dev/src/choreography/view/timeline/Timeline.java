/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package choreography.view.timeline;

import choreography.model.Event;
import choreography.model.fcw.FCW;
import choreography.view.colorPalette.ColorPaletteEnum;
import choreography.view.music.MusicPaneController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.logging.Logger;


public class Timeline extends ArrayList<Event>{
    
    /**
    * 
    */
   private static final long serialVersionUID = 7_242_109_851_591_362_314L;
   private static Timeline instance;
   private int time;
   private int numChannels;
   private SortedMap<Integer, ArrayList<FCW>> timeline;
   private SortedMap<Integer, ArrayList<FCW>> waterTimeline;
   private SortedMap<Integer, ArrayList<FCW>> lightTimeline;
   private HashMap<Integer, SortedMap<Integer, Integer>> lightFCWColorMap;
    private static final Logger LOG = Logger.getLogger(Timeline.class.getName());
   
    
    /**
     *
     * @return
     */
    public static synchronized Timeline getInstance() {
        if(instance == null) {
            instance = new Timeline();
        }
        return instance;
        
    }
    
    private Timeline() {
        timeline = new ConcurrentSkipListMap<>();
        waterTimeline = new ConcurrentSkipListMap<>();
        lightTimeline = new ConcurrentSkipListMap<>();
        time = (int)(MusicPaneController.getInstance().getTime() * 10); //tenths of a second
        numChannels = TimelineController.getInstance().lightRecArray.length;
        lightFCWColorMap = new LinkedHashMap<>();
    }
    
    /**
     * @param timeline the timeline to set
     */
    public void setTimeline(SortedMap<Integer, ArrayList<FCW>> timeline) {
        this.timeline = timeline;
        timeline.keySet().stream().forEach((i) -> {
            timeline.get(i).stream().forEach((f) -> {
                if (f.getIsWater()) {
                    if (waterTimeline.containsKey(i)) {
                        waterTimeline.get(i).add(f);
                    } else {
                        ArrayList<FCW> newList = new ArrayList<>();
                        newList.add(f);
                        waterTimeline.put(i, newList);
                    }
                } else {
                    if (lightTimeline.containsKey(i)) {
                        lightTimeline.get(i).add(f);
                    } else {
                        ArrayList<FCW> newList = new ArrayList<>();
                        newList.add(f);
                        lightTimeline.put(i, newList);
                    }
                }
            });
        });
        populateLightFcwArray();
        TimelineController.getInstance().rePaint();
    }

    /**
     * @return the timeline
     */
    public SortedMap<Integer, ArrayList<FCW>> getTimeline() {
        collapseLightArray();
        collapseLightAndWaterMaps();
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
        collapseLightArray();
        return lightTimeline;
    }
    
    /**
     *
     * @param pointInTime
     * @param f
     */
    public void setWaterFcwAtPoint(int pointInTime, FCW f) {
        waterTimeline.get(pointInTime).add(f);
    }
    
    /**
     *
     * @param start
     * @param end
     * @param f
     */
    public void setLightFcwWithRange(int start, int end, FCW f) {
        int row = f.getAddr();
        int color = f.getData();
        for(int i = start; i < end; i++) {
            lightFCWColorMap.get(row).put(i, color);
        }
    }
    
    /**
     *
     */
    public void collapseLightArray() {
        lightTimeline.clear();
        for(int i = 0; i < MusicPaneController.getInstance().getTime() * 10; i++) {
            ArrayList<FCW> fcws = new ArrayList<>(5);
            for(int chAddr = 0; chAddr < numChannels; chAddr++) {
                //TODO double check algo
                fcws.add(new FCW(chAddr, lightFCWColorMap.get(chAddr).get(i)));
            }
            lightTimeline.put(i, fcws);
        }
    }

    private void collapseLightAndWaterMaps() {
        
        insertTimelineIterator(waterTimeline);
        insertTimelineIterator(lightTimeline);
        
    }

    private void insertTimelineIterator(SortedMap<Integer, ArrayList<FCW>> srcTimeline) {
        srcTimeline.keySet().stream().forEach((i) -> {
            srcTimeline.get(i).stream().forEach((f) -> {
                insertIntoTimeline(timeline, i, f);
            });
        });
    }
    private void insertIntoTimeline(SortedMap<Integer, ArrayList<FCW>> srcTimeline, Integer i, FCW f) {
        if(srcTimeline.containsKey(i)) {
            srcTimeline.get(i).add(f);
        }
        else {
            ArrayList<FCW> newFcw = new ArrayList(1);
            newFcw.add(f);
            srcTimeline.put(i, newFcw);
        }
    }
    private void populateLightFcwArray() {
        //TODO populate LightFcwArray with LightTimeline data
        
        //Decompose LightTimeline into Channels
        for(Integer i: lightTimeline.keySet()) {
            for(FCW f: lightTimeline.get(i)) {
                //For each channel,
                if(!lightFCWColorMap.containsKey(f.getAddr())) {
                    SortedMap<Integer, Integer> fcwComponents = new ConcurrentSkipListMap<>();
                    
                    recursiveTailMapSearch(f.getAddr(), 0);
                    
                    
                    lightFCWColorMap.put(i, fcwComponents);
                }
            }
        }
        
           
            
            //use setLightWithRange(start, end, color) to fill information
            //
    }
//TODO Fix this broken piece...
    private void recursiveTailMapSearch(int target, int index) {
        Iterator<Entry<Integer, ArrayList<FCW>>> it = lightTimeline.tailMap(index).entrySet().iterator();
        int start = it.next().getKey();
        int end = 0;
        while(it.hasNext()){
            Entry<Integer, ArrayList<FCW>> entry = it.next();
            for(FCW f: entry.getValue()) {
                if(f.getAddr() == target && f.getData() == 0) {
                    end = entry.getKey();
                    System.out.println("Start:" + start + " End:" + end + " FCW:" + f);
                    setLightFcwWithRange(start, end, f);
                }
                recursiveTailMapSearch(target, end);
            }
            
        }
        return;
    }

    
    
    
}
