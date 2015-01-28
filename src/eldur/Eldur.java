/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eldur;

import java.util.ArrayList;

/**
 *
 * @author trand
 */
public class Eldur {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Statistics


        
    }
    
    //public int cash;
    public int iron;
    public int copper;
    public ArrayList<Sword> inventory = new ArrayList<Sword>();
    public void craft(Recipe someRp) {
        iron -= someRp.getCostIron();
        copper -= someRp.getCostCopper();
        inventory.add(new Sword(someRp));
    }
}
