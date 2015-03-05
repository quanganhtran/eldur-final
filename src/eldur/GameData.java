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

    //public int cash;
    // Materials: copper, iron, silver, gold, platinum, mithril, orichalcum, adamantite.
    public Material copper;
    public Material iron;
    public Material silver;
    public Material gold;
    public Material platinum;
    public Material mithril;
    public Material orichalcum;
    public Material adamantite;
    public HashMap<String, Material> materials;
    // Gemstones
    public Gemstone ruby;
    public Gemstone sapphire;
    public Gemstone emerald;
    public HashMap<String, Gemstone> gemstonesDict;
    // Recipes
    public HashMap<String, Recipe> recipes;
    // Identification Scroll TODO: Must be treated as items.
//    public IdScroll excalibur;
//    public IdScroll genesis;
//    public IdScroll umi;
    public HashMap<String, IdScroll> idScrollsDict;
    // Ascension Scroll TODO: Must be treated as items.
//    public AscensionScroll rin;
    public HashMap<String, AscensionScroll> aScrollsDict;
    // Equipped Sword
    public Sword equippedSword;
    // Inventory
    public ArrayList<Sword> inventory;
    public ArrayList<Item> itemInv;
    public ArrayList<String> inventoryViewer;
    public ArrayList<Adventure> mapList;

    public GameData() {
        // Materials
        this.copper = new Material("Copper", 20);
        this.iron = new Material("Iron", 20);
        this.silver = new Material("Silver", 20);
        this.gold = new Material("Gold", 20);
        this.platinum = new Material("Platinum", 20);
        this.mithril = new Material("Mithril", 20);
        this.orichalcum = new Material("Orichalcum", 20);
        this.adamantite = new Material("Adamantite", 20);
        this.materials = new LinkedHashMap<>();

        this.materials.put("copper", copper);
        this.materials.put("iron", iron);
        this.materials.put("silver", silver);
        this.materials.put("gold", gold);
        this.materials.put("platinum", platinum);
        this.materials.put("mithril", mithril);
        this.materials.put("orichalcum", orichalcum);
        this.materials.put("adamantite", adamantite);
        // Gemstones
        this.ruby = new Gemstone("Ruby", "of Might", 10, 0, 0, 0);
        this.sapphire = new Gemstone("Sapphire", "of Protection", 0, 20, 0, 0);
        this.emerald = new Gemstone("Emerald", "of Precision", 0, 0, 10, 0);
        this.gemstonesDict = new HashMap<>();

        this.gemstonesDict.put("atk", ruby);
        this.gemstonesDict.put("def", sapphire);
        this.gemstonesDict.put("cri", emerald);
        // Recipes
        this.recipes = new HashMap<>();
        // Identification Scrolls
//        this.excalibur = new IdScroll("Excalibur", new Sword("Excalibur", 3, "Epic", 78, 2));
//        this.genesis = new IdScroll("Genesis", new Sword("Genesis", 3, "Epic", 88, 2));
//        this.umi = new IdScroll("Umi", new Sword("Umi", 4, "Epic", 140, 2));
        this.idScrollsDict = new HashMap<>();

//        this.idScrollsDict.put("exc", excalibur);
//        this.idScrollsDict.put("gen", genesis);
//        this.idScrollsDict.put("umi", umi);
        // Ascension Scrolls
//        this.rin = new AscensionScroll("Rin", new Sword("Rin", 4, "Legendary", 200, 2));
        this.aScrollsDict = new HashMap<>();

//        this.aScrollsDict.put("rin", rin);
        // Initial Sword
        this.equippedSword = new Sword("no sword", 1, "none", 1, 0);
        // Inventory
        this.inventory = new ArrayList<>();
        this.itemInv = new ArrayList<>();
        this.inventoryViewer = new ArrayList<>();
        this.mapList = new ArrayList<>();
        // Game Data
        FileReader assets = new FileReader();
        assets.loadRecipe(recipes);
        assets.loadScroll(idScrollsDict, aScrollsDict);
        
//        inventory.add(new Sword(recipes.get("imperial")));
//        itemInv.add(aScrollsDict.get("ann"));
        
        // FUNCTIONALITY TEST
//        itemInv.add(umi);
//        itemInv.add(umi);
//        itemInv.add(excalibur);
//        itemInv.add(rin);
//        itemInv.add(ruby);
//        itemInv.add(ruby);
//        itemInv.add(ruby);
//        itemInv.add(sapphire);
//        itemInv.add(emerald);
//        itemInv.add(sapphire);
//        itemInv.add(emerald);
//        itemInv.add(sapphire);
//        itemInv.add(emerald);
//        itemInv.add(genesis);
    }

    public void craft(Recipe someRp) { // Needs to transfer all feedbacks to Game
        if (materials.get(someRp.getMainMat()).consume(10) && materials.get(someRp.getSupportMat()).consume(10)) {
            //;
            //;
            Sword newSword = new Sword(someRp);
            inventory.add(newSword);
            System.out.println("Sword crafted: " + newSword.getName() + ".");
            inspect(newSword);
        } else {
            System.out.println("There is not enough material to craft this sword.");
        }
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
            inspect(sw);
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
            inspect(sw);
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
            inspect(sw);
        }
        //return sw;
    }

    public void inspect(Sword sw) {
        System.out.println(sw.getName());
        System.out.println("Attack: " + sw.getAtk());
        System.out.println("Tier " + sw.getTier() + " " + sw.getRarity() + " Sword.");
        System.out.println("Socket remaining: " + sw.getSocket());
        System.out.println("Defense: " + sw.getDefense());
        System.out.println("Critical Chance: " + sw.getCritRate() + "%");
        System.out.println("Evasion: " + sw.getEvasion() + "%");
    }
    
//    public void destroy(Sword sw) {
//        inventory.remove(sw);
//    }

    public void setEquippedSword(Sword sw) {
        this.equippedSword = sw;
    }
}
