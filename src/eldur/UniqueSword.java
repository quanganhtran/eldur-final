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
public class UniqueSword extends Sword {

    //private 
            
    public UniqueSword(String name, int tier, String rarity, int atk, int socket, Sword sw) {
        super(name, tier, rarity, atk, socket);
        this.atkBoost = sw.atkBoost;
        this.setAtk(this.atk + this.atkBoost/100);
        this.setDefense(sw.getDefense());
        this.setCritRate(sw.getCritRate());
        this.setEvasion(sw.getEvasion());
    }
    
}
