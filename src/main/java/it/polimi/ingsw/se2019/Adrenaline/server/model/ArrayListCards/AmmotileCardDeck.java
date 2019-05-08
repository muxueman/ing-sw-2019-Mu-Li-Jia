package it.polimi.ingsw.se2019.Adrenaline.server.model.ArrayListCards;

import com.google.gson.*;
import it.polimi.ingsw.se2019.Adrenaline.server.model.ArrayListCards.WeaponCard;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AmmotileCardDeck extends AmmotileCard {

    private static Object AmmotileCard;

    public class AmmotileCard {

        List<AmmotileCard> ammotileList = new ArrayList<>();


        public List<AmmotileCard> deserialize() {
            Gson gson = new Gson();

            try {
                FileReader fileReader = new FileReader("src/mian/java/it.polimi.ingsw.se2019.Adrenaline/resource/Json/AmmotileCard.json");
                BufferedReader reader = new BufferedReader(fileReader);
                JsonParser parser = new JsonParser();
                JsonObject json = parser.parse(reader).getAsJsonObject();

                JsonArray ammotileArray = json.getAsJsonArray("ammotileDeck");

                for(JsonElement ammotileElement : ammotileArray) {

                    JsonObject ammoObject = ammotileElement.getAsJsonObject();

                    AmmotileCard ammotileCard = gson.fromJson(reader, AmmotileCard.class);

                    ammotileList.add(ammotileCard);
                }


                System.out.println(AmmotileCard);

            } catch (IOException e) {
                e.printStackTrace();
            }

            return ammotileList;
        }
    }
}