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
    private int tier;
    private int atk;
    private int socket;
    public String refinement;
    
    public Sword(Recipe rp) {
        this.name = rp.getWeaponName();
        this.tier = rp.getTier();
        this.atk = rp.getAtk();
        this.socket = 1;
    }
    public String getName() {
        return name;
    }
    public int getAtk() {
        return atk;
    }
    public int getSocket() {
        return socket;
    }
    public void useSocket() {
        socket--;
    }
    public void addSocketName(String s) {
        name += " " + s;
    }
}
