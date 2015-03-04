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
    private Item questLoot;
    
    public Loot(GameData gD, Material[] l, Item rL, Item qL) {
        this.commonLoot = l;
        this.rareLoot = new ArrayList<>(gD.gemstonesDict.values());
        this.epicLoot = new ArrayList<>(gD.idScrollsDict.values());
        this.questLoot = qL;
    }
    
    public void drop() {
        for (Material mat : commonLoot) {
            int amount = 2;
            mat.gain(amount);
            System.out.println( amount + "\u001B[36m " + mat.getName() + " obtained.\u001B[0m");
        }
//        if (!(rareLoot.isEmpty()) && rng.nextInt(5) == 4) {
//            Item rareDrop = rareLoot.get(rng.nextInt(rareLoot.size()));
//            gD.itemInv.add(rareDrop);
//            System.out.println(rareDrop.getName() + " obtained.");
//        }
//        if (!(epicLoot.isEmpty()) && rng.nextInt(25) == 24) {
//            Item epicDrop = epicLoot.get(rng.nextInt(epicLoot.size()));
//            gD.itemInv.add(epicDrop);
//            System.out.println(epicDrop.getName() + " obtained.");
//        }
    }
}
