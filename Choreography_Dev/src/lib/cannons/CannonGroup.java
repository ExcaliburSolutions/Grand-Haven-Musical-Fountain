/**
 * 
 */
package lib.cannons;

import java.util.ArrayList;

/**
 * @author madridf
 * @param <T>
 *
 */
public class CannonGroup<T extends Cannon> {
    private ArrayList<T> cannons;
    
    public CannonGroup(){
        cannons = new ArrayList<>();
    }
    
    public void add(T c){
        cannons.add(c);
    }
    
    public ArrayList<T> getCannons(){
        return cannons;
    }
}
