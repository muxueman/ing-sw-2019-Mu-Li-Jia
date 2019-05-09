package it.polimi.ingsw.se2019.Adrenaline.server.model.arrayListCards;

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

    @Override
    public String toString(){

        String resultString = "[";

        for(int i = 0; i < 12 ; ++i ){
            resultString += "{" + "\n";
            resultString += "numAmmotileCard=" + numAmmotileCard + "\n";
            resultString += "ammoColor='" + ammoColor + "\n";
            resultString += "totalnumCard='" + totalnumCard + "\n";
            resultString += "}\n";
        }

        resultString = "]";

        return  resultString;


    }


}


