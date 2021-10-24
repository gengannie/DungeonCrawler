package ui;

import java.io.FileNotFoundException;

// Runnable file: where you start interacting with console interface
// Initializes game world and iterates until game is over

public class ConsoleInterface {


    public static void main(String[] args) {
        try {
            new GameWorldApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }

}