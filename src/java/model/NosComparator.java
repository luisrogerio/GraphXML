package model;

import java.util.Comparator;

/**
 *
 * @author Gabriel
 */
public class NosComparator implements Comparator<No> {

    @Override
    public int compare(No no1, No no2) {
        return no1.getId().compareToIgnoreCase(no2.getId());
    }
}
