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
    // Attributes
    private int defense, critRate, critFactor, evasion, block, reflect, resist;
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
        this.defense = 0;
        this.critRate = 20; // prone to changes
        this.evasion = 0;
        this.critFactor = 3; // prone to changes
        unsheathe(gameData.equippedSword);
    }
    
    public void unsheathe(Sword sw) {
        this.atk = this.atk0 + sw.getAtk();
        this.defense = sw.getDefense();
        this.critRate = sw.getCritRate();
        this.evasion = sw.getEvasion();
    }
    
    public String attack(Enemy en) {
        int dmg = atk;
        if (rng.nextInt(100) < critRate) {
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
}
