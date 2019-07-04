package it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards;

import com.google.gson.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * This powerupCardDeck is use to use Gson to verify the messege and get info about all the
 * powerupCards , and collect it to the json file
 *
 * @author li xuejing
 *
 */

public class PowerupCardDeck implements Serializable {

    private ArrayList<PowerupCard> ppCards;
    private transient BufferedReader reader;

    public PowerupCardDeck() {
        ppCards = new ArrayList<>();

        try {
            Gson gson = new Gson();
            reader = new BufferedReader(new FileReader("src/main/resource/json/PowerupCard.json"));
            JsonParser parser = new JsonParser();
            JsonObject json = parser.parse(reader).getAsJsonObject();
            JsonArray powerupCardArray = json.getAsJsonArray("powerupCardDeck");
            for (JsonElement powerupCardElement : powerupCardArray) {
                PowerupCard powerupCardReaded = gson.fromJson(powerupCardElement.toString(), PowerupCard.class);
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
        Collections.shuffle(ppCards);
    }

    public ArrayList<PowerupCard> getPpCards() {
        return ppCards;
    }

    @Override
    public String toString() {
        return "PowerupCardDeck{" +
                "powerupCards=" +  +
                '}';
    }
}

