/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Comparator;

/**
 *
 * @author luisr
 */
public class ArestaComparator implements Comparator<Aresta> {

    @Override
    public int compare(Aresta o1, Aresta o2) {
        if (o1.getValor() > o2.getValor()) {
            return 1;
        }
        if (o1.getValor() < o2.getValor()) {
            return -1;
        }
        return 0;
    }

}
