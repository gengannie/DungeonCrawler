package ui;

import model.Hero;

import java.util.Scanner;

// Runnable file: where you start interacting with console interface
// Initializes game world and iterates until game is over

public class ConsoleInterface {
    // MODIFIES: this, GameWorld
    // EFFECTS: runs the program by initializing a hero and GameWorld
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameWorld gameOne = new LevelOne(250);
        gameOne.displayIntroMessage();
        String nameOfHero = scanner.nextLine();
        Hero mainCharacter = new Hero(1, nameOfHero);
        int maxTurns = mainCharacter.getMaxTurns();
        int currentTurn = maxTurns;
        while (true) {
            while (currentTurn > 0) {
                gameOne.displayCurrWorld(mainCharacter, currentTurn);
                String input = scanner.nextLine();
                iterations(input, gameOne, mainCharacter);
                currentTurn -= 1;
            }
            gameOne.updateDeaths(mainCharacter);
            gameOne.moveMonsters(mainCharacter);
            gameOne.displayCurrWorld(mainCharacter, currentTurn);

            currentTurn = maxTurns;
        }
    }


    //REQUIRES: input to be valid string
    //EFFECTS: iterates the various options the hero has until user terminates the game
    public static void iterations(String input, GameWorld gameOne, Hero mainCharacter) {
        Scanner scanner = new Scanner(System.in);
        if (input.equals("QUIT")) {
            System.exit(0);
        } else if (input.equals("1")) {
            gameOne.displayCardInfo(mainCharacter);
            System.out.println("Which card do you want to use? Input the number associated with it");
            String whichCard = scanner.nextLine();
            int indexOfCard = Integer.parseInt(whichCard) - 1;
            gameOne.processCardBehavior(mainCharacter, indexOfCard);
            gameOne.displayHeroStats(mainCharacter);
        } else if (input.equals("2")) {
            mainCharacter.moveHero(1, 1);
        } else if (input.equals("4")) {
            gameOne.attackMonsters(mainCharacter);

        }

    }
}