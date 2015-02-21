/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eldur;

import java.util.Scanner;

/**
 *
 * @author Oliver
 */
public class Adventure extends Screen {

    private GameData gameData;
    private Character player;

    public Adventure(String sN, GameData gD) {
        super(sN);
        this.gameData = gD;
    }

    @Override
    public Screen runScreen() {
        reader = new Scanner(System.in);
        // Re-initialize
        this.player = new Character(gameData);

        // Potential loop
        //Enemy enemy = new Enemy("Slime", 100, 100);
        while (true) {
            Encounter encounter = new Encounter(new Enemy("Slime", 10000, 1000));

            while (encounter.outcome.equals("")) {

                String connection = encounter.turn();
                if (connections.get(connection) != null) {// TODO Needs proper feedback
                    return connections.get(connection);
                }
            }
            if (encounter.outcome.equals("pWin")) {
                System.out.println("Another enemy incoming...");
            } else {
                if (encounter.outcome.equals("pLose")) {
                    System.out.println("debug");
                    this.player = new Character(gameData);
                    encounter.outcome = "";
                }
            }
        }
    }

    @Override
    public String interpret(String input) {
        return input;
    }

    public class Encounter {

        //private Character player;
        private Enemy enemy;
        private String outcome; // int here String below

        public Encounter(Enemy en) {
            //this.player = c;
            this.enemy = en;
            this.outcome = "";
            System.out.println("A " + this.enemy.getName() + " has appeared!");
//            outcome = 0;
//            while (outcome == 0) {
//                outcome = turn();
//            }
        }

        public String turn() {
            String input = "";
            this.outcome = "noAction";
            preturn();
            //while (turnOutcome.equals("noAction")) {
            String[] afterAction = playerAction();
            input = afterAction[0];
            this.outcome = afterAction[1];
            if (this.outcome.equals("")) {
                afterAction = enemyAction();
                this.outcome = afterAction[1];
                postturn();
            }
            //return new String[]{input, this.outcome};
            return input;
        }

        public void preturn() {

        }

        public String[] playerAction() {
            String input = "";
            String pOutcome = "noAction"; // Outcome of the actions of the player
            while (pOutcome.equals("noAction")) {
                System.out.println("Your action: ");
                input = reader.nextLine();
                String[] inputParts = input.split("\\s");
                // Resolve actions
                switch (inputParts[0]) {
                    case "where":
                        reportScreen();
                        input = "";
                        //pOutcome = "noAction";
                        break;
                    case "quit":
                        System.out.println("Thanks for playing!");
                        System.exit(0);
                    case "a":
                    case "attack":
                        input = "";
                        pOutcome = "";
                        // begin Player will attack
                        pOutcome = player.attack(enemy);
                        System.out.println(enemy.getName() + "'s HP is " + enemy.getHp());
                        if (pOutcome.equals("pWin")) {
                            System.out.println("You have successfully defeated " + enemy.getName());
                            //outcome = "pWin";
                        }
                        // end   Player will attack
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
                            if (0 <= invPos && invPos < gameData.inventory.size()) {
                                sw = gameData.inventory.get(invPos);
                                //gameData.setEquippedSword(sw);
                                player.unsheathe(sw);
                                System.out.println("You have equipped " + sw.getName() + ".");
                                input = "";
                                pOutcome = "";
                            } else {
                                System.out.println("Sword not found.");
                                input = "";
                            }
                        }
                        break;
                    default:
                        if (connections.get(input) != null) {
                            System.out.println("Do you really want to retreat from this encounter? ('y' to confirm)");
                            if (reader.nextLine().equals("y")) {
                                // input is not changed
                                pOutcome = "switchScreen";
                            } else {
                                input = "";
                                // pOutcome is not changed
                            }
                        } else {
                            input = "";
                            //pOutcome = "noAction";
                            System.out.println("Invalid action. Please enter again.");
                        }
                }
            }
            return new String[]{input, pOutcome};
        }

        public String[] enemyAction() {
            String input = "";
            String eOutcome = "";
            System.out.println(enemy.getName() + " has made a move!");
            eOutcome = enemy.attack(player);
            // Resolve actions
            System.out.println("Your HP is " + player.getHp());
            if (eOutcome.equals("pLose")) {
                input = "back";
                System.out.println("You have been defeated by " + enemy.getName() + ". Reviving yourself...");
            }
            return new String[]{input, eOutcome};
        }

        public void postturn() {

        }
    }
}
