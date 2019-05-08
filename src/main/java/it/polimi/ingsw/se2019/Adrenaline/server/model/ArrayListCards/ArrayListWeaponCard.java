package it.polimi.ingsw.se2019.Adrenaline.server.model.arrayListCards;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class ArrayListWeaponCard extends WeaponCard{

    private static Object WeaponCard;
    public class WeaponCard
    {
        public void main(String[] args)
        {
            Gson gson = new Gson();

            try(Reader reader = new FileReader("resource/Json/WeaponCard.json"))
            {
                WeaponCard weaponCard = gson.fromJson(reader,WeaponCard.class);

                System.out.println(WeaponCard);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

}
