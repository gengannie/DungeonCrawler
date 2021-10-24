package ui;

import java.io.FileNotFoundException;

// Runnable file: where you start interacting with console interface
// Initializes game world and iterates until game is over

public class ConsoleInterface {
    // This method references code from this CPSC210/JsonSerializationDemo
    // Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

    //EFFECTS: runs the GameWorldApp until exception is caught or user terminates
    public static void main(String[] args) {
        try {
            new GameWorldApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }

}