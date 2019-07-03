package it.polimi.ingsw.se2019.Adrenaline.client.view.CLI;

import it.polimi.ingsw.se2019.Adrenaline.server.model.Board;
import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.PowerupCard;
import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.PowerupCardDeck;
import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.WeaponCard;

import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.WeaponCardDeck;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.MapA;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TestTable {
    MapA a = new MapA();
    Board board = new Board(a,5,1);
    ArrayList<PowerupCard> powerupCards = new ArrayList<>();
    //PowerupCardDeck powerupCardDeck = new PowerupCardDeck();
    WeaponCardDeck weaponCardDeck = new WeaponCardDeck();
    Map<WeaponCard,Boolean> weaponCards = new HashMap<>();

    @Test
    public void TestTable(){

        powerupCards.add(board.extractPowerupcard());
        powerupCards.add(board.extractPowerupcard());
        powerupCards.add(board.extractPowerupcard());
        powerupCards.add(board.extractPowerupcard());


        weaponCards.put(weaponCardDeck.getWeaponCards().get(0),true);
        //weaponCards.put(weaponCardDeck.weaponCards.get(1),true);
        //weaponCards.put(weaponCardDeck.weaponCards.get(3),true);

        new ShowPowerupCard(powerupCards);
        new ShowWeaponCard(weaponCards);
    }
}
