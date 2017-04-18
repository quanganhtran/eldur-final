/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eldur;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Anh
 */
public class Loot {
    private Random rng = new Random();
    private GameData gD;
    private Material[] commonLoot;
    private ArrayList<Gemstone> rareLoot;
    private ArrayList<IdScroll> epicLoot;
    private ArrayList<AscensionScroll> legendaryLoot;
    private Item[] questLoot;
    
    public Loot(GameData gD, Material[] l) {
        this.gD = gD;
        this.commonLoot = l;
    }
    
    public Loot(GameData gD, Material[] l, boolean rL) {
        this(gD, l);
        if (rL) {
            this.rareLoot = new ArrayList<>(gD.gemstonesDict.values());
        }
    }
    
    public Loot(GameData gD, Material[] l, boolean rL, boolean eL) {
        this(gD, l, rL);
        if (eL) {
            this.epicLoot = new ArrayList<>(gD.idScrollsDict.values());
        }
    }
    
    public Loot(GameData gD, Material[] l, boolean rL, boolean eL, boolean lL) {
        this(gD, l, rL, eL);
        if (lL) {
            this.legendaryLoot = new ArrayList<>(gD.aScrollsDict.values());
        }
    }
    
    public Loot(GameData gD, Material[] l, Item[] items) {
        this(gD, l);
        this.questLoot = items;
    }
    
    public void drop() {
        for (Material mat : commonLoot) {
            int amount = 10;
            mat.gain(amount);
            System.out.println( amount + "\u001B[30;1m " + mat.getName() + " obtained.\u001B[0m");
        }
        if ((rareLoot != null) && (rng.nextInt(2) == 1)) {
            Item rareDrop = rareLoot.get(rng.nextInt(rareLoot.size()));
            gD.itemInv.add(rareDrop);
            System.out.println("\u001B[32m" + rareDrop.getName() + " obtained.\u001B[0m");
        }
        if ((epicLoot != null) && rng.nextInt(4) == 3) {
            Item epicDrop = epicLoot.get(rng.nextInt(epicLoot.size()));
            gD.itemInv.add(epicDrop);
            System.out.println("\u001B[35m" + epicDrop.getName() + " obtained.\u001B[0m");
        }
        if ((legendaryLoot != null) && rng.nextInt(8) == 7) {
            Item legendaryDrop = legendaryLoot.get(rng.nextInt(legendaryLoot.size()));
            gD.itemInv.add(legendaryDrop);
            System.out.println("\u001B[35m" + legendaryDrop.getName() + " obtained.\u001B[0m");
        }
    }
}
