package it.polimi.ingsw.se2019.Adrenaline.server.model.arrayListCards;

import com.google.gson.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PowerupCardDeck {

    private static Object PowerupCard;

    public class PowerupCard {

        List<PowerupCard> powerupCardList = new ArrayList<>();

        public List<PowerupCard> deserialize() {

            Gson gson = new Gson();

            try {

                BufferedReader reader = new BufferedReader(new FileReader("src/mian/java/it.polimi.ingsw.se2019.Adrenaline/resource/Json/PowerupCard.json"));
                JsonParser parser = new JsonParser();
                JsonObject json = parser.parse(reader).getAsJsonObject();

                JsonArray powerupCardArray = json.getAsJsonArray("powerupCardDeck");

                for (JsonElement powerupCardElement : powerupCardArray) {

                    JsonObject powerupCardObject = powerupCardElement.getAsJsonObject();

                    PowerupCard powerupCard = gson.fromJson(reader, PowerupCard.class);

                    powerupCardList.add(powerupCard);
                }


                System.out.println(PowerupCard);

            } catch (IOException e) {
                e.printStackTrace();
            }

            return powerupCardList;

        }
    }

}



