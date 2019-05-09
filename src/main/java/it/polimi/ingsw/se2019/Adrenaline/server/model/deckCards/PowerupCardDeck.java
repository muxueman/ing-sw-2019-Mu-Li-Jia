package it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards;

import com.google.gson.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PowerupCardDeck {

    private static Object PowerupCard;
    private BufferedReader reader;

    public class PowerupCard {

        List<PowerupCard> powerupCardList = new ArrayList<>();

        public List<PowerupCard> deserialize() {

            Gson gson = new Gson();


            try {

                reader = new BufferedReader(new FileReader("src/mian/java/it.polimi.ingsw.se2019.Adrenaline/resource/Json/PowerupCard.json"));
                JsonParser parser = new JsonParser();
                JsonObject json = parser.parse(reader).getAsJsonObject();

                JsonArray powerupCardArray = json.getAsJsonArray("PowerupCardDeck");

                for (JsonElement powerupCardElement : powerupCardArray) {

                    String powerupCardJson = powerupCardElement.getAsString();

                    PowerupCard powerupCard= gson.fromJson(powerupCardJson, PowerupCard.class);

                    powerupCardList.add(powerupCard);
                }


                System.out.println(PowerupCard);

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
            return powerupCardList;

        }
    }
}

