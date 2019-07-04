package it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards;

import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.Status;
import javafx.scene.image.Image;
import org.fusesource.jansi.Ansi;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * This is PowerupCard verify the  json file and use it in the powerupCardDeck
 *
 *
 * @author li xuejing
 *
 */

public class PowerupCard implements Status {

    private String cardName;
    private String ammoCost;
    private int[] damageDeal;
    private String effect;
    private String note;
    private String Color;
    private String image;

    /**
     *
     * This is getCardName is to get the powerupcard's name
     *
     * @return cardName is a string of cardname
     *
     */

    public String getCardName(){

        return cardName;
    }

    /**
     *
     * This is setCardName is to get the powerupcard's
     *
     * @param cardName is a string of cardname
     *
     */


    public void setCardName(String cardName){

        this.cardName = cardName;
    }

    /**
     *
     * This is getColore is to get the weaponcard's color
     *
     * @return color is a string of colorname
     *
     */

    public String getColor() {
        return Color;
    }

    /**
     *
     * This is setColor is to set the powerupcard's color
     *
     * @param color is a string of colorname
     *
     */

    public void setColor(String color) {
        Color = color;
    }

    /**
     *
     * This is getAmmoCost is to get the powerupcard's will cost how much ammo
     *
     * @return  ammoCost is a string of colorname
     *
     */

    public String getAmmoCost(){

        return ammoCost;
    }

    /**
     *
     * This is setAmmoCost is to set the powerupcard's ammocost
     *
     * @param ammoCost is a string of how many ammocost or null
     *
     */

    public void setAmmoCost(String ammoCost){

        this.ammoCost = ammoCost;
    }

    /**
     *
     * This is getDamageDeal is to get the info about can give others how much damageDeal
     *
     * @return damageDeal if a  arraylist of damagedeal
     *
     */

    public int[] getDamageDeal(){

        return damageDeal;
    }

    /**
     *
     * This is setDamageDeeal is to set the powerupcard's color
     *
     * @param damageDeal is a arraylist of damagedeal
     *
     */

    public void setDamageDeal(int[] damageDeal){

        this.damageDeal = damageDeal;
    }

    /**
     *
     * This is getEffect is to get the powerupcard's effect
     *
     * @return  effect is a string of powerupcard's effect
     *
     */

    public String getEffect() {

        return effect;
    }

    /**
     *
     * This is setEffect is to set the powerupcard's effect
     *
     * @param effect is a string of powerupcard's effect
     *
     */

    public void setEffect(String effect) {

        this.effect = effect;
    }

    /**
     *
     * This is getNote is to get the powerupcard's note
     *
     * @return note is a string of powerupcard's note
     *
     */

    public String getNote() {

        return note;
    }

    /**
     *
     * This is setNote is to set the powerupcard's note
     *
     * @param note is a string of powerupcard's note
     *
     */

    public void setNote(String note) {

        this.note = note;
    }

    /**
     *
     * This is getImage is to get the powerupcard's image
     *
     * @return image is a image of powerupcard's image
     *
     */

    public String getImage(){
        return image;
    }

    /**
     *
     * This is setImage is to set the powerupcard's image
     *
     * @param image is a string of powerupcard's image
     *
     */

    public void setImage(String image){
        this.image = image;
    }

    @Override
    public String toString() {
        return "PowerupCard{" +
                "cardName='" + cardName + '\'' +
                "image" + image +'\'' +
                ", ammoCost='" + ammoCost + '\'' +
                ", damageDeal=" + Arrays.toString(damageDeal) +
                ", effect='" + effect + '\'' +
                ", note='" + note + '\'' +
                '}';
    }


    public Ansi toAnsi() {
        Ansi ansi = new Ansi().a("PowerupCard{" +
                "cardName='" + cardName + '\'' +
                "image" + image +'\'' +
                ", ammoCost='" + ammoCost + '\'' +
                ", damageDeal=" + Arrays.toString(damageDeal) +
                ", effect='" + effect + '\'' +
                ", note='" + note + '\'' +
                '}');
        return ansi;
    }
}
