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

    private Skill(String buff, int b, int cd) {
        this.buffType = buff;
        this.buff = b;
        this.cooldown = cd;
        this.cooldownCounter = 0;
    }

    public Skill(String n, String buff, int b, int cd) {
        this(buff, b, cd);
        this.skillName = n;
//        this.buffType = buff;
//        this.buff = b;
//        this.cooldown = cd;
//        this.cooldownCounter = 0;
    }
    
    public static Skill createFromTag(String shortname) {
        switch (shortname) {
            case "hp1":
                return new Skill("Regeneration Lv1", "hp", 200, 3);
            case "hp2":
                return new Skill("Regeneration Lv2", "hp", 300, 2);
            case "hp3":
                return new Skill("Regeneration Lv3", "hp", 500, 3);
            case "def1":
                return new Skill("Fortification Lv1", "hp", 10, 2);
            case "def2":
                return new Skill("Fortification Lv2", "hp", 20, 2);
            case "def3":
                return new Skill("Fortification Lv3", "hp", 35, 2);
            case "cri1":
                return new Skill("Focus Lv1", "cri", 5, 3);
            case "cri2":
                return new Skill("Focus Lv2", "cri", 9, 3);
            case "cri3":
                return new Skill("Focus Lv3", "cri", 7, 2);
            case "eva1":
                return new Skill("Shadow Blend Lv1", "eva", 5, 4);
            case "eva2":
                return new Skill("Shadow Blend Lv2", "eva", 5, 3);
            case "eva3":
                return new Skill("Shadow Blend Lv3", "eva", 6, 3);
            case "ber1":
                return new Skill("Injustice Lv1", "berserk", 100, 4);
            case "ber2":
                return new Skill("Injustice Lv1", "berserk", 100, 3);
            case "ber3":
                return new Skill("Injustice Lv1", "berserk", 200, 4);
            default:
                return null;
        }
    }
    
//    public Skill(String shortname) {
//        switch (shortname) {
//            case "hp1":
//                this("Rejuvenation 1", "hp", 200, 3);
//        }
//    }

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
                case "def":
                    c.setDefBuff(c.getDefBuff() + buff);
                    System.out.println("Defense increased!");
                    break;
                case "cri":
                    c.setCriBuff(c.getCriBuff() + buff);
                    System.out.println("Critical Rate increased!");
                    break;
                case "eva":
                    c.setEvaBuff(c.getEvaBuff() + buff);
                    System.out.println("Evasion increased!");
                    break;
                case "berserk":
                    e.receiveDamage(c.getAtk() + this.buff * (c.getHpMax() - c.getHp()) /100);
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

    /**
     * @return the skillName
     */
    public String getSkillName() {
        return skillName;
    }

    /**
     * @param skillName the skillName to set
     */
    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }
}
