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
    // Secondary attributes
    private int defense;
    private int critRate;
    private int evasion;
    
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

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getCritRate() {
        return critRate;
    }

    public void setCritRate(int critRate) {
        this.critRate = critRate;
    }

    public int getEvasion() {
        return evasion;
    }

    public void setEvasion(int evasion) {
        this.evasion = evasion;
    }
    
    public void insertSocket(Gemstone g) {
        this.atk += this.atk * g.attackPercent / 100;
        this.setDefense(g.defense);
        this.setCritRate(g.critRate);
        this.setEvasion(g.evasion);
        name += " " + g.refinementPrefix;
    }
    public void addSocketToName(String s) {
        name += " " + s;
    }
}
