package it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards;

import com.google.gson.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Logger;

/**
 *
 * This weaponCardDeck is use to use Gson to verify the messege and get info about all the
 * WeaponCards , and collect it to the json file
 *
 * @author li xuejing
 *
 */

public class WeaponCardDeck implements Serializable {


    private ArrayList<WeaponCard> weaponCards;
    private ArrayList<WeaponCard> redWeapons;
    private ArrayList<WeaponCard> yellowWeapons;
    private ArrayList<WeaponCard> blueWeapons;
    private transient BufferedReader reader;

    public WeaponCardDeck() {
        weaponCards = new ArrayList<>();
        redWeapons = new ArrayList<>();
        yellowWeapons = new ArrayList<>();
        blueWeapons = new ArrayList<>();

        try {
            Gson gson = new Gson();

            reader = new BufferedReader(new FileReader("src/main/resource/json/WeaponCard.json"));
            JsonParser parser = new JsonParser();
            JsonObject json = parser.parse(reader).getAsJsonObject();
            JsonArray weaponCardArray = json.getAsJsonArray("weaponCardDeck");
            for (JsonElement weaponCardElement : weaponCardArray) {
                WeaponCard weaponCardReaded = gson.fromJson(weaponCardElement.toString(), WeaponCard.class);
                weaponCards.add(weaponCardReaded);
            }
        } catch (FileNotFoundException e) {
            Logger.getGlobal().warning(e.getMessage());
        }finally {
            {
                if(null != reader ){
                    try {
                        reader.close();
                    } catch (IOException e) {
                        Logger.getGlobal().warning(e.getMessage());
                    }
                }
            }
        }
        Collections.shuffle(weaponCards);
        colorDistributed();
    }
    public void colorDistributed(){
        for(WeaponCard w : weaponCards){
            switch (w.getColor()){
                case "yellow": this.yellowWeapons.add(w); break;
                case "red": this.redWeapons.add(w); break;
                case "blue": this.blueWeapons.add(w); break;
                default: break;
            }
        }
    }

    public ArrayList<WeaponCard> getWeaponCards() {
        return weaponCards;
    }

    public ArrayList<WeaponCard> getRedWeapons() {
        return redWeapons;
    }

    public ArrayList<WeaponCard> getYellowWeapons() {
        return yellowWeapons;
    }

    public ArrayList<WeaponCard> getBlueWeapons() {
        return blueWeapons;
    }

    @Override
    public String toString() {
        return "WeaponCardDeck{" +
                "weaponCards=" + weaponCards +
                '}';
    }
}