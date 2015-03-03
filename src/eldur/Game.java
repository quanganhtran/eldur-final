/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eldur;

import java.util.Collections;

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

//    @Override
//    public Screen onCommand() {
//        reader = new Scanner(System.in);
//        while (true) {
//            System.out.println("Enter a command: ");
//            String input = reader.nextLine();
//            String connection = interpret(input);
//
//            if (connections.get(connection) != null) { // Needs proper feedback
//                return connections.get(connection);
//            }
//        }
//    }
    @Override
    public String interpret(String input) {
        String[] inputParts = input.split("\\s");
        switch (inputParts[0]) {
            case "where":
                reportScreen();
                return "";
            case "help":
                System.out.println("stat - Show your inventory.");
                System.out.println("craft <type> - 'iron', 'copper', 'imperial', etc.");
                System.out.println("refine <slot number> <type> - 'ruby', 'sapphire', 'emerald', etc.");
                System.out.println("identify <slot number> <type> - 'umi', 'exc', 'gen', etc.");
                System.out.println("ascend <slot number> <type> - 'rin', etc.");
                System.out.println("equip <slot number>");
                System.out.println("inspect <slot number>");
                System.out.println("areax - Adventure! - change x to 1-10");
                System.out.println("quit - Quit the game.");
                return "";
            case "quit":
                System.out.println("Thanks for playing!");
                System.exit(0);
            case "stat":
//                System.out.println("Copper: " + gameData.copper.getStock());
//                System.out.println("Town area: " + gameData.currentArea);
                System.out.println("Cash: " + gameData.cash);
                gameData.materials.keySet().stream().forEach((k) -> {
                    System.out.print(gameData.materials.get(k).getName() + ": " + gameData.materials.get(k).getStock() + " ");
                });
                System.out.println("\nItems:");
                if (gameData.itemInv.isEmpty()) {
                    System.out.println("There is no item in your invertory.");
                }
                Collections.sort(gameData.itemInv, new ItemComparator());
                for (int i = 0; i < gameData.itemInv.size(); i++) {
                    if (i == gameData.itemInv.indexOf(gameData.itemInv.get(i))) {
                        System.out.print(gameData.itemInv.get(i).getName() + " x" + Collections.frequency(gameData.itemInv, gameData.itemInv.get(i)) + " | ");
                    }
                }
                System.out.println("\nWeapons:");
                if (gameData.inventory.isEmpty()) {
                    System.out.println("There is no weapon in your invertory.");
                }
//                gameData.inventory.stream().forEach((sw) -> {
//                    System.out.print(sw.getName() + " | ");
//                });
                for (int i = 0; i < gameData.inventory.size(); i++) {
                    System.out.print((i+1) + ". " + gameData.inventory.get(i).getName() + " | ");
                }
                //System.out.println("");
                //System.out.println(gameData.inventoryViewer);
                return "";
//            case "adventure":
//                if (inputParts.length >= 2) {
//                    //int area = -1;
//                    try {
//                        gameData.currentArea = Integer.parseInt(inputParts[1]);
//                    } catch (NumberFormatException numberFormatException) {
//                        System.out.println("Area index must be a number.");
//                        return "";
//                    }
//                }
//                return "adventure";
            case "inspect":
                if (inputParts.length >= 2) {
                    int invPos = -1;
                    try {
                        invPos = Integer.parseInt(inputParts[1]) - 1;
                    } catch (NumberFormatException numberFormatException) {
                        System.out.println("Sword index must be a number.");
                        return "";
                    }
                    Sword sw = null;
                    if (0 <= invPos && invPos < gameData.inventory.size()) {
                        sw = gameData.inventory.get(invPos);
                    } else {
                        System.out.println("Sword not found.");
                        return "";
                    }
                    if (sw != null) {
                        System.out.println(sw.getName());
                        System.out.println("Attack: " + sw.getAtk());
                        System.out.println("Socket remaining: " + sw.getSocket());
                        System.out.println("Defense: " + sw.getDefense());
                        System.out.println("Critical Chance: " + sw.getCritRate() + "%");
                        System.out.println("Evasion: " + sw.getEvasion() + "%");
                        return "";
                    }
                }
                return "";
            case "craft": // Needs proper feedback
                if (inputParts.length >= 2) {
                    if (gameData.recipes.get(inputParts[1]) != null) {
                        gameData.craft(gameData.recipes.get(inputParts[1]));
                    }
                }
                return "";
            case "equip":
                if (inputParts.length >= 2) {
                    int invPos = -1;
                    try {
                        invPos = Integer.parseInt(inputParts[1]) - 1;
                    } catch (NumberFormatException numberFormatException) {
                        System.out.println("Sword index must be a number.");
                        return "";
                    }
                    Sword sw = null;
                    if (0 <= invPos && invPos < gameData.inventory.size()) {
                        sw = gameData.inventory.get(invPos);
                        gameData.setEquippedSword(sw);
                        System.out.println("You have equipped " + sw.getName() + ".");
                    } else {
                        System.out.println("Sword not found.");
                    }
                }
                return "";
            case "refine": // Needs proper feedback
                if (inputParts.length >= 3) {
                    int invPos = -1;
                    try {
                        invPos = Integer.parseInt(inputParts[1]) - 1;
                    } catch (NumberFormatException numberFormatException) {
                        System.out.println("Sword index must be a number.");
                        return "";
                    }
                    Sword sw = null;
                    if (0 <= invPos && invPos < gameData.inventory.size()) {
                        sw = gameData.inventory.get(invPos);
                    } else {
                        System.out.println("Sword not found.");
                    }
                    Gemstone gem = gameData.gemstonesDict.get(inputParts[2]);
                    if (sw != null && gem != null) {
                        gameData.socket(sw, gem);
                        //gameData.inventory.set(invPos, sw);
                    } else {
                        System.out.println("Gemstone not found.");
                    }
                }
                return "";
            case "identify": // Needs proper feedback
                if (inputParts.length >= 3) {
                    int invPos = -1;
                    try {
                        invPos = Integer.parseInt(inputParts[1]) - 1;
                    } catch (NumberFormatException numberFormatException) {
                        System.out.println("Sword index must be a number.");
                        return "";
                    }
                    Sword sw = null;
                    if (0 <= invPos && invPos < gameData.inventory.size()) {
                        sw = gameData.inventory.get(invPos);
                    } else {
                        System.out.println("Sword not found.");
                        return "";
                    }
                    IdScroll id = gameData.idScrollsDict.get(inputParts[2]);
                    if (id != null) {
                        //gameData.inventory.set(invPos, gameData.identify(sw, id));
                        gameData.identify(sw, id);
                        //gameData.inventory.set(invPos, sw);
                    } else {
                        System.out.println("Invalid Identification scroll.");
                        return "";
                    }
                }
                return "";
            case "ascend": // Needs proper feedback
                if (inputParts.length >= 3) {
                    int invPos = -1;
                    try {
                        invPos = Integer.parseInt(inputParts[1]) - 1;
                    } catch (NumberFormatException numberFormatException) {
                        System.out.println("Sword index must be a number.");
                        return "";
                    }
                    Sword sw = null;
                    if (0 <= invPos && invPos < gameData.inventory.size()) {
                        sw = gameData.inventory.get(invPos);
                    } else {
                        System.out.println("Sword not found.");
                        return "";
                    }
                    AscensionScroll a = gameData.aScrollsDict.get(inputParts[2]);
                    if (a != null) {
                        gameData.ascend(sw, a);
                        //gameData.inventory.set(invPos, sw);
                    } else {
                        System.out.println("Invalid Ascension scroll.");
                        return "";
                    }
                }
                return "";
            default:
                return input;
        }
        //return input;

    }

}
