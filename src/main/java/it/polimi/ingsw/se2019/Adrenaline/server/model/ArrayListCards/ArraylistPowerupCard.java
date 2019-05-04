package it.polimi.ingsw.se2019.Adrenaline.server.model.ArrayListCards;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class ArraylistPowerupCard extends PowerupCard{

    private static Object PowerupCard;
    public class PowerupCard{
        public  void main(String[] args)
        {
            Gson gson = new Gson();

            try(Reader reader = new FileReader("/resource/Json/PowerupCard.json"))
            {
                PowerupCard powerupCard = gson.fromJson(reader,PowerupCard.class);

                System.out.println(powerupCard);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

}
