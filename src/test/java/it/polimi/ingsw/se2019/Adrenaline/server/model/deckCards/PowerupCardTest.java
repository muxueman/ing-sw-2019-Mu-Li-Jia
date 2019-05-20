package it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


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

        String cardName;
        cardName = "TARGETINE SCOPE";
        powerupCard.setCardName(cardName);
        System.out.println("cardName: " + powerupCard.getCardName());
        Assert.assertEquals("TARGETINE SCOPE",powerupCard.getCardName());

    }


    @Test
    public void testAmmoCost() {
        String ammoCost;
        ammoCost = "Choose 1 ammo(anyone)";
        powerupCard.setAmmoCost(ammoCost);
        System.out.println("ammoCost:" + powerupCard.getAmmoCost());
        Assert.assertEquals("Choose 1 ammo(anyone)",powerupCard.getAmmoCost());

    }


    @Test
    public void testDamageDeal() {

        int[] damageDeal;
        damageDeal = new int[]{0,1,0};
        powerupCard.setDamageDeal(damageDeal);
        Assert.assertEquals(damageDeal,powerupCard.getDamageDeal());

    }


    @Test
    public void testEffect() {

        String effect;
        effect = "You may play this card when you are dealing damage to one or more targets.Pay 1 ammo cube of any color.Choose 1 of those targets and give it an extra point of damage. ";
        powerupCard.setEffect(effect);
        System.out.println("effect:" + powerupCard.getEffect());
        Assert.assertEquals(effect,powerupCard.getEffect());

    }



    @Test
    public void testNote() {

        String note;
        note = "You cannot use this to do 1 damage to a target that is receiving only marks.";
        powerupCard.setNote(note);
        System.out.println("note: " + powerupCard.getNote());
        Assert.assertEquals(note,powerupCard.getNote());

    }
    @Test
    public void readPowerupCardTest() {

     //   PowerupCardDeck powerupCardDeck = new PowerupCardDeck();
     //   System.out.println(powerupCardDeck.toString());

    }


}