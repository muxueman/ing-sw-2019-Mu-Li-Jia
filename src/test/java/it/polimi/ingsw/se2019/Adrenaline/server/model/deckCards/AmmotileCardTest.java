package it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards;

import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.AmmotileCard;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AmmotileCardTest {

    AmmotileCard ammotileCard = new AmmotileCard();

    @Before
    public void setUp() throws Exception {

        System.out.println("{");

    }

    @After
    public void tearDown() throws Exception {
        System.out.println("}");
    }

    @Test
    public void testAmmotileCard() {
        ammotileCard.setTotalnumCard(1);
        ammotileCard.setTotalnumCard(3);
    }
}