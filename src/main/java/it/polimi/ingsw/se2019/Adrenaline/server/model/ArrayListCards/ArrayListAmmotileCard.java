package it.polimi.ingsw.se2019.Adrenaline.server.model.arrayListCards;


import com.google.gson.Gson;


import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class ArrayListAmmotileCard extends AmmotileCard{

    private static Object AmmotileCard;

    public class AmmotileCard
    {

        public void main(String[] args)
        {
            Gson gson = new Gson();

            try(Reader reader = new FileReader("resource/Json/AmmotileCard.json"))
            {
                AmmotileCard ammotileCard = gson.fromJson(reader,AmmotileCard.class);
                System.out.println(AmmotileCard);

            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }


}