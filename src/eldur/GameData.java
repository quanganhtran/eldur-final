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
    // Materials: copper, iron, silver, gold, platinum, mithril, orichalcum, adamantite.
    public int copper,iron;
    public HashMap<String,Integer> materials = new HashMap<>();
    public HashMap<String,Recipe> recipes = new HashMap<>();
    

    public void initialize() {
        // Statistics
        copper = 100;
        iron = 100;
        materials.put("copper", 100);
        materials.put("iron", 100);
        // Game Data
        FileReader assets = new FileReader();
        assets.loadRecipe(recipes);
    }
    
    public ArrayList<Sword> inventory = new ArrayList<>();
    public ArrayList<String> inventoryViewer = new ArrayList<>();
    
    public void craft(Recipe someRp) {
        materials.put(someRp.getMainMat(),materials.get(someRp.getMainMat()) - 10);
        materials.put(someRp.getSupportMat(),materials.get(someRp.getSupportMat()) - 10);
        Sword newSword = new Sword(someRp);
        inventory.add(newSword);
        inventoryViewer.add(newSword.getName() + " " + newSword.getAtk());
        System.out.println("Sword crafted: "+newSword.getName() + " " + newSword.getAtk());
    }
}
