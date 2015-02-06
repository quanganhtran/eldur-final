/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eldur;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Anh
 */
public class GameData {
    public int cash;
    public int iron;
    public int copper;
    public HashMap<String,Recipe> recipes = new HashMap<>();
    
    public void initialize() {
        // Statistics
        iron = 10;
        copper = 10;
        // Game Data
        recipes.put("iron", new Recipe("Iron Sword", "Iron Sword", 10, 1, 20, 5));
        recipes.put("copper", new Recipe("Copper Sword", "Copper Sword", 1, 10, 15, 10));
    }
    
    public ArrayList<Sword> inventory = new ArrayList<>();
    public ArrayList<String> inventoryViewer = new ArrayList<>();
    
    public void craft(Recipe someRp) {
        iron -= someRp.getCostIron();
        copper -= someRp.getCostCopper();
        Sword newSword = new Sword(someRp);
        inventory.add(newSword);
        inventoryViewer.add(newSword.getName() + " " + newSword.getAtk() + " " + newSword.getSpd());
        System.out.println("Sword crafted: "+newSword.getName() + " " + newSword.getAtk() + " " + newSword.getSpd());
    }
}
