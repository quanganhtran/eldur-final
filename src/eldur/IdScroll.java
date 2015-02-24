/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eldur;

/**
 *
 * @author Anh
 */
public class IdScroll {
    private String scrollName;
    private Sword sword;
    private int scrollTier;
    
    public IdScroll(String sN, Sword sw) {
        this.scrollName = sN;
        this.sword = sw;
        this.scrollTier = sw.getTier();
    }

    public int getScrollTier() {
        return scrollTier;
    }

    public Sword getSword() {
        return sword;
    }
}
