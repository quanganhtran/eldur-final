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
    private int vitality;
    private int atkUpgrade;
    private int defense;
    private int critRate;
    private int evasion;
    //private int atkI;
//    private int defI;
//    private int critRateI;
//    private int evasionI;
    // Skills
    private Skill skill;
    

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
        this.skill = new Skill("Rejuvenation","hp",200,3);
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
    
    public void stackDefense(int defense) {
        this.defense += defense;
    }

    public int getCritRate() {
        return critRate;
    }

    public void setCritRate(int critRate) {
        this.critRate = critRate;
    }

    public void stackCritRate(int critRate) {
        this.critRate += critRate;
    }

    public int getEvasion() {
        return evasion;
    }

    public void setEvasion(int evasion) {
        this.evasion = evasion;
    }

    public void stackEvasion(int evasion) {
        this.evasion += evasion;
    }

    public void insertSocket(Gemstone g) {
        this.atkUpgrade += g.attackUpgrade;
        this.atk += this.atk * g.attackUpgrade / 100;
        this.stackDefense(g.defense);
        this.stackCritRate(g.critRate);
        this.stackEvasion(g.evasion);
        if (rarity.equals("Common")) {
            name += " " + g.refinementPrefix;
            this.setRarity("Rare");
        }
    }
//    public void addSocketToName(String s) {
//        name += " " + s;
//    }

    public void transform(Sword fromSw) {
//        this.atkPercent = fromSw.atkPercent;
//        this.setAtk(this.getAtk() + this.getAtk() * this.atkPercent / 100);
//        this.setDefense(fromSw.getDefense());
//        this.setCritRate(fromSw.getCritRate());
//        this.setEvasion(fromSw.getEvasion());
        this.name = fromSw.name;
        this.tier = fromSw.tier;
        this.rarity = fromSw.rarity;
        this.atk = fromSw.atk + fromSw.atk * this.atkUpgrade/100;
        //this.defense = fromSw.defense;
        //this.
        this.socket = fromSw.socket;
    }
    
    public boolean activateSkill(Skill s, Character c, Enemy e) {
        if (this.skill != null) {
            return this.skill.execute(c, e);
        } else {
            System.out.println("This sword does not have any skill.");
            return false;
        }
    }

//    public int getDefI() {
//        return defI;
//    }
//
//    public void setDefI(int defI) {
//        this.defI = defI;
//    }
//
//    public int getCritRateI() {
//        return critRateI;
//    }
//
//    public void setCritRateI(int critRateI) {
//        this.critRateI = critRateI;
//    }
//
//    public int getEvasionI() {
//        return evasionI;
//    }
//
//    public void setEvasionI(int evasionI) {
//        this.evasionI = evasionI;
//    }

    public Skill getSkill() {
        return skill;
    }

}
