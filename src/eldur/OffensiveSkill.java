/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eldur;

/**
 *
 * @author Anh
 */
public class OffensiveSkill {

    private String skillName;
    private String debuffType;
    private int debuff;
    private int cooldown;
    private int cooldownCounter;

    public OffensiveSkill(String n, String debuff, int db, int cd) {
        this.skillName = n;
        this.debuffType = debuff;
        this.debuff = db;
        this.cooldown = cd;
        this.cooldownCounter = 0;
    }

    public boolean execute(Character c, Enemy e) {
        if (cooldownCounter == 0) {
            switch (debuffType) {
                case "berserk":
                    e.receiveDamage(c.getAtk()+this.debuff*(c.getHpMax()-c.getHp()));
                    break;
            }
            return true;
        } else {
            System.out.println("This skill is not yet ready.");
            return false;
        }
    }
    
}
