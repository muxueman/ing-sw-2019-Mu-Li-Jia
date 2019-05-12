package it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards;

import com.google.gson.*;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class PowerupCardDeck {



    public ArrayList<PowerupCard> ppCards;
    BufferedReader reader = null;

    public PowerupCardDeck() {

        ppCards = new ArrayList<>();


        try {
            Gson gson = new Gson();
            reader = new BufferedReader(new FileReader("src/main/resource/json/PowerupCard.json"));
            JsonParser parser = new JsonParser();
            JsonObject json = parser.parse(reader).getAsJsonObject();

            JsonArray powerupCardArray = json.getAsJsonArray("powerupCardDeck");

            for (JsonElement powerupCardElement : powerupCardArray) {

                PowerupCard powerupCardReaded = gson.fromJson(powerupCardElement.toString(),PowerupCard.class);
                ppCards.add(powerupCardReaded);

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
        return "PowerupCardDeck{" +
                "powerupCards=" +  +
                '}';
    }
}

