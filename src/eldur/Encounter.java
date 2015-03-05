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
public class Encounter {

    //private Character player;
    private Scanner reader;
    private Enemy enemy;
    String outcome; // int here String below
    String encInput;
    private Adventure adventure;

    public Encounter(Enemy en, Adventure adv) {
        reader = new Scanner(System.in);
        this.adventure = adv;
        //this.player = c;
        this.enemy = en;
        this.outcome = "outOfCombat";
        System.out.println(this.enemy.getName() + " has appeared!");
        this.encInput = "";
    }

//    public Encounter(Enemy en, final Adventure adv, String initO) {
//        this(en, adv);
//        this.outcome = initO;
//    }
    public void outOfCombat() {
        //OUTER:
        //while (true) {
        System.out.println("Do you want to engage? ('y' to engage, 'n' to skip)");
        this.encInput = reader.nextLine();
        switch (this.encInput) {
            case "":
            case "y":
                this.encInput = "";
                this.outcome = "";
                break;
            case "n":
                // Skip the encounter.
                this.outcome = "pAvoid";
                break;
            default:
                //this.outcome = "pAvoid";
                defaultHandler();
        }
    }

    public String turn() {
        //String this.encInput = "";
        //this.outcome = "noAction";
        preturn();
        playerAction();
        if (this.outcome.equals("")) {
            enemyAction();
            if (!this.outcome.equals("pLose")) {
                postturn();
            }
        }
        return this.encInput;
    }

    public void preturn() {
        Skill usableSkill = adventure.gameData.equippedSword.getSkill();
        if (usableSkill != null) {
            int cooldownCounter = usableSkill.getCooldownCounter();
            if (cooldownCounter == 0) {
                System.out.println("Skill is available.");
            } else {
                usableSkill.setCooldownCounter(cooldownCounter - 1);
            }
        }
    }

    public void playerAction() {
        //String this.encInput = "";
        this.outcome = "noAction"; // Outcome of the actions of the player
        while (this.outcome.equals("noAction")) {
            System.out.println("========================================"); // Clear a line for visibility
            System.out.println("\u001B[34mYour action: \u001B[0m");
            this.encInput = reader.nextLine();
            System.out.println("--------------------"); // Clear a line for visibility
            String[] inputParts = this.encInput.split("\\s");
            // Resolve actions
            switch (inputParts[0]) {
                case "":
                case "a":
                case "attack":
                    this.encInput = "";
                    this.outcome = "";
                    // begin Player will attack
                    this.outcome = adventure.player.attack(enemy);
                    if (!this.outcome.equals("pWin")) {
                        System.out.println("\u001B[31m" + enemy.getName() + "'s HP is \u001B[0m" + enemy.getHp());
                    } else {
                        System.out.println("You have successfully defeated " + enemy.getName());
                        //outcome = "pWin";
                    }
                    // end   Player will attack
                    break;
                case "skill":
                    if (adventure.gameData.equippedSword.activateSkill(adventure.gameData.equippedSword.getSkill(), adventure.player, enemy)) {
                        adventure.gameData.equippedSword.getSkill().setCooldownCounter(adventure.gameData.equippedSword.getSkill().getCooldown());
                        this.outcome = "";
                    }
                    this.encInput = "";
                    break;
                case "e":
                case "equip":
                    if (inputParts.length >= 2) {
                        int invPos = -1;
                        try {
                            invPos = Integer.parseInt(inputParts[1]) - 1;
                        } catch (NumberFormatException numberFormatException) {
                            System.out.println("Sword index must be a number.");
                            break;
                        }
                        Sword sw = null;
                        if (0 <= invPos && invPos < adventure.gameData.inventory.size()) {
                            sw = adventure.gameData.inventory.get(invPos);
                            //gameData.setEquippedSword(sw);
                            adventure.player.unsheathe(sw);
                            System.out.println("You have equipped " + sw.getName() + ".");
                            this.encInput = "";
                            this.outcome = ""; // Equip successfully
                        } else {
                            System.out.println("Sword not found.");
                            this.encInput = "";
                        }
                    }
                    break;
                default:
                    defaultHandler();
            }
        }
        //return new String[]{this.encInput, this.outcome};
    }

    public void defaultHandler() {
        switch (this.encInput) {
            case "where":
                adventure.reportScreen();
                System.out.println("'back' - Back to Town");
                this.encInput = "";
                //pOutcome = "noAction";
                break;
            case "quit":
                System.out.println("Thanks for playing!");
                System.exit(0);
            default:
                if (adventure.getConnections().get(this.encInput) != null) {
                    if (adventure.getConnections().get(this.encInput).unlocked) {
                        System.out.println("Do you really want to leave this area? ('y' to confirm)");
                        if (reader.nextLine().equals("y")) {
                            // input is not changed
                            this.outcome = "switchScreen";
                        } else {
                            this.encInput = "";
                            // pOutcome is not changed
                        }
                    } else {
                        this.encInput = "";
                        System.out.println("You cannot go to that area yet.");
                    }
                } else {
                    this.encInput = "";
                    //pOutcome = "noAction";
                    System.out.println("Invalid action. Please enter again.");
                }
        }
    }

    public void enemyAction() {
        //String this.encInput = "";
        //String this.outcome = "";
        System.out.println(enemy.getName() + " has made a move!");
        this.outcome = enemy.attack(adventure.player);
        // Resolve actions
        if (!this.outcome.equals("pLose")) {
            System.out.println("\u001B[32mYour HP is \u001B[0m" + adventure.player.getHp());
        } else {
            this.encInput = "back";
            System.out.println("\n\u001B[31mYou have been defeated by " + enemy.getName() + ". Reviving yourself...\u001B[0m");
        }
        //return new String[]{this.encInput, this.outcome};
    }

    public void postturn() {

    }

}
