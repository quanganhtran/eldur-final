/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eldur;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author trand
 */
public class FileReader {

    public void loadRecipe(HashMap<String, Recipe> recipes) {
        File recipesFile = new File("assets/recipes");
        Scanner recipesReader = null;
        try {
            recipesReader = new Scanner(recipesFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GameData.class.getName()).log(Level.SEVERE, "File not found", ex);
        }

        while (recipesReader.hasNextLine()) {
            String line = recipesReader.nextLine();
            String[] lineParts = line.split(",");
            recipes.put(lineParts[0], new Recipe(lineParts[1], lineParts[2], Integer.parseInt(lineParts[3]), lineParts[4], Integer.parseInt(lineParts[5]), lineParts[6], lineParts[7]));
        }

        recipesReader.close();
    }
    
    public void loadEnemy(ScreenManager s) {
        File enemiesFile = new File("assets/enemies");
        Scanner enemiesReader = null;
        try {
            enemiesReader = new Scanner(enemiesFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GameData.class.getName()).log(Level.SEVERE, "File not found", ex);
        }
        
        while (enemiesReader.hasNextLine()) {
            String line = enemiesReader.nextLine();
            String[] lineParts = line.split("</split>");
            if (!lineParts[0].endsWith("Boss")) {
                s.areaMapDict.get(lineParts[0]).addEnemy(new Enemy(lineParts[1], Integer.parseInt(lineParts[2]), Integer.parseInt(lineParts[3])));
            } else {
                s.areaToBoss.get(s.areaMapDict.get(lineParts[0].substring(0, lineParts[0].length()-4))).addEnemy(new Enemy(lineParts[1], Integer.parseInt(lineParts[2]), Integer.parseInt(lineParts[3])));
            }
        }
        //return enemy;
    }
    
    public void loadScroll(HashMap<String, IdScroll> idDict, HashMap<String, AscensionScroll> aDict) {
        File scrollsFile = new File("assets/scrolls");
        Scanner scrollsReader = null;
        try {
            scrollsReader = new Scanner(scrollsFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GameData.class.getName()).log(Level.SEVERE, "File not found", ex);
        }
        
        while (scrollsReader.hasNextLine()) {
            String line = scrollsReader.nextLine();
            String[] lineParts = line.split("</split>");
            if (lineParts[3].equals("Legendary")) {
                Sword swordFrame = new Sword(lineParts[1], Integer.parseInt(lineParts[2]), lineParts[3], Integer.parseInt(lineParts[4]), Integer.parseInt(lineParts[5]));
                swordFrame.setSkill(Skill.createFromTag(lineParts[6]));
                aDict.put(lineParts[0], new AscensionScroll(lineParts[1], swordFrame));
            } else if (lineParts[3].equals("Epic")) {
                Sword swordFrame = new Sword(lineParts[1], Integer.parseInt(lineParts[2]), lineParts[3], Integer.parseInt(lineParts[4]), Integer.parseInt(lineParts[5]));
                
                idDict.put(lineParts[0], new AscensionScroll(lineParts[1], swordFrame));
            }
        }
    }

    public void loadStory(Collection<BossScreen> bSet) {
        ArrayList<BossScreen> bossScreens = new ArrayList<>(bSet);
        File storyFile = new File("assets/story");
        Scanner storyReader = null;
        try {
            storyReader = new Scanner(storyFile).useDelimiter("\\A");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GameData.class.getName()).log(Level.SEVERE, "File not found", ex);
        }
        String allStories = storyReader.next();
        String[] storyPieces = allStories.split("<nextPart>");
        for (int i = 0; i < bSet.size(); i++) {
            if (i < storyPieces.length) {
                //if (i % 2 == 0) {
                bossScreens.get(i).setStory(storyPieces[2 * i]);
                //} else {
                bossScreens.get(i).setStoryAfter(storyPieces[2 * i + 1]);
                //}
            }
        }
        
        storyReader.close();
    }
}
