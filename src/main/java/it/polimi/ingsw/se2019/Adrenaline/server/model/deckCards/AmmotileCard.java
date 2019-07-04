package it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards;

import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.Status;
import org.fusesource.jansi.Ansi;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * This is AmmotileCard verify the  json file and use it in the AmmotileCardDeck
 *
 *
 * @author li xuejing
 *
 */

public class AmmotileCard implements Status {

    private int numAmmotileCard;
    private int[] ammoColor;
    private int totalnumCard;
    private String image;
    private String content;

    /**
     *The getContent methods is use to get the content ammo color
     *
     * @return content string ammotlie color
     *
     */
    public String getContent() {
        return content;
    }

    /**
     *The setContent methods is use to set the content ammo color
     *
     * @param content is the string of content
     *
     */

    public void setContent(String content) {
        this.content = content;
    }

    /**
     *The getNumAmmotileCard methods is use to get the AmmotileCard number
     *
     * @return integer number of AmmotileCard
     *
     */

    public int getNumAmmotileCard(){

        return numAmmotileCard;
    }

    /**
     *The setNumAmmotileCard methods is use to set the AmmotileCard number
     *
     * @param numAmmotileCard is the value of NumAmmotileCard
     *
     */

    public void setNumAmmotileCard(int numAmmotileCard){

        this.numAmmotileCard = numAmmotileCard;
    }

    /**
     *The getAmmoColor methods is use to get the ammocolor
     *
     * @return arraylist of ammocolor
     *
     */


    public int[] getAmmoColor() {
        return ammoColor;
    }

    /**
     *The setAmmoColor methods is use to set the arraylist ammocolor
     * 1 is red ,2 is blue ,3 is yellow
     *
     * @param ammoColor is the arraylist of ammocolor
     *
     */


    public void setAmmoColor(int[] ammoColor) {
        this.ammoColor = ammoColor;
    }

    /**
     *The getTotalnumCard methods is use to get the value of how many this ammotilecard have
     *
     *
     * @return totalnumCard is the value of how many this ammotilecard have
     *
     */

    public int getTotalnumCard() {

        return totalnumCard;
    }

    /**
     *The setTotalnumCard methods is use to get the value of how many this ammotilecard have
     *
     *
     * @param totalnumCard is the value of how many this ammotilecard have
     */


    public void setTotalnumCard(int totalnumCard) {

        this.totalnumCard = totalnumCard;
    }

    /**
     *The getImage methods is use to get the image of this ammotilecard
     *
     *
     * @return the image of this ammotilecard
     */


    public String getImage(){
        return image;
    }

    /**
     *The setImage is use to get the image through the url about image string and image location
     *
     *
     * @param image  the image of this ammotilecard
     */
    public void setImage(String image){
        this.image = image;
    }

    /**
     *
     * The toString methods is use to print the info about the ammotilecard that the player get
     *
     *
     */

    @Override
    public String toString() {
        return "AmmotileCard{" +
                "numAmmotileCard=" + numAmmotileCard +
                "image" + image +
                ", ammoColor=" + Arrays.toString(ammoColor) +
                ", totalnumCard=" + totalnumCard +
                '}';
    }

    /**
     *
     * The toAnsi methods is use to get the info about the ammotilecard that the player get
     *
     *
     */

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


