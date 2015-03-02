/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eldur;

import java.util.ArrayList;

/**
 *
 * @author trand
 */
public class ScreenManager {
    private Screen currentScreen;
    private ArrayList<Screen> unlockOrder;
    
    //public int cash;
    //public int iron;
    //public int copper;
    //public String command;
    //public HashMap<String,Recipe> recipes = new HashMap<>();
    
//    public void initialize() {
//        // Statistics
////        iron = 10;
////        copper = 10;
//        // Game Data
////        recipes.put("iron", new Recipe("Iron Sword", "Iron Sword", 10, 1, 20, 5));
////        recipes.put("copper", new Recipe("Copper Sword", "Copper Sword", 1, 10, 15, 10));
//    }
    
    public void toScreen(Screen targetScreen) {
        currentScreen = targetScreen;
        currentScreen.reportScreen();
    }
    
    public void execute() {
        GameData gameData = new GameData();
        gameData.initialize();
        
        Game game = new Game("Town", gameData);
        Adventure frea = new Adventure("Frea", gameData);
        BossScreen freaBoss = new BossScreen("Frea Forest", gameData);
        Adventure elysia = new Adventure("Elysia", gameData, true);
        BossScreen elysiaBoss = new BossScreen("Elysia Temple", gameData);
        Adventure havenport = new Adventure("Havenport", gameData, true);
        BossScreen havenportBoss = new BossScreen("Havenport Shore", gameData);
        Adventure dragonkeep = new Adventure("Dragonkeep", gameData, true);
        BossScreen dragonkeepBoss = new BossScreen("Dragonkeep Mountain", gameData);
        Screen market = new Screen("Marketplace");
        Screen inn = new Screen("Inn");
        Screen map = new Screen("Map Screen");
        
        game.addConnection("frea", frea);
        game.addConnection("elysia", elysia);
        game.addConnection("havenport", havenport);
        game.addConnection("dragonkeep", dragonkeep);
        game.addConnection("market", market);
        game.addConnection("inn", inn);
        game.addConnection("map", map);
        
        frea.addConnection("town", game);
        frea.addConnection("boss", freaBoss);
        freaBoss.addConnection("back", frea);
        
        elysia.addConnection("town", game);
        elysia.addConnection("boss", elysiaBoss);
        elysiaBoss.addConnection("back", elysia);
        
        havenport.addConnection("town", game);
        havenport.addConnection("boss", havenportBoss);
        havenportBoss.addConnection("back", havenport);
        
        dragonkeep.addConnection("town", game);
        dragonkeep.addConnection("boss", dragonkeepBoss);
        dragonkeepBoss.addConnection("back", dragonkeep);
        
        unlockOrder = new ArrayList<>();
        unlockOrder.add(frea);
        unlockOrder.add(elysia);
        unlockOrder.add(havenport);
        unlockOrder.add(dragonkeep);
        
        market.addConnection("back", game);
        inn.addConnection("back", game);
        map.addConnection("back", game);
        
        // Populate the areas
        frea.addEnemy(new Enemy(0, "Slime", 100, 10));
        frea.addEnemy(new Enemy(0, "Big Slime", 100, 10));
        freaBoss.addEnemy(new Enemy(0, "Agressive Slime", 120, 12));
        freaBoss.addEnemy(new Enemy(0, "Agressive Slime 2", 120, 12));
        freaBoss.addEnemy(new Enemy(0, "Agressive Slime 3", 120, 12));
        freaBoss.addEnemy(new Enemy(0, "Slime King", 150, 15));
        elysia.addEnemy(new Enemy(1, "Wolf", 150, 20));
        elysia.addEnemy(new Enemy(1, "Big Wolf", 150, 20));
        havenport.addEnemy(new Enemy(2, "Goblin", 250, 25));
        havenport.addEnemy(new Enemy(2, "Big Goblin", 250, 25));
        dragonkeep.addEnemy(new Enemy(3, "Phantom", 300, 35));
        dragonkeep.addEnemy(new Enemy(3, "Big Phantom", 300, 35));
        
        currentScreen = game; // This should be set to 'game' in the final product.
        
        while (true) {
            toScreen(currentScreen.runScreen());
        }
    }

}
