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
    public GameData gameData;
    public Character player;
    private ArrayList<Enemy> enemyTypes;

    public Adventure(String sN, GameData gD) {
        super(sN);
        this.gameData = gD;
        this.enemyTypes = new ArrayList<>();
        //System.out.println(enemyTypes.size());
    }

    @Override
    public Screen runScreen() {
        //reader = new Scanner(System.in);
        // Re-initialize
        this.player = new Character(gameData);
//        this.area = gameData.currentArea;

        // Potential loop
        //Enemy enemy = new Enemy("Slime", 100, 100);
        while (true) {
            Encounter encounter = new Encounter(new Enemy(enemyTypes.get(rng.nextInt(enemyTypes.size()))), this);
            while (encounter.outcome.equals("outOfCombat")) {
                encounter.outOfCombat();
            }
            while (encounter.outcome.equals("")) { // Turns happen until the encounter's outcome is decided.
                encounter.turn();
            }
            if (encounter.outcome.equals("pWin")) {
                System.out.println("Another enemy incoming...");
            } else if (encounter.outcome.equals("pLose")) {
                this.player = new Character(gameData);
            } else if (encounter.outcome.equals("callBoss")) {
                
            } else if (encounter.outcome.equals("switchScreen")) {
                return connections.get(encounter.encInput);
            }
        }
    }

//    @Override
//    public String interpret(String input) {
//        return input;
//    }

    public void addEnemy(Enemy en) {
        this.enemyTypes.add(en);
    }

}
