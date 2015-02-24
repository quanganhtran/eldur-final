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
    protected int atk;
    private int socket;
    public String refinement;
    // Secondary attributes
    public int atkBoost;
    private int defense;
    private int critRate;
    private int evasion;

    public Sword(String name, int tier, String rarity, int atk, int socket) {
        this.name = name;
        this.tier = tier;
        this.rarity = rarity;
        this.atk = atk;
        this.socket = socket;
    }

    public Sword(Recipe rp) {
        this.name = rp.getWeaponName();
        this.tier = rp.getTier();
        this.rarity = rp.getRarity();
        this.atk = rp.getAtk();
        this.socket = 1;
    }

    public String getName() {
        return name;
    }

    public int getTier() {
        return tier;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public int getAtk() {
        return atk;
    }
    
    public void setAtk(int atk) {
        this.atk = atk;
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
        this.atkBoost = g.attackPercent;
        this.setDefense(g.defense);
        this.setCritRate(g.critRate);
        this.setEvasion(g.evasion);
        if (rarity.equals("Common")) {
            name += " " + g.refinementPrefix;
            this.setRarity("Rare");
        }
    }
//    public void addSocketToName(String s) {
//        name += " " + s;
//    }

}
