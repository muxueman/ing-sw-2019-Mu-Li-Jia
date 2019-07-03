package it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards;

import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.Status;
import org.fusesource.jansi.Ansi;

import java.util.ArrayList;
import java.util.Arrays;



public class AmmotileCard implements Status {

    public int numAmmotileCard;
    public int[] ammoColor;
    public int totalnumCard;
    public String image;

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
    public String getImage(){
        return image;
    }
    public void setImage(String image){
        this.image = image;
    }





    @Override
    public String toString() {
        return "AmmotileCard{" +
                "numAmmotileCard=" + numAmmotileCard +
                "image" + image +
                ", ammoColor=" + Arrays.toString(ammoColor) +
                ", totalnumCard=" + totalnumCard +
                '}';
    }

    public Ansi toAnsi() {
        Ansi ansi = new Ansi().a("AmmotileCard{" +
                "numAmmotileCard=" + numAmmotileCard +
                "image" + image +
                ", ammoColor=" + Arrays.toString(ammoColor) +
                ", totalnumCard=" + totalnumCard +
                '}');
        return ansi;
    }
}


