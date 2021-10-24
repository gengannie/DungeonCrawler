package persistence;

import model.Hero;
import model.Rat;
import model.SmallMonsters;
import org.json.JSONArray;
import org.json.JSONObject;
import ui.GameWorld;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import static javax.swing.UIManager.getInt;

// This class references code from this CPSC 210/JsonSerializationDemo
// Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonReader {
    private String sourceFile;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String sourceFile) {
        this.sourceFile = sourceFile;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public GameWorld read() throws IOException {
        GameWorld newGame = new GameWorld(250);
        String jsonData = readFile(sourceFile);
        JSONObject jsonObject = new JSONObject(jsonData);
        newGame.loadWorldGrid(parseWorldGrid(jsonObject));
        newGame.loadMonsters(parseMonsters(jsonObject));
        newGame.addHeroToGame(parseHero(jsonObject));
        return newGame;
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    //EFFECTS: creates and returns a new hero based on attributes in json file
    private Hero parseHero(JSONObject jsonObject) {
        int canMove = jsonObject.getInt("Move");
        String name = jsonObject.getString("Name");
        int health = jsonObject.getInt("Hero Health");
        int mana = jsonObject.getInt("Mana");
        int posX = jsonObject.getInt("PosX");
        int posY = jsonObject.getInt("PosY");
        Hero loadHero = new Hero(canMove, name);
        loadHero.resetCardInventory();
        addCardsToInventory(loadHero, jsonObject);
        loadHero.setHealthAndMana(health, mana);
        loadHero.moveHero(posX, posY);
        loadHero.setIsDead();
        return loadHero;
    }

    //MODIFIES: loadHero
    //EFFECTS: parses card list and adds it to Hero's inventory
    private void addCardsToInventory(Hero loadHero, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Cards");
        for (int i = 0; i < jsonArray.length(); i++) {
            String nameOfCard = jsonArray.getString(i);
            if (nameOfCard.equals("Healing Potion")) {
                loadHero.getHealingPot();
            } else if (nameOfCard.equals("Freeze")) {
                loadHero.getStunCard();

            }
        }
    }

    // EFFECTS: parses ArrayList of Monsters from JSON object and returns it
    private ArrayList<SmallMonsters> parseMonsters(JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("monsters");
        ArrayList<SmallMonsters> allMonsters = new ArrayList<>();
        for (Object json : jsonArray) {
            JSONObject nextItem = (JSONObject) json;
            int posX = nextItem.getInt("posX");
            int posY = nextItem.getInt("posY");
            int health = nextItem.getInt("health");
            Boolean ifInSight = nextItem.getBoolean("ifInSight");
            Boolean canMove = nextItem.getBoolean("canMove");
            SmallMonsters addRat = new Rat(posX, posY);
            addRat.setIsDead(nextItem.getBoolean("isDead"));
            if (ifInSight) {
                addRat.changeThisSight();
            }
            if (!canMove) {
                addRat.changeCanMove();
            }
            addRat.setHealth(health);
            allMonsters.add(addRat);
        }
        return allMonsters;
    }

    // EFFECTS: parses world grid from JSON object and returns it
    private int[][] parseWorldGrid(JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("worldGrid");
        int[][] newGrid = new int[jsonArray.length()][jsonArray.length()];

        for (int i = 0; i < jsonArray.length(); i++) {
            for (int j = 0; j < jsonArray.length(); j++) {
                newGrid[i][j] = getInt(jsonArray.getJSONArray(i).get(j));
            }
        }
        return newGrid;
    }


}
