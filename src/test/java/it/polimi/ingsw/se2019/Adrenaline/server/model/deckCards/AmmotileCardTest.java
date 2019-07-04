package it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class AmmotileCardTest {

    AmmotileCardDeck ammoDeck = new AmmotileCardDeck();
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
        ammotileCard = ammoDeck.getAtCards().get(0);
        ammotileCard.setTotalnumCard(3);
        System.out.println("test totalnumCard: " + ammotileCard.getTotalnumCard());
        Assert.assertEquals(3,ammotileCard.getTotalnumCard());

    }

    @Test
    public void testNumammotileCard(){

        ammotileCard.setNumAmmotileCard(1);
        System.out.println("test numAmmotileCard: " + ammotileCard.getNumAmmotileCard());
        Assert.assertEquals(1,ammotileCard.getNumAmmotileCard());

    }

    //AmmoColor 1 for red , 2 for Blue ,3 for yellow


    @Test
    public void setAmmoTest() {
        int[] ammoTest;

        ammoTest = new int[]{1,1,1};
        ammotileCard.setAmmoColor(ammoTest);
        System.out.println(ammotileCard.toString());
        Assert.assertEquals(ammoTest,ammotileCard.getAmmoColor());
    }

    @Test
    public void readAmmoTest() {

        AmmotileCardDeck ammotileCardDeck = new AmmotileCardDeck();
        System.out.println(ammotileCardDeck.toString());

    }
}