package it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards;

import com.google.gson.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WeaponCardDeck {

    private static Object WeaponCard;
    private BufferedReader reader;

    public class WeaponCard {

        List<WeaponCard> weaponCardList = new ArrayList<>();

        public List<WeaponCard> deserialize() {

            Gson gson = new Gson();


            try {

                reader = new BufferedReader(new FileReader("src/mian/java/it.polimi.ingsw.se2019.Adrenaline/resource/Json/WeaponCard.json"));
                JsonParser parser = new JsonParser();
                JsonObject json = parser.parse(reader).getAsJsonObject();

                JsonArray weaponCardArray = json.getAsJsonArray("weaponCardDeck");

                for (JsonElement weaponCardElement : weaponCardArray) {

                    String weaponCardJson = weaponCardElement.getAsString();

                    WeaponCard weaponCard= gson.fromJson(weaponCardJson, WeaponCard.class);

                    weaponCardList.add(weaponCard);
                }


                System.out.println(WeaponCard);

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
            return weaponCardList;

        }
    }
}
