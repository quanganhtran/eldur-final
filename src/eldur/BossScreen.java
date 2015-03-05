/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eldur;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Anh
 */
public class BossScreen extends Adventure {

    private Random rng = new Random();
    private ArrayList<Enemy> enemyWaves;
    private String story;
    private String storyAfter;

    public BossScreen(String sN, GameData gD) {
        super(sN, gD);
        this.enemyWaves = new ArrayList<>();
    }

    @Override
    public Screen runScreen() {
        System.out.println(getStory());
        this.player = new Character(gameData);
        for (Enemy en : this.enemyWaves) {
            Encounter encounter = new Encounter(en, this);
            encounter.outcome = ""; // Player engages immediately.
            while (encounter.outcome.equals("")) { // Turns happen until the encounter's outcome is decided.
                encounter.turn();
            }
            if (encounter.outcome.equals("pWin")) {
                System.out.println("Another enemy incoming...");
            } else if (encounter.outcome.equals("pLose")) {
                return getConnections().get("back");
            } else if (encounter.outcome.equals("switchScreen")) {
                return getConnections().get(encounter.encInput);
            }
        }
        // Do something more here.
        System.out.println("Congratulations! You have cleared the area!");
        System.out.println(getStoryAfter());
//        if (unlocked) {
//            
//        }
        return getConnections().get("back");
    }

    @Override
    public void addEnemy(Enemy en) {
        this.enemyWaves.add(en);
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getStoryAfter() {
        return storyAfter;
    }

    public void setStoryAfter(String storyAfter) {
        this.storyAfter = storyAfter;
    }
    
}
