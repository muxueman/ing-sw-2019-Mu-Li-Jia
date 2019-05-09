package it.polimi.ingsw.se2019.Adrenaline.server.model.arrayListCards;

import java.util.ArrayList;

public class PowerupCard {

    private String cardName;
    private String ammoCost;
    private ArrayList<Integer> damageDeal;
    private String effect;
    private String note;

    public String getCardName(){

        return cardName;
    }
    public void setCardName(String cardName){

        this.cardName = cardName;
    }

    public String getAmmoCost(){

        return ammoCost;
    }
    public void setAmmoCost(String ammoCost){

        this.ammoCost = ammoCost;
    }

    public ArrayList<Integer> getDamageDeal(){

        return damageDeal;
    }
    public void setDamageDeal(ArrayList<Integer> damageDeal){

        this.damageDeal = damageDeal;
    }

    public String getEffect() {

        return effect;
    }
    public void setEffect(String effect) {
        this.effect = effect;
    }

    public String getNote() {

        return note;
    }
    public void setNote(String note) {

        this.note = note;
    }

    @Override
    public String toString(){

        String resultString = "[";

        for(int i = 0; i < 4 ; ++i ){

            resultString += "{" + "\n";
            resultString += "cardName=" + cardName + "\n";
            resultString += "ammoCost='" + ammoCost + "\n";
            resultString += "damageDeal='" + damageDeal + "\n";
            resultString += "effect='" + effect + "\n";
            resultString += "note='" + note + "\n";
            resultString += "}\n";
        }

        resultString = "]";

        return  resultString;


    }
}
