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
public class IdScroll extends Item{
    protected String name;
    private Sword frame;
    private int scrollTier;
    
    public IdScroll(String sN, Sword sw) {
        this.name = sN;
        this.frame = sw;
        this.scrollTier = sw.getTier();
    }

    public int getScrollTier() {
        return scrollTier;
    }

    public Sword getFrame() {
        return frame;
    }

    @Override
    public String getName() {
        return "[i] " + name;
    }
}
