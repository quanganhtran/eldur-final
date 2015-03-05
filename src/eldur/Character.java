/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eldur;

import java.util.Random;

/**
 *
 * @author trand
 */
public class Character {
    private Random rng = new Random();
    private GameData gameData;
    private int level;
    private int hpMax0, hpMax, hp;
    private int atk0, atk;
    // Attributes: Personal - Buff - Final
    private int defense0, critRate0, critFactor, evasion0, block, reflect, resist;
    private int defBuff, criBuff, evaBuff;
    private int defense, critRate, evasion;
    // Outgoing statuses
    private int stunOut, bleedOut, freezeOut, poisonOut;
    // Debuff received
    private int poison, poisonDamage, poisonOnAtk, poisonOnRef, stun, freeze, freezeOnEva, freezeOnRes, freezeOnCri, fear, fearOnDef, fearOnBlo;
    // Equipment
    private Sword sword;
    
    public Character(GameData gD) {
        this.gameData = gD;    
        this.hpMax0 = 500;
        this.atk0 = 10; // prone to changes
        // Real stats for battle
        this.hpMax = this.hpMax0;
        this.hp = this.hpMax;
        this.atk = this.atk0;
        this.defense0 = 0;
        this.defBuff = 0;
        this.critRate0 = 20; // prone to changes
        this.criBuff = 0;
        this.evasion0 = 0;
        this.evaBuff = 0;
        this.critFactor = 3; // prone to changes
        unsheathe(gameData.equippedSword);
    }
    
    public void unsheathe(Sword sw) {
        this.atk = this.atk0 + sw.getAtk();
        this.defense0 = sw.getDefense();
        this.critRate0 = sw.getCritRate();
        this.evasion0 = sw.getEvasion();
    }
    
    public String attack(Enemy en) {
        int dmg = atk;
        if (rng.nextInt(100) < critRate0) {
            dmg *= critFactor;
            System.out.println("A critical hit!");
        }
        String aOutcome = en.receiveDamage(dmg);
        return aOutcome;
    }
    
    public String receiveDamage(int damage) {
        setHp(getHp() - damage); // Another way of presentation
        String outcome = "";
        if (this.hp <= 0) {
            //System.out.println("You have successfully defeated " + this.name);
            outcome = "pLose";
        }
        return outcome;
    }

    public int getHpMax() {
        return hpMax;
    }

    public void setHpMax(int hpMax) {
        this.hpMax = hpMax;
    }

    /**
     * @return the hp
     */
    public int getHp() {
        return hp;
    }

    /**
     * @param hp the hp to set
     */
    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAtk0() {
        return atk0;
    }

    public void setAtk0(int atk0) {
        this.atk0 = atk0;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getDefense0() {
        return defense0;
    }

    public void setDefense0(int defense0) {
        this.defense0 = defense0;
    }

    public int getDefBuff() {
        return defBuff;
    }

    public void setDefBuff(int defBuff) {
        this.defBuff = defBuff;
    }

    public int getCritRate0() {
        return critRate0;
    }

    public void setCritRate0(int critRate0) {
        this.critRate0 = critRate0;
    }

    public int getEvasion0() {
        return evasion0;
    }

    public void setEvasion0(int evasion0) {
        this.evasion0 = evasion0;
    }

    public int getCriBuff() {
        return criBuff;
    }

    public void setCriBuff(int criBuff) {
        this.criBuff = criBuff;
    }

    public int getEvaBuff() {
        return evaBuff;
    }

    public void setEvaBuff(int evaBuff) {
        this.evaBuff = evaBuff;
    }
    
    public boolean useSkill(Skill s, Enemy e) {
        return gameData.equippedSword.activateSkill(s, this, e);
    }
}
