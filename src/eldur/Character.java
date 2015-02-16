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
    private int hp;
    private int atk;
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
        this.hp = 10000;
        this.atk = 10; // prone to changes
        this.defense = 0;
        this.critRate = 20; // prone to changes
        this.evasion = 0;
        this.critFactor = 3; // prone to changes
    }
    
    public void equipSword(Sword sw) {
        this.atk = sw.getAtk();
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
        String aOutcome = en.isAttacked(dmg);
        return aOutcome;
    }
    
    public void receiveDamage(int damage) {
        setHp(getHp() - damage);
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}
