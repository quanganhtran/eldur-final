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
    private HashMap<String, Screen> connections = new HashMap<>();
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
    
    public HashMap<String, Screen> getConnections() {
        return connections;
    }

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
            System.out.println("========================================"); // Clear a line for visibility
            System.out.println("\u001B[34mEnter a command: \u001B[0m");
            String input = reader.nextLine();
            System.out.println("--------------------"); // Clear a line for visibility
            String connection = interpret(input);

            if (connections.get(connection) != null) {
                if (connections.get(connection).unlocked) {
                    return connections.get(connection);
                } else {
                    System.out.println("You cannot go to that area yet.");
                }
            } else {
                if (!connection.equals("")) {
                    System.out.println("Invalid command. Please try again");
                }
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
