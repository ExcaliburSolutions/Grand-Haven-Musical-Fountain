/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package choreography.io;

/**
 *
 * @author elementsking
 */
public class FilePayload {
    private String name;
    private byte[] payload;
    
    public FilePayload(String name, byte[] payload) {
        this.name = name;
        this.payload = payload;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the payload
     */
    public byte[] getPayload() {
        return payload;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param payload the payload to set
     */
    public void setPayload(byte[] payload) {
        this.payload = payload;
    }
}
