/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eldur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeSet;

/**
 *
 * @author Anh
 */
public class GameData {

    public int cash;
    // Materials: copper, iron, silver, gold, platinum, mithril, orichalcum, adamantite.
    public Material copper = new Material("Copper", 0);
    public Material iron = new Material("Iron", 0);
    public Material silver = new Material("Silver", 0);
    public Material gold = new Material("Gold", 100);
    public Material platinum = new Material("Platinum", 0);
    public Material mithril = new Material("Mithril", 0);
    public Material orichalcum = new Material("Orichalcum", 0);
    public Material adamantite = new Material("Adamantite", 0);
    public HashMap<String, Material> materials = new HashMap<>();
    // Gemstones
    public Gemstone ruby = new Gemstone("Ruby", "of Might", 10, 0, 0, 0);
    public Gemstone sapphire = new Gemstone("Sapphire", "of Protection", 0, 10, 0, 0);
    public Gemstone emerald = new Gemstone("Emerald", "of Precision", 0, 0, 10, 0);
    public HashMap<String, Gemstone> gemstonesDict = new HashMap<>();
    // Recipes
    public HashMap<String, Recipe> recipes = new HashMap<>();
    // Identification Scroll TODO: Must be treated as items.
    public IdScroll excalibur = new IdScroll("Excalibur", new Sword("Excalibur", 3, "Epic", 78, 2));
    public IdScroll genesis = new IdScroll("Genesis", new Sword("Genesis", 3, "Epic", 88, 2));
    public IdScroll umi = new IdScroll("Umi", new Sword("Umi", 4, "Epic", 140, 2));
    public HashMap<String, IdScroll> idScrollsDict = new HashMap<>();
    // Ascension Scroll TODO: Must be treated as items.
    public AscensionScroll rin = new AscensionScroll("Rin", new Sword("Rin", 4, "Legendary", 165, 2));
    public HashMap<String, AscensionScroll> aScrollsDict = new HashMap<>();
    // Equipped Sword
    public Sword equippedSword = new Sword("no sword", 1, "none", 0, 0);
    // Inventory
    public ArrayList<Sword> inventory = new ArrayList<>();
    public ArrayList<Item> itemInv = new ArrayList<>();
    public ArrayList<String> inventoryViewer = new ArrayList<>();
    // Adventure Data
    public ArrayList<Adventure> mapList = new ArrayList<>();

    public void initialize() {
        // Materials
        materials.put("copper", copper);
        materials.put("iron", iron);
        materials.put("silver", silver);
        materials.put("gold", gold);
        materials.put("platinum", platinum);
        materials.put("mithril", mithril);
        materials.put("orichalcum", orichalcum);
        materials.put("adamantite", adamantite);
        // Gemstones
        gemstonesDict.put("ruby", ruby);
        gemstonesDict.put("sapphire", sapphire);
        gemstonesDict.put("emerald", emerald);
        // Identification Scroll TODO: Must be treated as items.
        idScrollsDict.put("exc", excalibur);
        idScrollsDict.put("gen", genesis);
        idScrollsDict.put("umi", umi);
        // Ascension Scroll TODO: Must be treated as items.
        aScrollsDict.put("rin", rin);
        // Adventure Data
        
//        enemiesAll.put("frea", new ArrayList<>());
//        //enemies
//        enemiesInFrea.add(new Enemy(0, "Slime", 100, 10));
//        enemiesInFrea.add(new Enemy(0, "Big Slime", 100, 10));
//        enemiesInFrea.add(new Enemy(1, "Wolf", 150, 20));
//        enemiesInFrea.add(new Enemy(1, "Big Wolf", 150, 20));
//        enemiesInFrea.add(new Enemy(2, "Goblin", 250, 25));
//        enemiesInFrea.add(new Enemy(2, "Big Goblin", 250, 25));
//        enemiesInFrea.add(new Enemy(3, "Phantom", 300, 35));
//        enemiesInFrea.add(new Enemy(3, "Big Phantom", 300, 35));
        // Game Data
        FileReader assets = new FileReader();
        assets.loadRecipe(recipes);
        
        // FUNCTIONALITY TEST
        itemInv.add(umi);
        itemInv.add(umi);
        itemInv.add(excalibur);
        itemInv.add(rin);
        itemInv.add(ruby);
        itemInv.add(ruby);
        itemInv.add(ruby);
        itemInv.add(sapphire);
        itemInv.add(emerald);
        itemInv.add(genesis);
    }


    public void craft(Recipe someRp) { // Needs to transfer all feedbacks to Game
        materials.get(someRp.getMainMat()).consume(10);
        materials.get(someRp.getSupportMat()).consume(10);
//        materials.put(someRp.getMainMat(),materials.get(someRp.getMainMat()) - 10);
//        materials.put(someRp.getSupportMat(),materials.get(someRp.getSupportMat()) - 10);
        Sword newSword = new Sword(someRp);
        inventory.add(newSword);
        //inventoryViewer.add(newSword.getName() + " " + newSword.getAtk());
        System.out.println("Sword crafted: " + newSword.getName() + " " + newSword.getAtk());
    }

    public void socket(Sword sw, Gemstone someG) { // Needs to transfer all feedbacks to Game
        // Remember to use up a gemstone here
//        sw.refinement = someG.refinementPrefix;
        if (sw.getSocket() <= 0) {
            System.out.println(sw.getName() + ": Not enough socket.");
        } else if (!itemInv.remove(someG)) {
            System.out.println("The needed Gemstone is not found.");
        } else {
            sw.useSocket();
            sw.insertSocket(someG);
            System.out.println("Refined sword: " + sw.getName());
        }
    }

    public void identify(Sword sw, IdScroll ids) {
        //Sword newSword = sw;
        if (!sw.getRarity().equals("Rare") || (sw.getTier() != ids.getScrollTier())) {
            System.out.println("The sword is not identifiable.");
        } else if (!itemInv.remove(ids)) {
            System.out.println("The needed Identification scroll is not found.");
        } else {
            //newSword = ids.getSword();
            //newSword.inherit(sw);
            sw.transform(ids.getFrame());
            //itemInv.remove(ids);
            System.out.println("Sword identified: " + sw.getName() + ".");
        }
        //return sw;
    }

    public void ascend(Sword sw, AscensionScroll as) {
        if (!sw.getRarity().equals("Epic") || (sw.getTier() != as.getScrollTier())) {
            System.out.println("The sword cannot be ascended.");
        } else if (!itemInv.remove(as)) {
            System.out.println("The needed Ascension scroll is not found.");
        } else {
            sw.transform(as.getFrame());
            //itemInv.remove(as);
            System.out.println("Sword obtained: " + sw.getName() + ".");
        }
        //return sw;
    }

    public void setEquippedSword(Sword sw) {
        this.equippedSword = sw;
    }
}
