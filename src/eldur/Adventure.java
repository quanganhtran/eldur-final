/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eldur;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Oliver
 */
public class Adventure extends Screen {

    private Random rng = new Random();
    private GameData gameData;
    private Character player;
    private ArrayList<Enemy> enemyTypes;

    public Adventure(String sN, GameData gD) {
        super(sN);
        this.gameData = gD;
        this.enemyTypes = new ArrayList<>();
        //System.out.println(enemyTypes.size());
    }

    @Override
    public Screen runScreen() {
        reader = new Scanner(System.in);
        // Re-initialize
        this.player = new Character(gameData);
//        this.area = gameData.currentArea;

        // Potential loop
        //Enemy enemy = new Enemy("Slime", 100, 100);
        while (true) {
            Encounter encounter = new Encounter(new Enemy(enemyTypes.get(rng.nextInt(enemyTypes.size()))));
            while (encounter.outcome.equals("outOfCombat")) {
                encounter.outOfCombat();
            }
//            if (this.connections.get(oocInput) != null) {
//                return this.connections.get(oocInput);
//            } else if (!oocInput.equals("y")) {
//                encounter.outcome = "pAvoid";
//            }

            while (encounter.outcome.equals("")) { // Turns happen until the encounter's outcome is decided.

                //String getInput = encounter.turn();
                encounter.turn();
//                if (connections.get(connection) != null) {// TODO Needs proper feedback
//                    return connections.get(connection);
//                }
            }
            if (encounter.outcome.equals("pWin")) {
                System.out.println("Another enemy incoming...");
            } else if (encounter.outcome.equals("pLose")) {
                //System.out.println("debug");
                this.player = new Character(gameData);
                //encounter.outcome = "";
            } else if (encounter.outcome.equals("switchScreen")) {
                return connections.get(encounter.encInput);
            }
        }
    }

    @Override
    public String interpret(String input) {
        return input;
    }

    public void addEnemy(Enemy en) {
        this.enemyTypes.add(en);
    }

    public class Encounter {

        //private Character player;
        private Enemy enemy;
        private String outcome; // int here String below
        private String encInput;

        public Encounter(Enemy en) {
            //this.player = c;
            this.enemy = en;
            this.outcome = "outOfCombat";
            System.out.println("A " + this.enemy.getName() + " has appeared!");
            this.encInput = "";
//            outcome = 0;
//            while (outcome == 0) {
//                outcome = turn();
//            }
        }

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
            //}
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

        }

        private void playerAction() {
            //String this.encInput = "";
            this.outcome = "noAction"; // Outcome of the actions of the player
            while (this.outcome.equals("noAction")) {
                System.out.println("Your action: ");
                this.encInput = reader.nextLine();
                String[] inputParts = this.encInput.split("\\s");
                // Resolve actions
                switch (inputParts[0]) {
                    case "a":
                    case "attack":
                        this.encInput = "";
                        this.outcome = "";
                        // begin Player will attack
                        this.outcome = player.attack(enemy);
                        if (!this.outcome.equals("pWin")) {
                            System.out.println(enemy.getName() + "'s HP is " + enemy.getHp());
                        } else {
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
                    reportScreen();
                    System.out.println("'back' - Back to Town");
                    this.encInput = "";
                    //pOutcome = "noAction";
                    break;
                case "quit":
                    System.out.println("Thanks for playing!");
                    System.exit(0);
                default:
                    if (connections.get(this.encInput) != null) {
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
                        //pOutcome = "noAction";
                        System.out.println("Invalid action. Please enter again.");
                    }
            }
        }

        private void enemyAction() {
            //String this.encInput = "";
            //String this.outcome = "";
            System.out.println(enemy.getName() + " has made a move!");
            this.outcome = enemy.attack(player);
            // Resolve actions
            if (!this.outcome.equals("pLose")) {
                System.out.println("Your HP is " + player.getHp());
            } else {
                this.encInput = "back";
                System.out.println("You have been defeated by " + enemy.getName() + ". Reviving yourself...");
            }
            //return new String[]{this.encInput, this.outcome};
        }

        private void postturn() {

        }
    }
}
