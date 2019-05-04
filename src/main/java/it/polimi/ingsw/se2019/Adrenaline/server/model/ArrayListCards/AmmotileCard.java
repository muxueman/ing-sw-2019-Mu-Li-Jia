package it.polimi.ingsw.se2019.Adrenaline.server.model.ArrayListCards;

import java.util.ArrayList;

public class AmmotileCard {

    private Integer numAmmotileCard;
    private ArrayList<Integer> ammoColor;
    private Integer totalnumCard;

    public Integer getNumAmmotileCard(){
        return numAmmotileCard;
    }
    public void setNumAmmotileCard(Integer numAmmotileCard){
        this.numAmmotileCard = numAmmotileCard;
    }

    public ArrayList<Integer> getAmmoColor() {
        return ammoColor;
    }

    public void setAmmoColor(ArrayList<Integer> ammoColor) {
        this.ammoColor = ammoColor;
    }

    public Integer getTotalnumCard() {
        return totalnumCard;
    }

    public void setTotalnumCard(Integer totalnumCard) {
        this.totalnumCard = totalnumCard;
    }

    public String toString(){
        return '{' +
                "numAmmotileCard='" + numAmmotileCard + '\'' +
                ", ammoColor='" + ammoColor + '\'' +
                ", totalnumCard='" + totalnumCard + '\'' +
                '}';

    }
}
