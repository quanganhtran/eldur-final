/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eldur;

import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Anh
 */
public class Screen {
    protected Scanner reader;
    protected String screenName;
    private GameData gameData;
    protected HashMap<String,Screen> connections = new HashMap<>();
    protected boolean unlocked;
        
    public Screen(String sN) {
        screenName = sN;
        this.unlocked = true;
    }
    
    public Screen(String sN, boolean unl) {
        this(sN);
        this.unlocked = unl;
    }
    
//    public Screen(HashMap<String,Screen> conn) {
//        connections = conn;
//    }
    
    public void addConnection(String cmd, Screen targetScreen) {
        connections.put(cmd, targetScreen);
    }
    
    public void unlock() {
        this.unlocked = true;
    }
    
    public void reportScreen() {
        System.out.println("This is " + screenName + ".");
    }
    
    public Screen runScreen() {
        reader = new Scanner(System.in);
        while (true) {
            System.out.println("Enter a command: ");
            String input = reader.nextLine();
            String connection = interpret(input);

            if (connections.get(connection) != null) {
                if (connections.get(connection).unlocked) {
                    return connections.get(connection);
                } else {
                    System.out.println("You cannot go to that area yet.");
                }
            } else {
                //System.out.println("Invalid command. Please try again");
            }
        }
    }
    
    public String interpret(String input) {
        switch (input) {
            case "where":
                reportScreen();
                return "";
            case "quit":
                System.out.println("Thanks for playing!");
                System.exit(0);
        }
        return input;
    }
}
