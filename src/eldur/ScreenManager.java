/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eldur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author trand
 */
public class ScreenManager {
    private Screen currentScreen;
    
    //public int cash;
    public int iron;
    public int copper;
    public String command;
    public HashMap<String,Recipe> recipes = new HashMap<>();
    
    public void initialize() {
        // Statistics
        iron = 10;
        copper = 10;
        // Game Data
//        recipes.put("iron", new Recipe("Iron Sword", "Iron Sword", 10, 1, 20, 5));
//        recipes.put("copper", new Recipe("Copper Sword", "Copper Sword", 1, 10, 15, 10));
        
    }
    
    public void toScreen(Screen targetScreen) {
        currentScreen = targetScreen;
        currentScreen.reportScreen();
    }
    
    public void execute() {
        GameData gameData = new GameData();
        gameData.initialize();
        
        Game game = new Game("Town", gameData);
        Adventure frea = new Adventure("Frea", gameData);
        Adventure elysia = new Adventure("Elysia", gameData);
        Adventure havenport = new Adventure("Havenport", gameData);
        Adventure dragonkeep = new Adventure("Dragonkeep", gameData);
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
        frea.addConnection("back", game);
        elysia.addConnection("back", game);
        havenport.addConnection("back", game);
        dragonkeep.addConnection("back", game);
        market.addConnection("back", game);
        inn.addConnection("back", game);
        map.addConnection("back", game);
        
        frea.addEnemy(new Enemy(0, "Slime", 100, 10));
        frea.addEnemy(new Enemy(0, "Big Slime", 100, 10));
        elysia.addEnemy(new Enemy(1, "Wolf", 150, 20));
        elysia.addEnemy(new Enemy(1, "Big Wolf", 150, 20));
        havenport.addEnemy(new Enemy(2, "Goblin", 250, 25));
        havenport.addEnemy(new Enemy(2, "Big Goblin", 250, 25));
        dragonkeep.addEnemy(new Enemy(3, "Phantom", 300, 35));
        dragonkeep.addEnemy(new Enemy(3, "Big Phantom", 300, 35));
        
        currentScreen = game; // TODO change this back to 'game'
        
        
        while (true) {
            toScreen(currentScreen.runScreen());
        }
    }

}
