package it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards;

import com.google.gson.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AmmotileCardDeck {

    private static Object AmmotileCard;
    private BufferedReader reader;

    public class AmmotileCard {

        List<AmmotileCard> ammotileCardList = new ArrayList<>();

        public List<AmmotileCard> deserialize() {

            Gson gson = new Gson();


            try {

                reader = new BufferedReader(new FileReader("src/mian/java/it.polimi.ingsw.se2019.Adrenaline/resource/Json/AmmotileCard.json"));
                JsonParser parser = new JsonParser();
                JsonObject json = parser.parse(reader).getAsJsonObject();

                JsonArray AmmotileCardArray = json.getAsJsonArray("ammotileCardDeck");

                for (JsonElement ammotileCardElement : AmmotileCardArray) {

                    String ammotileCardJson = ammotileCardElement.getAsString();

                    AmmotileCard ammotileCard= gson.fromJson(ammotileCardJson, AmmotileCard.class);

                    ammotileCardList.add(ammotileCard);
                }

                System.out.println(AmmotileCard);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return ammotileCardList;

        }
    }
}
