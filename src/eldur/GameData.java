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
    public Material copper = new Material("Copper",100);
    public Material iron = new Material("Iron",100);
    public Material silver = new Material("Silver",100);
    public HashMap<String,Material> materials = new HashMap<>();
    // Gemstones
    public Gemstone ruby = new Gemstone("of Strength");
    
    public HashMap<String,Recipe> recipes = new HashMap<>();

    public void initialize() {
        // Materials
//        materials.put("gold", 100);
//        materials.put("platinum", 100);
//        materials.put("mithril", 100);
//        materials.put("orichalcum", 100);
//        materials.put("adamantite", 100);
        materials.put("copper",copper);
        materials.put("iron", iron);
        materials.put("silver", silver);
        // Gemstones
        // Game Data
        FileReader assets = new FileReader();
        assets.loadRecipe(recipes);
    }
    
    public ArrayList<Sword> inventory = new ArrayList<>();
    public ArrayList<String> inventoryViewer = new ArrayList<>();
    
    public void craft(Recipe someRp) {
        materials.get(someRp.getMainMat()).consume(10);
        materials.get(someRp.getSupportMat()).consume(10);
//        materials.put(someRp.getMainMat(),materials.get(someRp.getMainMat()) - 10);
//        materials.put(someRp.getSupportMat(),materials.get(someRp.getSupportMat()) - 10);
        Sword newSword = new Sword(someRp);
        inventory.add(newSword);
        inventoryViewer.add(newSword.getName() + " " + newSword.getAtk());
        System.out.println("Sword crafted: "+ newSword.getName() + " " + newSword.getAtk());
    }
    
    public void socket(Sword sw, Gemstone someG) {
        // Remember to use up a gemstone here
        sw.refinement = someG.refinement;
        sw.addSocketName(someG.refinement);
    }
}
