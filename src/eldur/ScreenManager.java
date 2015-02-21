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
        
        Game game = new Game("Town", gameData);
        Adventure adventure = new Adventure("Adventure", gameData);
        Screen market = new Screen("Marketplace");
        Screen inn = new Screen("Inn");
        Screen map = new Screen("Map Screen");
        
        game.addConnection("adventure", adventure);
        game.addConnection("market", market);
        game.addConnection("inn", inn);
        game.addConnection("map", map);
        adventure.addConnection("back", game);
        market.addConnection("back", game);
        inn.addConnection("back", game);
        map.addConnection("back", game);
        
        currentScreen = game; // TODO change this back to 'game'
        
        gameData.initialize();
        
        while (true) {
            toScreen(currentScreen.runScreen());
        }
    }

}
