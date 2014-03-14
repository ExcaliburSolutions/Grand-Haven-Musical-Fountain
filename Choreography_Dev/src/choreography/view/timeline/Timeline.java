/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package choreography.view.timeline;

import choreography.io.FCWLib;
import choreography.model.Event;
import choreography.model.fcw.FCW;
import choreography.view.colorPalette.ColorPaletteEnum;
import choreography.view.music.MusicPaneController;
import choreography.view.timeline.TimelineController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.logging.Logger;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;


public class Timeline extends ArrayList<Event>{
    
    /**
    * 
    */
	public static final int OFF = -5;
   private static final long serialVersionUID = 7_242_109_851_591_362_314L;
   private static Timeline instance;
   private int time;
   private int numChannels;
   private SortedMap<Integer, ArrayList<FCW>> timeline;
   private SortedMap<Integer, ArrayList<FCW>> waterTimeline;
   private SortedMap<Integer, ArrayList<FCW>> lightTimeline;
   private SortedMap<Integer, SortedMap<Integer, Integer>> gtfoArray;
   private int[][] lightFCWColorMap;
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
    }
    
    public SortedMap<Integer, SortedMap<Integer, Integer>> getGtfoArray() {
		return gtfoArray;
	}

	public void setGtfoArray(SortedMap<Integer, SortedMap<Integer, Integer>> gtfoArray) {
		this.gtfoArray = gtfoArray;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getNumChannels() {
		return numChannels;
	}

	public void setNumChannels(int numChannels) {
		this.numChannels = numChannels;
	}

	public void setWaterTimeline(SortedMap<Integer, ArrayList<FCW>> waterTimeline) {
		this.waterTimeline = waterTimeline;
	}

	public void setLightTimeline(SortedMap<Integer, ArrayList<FCW>> lightTimeline) {
		this.lightTimeline = lightTimeline;
		
	}

	/**
     * @param timeline the timeline to set
     */
    public void setTimeline(SortedMap<Integer, ArrayList<FCW>> timeline) {
        this.timeline = timeline;
        timeline.keySet().stream().forEach((i) -> {
            timeline.get(i).stream().forEach((f) -> {
                if (f.getIsWater()) {
                    insertIntoTimeline(waterTimeline, i, f);
                }
                else {
                    insertIntoTimeline(lightTimeline, i, f);
                }
            });
        });
        time = (int)(MusicPaneController.getInstance().SONG_TIME * 10); //tenths of a second
        numChannels = countUChannels(lightTimeline);
//        lightFCWColorMap = new LinkedHashMap<>(numChannels);
        gtfoArray = new ConcurrentSkipListMap<Integer, SortedMap<Integer, Integer>>(); //new//new int[time][numChannels];
        startAndEndPoints(gtfoArray);
        fillTheSpaces(gtfoArray);
//        populateLightFcwArray();
        TimelineController.getInstance().rePaint();
    }

    /**
     * @return the timeline
     */
    public SortedMap<Integer, ArrayList<FCW>> getTimeline() {
//        collapseLightArray();
//        collapseLightAndWaterMaps();
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
//        collapseLightArray();
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
    
//    /**
//     *
//     * @param start
//     * @param end
//     * @param f
//     */
//    public void setLightFcwWithRange(int start, int end, FCW f) {
//        int row = f.getAddr();
//        int color = f.getData();
//        for(int i = start; i < end; i++) {
//            lightFCWColorMap.get(row).put(i, color);
//        }
//    }
    
    public void setLightFcwAtPoint(int point, FCW f) {
        
    }
    
    public void fillTheSpaces(SortedMap<Integer, SortedMap<Integer, Integer>> oldArray ){
    	int currentNumber = 0;
		boolean fill = false;
		for(Integer channel: oldArray.keySet()) {
			for(Integer tenth: oldArray.get(channel).keySet()) {
				if(oldArray.get(channel).get(tenth) != 0 && oldArray.get(channel).get(tenth) != OFF){
    				currentNumber = oldArray.get(channel).get(tenth);
    				fill = true;
    			}
    			if(oldArray.get(channel).get(tenth) == OFF){
//    				oldArray[i][j] = 0;
    				fill = false;
    			}
    			if(fill = true && oldArray.get(channel).get(tenth) != currentNumber){
    				oldArray.get(channel).put(tenth, currentNumber);
    			}
			}
		}
//    	for(int i = 0; i < oldArray.length; i++){
//    		for(int j = 0; j < oldArray[i].length; j++){
//    			
//    			
//    			
//    		}
//    	}
    }
//    /**
//     *
//     */
//    public void collapseLightArray() {
//        lightTimeline.clear();
//        for(int i = 0; i < MusicPaneController.getInstance().getTime() * 10; i++) {
//            ArrayList<FCW> fcws = new ArrayList<>(5);
//            for(int chAddr = 0; chAddr < numChannels; chAddr++) {
//                //TODO double check algo
//                fcws.add(new FCW(chAddr, lightFCWColorMap.get(chAddr).get(i)));
//            }
//            lightTimeline.put(i, fcws);
//        }
//    }

//    private void collapseLightAndWaterMaps() {
//        
//        insertTimelineIterator(waterTimeline);
//        insertTimelineIterator(lightTimeline);
//        
//    }

//    private void insertTimelineIterator(SortedMap<Integer, ArrayList<FCW>> srcTimeline) {
//        srcTimeline.keySet().stream().forEach((i) -> {
//            srcTimeline.get(i).stream().forEach((f) -> {
//                insertIntoTimeline(timeline, i, f);
//            });
//        });
//    }
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
    
    private void startAndEndPoints(SortedMap<Integer, SortedMap<Integer, Integer>> oldArray){
    	lightTimeline.keySet().stream().forEach((i) -> {
            lightTimeline.get(i).stream().forEach((f) -> {
//                String name = FCWLib.getInstance().reverseLookupAddress(f);
//                String[] actions = FCWLib.getInstance().reverseLookupData(f);
                int data = f.getData();
                int tenthOfSec = i % 10;
                int secondsOnly = i /10; 
                double tenths = (double) tenthOfSec;
                double newTime = secondsOnly + (tenths / 10);
                int colAtTime = (int) (newTime * MusicPaneController.getInstance().getTimeFactor());
                if(colAtTime != 0){
                    colAtTime = colAtTime - 1;
                }
                if(data == 0){
                	data = -5;
                }
                SortedMap<Integer, Integer> newMap = new ConcurrentSkipListMap<>();
                newMap.put(i, f.getData());
                oldArray.put(f.getAddr(), newMap);//[f.getAddr()] = data;
            });
        });
    	
    	
    	
    }
    
    private int countUChannels(SortedMap<Integer, ArrayList<FCW>> srcTimeline){
    	HashSet<Integer> address = new HashSet<Integer>();
    	
    	for(ArrayList<FCW> a: srcTimeline.values()){
    		for(FCW f: a){
    			if(!address.contains(f.getAddr())){
    				address.add(f.getAddr());
    			}
    		}
    	}
    	
		return address.size();
    	
    }
//    private void populateLightFcwArray() {
//        //TODO populate LightFcwArray with LightTimeline data
//        
//        //Decompose LightTimeline into Channels
//        for(Integer i: timeline.keySet()) {
//            for(FCW f: timeline.get(i)) {
//                //For each channel,
//                if(!f.getIsWater()) {
//                    int start = i;
//                    int target = f.getAddr();
//                    Iterator<Entry<Integer, ArrayList<FCW>>> it = timeline.tailMap(i).entrySet().iterator();
//                    while(it.hasNext())
//                }
//            }
//        }
//        
//           
//            
//            //use setLightWithRange(start, end, color) to fill information
//            //
//    }
}
