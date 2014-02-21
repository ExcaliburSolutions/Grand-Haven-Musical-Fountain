/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package choreography.model.fcw;

/**
 *
 * @author elementsking
 */
public class FCW {
    private int addr;
    private int data;

    /**
     *
     * @param addr the value of addr
     * @param data the value of data
     */
    public FCW(int addr, int data){
        this.addr = addr;
        this.data = data;
    }

    /**
     * @return the addr
     */
    public int getAddr() {
        return addr;
    }

    /**
     * @param addr the addr to set
     */
    public void setAddr(int addr) {
        this.addr = addr;
    }

    /**
     * @return the data
     */
    public int getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(int data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return String.format("%10s", addr + "-" + data);
    }
    
    
}
