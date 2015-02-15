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
            Encounter encounter = new Encounter(new Enemy("Slime", 100, 100));

            while (encounter.outcome.equals("")) {

                String connection = encounter.turn()[0];
                //System.out.println("debugmsg");
                if (connections.get(connection) != null) {//Needs proper feedback
                    return connections.get(connection);
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

        public String[] turn() {
            String input = "";
            String turnOutcome = "noAction";
            preturn();
            while (turnOutcome.equals("noAction")) {
                String[] afterAction = playerAction();
                input = afterAction[0];
                turnOutcome = afterAction[1];
                // begin Confirmation of switching screen
                if (turnOutcome.equals("switchScreen")) {
                    System.out.println("Do you really want to retreat from this encounter? ('Y' to confirm)");
                    if (!reader.nextLine().equals("Y")) {
                        input = "";
                        turnOutcome = "noAction";
                    }
                }
                // end   Confirmation of switching screen
            }
            enemyAction();
            postturn();
            return new String[]{input, turnOutcome};
        }

        public void preturn() {

        }

        public String[] playerAction() {
            String input = "";
            String pOutcome = "noAction";
            System.out.println("Your turn: ");
            while (pOutcome.equals("noAction")) {
                input = reader.nextLine();
                // Resolve actions
                switch (input) {
                    case "where":
                        reportScreen();
                        input = "";
                        pOutcome = "noAction";
                        System.out.println("Your turn: ");
                        break;
                    case "quit":
                        System.out.println("Thanks for playing!");
                        System.exit(0);
                    default:
                        if (connections.get(input) != null) {
                            pOutcome = "switchScreen";
                        } else {
                            input = "";
                            pOutcome = "noAction";
                            System.out.println("Invalid action. Please enter again: ");
                        }
                }
            }
            return new String[]{input, pOutcome};
        }

        public void enemyAction() {
            System.out.println(enemy.getName() + " has made a move!");
            enemy.attack(player);
            System.out.println("Your HP is " + player.getHp());
        }

        public void postturn() {

        }
    }
}
