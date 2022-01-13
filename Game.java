import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Game {
    List<Account> accounts;
    Map<Cell.cellType,List<String>> storiesMap;
    //singleton pattern
    private static Game instance = null;
    private Game(){
        storiesMap = new HashMap<>();
        accounts = new ArrayList<>();
    }
    public static Game getInstance(){
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }
    void run(){
        //read JSON file for stories
        File input = new File("/Users/crinagherase/Desktop/JAVA/Game/src/stories.json");
        try {
            JsonElement file = JsonParser.parseReader(new FileReader(input));
            JsonObject fileObject = file.getAsJsonObject();
            JsonArray fileArray = fileObject.get("stories").getAsJsonArray();
            Cell.cellType myCell = null;
            for(JsonElement i:fileArray){
                JsonObject j = i.getAsJsonObject();
                String key = j.get("type").getAsString();
                String story = j.get("value").getAsString();
                //create a cellType identifier
                if(key.compareTo("EMPTY")==0)
                    myCell = Cell.cellType.EMPTY;
                if(key.compareTo("ENEMY")==0)
                    myCell = Cell.cellType.ENEMY;
                if(key.compareTo("SHOP")==0)
                    myCell = Cell.cellType.SHOP;
                if(key.compareTo("FINISH")==0)
                    myCell = Cell.cellType.FINISH;
                int ok = 0;
                //if key already exist in map
                for(Cell.cellType k : storiesMap.keySet()){
                    if(k.compareTo(myCell) == 0){
                        storiesMap.get(k).add(story);
                        ok = 1;
                    }
                }
                if(ok == 0){
                    List<String> value = new ArrayList<String>();
                    value.add(story);
                    storiesMap.put(myCell, value);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //read JSON file for accounts, characters, etc
        File inputAcc = new File("/Users/crinagherase/Desktop/JAVA/Game/src/accounts.json");
        try {
            JsonElement fileAcc = JsonParser.parseReader(new FileReader(inputAcc));
            JsonObject fileObj = fileAcc.getAsJsonObject();
            JsonArray fileArr = fileObj.get("accounts").getAsJsonArray();
            for(JsonElement i:fileArr){
                ArrayList<String> forFavGames = new ArrayList<>();
                ArrayList<Character> characterArrayList = new ArrayList<>();
                JsonObject j = i.getAsJsonObject();
                JsonObject credential = j.get("credentials").getAsJsonObject();
                String email = credential.get("email").getAsString();
                String password = credential.get("password").getAsString();
                String name = j.get("name").getAsString();
                String country = j.get("country").getAsString();
                JsonArray favGames = j.get("favorite_games").getAsJsonArray();
                for(JsonElement a:favGames){
                    String game = a.getAsString();
                    forFavGames.add(game);
                }
                Integer mapsCompleted = j.get("maps_completed").getAsInt();
                JsonArray characters = j.get("characters").getAsJsonArray();
                for(JsonElement b:characters) {
                    JsonObject ch = b.getAsJsonObject();
                    String characterName = ch.get("name").getAsString();
                    String profession = ch.get("profession").getAsString();
                    Integer level = ch.get("level").getAsInt();
                    Integer experience = ch.get("experience").getAsInt();
                    //factory design pattern
                    Create create = new Create();
                    characterArrayList.add(create.getEntity(profession,characterName,level,experience));
                }
                Credentials c1 = new Credentials(email, password);
                accounts.add(new Account(c1,name,mapsCompleted,characterArrayList,country,forFavGames));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
//used factory design pattern
class Create{
    public Character getEntity(String profession, String characterName, int level, int experience){
        if(profession.compareTo("Warrior") == 0)
            return new Warrior(characterName,level,experience);
        if(profession.compareTo("Mage") == 0)
            return new Mage(characterName,level,experience);
        if(profession.compareTo("Rogue") == 0)
            return new Rogue(characterName,level,experience);
        return null;
    }
}
