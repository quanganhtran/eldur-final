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
public class Sword {
    private String name;
    private int atk;
    private int spd;
    
    public Sword(Recipe rp) {
        this.name = rp.getWeaponName();
        this.atk = rp.getAtk();
        this.spd = rp.getSpd();
    }
    public String getName() {
        return name;
    }
    public int getAtk() {
        return atk;
    }
    public int getSpd() {
        return spd;
    }
}
