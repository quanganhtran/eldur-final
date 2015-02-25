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
    public Material copper = new Material("Copper", 100);
    public Material iron = new Material("Iron", 100);
    public Material silver = new Material("Silver", 100);
    public Material gold = new Material("Gold", 100);
    public Material platinum = new Material("Platinum", 100);
    public Material mithril = new Material("Mithril", 100);
    public Material orichalcum = new Material("Orichalcum", 100);
    public Material adamantite = new Material("Adamantite", 100);
    public HashMap<String, Material> materials = new HashMap<>();
    // Gemstones
    public Gemstone ruby = new Gemstone("of Might", 10, 0, 0, 0);
    public Gemstone sapphire = new Gemstone("of Protection", 0, 10, 0, 0);
    public Gemstone emerald = new Gemstone("of Precision", 0, 0, 10, 0);
    public HashMap<String, Gemstone> gemstones = new HashMap<>();
    // Recipes
    public HashMap<String, Recipe> recipes = new HashMap<>();
    // Identification Scroll TODO: Must be treated as items.
    public IdScroll excalibur = new IdScroll("Excalibur", new Sword("Excalibur", 3, "Epic", 78, 2));
    public IdScroll genesis = new IdScroll("Genesis", new Sword("Genesis", 3, "Epic", 88, 2));
    public IdScroll umi = new IdScroll("Umi", new Sword("Umi", 4, "Epic", 140, 2));
    public HashMap<String, IdScroll> idScrolls = new HashMap<>();
    // Ascension Scroll TODO: Must be treated as items.
    public AscensionScroll rin = new AscensionScroll("Rin", new Sword("Rin", 4, "Legendary", 165, 2));
    public HashMap<String, AscensionScroll> aScrolls = new HashMap<>();
    // Equipped Sword
    public Sword equippedSword = new Sword("no sword", 1, "none", 0, 0);
    // Adventure Data
    public int currentArea = 1;
    public ArrayList<Enemy> enemies = new ArrayList<>();

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
        gemstones.put("ruby", ruby);
        gemstones.put("sapphire", sapphire);
        gemstones.put("emerald", emerald);
        // Identification Scroll TODO: Must be treated as items.
        idScrolls.put("exc", excalibur);
        idScrolls.put("gen", genesis);
        idScrolls.put("umi", umi);
        // Ascension Scroll TODO: Must be treated as items.
        aScrolls.put("rin", rin);
        // Adventure Data
        enemies.add(new Enemy("Slime", 100, 10));
        enemies.add(new Enemy("Wolf", 150, 20));
        enemies.add(new Enemy("Goblin", 250, 25));
        enemies.add(new Enemy("Phantom", 300, 35));
        // Game Data
        FileReader assets = new FileReader();
        assets.loadRecipe(recipes);
    }

    public ArrayList<Sword> inventory = new ArrayList<>();
    public ArrayList<String> inventoryViewer = new ArrayList<>();

    public void craft(Recipe someRp) { // Needs to transfer all feedbacks to Game
        materials.get(someRp.getMainMat()).consume(10);
        materials.get(someRp.getSupportMat()).consume(10);
//        materials.put(someRp.getMainMat(),materials.get(someRp.getMainMat()) - 10);
//        materials.put(someRp.getSupportMat(),materials.get(someRp.getSupportMat()) - 10);
        Sword newSword = new Sword(someRp);
        inventory.add(newSword);
        inventoryViewer.add(newSword.getName() + " " + newSword.getAtk());
        System.out.println("Sword crafted: " + newSword.getName() + " " + newSword.getAtk());
    }

    public void socket(Sword sw, Gemstone someG) { // Needs to transfer all feedbacks to Game
        // Remember to use up a gemstone here
//        sw.refinement = someG.refinementPrefix;
        if (sw.getSocket() > 0) {
            sw.useSocket();
            sw.insertSocket(someG);
            System.out.println("Refined sword: " + sw.getName());
        } else {
            System.out.println(sw.getName() + ": Not enough socket.");
        }
    }

    public void identify(Sword sw, IdScroll ids) {
        //Sword newSword = sw;
        if (sw.getRarity().equals("Rare") && (sw.getTier() == ids.getScrollTier())) {
            //newSword = ids.getSword();
            //newSword.inherit(sw);
            sw.transform(ids.getFrame());
            System.out.println("Sword identified: " + sw.getName() + ".");
        } else {
            System.out.println("The sword is not identifiable.");
        }
        //return sw;
    }

    public void ascend(Sword sw, AscensionScroll as) {
        if (sw.getRarity().equals("Epic") && (sw.getTier() == as.getScrollTier())) {
            sw.transform(as.getFrame());
            System.out.println("Sword obtained: " + sw.getName() + ".");
        } else {
            System.out.println("The sword cannot be ascended.");
        }
        //return sw;
    }

    public void setEquippedSword(Sword sw) {
        this.equippedSword = sw;
    }
}
