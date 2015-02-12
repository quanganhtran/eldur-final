/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eldur;

import java.util.Scanner;

/**
 *
 * @author Anh
 */
public class Game extends Screen {

    //private Scanner reader;
    private GameData gameData;

    public Game(String sN, GameData gD) {
        super(sN);
        gameData = gD;
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
            case "stat":
//                System.out.println("Copper: " + gameData.copper.getStock());
                gameData.materials.keySet().stream().forEach((k) -> {
                    System.out.print(gameData.materials.get(k).getName() + ": " + gameData.materials.get(k).getStock() + " ");
                });
                System.out.println("");
                gameData.inventory.stream().forEach((sw) -> {
                    System.out.print(sw.getName() + " | ");
                });
                System.out.println("");
                System.out.println(gameData.inventoryViewer);
                return "";
            case "craft":
                if (inputParts.length >= 2) {
                    if (gameData.recipes.get(inputParts[1]) != null) {
                        gameData.craft(gameData.recipes.get(inputParts[1]));
                    }
                }
                return "";
            case "refine":
                if (inputParts.length >= 2) {
                    int invPos = Integer.parseInt(inputParts[1]) - 1; // Exception possible
                    Sword sw = null;
                    if (invPos >= gameData.inventory.size()) {
                        System.out.println("Sword not found.");
                    } else {
                        sw = gameData.inventory.get(invPos);
                    }
                    if (sw != null) {
                        gameData.socket(sw, gameData.ruby);
                        System.out.println("Refined sword: " + sw.getName());
                        //gameData.inventory.set(invPos, sw);
                    }
                }
                return "";
            default:
                return input;
        }
        //return input;

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
