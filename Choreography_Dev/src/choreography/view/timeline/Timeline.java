/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package choreography.view.timeline;

import choreography.model.Event;
import choreography.model.fcw.FCW;
import choreography.view.music.MusicPaneController;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.logging.Logger;



public class Timeline extends ArrayList<Event>{
    
    /**
    * 
    */
	public static final int OFF = -5;
   private static final long serialVersionUID = 7_242_109_851_591_362_314L;
   private static Timeline instance;
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
   private int time;    
   private int numChannels;
   private SortedMap<Integer, ArrayList<FCW>> timeline;
   private SortedMap<Integer, ArrayList<FCW>> waterTimeline;
   private SortedMap<Integer, ArrayList<FCW>> lightTimeline;
   private SortedMap<Integer, SortedMap<Integer, Integer>> gtfoArray;
   private int[] lightChannelAddresses;

    private Timeline() {
        timeline = new ConcurrentSkipListMap<>();
        waterTimeline = new ConcurrentSkipListMap<>();
        lightTimeline = new ConcurrentSkipListMap<>();
    }
    
    public SortedMap<Integer, SortedMap<Integer, Integer>> getGtfoMap() {
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
                    if(f.getAddr() != 85)
                        insertIntoTimeline(lightTimeline, i, f);
                }
            });
        });
        time = (int)(MusicPaneController.SONG_TIME * 10); //tenths of a second
        numChannels = countUChannels(lightTimeline);
//        lightFCWColorMap = new LinkedHashMap<>(numChannels);
        gtfoArray = new ConcurrentSkipListMap<>(); //new//new int[time][numChannels];
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
    
    /**
     *
     * @param channel
     * @param start
     * @param end
     * @param color
     */
    public void setLightFcwWithRange(SortedMap<Integer, Integer> channel, int start, int end, int color) {
        for(int i = start; i < end; i++) {
            channel.put(i, color);
        }
    }
    
    public void setLightFcwAtPoint(int point, FCW f) {
        
    }
    
    public void fillTheSpaces(SortedMap<Integer, SortedMap<Integer, Integer>> channelMap) {
        for(Integer channel: channelMap.keySet()) {
            int start;
            int end;
            int color;
            SortedMap<Integer, Integer> newMap = new ConcurrentSkipListMap<>();
            for(Integer tenth: channelMap.get(channel).keySet()) {
                if(channelMap.get(channel).get(tenth) != 0){
                    start = tenth;
                    color = channelMap.get(channel).get(tenth);
                    Iterator<Entry<Integer, Integer>> it = channelMap.get(channel).tailMap(start + 1).entrySet().iterator();
                    while(it.hasNext()) {
                        Entry<Integer, Integer> timeColor = it.next();
                        if(timeColor.getValue() == 0 && timeColor.getKey() != start) {
                            end = timeColor.getKey();
                            setLightFcwWithRange(newMap, start, end, color);
                            break;
                        }
                        else if(timeColor.getValue() != color) {
                            end = timeColor.getKey() - 1;
                            setLightFcwWithRange(newMap, start, end, color);
                            break;
                        }
                    }
                }
                channelMap.get(channel).putAll(newMap);
            }
        }
    }
//    /**

//    private void collapseLightAndWaterMaps() {
    private void insertIntoTimeline(SortedMap<Integer, ArrayList<FCW>> srcTimeline, Integer i, FCW f){
        if(srcTimeline.containsKey(i)) {
            srcTimeline.get(i).add(f);
        }
        else {
            ArrayList<FCW> newFcw = new ArrayList(1);
            newFcw.add(f);
            srcTimeline.put(i, newFcw);
        }
    }
    private void startAndEndPoints(SortedMap<Integer, SortedMap<Integer, Integer>> channelArray) {
        
        for(Integer timeIndex: lightTimeline.keySet()) {
            SortedMap<Integer, Integer> newMap = new ConcurrentSkipListMap<>();
            int start = 0;
            for(FCW f: lightTimeline.get(timeIndex)) {
//                String name = FCWLib.getInstance().reverseLookupAddress(f);
//                String[] actions = FCWLib.getInstance().reverseLookupData(f);
                int color = f.getData();
                int tenthOfSec = timeIndex % 10;
                int secondsOnly = timeIndex /10; 
                double tenths = (double) tenthOfSec;
                double newTime = secondsOnly + (tenths / 10);
                int colAtTime = (int) (newTime * MusicPaneController.getInstance().getTimeFactor());
                if(colAtTime != 0){
                    colAtTime = colAtTime - 1;
                }
                if(color == 0) {
//                    setLightFcwWithRange(newMap, start, timeIndex, f);
                }
                if(channelArray.containsKey(f.getAddr())) {
                    channelArray.get(f.getAddr()).put(timeIndex, color);
                    start = timeIndex;
                } else {
                    newMap.put(timeIndex, color);
                    channelArray.putIfAbsent(f.getAddr(), newMap);  
                    start = timeIndex;
                }
            }
            //[f.getAddr()] = data;
        }
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
        
}
