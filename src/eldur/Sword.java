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
    private String rarity;
    private int atk;
    private int socket;
    public String refinement;
    
    public Sword(String n, int t, String r, int a, int s, String ref) {
        name = n;
        tier = t;
        rarity = r;
        atk = a;
        socket = s;
        refinement = ref;
    }
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
    public void addSocketToName(String s) {
        name += " " + s;
    }
}
