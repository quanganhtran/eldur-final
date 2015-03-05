/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eldur;

public class DefensiveSkill extends Skill{

    private String skillName;
    private String buffType;
    private int buff;
    private int cooldown;
    private int cooldownCounter;

    public DefensiveSkill(String n, String buff, int b, int cd) {
        this.skillName = n;
        this.buffType = buff;
        this.buff = b;
        this.cooldown = cd;
        this.cooldownCounter = 0;
    }

    @Override
    public boolean execute(Character c, Enemy e) {
        if (cooldownCounter == 0) {
            switch (buffType) {
                case "hp":
                    if (c.getHp() + buff < c.getHpMax()) {
                        c.setHp(c.getHp() + buff);
                    } else {
                        c.setHp(c.getHpMax());
                    }
                    break;
                case "critRate":
                    c.setCritRate(c.getCritRate() + buff);
                    break;
                case "evasion":
                    c.setEvasion(c.getEvasion() + buff);
                    break;
            }
            return true;
        } else {
            System.out.println("This skill is not yet ready.");
            return false;
        }
    }

}
