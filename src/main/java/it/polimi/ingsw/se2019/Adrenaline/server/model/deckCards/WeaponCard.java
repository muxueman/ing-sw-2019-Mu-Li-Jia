package it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards;

import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.Status;
import org.fusesource.jansi.Ansi;

import java.util.ArrayList;
import java.util.Arrays;


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
    private int effectDamageVison;
    private String image;

    public int getEffectDamageVison() {
        return effectDamageVison;
    }

    public void setEffectDamageVison(int effectDamageVison) {
        this.effectDamageVison = effectDamageVison;
    }

    public int getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(int weaponType) {
        this.weaponType = weaponType;
    }

    public String getCardName(){

        return cardName;
    }
    public void setCardName(String cardName){

        this.cardName = cardName;
    }

    public int[] getBasicammoCost(){

        return basicammoCost;
    }
    public void setBasicammoCost(int[] basicammoCost){

        this.basicammoCost = basicammoCost;
    }

    public void setBasicDamageVision(int basicDamageVision) {
        this.basicDamageVision = basicDamageVision;
    }

    public int getBasicDamageVision() {
        return basicDamageVision;
    }

    public int[] getDamageDeal(){

        return damageDeal;
    }
    public void setDamageDeal(int[] damageDeal){

        this.damageDeal = damageDeal;
    }

    public String getBasicEffect(){

        return basicEffect;
    }
    public void setBasicEffect(String basicEffect){

        this.basicEffect = basicEffect;
    }

    public String[] getSpecialEffectName(){

        return specialEffectName;
    }
    public void setSpecialEffectName(String[] specialEffectName){

        this.specialEffectName = specialEffectName;
    }

    public int[] getSpecialAmmoCost(){

        return specialAmmoCost;
    }
    public void setSpecialAmmoCost(int[] specialAmmoCost){

        this.specialAmmoCost = specialAmmoCost;
    }

    public String[] getSpecialEffect(){

        return specialEffect;
    }
    public void setSpecialEffect(String[] specialEffect){

        this.specialEffect = specialEffect;
    }

    public String getNotes(){

        return notes;
    }
    public void setNotes(String notes){

        this.notes = notes;
    }
    public String getImage(String image){
        return image;
    }
    public void setImage(String image){
        this.image = image;
    }

    @Override
    public String toString() {
        return "WeaponCard{" +
                "cardName='" + cardName + '\'' +
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