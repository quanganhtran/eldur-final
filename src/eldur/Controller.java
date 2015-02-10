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
public class Controller {
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
        Screen adventure = new Screen("Battle");
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
        
        currentScreen = game;
        
        gameData.initialize();
        
        while (true) {
            toScreen(currentScreen.onCommand());
        }
    }

//    public void game() {
//        Scanner reader = new Scanner(System.in);
//        // Statistics
//        iron = 10;
//        copper = 10;
//        
//        // Initialization
//        Recipe ironSwordRecipe = new Recipe("Iron Sword", "Iron Sword", 10, 1, 20, 5);
//        Recipe copperSwordRecipe = new Recipe("Copper Sword", "Copper Sword", 1, 10, 15, 10);
//        
//        // Game
//        System.out.println("Type a command");
//        command = reader.nextLine();
//        while (!command.equals("quit")) {
//            String[] input = command.split("\\s");
//            if (input[0].equals("stat")) {
//                System.out.println("Iron "+iron+" Copper "+copper);
//                System.out.println(inventoryViewer);
//            } else if (input[0].equals("craft") && input.length == 2) {
//                if (input[1].equals("iron")) {
//                    craft(ironSwordRecipe);
//                } else if (input[1].equals("copper")) {
//                    craft(copperSwordRecipe);
//                }
//            }
//            System.out.println("Type a command");
//            command = reader.nextLine();
//        }
//        
//    }
    
//    public ArrayList<Sword> inventory = new ArrayList<>();
//    public ArrayList<String> inventoryViewer = new ArrayList<>();
//    
//    public void craft(Recipe someRp) {
//        iron -= someRp.getCostIron();
//        copper -= someRp.getCostCopper();
//        Sword newSword = new Sword(someRp);
//        inventory.add(newSword);
//        inventoryViewer.add(newSword.getName() + " " + newSword.getAtk() + " " + newSword.getSpd());
//        System.out.println("Sword crafted: "+newSword.getName() + " " + newSword.getAtk() + " " + newSword.getSpd());
//    }

}
