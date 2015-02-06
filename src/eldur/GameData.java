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
    // Materials: copper, iron, silver, gold, platinum, mithril
    public int copperOre,ironOre,silverOre,goldOre,platinumOre,mithrilOre;
    public int copperIngot,ironIngot,silverIngot,goldIngot,platinumIngot,mithrilIngot;
    public HashMap<String,Recipe> recipes = new HashMap<>();
    

    public void initialize() {
        // Statistics
        ironOre = 10;
        copperOre = 10;
        // Game Data
        FileReader assets = new FileReader();
        assets.loadRecipe(recipes);
    }
    
    public ArrayList<Sword> inventory = new ArrayList<>();
    public ArrayList<String> inventoryViewer = new ArrayList<>();
    
    public void craft(Recipe someRp) {
        ironOre -= someRp.getCostIron();
        copperOre -= someRp.getCostCopper();
        Sword newSword = new Sword(someRp);
        inventory.add(newSword);
        inventoryViewer.add(newSword.getName() + " " + newSword.getAtk() + " " + newSword.getSpd());
        System.out.println("Sword crafted: "+newSword.getName() + " " + newSword.getAtk() + " " + newSword.getSpd());
    }
}
