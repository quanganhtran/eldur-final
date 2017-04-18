/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eldur;

import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author Anh
 */
public class Game extends Screen {

    private GameData gameData;

    public Game(String sN, GameData gD) {
        super(sN);
        gameData = gD;
    }

    @Override
    public String interpret(String input) {
        String[] inputParts = input.split("\\s");
        switch (inputParts[0]) {
            case "where":
                reportScreen();
                return "";
            case "help":
                System.out.println("\u001B[32mList of Commands to use: \u001B[0m");
                System.out.println("\nareax - \u001B[35mTravel to areas - change x to 1-10\u001B[0m");
                System.out.println("town - \u001B[35mReturn to town, only place you can craft.\u001B[0m");
                System.out.println("stat - Show your inventory. \n\u001B[32mSlot number is shown next to the weapon in your inventory.\u001B[0m");
                System.out.println("\ncraft + <type> - weapon types: 'iron', 'copper', 'imperial', etc.");
                System.out.println("refine + \u001B[32m<slot number>\u001B[0m + <type> - refine types: 'atk', 'def', 'cri', etc.");
                System.out.println("identify + \u001B[32m<slot number>\u001B[0m + <type> - identify types: 'umi', 'exc', 'gen', etc.");
                System.out.println("ascend + \u001B[32m<slot number>\u001B[0m + <type> - ascend types: 'rin', etc.");
                System.out.println("equip + \u001B[32m<slot number>\u001B[0m");
                System.out.println("inspect \u001B[32m<slot number>\u001B[0m");
                System.out.println("destroy \u001B[32m<slot number>\u001B[0m");
                System.out.println("hmat - Display keyword used for crafting");
                System.out.println("hgem - Display keyword used for refining");
                System.out.println("hids - Display keyword used for identification");
                System.out.println("hasc - Display keyword used for ascension");
                System.out.println("quit - Quit the game.");
                return "";
            case "quit":
                System.out.println("Thanks for playing!");
                System.exit(0);
            case "hmat":
                SortedSet<String> keys = new TreeSet<String>(gameData.recipes.keySet());
                for (String s : keys) {
                    System.out.println(s + " - " + gameData.recipes.get(s).getWeaponName());
                }
                return "";
            case "hgem":
                for (String s : new TreeSet<String>(gameData.gemstonesDict.keySet())) {
                    System.out.println(s + " - " + gameData.gemstonesDict.get(s).getName());
                }
                return "";
            case "hids":
                for (String s : new TreeSet<String>(gameData.idScrollsDict.keySet())) {
                    System.out.println(s + " - " + gameData.idScrollsDict.get(s).getName());
                }
                return "";
            case "hasc":
                for (String s : new TreeSet<String>(gameData.aScrollsDict.keySet())) {
                    System.out.println(s + " - " + gameData.aScrollsDict.get(s).getName());
                }
                return "";
            case "stat":
                for (Material mat : gameData.materials.values()) {
                    System.out.print(mat.getName() + ": " + mat.getStock() + " ");
                }
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
                for (int i = 0; i < gameData.inventory.size(); i++) {
                    System.out.print((i+1) + ". " + gameData.inventory.get(i).getName() + " | ");
                }
                System.out.println("");
                if (gameData.equippedSword.getName().equals("no sword")) {
                    System.out.println("You are not currently equipping any sword.");
                } else {
                    System.out.println("Your currently equipped sword is " + gameData.equippedSword.getName() + ".");
                }
                return "";
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
                        gameData.inspect(sw);
                        return "";
                    }
                }
                return "";
            case "destroy":
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
                        sw = gameData.inventory.remove(invPos);
                        System.out.println("Sword destroyed.");
                    } else {
                        System.out.println("Sword not found.");
                        return "";
                    }
                }
                return "";
            case "craft":
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
            case "refine":
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
                    } else {
                        System.out.println("Gemstone not found.");
                    }
                }
                return "";
            case "identify":
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
                        gameData.identify(sw, id);
                    } else {
                        System.out.println("Invalid Identification scroll.");
                        return "";
                    }
                }
                return "";
            case "ascend":
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
                    } else {
                        System.out.println("Invalid Ascension scroll.");
                        return "";
                    }
                }
                return "";
            default:
                return input;
        }
    }

}
