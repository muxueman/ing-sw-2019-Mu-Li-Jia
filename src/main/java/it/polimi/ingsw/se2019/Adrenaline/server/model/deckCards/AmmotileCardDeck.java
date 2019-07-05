package it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards;

import com.google.gson.*;
import sun.rmi.runtime.Log;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Logger;

/**
 *
 * This ammotileCardDeck is use to use Gson to verify the messege and get info about all the
 * AmmotileCards , and collect it to the json file
 *
 * @author li xuejing
 *
 */

public class AmmotileCardDeck implements Serializable {


    private ArrayList<AmmotileCard> atCards;
    private transient BufferedReader reader;
    public AmmotileCardDeck() {
        atCards = new ArrayList<>();
        try {
            Gson gson = new Gson();
            reader = new BufferedReader(new FileReader("src/main/resource/json/AmmotileCard.json"));
            JsonParser parser = new JsonParser();
            JsonObject json = parser.parse(reader).getAsJsonObject();
            JsonArray ammotileCardArray = json.getAsJsonArray("ammotileCardDeck");
            for (JsonElement ammotileCardElement : ammotileCardArray) {
                AmmotileCard ammotileCardReaded = gson.fromJson(ammotileCardElement.toString(),AmmotileCard.class);
                atCards.add(ammotileCardReaded);
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
        Collections.shuffle(atCards);
    }

    public ArrayList<AmmotileCard> getAtCards() {
        return atCards;
    }

    @Override
    public String toString() {
        return "AmmotileCardDeck{" +
                "ammotileCards=" + atCards +
                '}';
    }
}
