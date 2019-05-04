package it.polimi.ingsw.se2019.Adrenaline.server.model.ArrayListCards;

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

    public String toString(){
        return '{' +
                "cardName='" + cardName + '\'' +
                ", basicammoCost='" + ammoCost + '\'' +
                ", damageDeal='" + damageDeal + '\'' +
                ", effect='" + effect + '\'' +
                ", note='" + note + '\'' +
                '}';

    }

}
