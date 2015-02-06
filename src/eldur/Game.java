/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eldur;

import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Anh
 */
public class Game extends Screen {
    private Scanner reader;
    private String screenName;
    private HashMap<String,Screen> connections = new HashMap<>();
    
    public Game(String sN) {
        super(sN);
    }
    
    @Override
    public Screen onCommand() {
        reader = new Scanner(System.in);
        while (true) {
            System.out.println("Enter a command: ");
            String input = reader.nextLine();
            String connection = interpret(input);
            
            if (connections.get(connection) != null) {
                return connections.get(connection);
            }
        }
    }
    
    @Override
    public String interpret(String input) {
        String[] inputParts = input.split("\\s");
        switch (inputParts[0]) {
            case "where":
                reportScreen();
                return "";
            case "quit":
                System.out.println("Thanks for playing!");
                System.exit(0);
            case "craft":
                if (inputParts.length >= 2) {
                    if (inputParts[1].equals("iron")) {
                        //Controller.craft(recipes.get("iron"));
                    }
                }
        }
        return input;
        
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
    }

}