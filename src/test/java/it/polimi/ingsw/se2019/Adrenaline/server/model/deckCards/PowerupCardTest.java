package it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.Assert.*;

public class PowerupCardTest {

    PowerupCard powerupCard = new PowerupCard();

    @Before
    public void setUp() throws Exception {

        System.out.println("test powerupcard");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("test end.");
    }

    @Test
    public void testCardName() {

        powerupCard.setCardName("TARGETINE SCOPE");
        System.out.println("cardName: " + powerupCard.getCardName());

    }


    @Test
    public void testAmmoCost() {

        powerupCard.setAmmoCost("Choose 1 ammo(anyone)");
        System.out.println("ammoCost:" + powerupCard.getAmmoCost());

    }


    @Test
    public void testDamageDeal() {

        ArrayList<Integer> damageDeal = new ArrayList<>();
        damageDeal.add(0);
        damageDeal.add(1);
        damageDeal.add(0);
        powerupCard.setDamageDeal(damageDeal);

        System.out.println("damageDeal: " + powerupCard.getDamageDeal());
    }


    @Test
    public void testEffect() {

        powerupCard.setEffect("You may play this card when you are dealing damage to one or more targets.Pay 1 ammo cube of any color.Choose 1 of those targets and give it an extra point of damage. ");
        System.out.println("effect:" + powerupCard.getEffect());

    }



    @Test
    public void testNote() {

        powerupCard.setNote("You cannot use this to do 1 damage to a target that is receiving only marks.");
        System.out.println("note: " + powerupCard.getNote());

    }

}