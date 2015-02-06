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
    private Scanner reader;
    private String screenName;
    private HashMap<String,Screen> connections = new HashMap<>();
        
    public Screen(String sN) {
        screenName = sN;
    }
    
    public Screen(HashMap<String,Screen> conn) {
        connections = conn;
    }
    
    public void addConnection(String cmd, Screen targetScreen) {
        connections.put(cmd, targetScreen);
    }
    
    public class Event {
        
    }
    
    public void reportScreen() {
        System.out.println("This is " + screenName + ".");
    }
    
    public Screen onCommand() {
        reader = new Scanner(System.in);
        while (true) {
            System.out.println("Enter a command: ");
            String input = reader.nextLine();
            String connection = interpret(input);

            if (connections.get(connection) != null) {
                return connections.get(connection);
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
