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
        Enemy enemy = new Enemy("Slime", 100, 100);
        Encounter encounter = new Encounter(enemy);
        
        while (true) {
            
            
            System.out.println("Your turn: ");
            String input = reader.nextLine();
            
            String connection = interpret(input);

            if (connections.get(connection) != null) {//Needs proper feedback
                return connections.get(connection);
            }
        }
    }

    @Override
    public String interpret(String input) {
        return input;
    }
    
    public class Encounter{
        //private Character player;
        private Enemy enemy;
        private int outcome;
        
        public Encounter(Enemy en) {
            //this.player = c;
            this.enemy = en;
            System.out.println("A " + this.enemy.getName() + " has appeared!");
//            outcome = 0;
//            while (outcome == 0) {
//                outcome = turn();
//            }
        }
        
        public int turn() {
            preturn();
            playerAction();
            enemyAction();
            postturn();
            return 0;
        }
        
        public void preturn() {
            
        }
        public void playerAction() {
            
        }
        public void enemyAction() {
            
        }
        public void postturn() {
            
        }
    }
}
