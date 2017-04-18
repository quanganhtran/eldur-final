/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eldur;

import java.util.Comparator;

/**
 *
 * @author Anh
 */
public class ItemComparator implements Comparator<Item>{

    @Override
    public int compare(Item t, Item t1) {
        if ((t instanceof AscensionScroll) && !(t1 instanceof AscensionScroll)) {
            return 1;
        } else {
            return t.getName().compareTo(t1.getName());
        }
    }
    
}
