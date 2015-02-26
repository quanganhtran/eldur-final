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
public class Enemy {

    private String name;
    private int hp;
    private int atk;

    public Enemy(String name, int hp, int atk) {
        this.name = name;
        this.hp = hp;
        this.atk = atk;
    }
    
    public Enemy(Enemy e) {
        this.name = e.getName();
        this.hp = e.getHp();
        this.atk = e.getAtk();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public String attack(Character player) {
        String aOutcome = player.receiveDamage(atk);
        return aOutcome;
    }

    public String receiveDamage(int damage) {
        this.hp -= damage;
        String outcome = "";
        if (this.hp <= 0) {
            //System.out.println("You have successfully defeated " + this.name);
            outcome = "pWin";
        }
        return outcome;
    }
}
