/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eldur;

/**
 *
 * @author trand
 */
public class Gemstone {
    private String name;
    private int type;
    public String refinementPrefix;
    // Refinements/Attributes
    public int attackPercent;
    public int defense;
    public int critRate;
    public int evasion;
    
    public Gemstone(String n, int t) {
        name = n;
        type = t;
    }
    
    public Gemstone(String r, int aP, int d, int cR, int e) {
        this.refinementPrefix = r;
        this.attackPercent = aP;
        this.defense = d;
        this.critRate = cR;
        this.evasion = e;
    }
}
