/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eldur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 *
 * @author Anh
 */
public class GameData {
    public int cash;
    // Materials: copper, iron, silver, gold, platinum, mithril, orichalcum, adamantite.
    public int copper,iron;
    public LinkedHashMap<String,Integer> materials = new LinkedHashMap<>();
    public HashMap<String,Recipe> recipes = new HashMap<>();
    

    public void initialize() {
        // Statistics
        materials.put("copper", 100);
        materials.put("iron", 100);
        materials.put("silver", 100);
        materials.put("gold", 100);
        materials.put("platinum", 100);
        materials.put("mithril", 100);
        materials.put("orichalcum", 100);
        materials.put("adamantite", 100);
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
