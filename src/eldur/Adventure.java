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
public class Adventure extends Screen{
    
    private GameData gameData;

    public Adventure(String sN, GameData gD) {
        super(sN);
    }
    
    @Override
    public Screen onCommand() {
        reader = new Scanner(System.in);
        while (true) {
            System.out.println("Enter a command: ");
            String input = reader.nextLine();
            String connection = interpret(input);

            if (connections.get(connection) != null) { // Needs proper feedback
                return connections.get(connection);
            }
        }
    }
    
    @Override
    public String interpret(String input) {
        return input;
    }
}
