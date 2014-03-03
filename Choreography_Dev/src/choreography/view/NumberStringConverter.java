/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package choreography.view;

import javafx.util.StringConverter;

/**
 *
 * @author elementsking
 */
public class NumberStringConverter extends StringConverter<Double> {
    
    @Override
    public String toString(Double t) {
        return Double.toString(t);
    }

    @Override
    public Double fromString(String string) {
        return Double.parseDouble(string);
    }
    
}
