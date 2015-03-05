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
public class Gemstone extends Item {

    private String name;
    private int type;
    public String refinementPrefix;
    private int stock;
    // Refinements/Attributes
    public int attackUpgrade;
    public int defense;
    public int critRate;
    public int evasion;

    public Gemstone(String n, int t) {
        name = n;
    }

    public Gemstone(String name, String r, int aU, int d, int cR, int e) {
        this.name = name;
        this.refinementPrefix = r;
        this.attackUpgrade = aU;
        this.defense = d;
        this.critRate = cR;
        this.evasion = e;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void consumed() {
        this.stock--;
    }

    @Override
    public String getName() {
        return "<g> " + name;
    }
}
