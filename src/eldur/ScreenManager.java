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
 * @author trand
 */
public class ScreenManager {

    private Screen currentScreen;
    private ArrayList<Screen> unlockOrder;

    GameData gD;

    Game game;
    HashMap<String, Adventure> areaMapDict;
    HashMap<Adventure, BossScreen> areaToBoss; // Also used as free-roaming to adventure dictionary

    Adventure area1;
    BossScreen area1Boss;
    Adventure area2;
    BossScreen area2Boss;
    Adventure area3;
    BossScreen area3Boss;
    Adventure area4;
    BossScreen area4Boss;
    Adventure area5;
    BossScreen area5Boss;
    Adventure area6;
    BossScreen area6Boss;
    Adventure area7;
    BossScreen area7Boss;
    Adventure area8;
    BossScreen area8Boss;
    Adventure area9;
    BossScreen area9Boss;
    Adventure area10;
    BossScreen area10Boss;

    public ScreenManager() {
        this.gD = new GameData();
        this.game = new Game("Town", gD);
        this.areaMapDict = new LinkedHashMap<>();
        this.areaToBoss = new LinkedHashMap<>();
        
        this.area1 = new Adventure("Elysian Woods", gD);
        this.area1Boss = new BossScreen("Wreck of Boar", gD);
        areaToBoss.put(area1, area1Boss);
        this.area2 = new Adventure("Forest of Frea", gD, true);
        this.area2Boss = new BossScreen("Bandit's Camp", gD);
        areaToBoss.put(area2, area2Boss);
        this.area3 = new Adventure("The Magic Quarter", gD, true);
        this.area3Boss = new BossScreen("Wizard's Guild", gD);
        areaToBoss.put(area3, area3Boss);
        this.area4 = new Adventure("Skeleton Keep Entrance", gD, true);
        this.area4Boss = new BossScreen("Pirates' Camp", gD);
        areaToBoss.put(area4, area4Boss);
        this.area5 = new Adventure("Skeleton Keep", gD, true);
        this.area5Boss = new BossScreen("The Throne Room", gD);
        areaToBoss.put(area5, area5Boss);
        this.area6 = new Adventure("Wizard's Guild Inner", gD, true);
        this.area6Boss = new BossScreen("Wizard's Guild Basement", gD);
        areaToBoss.put(area6, area6Boss);
        this.area7 = new Adventure("Stormhold", gD, true);
        this.area7Boss = new BossScreen("Serpent's Water", gD);
        areaToBoss.put(area7, area7Boss);
        this.area8 = new Adventure("Ancient's Peak", gD, true);
        this.area8Boss = new BossScreen("The Arena", gD);
        areaToBoss.put(area8, area8Boss);
        this.area9 = new Adventure("Dragonkeep", gD, true);
        this.area9Boss = new BossScreen("Kuldan's Nest", gD);
        areaToBoss.put(area9, area9Boss);
        this.area10 = new Adventure("Temple of Eldur", gD, true);
        this.area10Boss = new BossScreen("Eldur's Hall", gD);
        areaToBoss.put(area10, area10Boss);
        
        game.addConnection("area 1", area1);
        game.addConnection("area 2", area2);
        game.addConnection("area 3", area3);
        game.addConnection("area 4", area4);
        game.addConnection("area 5", area5);
        game.addConnection("area 6", area6);
        game.addConnection("area 7", area7);
        game.addConnection("area 8", area8);
        game.addConnection("area 9", area9);
        game.addConnection("area 10", area10);
        
        areaMapDict.put("area1", area1);
        areaMapDict.put("area2", area2);
        areaMapDict.put("area3", area3);
        areaMapDict.put("area4", area4);
        areaMapDict.put("area5", area5);
        areaMapDict.put("area6", area6);
        areaMapDict.put("area7", area7);
        areaMapDict.put("area8", area8);
        areaMapDict.put("area9", area9);
        areaMapDict.put("area10", area10);
        
        for (Adventure adv : areaToBoss.keySet()) {
            adv.addConnection("town", game);
        }

        for (Adventure adv : areaToBoss.keySet()) {
            adv.addConnection("boss", areaToBoss.get(adv));
        }

        for (Adventure adv : areaToBoss.keySet()) {
            areaToBoss.get(adv).addConnection("back", adv);
        }
    }

    public void toScreen(Screen targetScreen) {
        currentScreen = targetScreen;
        currentScreen.reportScreen();
    }
    
    public void execute() {

        Screen market = new Screen("Marketplace");
        Screen inn = new Screen("Inn");
        Screen map = new Screen("Map Screen");

        game.addConnection("market", market);
        game.addConnection("inn", inn);
        game.addConnection("map", map);
        
        unlockOrder = new ArrayList<>();
        unlockOrder.add(area1);
        unlockOrder.add(area2);
        unlockOrder.add(area3);
        unlockOrder.add(area4);

        market.addConnection("back", game);
        inn.addConnection("back", game);
        map.addConnection("back", game);

        // Populate the areas
        FileReader assets = new FileReader();
        assets.loadEnemy(this);
        assets.loadStory(areaToBoss.values());

        area1.setLoot(new Loot(gD, new Material[]{gD.copper, gD.iron}));
        area1Boss.setLoot(new Loot(gD, new Material[]{gD.copper, gD.iron}));

        area2.setLoot(new Loot(gD, new Material[]{gD.copper, gD.iron}));
        area2Boss.setLoot(new Loot(gD, new Material[]{gD.copper, gD.iron}));

        area3.setLoot(new Loot(gD, new Material[]{gD.iron, gD.silver}));
        area3Boss.setLoot(new Loot(gD, new Material[]{gD.iron, gD.silver}));

        area4.setLoot(new Loot(gD, new Material[]{gD.silver, gD.gold}, true));
        area4Boss.setLoot(new Loot(gD, new Material[]{gD.silver, gD.gold}));

        area5.setLoot(new Loot(gD, new Material[]{gD.silver, gD.gold}, true));
        area5Boss.setLoot(new Loot(gD, new Material[]{gD.silver, gD.gold}));

        area6.setLoot(new Loot(gD, new Material[]{gD.gold, gD.platinum}, true));
        area6Boss.setLoot(new Loot(gD, new Material[]{gD.gold, gD.platinum}));

        area7.setLoot(new Loot(gD, new Material[]{gD.platinum, gD.mithril}, true, true));
        area7Boss.setLoot(new Loot(gD, new Material[]{gD.platinum, gD.mithril}));

        area8.setLoot(new Loot(gD, new Material[]{gD.platinum, gD.mithril}, true, true));
        area8Boss.setLoot(new Loot(gD, new Material[]{gD.platinum, gD.mithril}));

        area9.setLoot(new Loot(gD, new Material[]{gD.mithril, gD.orichalcum}, true, true));
        area9Boss.setLoot(new Loot(gD, new Material[]{gD.mithril, gD.orichalcum}));

        area10.setLoot(new Loot(gD, new Material[]{gD.orichalcum, gD.adamantite}, true, true, true));
        area10Boss.setLoot(new Loot(gD, new Material[]{gD.orichalcum, gD.adamantite}));

        currentScreen = game; // This should be set to 'game' in the final product.

        while (true) {
            toScreen(currentScreen.runScreen());
        }
    }

}
