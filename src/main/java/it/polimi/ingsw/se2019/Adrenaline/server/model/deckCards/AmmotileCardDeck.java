package it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;


public class AmmotileCardDeck implements Serializable {


    public ArrayList<AmmotileCard> atCards;
    BufferedReader reader = null;
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
        atCards.addAll(atCards);
        atCards.addAll(atCards);
        Collections.shuffle(atCards);
    }

    @Override
    public String toString() {
        return "AmmotileCardDeck{" +
                "ammotileCards=" + atCards +
                '}';
    }
}
