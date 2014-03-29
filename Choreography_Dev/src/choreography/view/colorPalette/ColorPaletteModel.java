/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2014 Oracle and/or its affiliates. All rights reserved.
 *
 * Oracle and Java are registered trademarks of Oracle and/or its affiliates.
 * Other names may be trademarks of their respective owners.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common
 * Development and Distribution License("CDDL") (collectively, the
 * "License"). You may not use this file except in compliance with the
 * License. You can obtain a copy of the License at
 * http://www.netbeans.org/cddl-gplv2.html
 * or nbbuild/licenses/CDDL-GPL-2-CP. See the License for the
 * specific language governing permissions and limitations under the
 * License.  When distributing the software, include this License Header
 * Notice in each file and include the License file at
 * nbbuild/licenses/CDDL-GPL-2-CP.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the GPL Version 2 section of the License file that
 * accompanied this code. If applicable, add the following below the
 * License Header, with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * If you wish your version of this file to be governed by only the CDDL
 * or only the GPL Version 2, indicate your decision by adding
 * "[Contributor] elects to include this software in this distribution
 * under the [CDDL or GPL Version 2] license." If you do not indicate a
 * single choice of license, a recipient has the option to distribute
 * your version of this file under either the CDDL, the GPL Version 2 or
 * to extend the choice of license to its licensees as provided above.
 * However, if you add GPL Version 2 code and therefore, elected the GPL
 * Version 2 license, then the option applies only if the new code is
 * made subject to such option by the copyright holder.
 *
 * Contributor(s):
 *
 * Portions Copyrighted 2014 Sun Microsystems, Inc.
 */

package choreography.view.colorPalette;

import choreography.io.MapLib;
import java.io.File;
import java.util.HashMap;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 *
 * @author elementsking
 */
public class ColorPaletteModel {
    
    private static ColorPaletteModel instance;
    private boolean classicColors;
    private Color[] colors;
    private int availableColors;
    private int selectedIndex;
    private HashMap<Integer, Integer> classicMap;
    
    public static ColorPaletteModel getInstance() {
        if(instance == null)
            instance = new ColorPaletteModel();
        return instance;
    }

    /**
     * @return the classicColors
     */
    public boolean isClassicColors() {
        return classicColors;
    }

    /**
     * @param aClassicColors the classicColors to set
     */
    public void setClassicColors(boolean aClassicColors) {
        classicColors = aClassicColors;
        colors = new Color[32];
        MapLib.openMap(new File("src/choreography/model/color/legacy.map"));
        ColorPaletteController.getInstance().rePaint();
    }
    
    public ColorPaletteModel() {
        colors = new Color[32];
        availableColors = 16;
        
//        colors[0] = Color.web(ColorPaletteEnum.RED.getColor()); // red
//        colors[1] = Color.web(ColorPaletteEnum.ORANGE.getColor()); // orange
//        colors[2] = Color.web(ColorPaletteEnum.YELLOW.getColor()); // yellow
//        colors[3] = Color.web(ColorPaletteEnum.GREEN.getColor()); // green
//        colors[4] = Color.web(ColorPaletteEnum.BLUE.getColor()); // blue
//        colors[5] = Color.web(ColorPaletteEnum.VIOLET.getColor()); // violet
//        colors[6] = Color.web(ColorPaletteEnum.LIGHTRED.getColor()); // lightRed
//        colors[7] = Color.web(ColorPaletteEnum.LIGHTORANGE.getColor()); // lightOrange
//        colors[8] = Color.web(ColorPaletteEnum.LIGHTYELLOW.getColor()); // lightYellow
//        colors[9] = Color.web(ColorPaletteEnum.LIGHTGREEN.getColor()); // lightGreen
//        colors[10] = Color.web(ColorPaletteEnum.LIGHTBLUE.getColor()); // lightBlue
//        colors[11] = Color.web(ColorPaletteEnum.LIGHTVIOLET.getColor()); // lightViolet
//        colors[12] = Color.web(ColorPaletteEnum.MAGENTA.getColor()); // magenta
//        colors[13] = Color.web(ColorPaletteEnum.CYAN.getColor()); // cyan
//        colors[14] = Color.web(ColorPaletteEnum.OFF.getColor()); // black/Off
//        colors[15] = Color.web(ColorPaletteEnum.WHITE.getColor()); // white/On
               	
        
        }
    

    /**
     * @return the colors
     */
    public Color[] getColors() {
        return colors;
    }
    

    /**
     * @return the availableColors
     */
    public int getAvailableColors() {
        return availableColors;
    }

      /**
     * @return the selectedIndex
     */
    public int getSelectedIndex() {
        return selectedIndex;
    }
    
    
    public void setColor(Color newColor, int index){
        this.colors[index] = newColor;
        this.colors[availableColors] = newColor;
        availableColors++;
    }		      
       
    /**
     * @param newColor
     * @param index
     * @return 
     */    
    public boolean changeColor(Color newColor, int index) {
    	if(index > 15) {
            return true;
    	}
    	return false;  		 	
    }
     
    /**
     * @param availableColors the availableColors to set
     */
    public void setAvailableColors(int availableColors) {
        this.availableColors = availableColors;
         }

       /**
     * @param selectedIndex the selectedIndex to set
     */
    public void setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
    }
    
    public Paint getColor(int i){
        if(classicColors) {
            classicMap = new HashMap<>();
            classicMap.put(32, 7);
            //TODO set up classicMap
        }
        if(i == 0)
            return colors[0];
        else
            return colors[i - 1];
    }

    public void setColors(Color[] parseMap) {
        this.colors = parseMap;
        ColorPaletteController.getInstance().rePaint();
    }

}
