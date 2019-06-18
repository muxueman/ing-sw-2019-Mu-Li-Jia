package it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Li xuejing
 *
 *
 */

public class WeaponCard {


    private String cardName;
    private int[] basicammoCost;
    private int damageVision;
    private int[] damageDeal;
    private String basicEffect;
    private String[] specialEffectName;
    private int[]specialAmmoCost;
    private String[] specialEffect;
    private String notes;

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

    public int getDamageVision(){

        return damageVision;
    }
    public void setDamageVision(int damageVision){

        this.damageVision = damageVision;
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

    @Override
    public String toString() {
        return "WeaponCard{" +
                "cardName='" + cardName + '\'' +
                ", basicammoCost=" + Arrays.toString(basicammoCost) +
                ", damageVision=" + damageVision +
                ", damageDeal=" + Arrays.toString(damageDeal) +
                ", basicEffect='" + basicEffect + '\'' +
                ", specialEffectName=" + Arrays.toString(specialEffectName) +
                ", specialAmmoCost=" + Arrays.toString(specialAmmoCost) +
                ", specialEffect=" + Arrays.toString(specialEffect) +
                ", notes='" + notes + '\'' +
                '}';
    }
}



