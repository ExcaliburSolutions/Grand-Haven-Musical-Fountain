/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lib.cannons;

import lib.CannonEnum;
import lib.Module;
import lib.ModuleGroup;

/**
 *
 * @author elementsking
 */
public class CannonFactory {
    
    public CannonFactory() {
       
    }
    
    public static Cannon createCannon(CannonEnum e) {
        switch(e) {
            case RING1:
                return new Ring(1);
            case RING2:
                return new Ring(2);
            case RING3:
                return new Ring(3);
            case RING4:
                return new Ring(4);
            case RING5:
                return new Ring(5);
            case MULTI:
                return new Multi(0);
            case CANDELABRA:
                return new Candelabra(0);
            case SWEEP:
                return new Sweep(0);
            case FTCURT:
                return new FtCurt(0);
            case BKCURT:
                return new BkCurt(0);
            case BAZOOKA:
                return new Bazooka(0);
            case SPOUT:
                return new Spout(0);
            case PEACOCK:
                return new Peacock(0);
            default:
                throw new IllegalArgumentException("e must be a Cannon subtype");
        }
    }
    
    public static Module createModule(int number){
        return new Module(number, createCannon(CannonEnum.RING1), 
                createCannon(CannonEnum.RING2), createCannon(CannonEnum.RING3), 
                createCannon(CannonEnum.RING4), createCannon(CannonEnum.RING5), 
                createCannon(CannonEnum.MULTI), createCannon(CannonEnum.CANDELABRA), 
                createCannon(CannonEnum.SWEEP));
    }
    
    public static ModuleGroup createModuleGroup(String aB) {
        switch(aB){
            case "A":
               return new ModuleGroup(new Module[]{createModule(1),  createModule(3),  
                   createModule(5), createModule(7)});
            case "B":
                return new ModuleGroup(new Module[]{createModule(2), createModule(4), createModule(6)});
            default:
                throw new IllegalArgumentException("aB must be A or B");
        }
    }
    
    
}
