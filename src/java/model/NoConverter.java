/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.thoughtworks.xstream.converters.SingleValueConverter;

/**
 *
 * @author LuísRogério
 */
public class NoConverter implements SingleValueConverter {

    public String toString(Object o) {
        return ((No) o).getId();
    }

    public Object fromString(String string) {
        return new No(string);
    }

    public boolean canConvert(Class type) {
        return type.equals(No.class);
    }
}
