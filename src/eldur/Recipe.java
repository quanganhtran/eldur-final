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
public class Recipe {
    private String recipeName;
    private String weaponName;
    private int costIron;
    private int costCopper;
    private int atk;
    private int spd;
    
    public Recipe(String rN, String wN, int cIr, int cCp, int at, int sp) {
        this.recipeName = rN;
        this.weaponName = wN;
        this.costIron = cIr;
        this.costCopper = cCp;
        this.atk = at;
        this.spd = sp;
    }
    
    public String getWeaponName() {
        return weaponName;
    }
    public int getCostIron() {
        return costIron;
    }
    public int getCostCopper() {
        return costCopper;
    }
    public int getAtk() {
        return atk;
    }
    public int getSpd() {
        return spd;
    }
}
