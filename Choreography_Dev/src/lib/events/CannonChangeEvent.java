/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lib.events;

import lib.cannons.Cannon;

/**
 *
 * @author elementsking
 */
public class CannonChangeEvent {
    private Cannon cannon;
    private int level;

    public CannonChangeEvent(Cannon c, int newValue) {
        this.cannon = c;
        this.level = newValue;
    }

    /**
     * @return the cannon
     */
    public Cannon getCannon() {
        return cannon;
    }

    /**
     * @return the level
     */
    public int getLevel() {
        return level;
    }
    
}
