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
    private int tier;
    private int atk;
    private String mainMat;
    private String supportMat;
    
    public Recipe(String rN, String wN, int t, int at, String mM, String sM) {
        this.recipeName = rN;
        this.weaponName = wN;
        this.tier = t;
        this.atk = at;
        this.mainMat = mM;
        this.supportMat = sM;
    }
    
    public String getWeaponName() {
        return weaponName;
    }
    public int getTier() {
        return tier;
    }
    public int getAtk() {
        return atk;
    }
    public String getMainMat() {
        return mainMat;
    }
    public String getSupportMat() {
        return supportMat;
    }
}
