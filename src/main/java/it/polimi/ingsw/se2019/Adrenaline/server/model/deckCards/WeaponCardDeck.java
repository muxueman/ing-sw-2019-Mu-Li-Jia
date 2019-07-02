package it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards;

import com.google.gson.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;


public class WeaponCardDeck implements Serializable {


    public ArrayList<WeaponCard> weaponCards;
    public ArrayList<WeaponCard> redWeapons;
    public ArrayList<WeaponCard> yellowWeapons;
    public ArrayList<WeaponCard> blueWeapons;
    BufferedReader reader = null;
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
            e.printStackTrace();
        }finally {
            {
                if(null != reader ){
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        for(WeaponCard w : weaponCards){
            switch (w.getColor()){
                case "yellow": yellowWeapons.add(w); break;
                case "red": redWeapons.add(w); break;
                case "blue": blueWeapons.add(w); break;
            }
        }
    }

    @Override
    public String toString() {
        return "WeaponCardDeck{" +
                "weaponCards=" + weaponCards +
                '}';
    }
}