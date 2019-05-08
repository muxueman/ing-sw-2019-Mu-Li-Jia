package it.polimi.ingsw.se2019.Adrenaline.server.model.ArrayListCards;

import com.google.gson.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WeaponCardDeck {

    private static Object WeaponCard;

    public class WeaponCard {

        List<WeaponCard> weaponCardList = new ArrayList<>();

        public List<WeaponCard> deserialize() {

            Gson gson = new Gson();

            try {

                BufferedReader reader = new BufferedReader(new FileReader("src/mian/java/it.polimi.ingsw.se2019.Adrenaline/resource/Json/WeaponCard.json"));
                JsonParser parser = new JsonParser();
                JsonObject json = parser.parse(reader).getAsJsonObject();

                JsonArray weaponCardArray = json.getAsJsonArray("weaponCardDeck");

                for (JsonElement weaponCardElement : weaponCardArray) {

                    JsonObject weaponCardObject = weaponCardElement.getAsJsonObject();

                    WeaponCard weaponCard= gson.fromJson(reader, WeaponCard.class);

                    weaponCardList.add(weaponCard);
                }


                System.out.println(WeaponCard);

            } catch (IOException e) {
                e.printStackTrace();
            }

            return weaponCardList;

        }
    }

}

