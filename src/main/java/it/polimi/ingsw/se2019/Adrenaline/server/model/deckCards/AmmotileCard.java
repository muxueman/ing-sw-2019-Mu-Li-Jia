package it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Li xuejing
 *
 *
 */

public class AmmotileCard {

    public int numAmmotileCard;
    public int[] ammoColor;
    public int totalnumCard;

    public int getNumAmmotileCard(){

        return numAmmotileCard;
    }
    public void setNumAmmotileCard(int numAmmotileCard){

        this.numAmmotileCard = numAmmotileCard;
    }

    public int[] getAmmoColor() {
        return ammoColor;
    }

    public void setAmmoColor(int[] ammoColor) {
        this.ammoColor = ammoColor;
    }

    public int getTotalnumCard() {

        return totalnumCard;
    }

    public void setTotalnumCard(int totalnumCard) {

        this.totalnumCard = totalnumCard;
    }



    @Override
    public String toString() {
        return "AmmotileCard{" +
                "numAmmotileCard=" + numAmmotileCard +
                ", ammoColor=" + Arrays.toString(ammoColor) +
                ", totalnumCard=" + totalnumCard +
                '}';
    }
}


