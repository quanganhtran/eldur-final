/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eldur;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author trand
 */
public class Game {

    /**
     * @param args the command line arguments
     */
    public void game() {
        Scanner reader = new Scanner(System.in);
        // Statistics
        iron = 10;
        copper = 10;
        
        // Initialization
        Recipe ironSwordRecipe = new Recipe("Iron Sword", "Iron Sword", 10, 1, 20, 5);
        Recipe copperSwordRecipe = new Recipe("Copper Sword", "Copper Sword", 1, 10, 15, 10);
        
        // Game
        System.out.println("Type a command");
        command = reader.nextLine();
        while (!command.equals("quit")) {
            String[] input = command.split("\\s");
            if (input[0].equals("stat")) {
                System.out.println("Iron "+iron+" Copper "+copper);
                System.out.println(inventoryViewer);
            } else if (input[0].equals("craft") && input.length == 2) {
                if (input[1].equals("iron")) {
                    craft(ironSwordRecipe);
                } else if (input[1].equals("copper")) {
                    craft(copperSwordRecipe);
                }
            }
            System.out.println("Type a command");
            command = reader.nextLine();
        }
        
    }
    
    //public int cash;
    public int iron;
    public int copper;
    public String command;
    
    public ArrayList<Sword> inventory = new ArrayList<Sword>();
    public ArrayList<String> inventoryViewer = new ArrayList<String>();
    public void craft(Recipe someRp) {
        iron -= someRp.getCostIron();
        copper -= someRp.getCostCopper();
        Sword newSword = new Sword(someRp);
        inventory.add(newSword);
        inventoryViewer.add(newSword.getName() + " " + newSword.getAtk() + " " + newSword.getSpd());
        System.out.println("Sword crafted: "+newSword.getName() + " " + newSword.getAtk() + " " + newSword.getSpd());
    }
}
