/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eldur;

public class Skill {

    private String skillName;
    private String buffType;
    private int buff;
    private int cooldown;
    private int cooldownCounter;

    public Skill(String n, String buff, int b, int cd) {
        this.skillName = n;
        this.buffType = buff;
        this.buff = b;
        this.cooldown = cd;
        this.cooldownCounter = 0;
    }

    //@Override
    public boolean execute(Character c, Enemy e) {
        if (cooldownCounter == 0) {
            switch (buffType) {
                case "hp":
                    if (c.getHp() + buff < c.getHpMax()) {
                        c.setHp(c.getHp() + buff);
                    } else {
                        c.setHp(c.getHpMax());
                    }
                    System.out.println("Health restored!");
                    break;
                case "critRate":
                    c.setCriBuff(c.getCriBuff() + buff);
                    System.out.println("Critical Rate increased!");
                    break;
                case "evasion":
                    c.setEvaBuff(c.getEvaBuff() + buff);
                    System.out.println("Evasion increased!");
                    break;
                case "berserk":
                    e.receiveDamage(c.getAtk()+this.buff*(c.getHpMax()-c.getHp()));
                    System.out.println("Enemy received damage from the skill!");
                    break;
            }
            return true;
        } else {
            System.out.println("This skill is not yet ready.");
            return false;
        }
    }

    public int getCooldown() {
        return cooldown;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    public int getCooldownCounter() {
        return cooldownCounter;
    }

    public void setCooldownCounter(int cooldownCounter) {
        this.cooldownCounter = cooldownCounter;
    }

}
