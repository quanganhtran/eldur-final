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
public class Character {
    private GameData gameData;
    private int hp;
    private int atk;
    // Attributes
    private int defense, critRate, critDamage, evasion, block, reflect, resist;
    // Outgoing statuses
    private int stunOut, bleedOut, freezeOut, poisonOut;
    // Debuff received
    private int poison, poisonDamage, poisonOnAtk, poisonOnRef, stun, freeze, freezeOnEva, freezeOnRes, freezeOnCri, fear, fearOnDef, fearOnBlo;
    // Equipment
    private Sword sword;
    
    public Character(GameData gD) {
        this.gameData = gD;        
        this.hp = 100000;
        this.atk = 0;
        this.defense = 0;
        this.critRate = 0;
        this.evasion = 0;
    }
    
    public void equipSword(Sword sw) {
        this.atk = sw.getAtk();
        this.defense = sw.getDefense();
        this.critRate = sw.getCritRate();
        this.evasion = sw.getEvasion();
    }
}
