package it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards;

import it.polimi.ingsw.se2019.Adrenaline.server.model.Color;
import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.Status;
import org.fusesource.jansi.Ansi;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * This is WeaponCard verify the  json file and use it in the weaponCardDeck
 *
 *
 * @author li xuejing
 *
 */
public class WeaponCard implements Status {


    private String cardName;
    private int[] basicammoCost;
    private int basicDamageVision;
    private int[] damageDeal;
    private String basicEffect;
    private String[] specialEffectName;
    private int[]specialAmmoCost;
    private String[] specialEffect;
    private String notes;
    private int weaponType;
    private int effectDamageVision;
    private String image;
    private String color;


    /**
     *
     * This is getColor is to get the weaponcard's color
     *
     * @return color is a string  of weaponcard's color
     *
     */
    public String getColor() { return color; }

    /**
     *
     * This is setColor is to set the weaponcard's color
     *
     * @param color is a value  of weaponcard's color
     *
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     *
     * This is getEffectDamageVision is to get the weaponcard's effectdamagevision
     *
     * @return effectdamagevision is a string  of weaponcard's effectdamagevision
     *
     */
    public int getEffectDamageVison() {
        return effectDamageVision;
    }

    /**
     *
     * This is setEffectDamageVision is to set the weaponcard's effectdamagevision
     *
     * @param effectDamageVison is a value  of weaponcard's effectdamagevision
     *
     */

    public void setEffectDamageVision(int effectDamageVison) {
        this.effectDamageVision = effectDamageVison;
    }

    /**
     *
     * This is getType is to get the weaponcard's weapontype
     *
     * @return weapontype is a value  of weaponcard's weapontype
     *
     */

    public int getType() {
        return weaponType;
    }

    /**
     *
     * This is setWeaponType is to get the weaponcard's weapontype
     *
     * @param   weaponType is a value  of weaponcard's weapontype
     *
     */

    public void setWeaponType(int weaponType) {
        this.weaponType = weaponType;
    }

    /**
     *
     * This is getCardName is to get the weaponcard's cardname
     *
     * @return cardname is a arraylist  of weaponcard's cardname
     *
     */

    public String getCardName(){

        return cardName;
    }

    /**
     *
     * This is setCardName is to get the weaponcard's cardname
     *
     * @param   cardName is a arraylist  of weaponcard's cardname
     *
     */

    public void setCardName(String cardName){

        this.cardName = cardName;
    }

    /**
     *
     * This is getBasicammoCost is to get the weaponcard's basicammocost
     *
     * @return basicammocost is a arraylist  of weaponcard's basicammocost
     *
     */

    public int[] getBasicammoCost(){

        return basicammoCost;
    }

    /**
     *
     * This is setBasicammoCost is to set the weaponcard's basicammocost
     *
     * @param   basicammoCost is a arraylist  of weaponcard's basicammocost
     *
     */

    public void setBasicammoCost(int[] basicammoCost){

        this.basicammoCost = basicammoCost;
    }

    /**
     *
     * This is setBasicDamageVision is to set the weaponcard's basicdamagevision
     *
     * @param   basicDamageVision is a value of weaponcard's basicdamagevision
     *
     */

    public void setBasicDamageVision(int basicDamageVision) {
        this.basicDamageVision = basicDamageVision;
    }

    /**
     *
     * This is getBasicDamageVision is to get the weaponcard's basicdamagevision
     *
     * @return basicdamagevision is a value  of weaponcard's basicdamagevision
     *
     */

    public int getBasicDamageVision() {
        return basicDamageVision;
    }

    /**
     *
     * This is getDamageDeal is to get the weaponcard's damageDeal
     *
     * @return  damageDeal is a arraylist  of weaponcard's damageDeal
     *
     */

    public int[] getDamageDeal(){

        return damageDeal;
    }

    /**
     *
     * This is setDamageDeal is to set the weaponcard's damageDeal
     *
     * @param   damageDeal is a arraylist  of weaponcard's damageDeal
     *
     */

    public void setDamageDeal(int[] damageDeal){

        this.damageDeal = damageDeal;
    }

    /**
     *
     * This is getBasicEffect is to get the weaponcard's basiceffect
     *
     * @return basiceffect is a string  of weaponcard's basiceffect
     *
     */
    public String getBasicEffect(){

        return basicEffect;
    }

    /**
     *
     * This is setBasicEffect is to set the weaponcard's basicEffect
     *
     * @param   basicEffect is a arraylist  of weaponcard's basicEffect3
     *
     */

    public void setBasicEffect(String basicEffect){

        this.basicEffect = basicEffect;
    }

    /**
     *
     * This is getSpecialEffectName is to get the weaponcard's specialEffectName
     *
     * @return   specialEffectName is a arraylist  of weaponcard's specialEffectName
     *
     */
    public String[] getSpecialEffectName(){

        return specialEffectName;
    }

    /**
     *
     * This is setSpecialEffectName is to set the weaponcard's specialEffectName
     *
     * @param   specialEffectName is a arraylist  of weaponcard's specialEffectName
     *
     */

    public void setSpecialEffectName(String[] specialEffectName){

        this.specialEffectName = specialEffectName;
    }

    /**
     *
     * This is getSpecialAmmoCost is to get the weaponcard's specialAmmoCost
     *
     * @return  specialAmmosCost is a arraylist  of weaponcard's specialeAmmoCost
     *
     */

    public int[] getSpecialAmmoCost(){

        return specialAmmoCost;
    }

    /**
     *
     * This is setSpecialAmmoCost is to set the weaponcard's specialAmmoCost
     *
     * @param specialAmmoCost is a arraylist  of weaponcard's specialAmmoCost
     *
     */

    public void setSpecialAmmoCost(int[] specialAmmoCost){

        this.specialAmmoCost = specialAmmoCost;
    }

    /**
     *
     * This is getSpecialEffect is to get the weaponcard's specialeffect
     *
     * @return  specialEffect is a arraylist  of weaponcard's specialeffect
     *
     */

    public String[] getSpecialEffect(){

        return specialEffect;
    }

    /**
     *
     * This is setSpecialEffect is to set the weaponcard's specialeffect
     *
     * @param specialEffect is a arraylist  of weaponcard's specialeffect
     *
     */

    public void setSpecialEffect(String[] specialEffect){

        this.specialEffect = specialEffect;
    }

    /**
     *
     * This is getNotes is to get the weaponcard's notes
     *
     * @return notes is a image of weaponcard's notes
     *
     */

    public String getNotes(){

        return notes;
    }

    /**
     *
     * This is setImage is to set the weaponcard's notes
     *
     * @param  notes is a image of weaponcard's notes
     *
     */

    public void setNotes(String notes){

        this.notes = notes;
    }

    /**
     *
     * This is getImage is to get the weaponcard's image
     *
     * @return image is a image of weaponcard's image
     *
     */

    public String getImage(){
        return image;
    }

    /**
     *
     * This is setImage is to set the weaponcard's image
     *
     * @param image is a string of weaponcard's image
     *
     */
    public void setImage(String image){
        this.image = image;
    }

    @Override
    public String toString() {
        return "WeaponCard{" +
                "cardName='" + cardName + '\'' +
                "image" + image +'\'' +
                ", basicammoCost=" + Arrays.toString(basicammoCost) +
                ", damageVision=" + basicDamageVision +
                ", damageDeal=" + Arrays.toString(damageDeal) +
                ", basicEffect='" + basicEffect + '\'' +
                ", specialEffectName=" + Arrays.toString(specialEffectName) +
                ", specialAmmoCost=" + Arrays.toString(specialAmmoCost) +
                ", specialEffect=" + Arrays.toString(specialEffect) +
                ", notes='" + notes + '\'' +
                '}';
    }

    public Ansi toAnsi() {
        Ansi ansi = new Ansi().a("WeaponCard{" +
                "cardName='" + cardName + '\'' +
                "image" + image +'\'' +
                ", basicammoCost=" + Arrays.toString(basicammoCost) +
                ", damageVision=" + basicDamageVision +
                ", damageDeal=" + Arrays.toString(damageDeal) +
                ", basicEffect='" + basicEffect + '\'' +
                ", specialEffectName=" + Arrays.toString(specialEffectName) +
                ", specialAmmoCost=" + Arrays.toString(specialAmmoCost) +
                ", specialEffect=" + Arrays.toString(specialEffect) +
                ", notes='" + notes + '\'' +
                '}');
        return ansi;
    }


}