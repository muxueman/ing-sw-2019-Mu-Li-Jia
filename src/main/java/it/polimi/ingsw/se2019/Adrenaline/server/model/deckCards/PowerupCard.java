package it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards;

import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.Status;
import org.fusesource.jansi.Ansi;

import java.util.ArrayList;
import java.util.Arrays;


public class PowerupCard implements Status {

    private String cardName;
    private String ammoCost;
    private int[] damageDeal;
    private String effect;
    private String note;
    private String Color;

    public String getCardName(){

        return cardName;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
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

    public int[] getDamageDeal(){

        return damageDeal;
    }
    public void setDamageDeal(int[] damageDeal){

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
    public String toString() {
        return "PowerupCard{" +
                "cardName='" + cardName + '\'' +
                ", ammoCost='" + ammoCost + '\'' +
                ", damageDeal=" + Arrays.toString(damageDeal) +
                ", effect='" + effect + '\'' +
                ", note='" + note + '\'' +
                '}';
    }


    public Ansi toAnsi() {
        Ansi ansi = new Ansi().a("PowerupCard{" +
                "cardName='" + cardName + '\'' +
                ", ammoCost='" + ammoCost + '\'' +
                ", damageDeal=" + Arrays.toString(damageDeal) +
                ", effect='" + effect + '\'' +
                ", note='" + note + '\'' +
                '}');
        return ansi;
    }
}
