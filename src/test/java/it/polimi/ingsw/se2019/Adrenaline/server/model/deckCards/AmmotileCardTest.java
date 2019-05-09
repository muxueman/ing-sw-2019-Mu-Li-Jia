package it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards;


import it.polimi.ingsw.se2019.Adrenaline.server.model.Color;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class AmmotileCardTest {

    AmmotileCard ammotileCard = new AmmotileCard();


    @Before
    public void setUp() throws Exception {

        System.out.println("test ammocard:");

    }
    @After
    public void tearDown() throws Exception {

        System.out.println("test end");

    }


    @Test
    public void testotalnumCard(){

        ammotileCard.setTotalnumCard(3);
        System.out.println("test totalnumCard: " + ammotileCard.getTotalnumCard());

    }

    @Test
    public void testNumammotileCard(){

        ammotileCard.setNumAmmotileCard(1);
        System.out.println("test numAmmotileCard: " + ammotileCard.getNumAmmotileCard());

    }

    //AmmoColor 1 for red , 2 for Blue ,3 for yellow

    @Test
    public void testAmmoColor(){
        ArrayList<Integer> ammoColor = new ArrayList<>();
        ammoColor.add(3);
        ammoColor.add(2);
        ammoColor.add(2);
        ammotileCard.setAmmoColor(ammoColor);
        Map<Color,Integer> testColor = new HashMap<>();
        testColor.put(Color.RED,1);
        testColor.put(Color.BLUE,2);
        testColor.put(Color.YELLOW,3);

        System.out.println(("test AmmoColor:" + ammotileCard.getAmmoColor()));

    }



}