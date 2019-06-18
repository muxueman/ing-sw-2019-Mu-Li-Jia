package it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards;

import com.google.gson.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Li xuejing
 *
 *
 */


public class WeaponCardDeck {


    public ArrayList<WeaponCard> weaponCards;
    BufferedReader reader = null;
    public WeaponCardDeck() {

        weaponCards = new ArrayList<>();

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

    }

    @Override
    public String toString() {
        return "WeaponCardDeck{" +
                "weaponCards=" + weaponCards +
                '}';
    }
}