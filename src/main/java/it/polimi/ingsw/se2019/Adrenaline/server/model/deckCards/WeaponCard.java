package it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards;

import java.util.ArrayList;

public class WeaponCard {


    private String cardName;
    private ArrayList<Integer> basicammoCost;
    private Integer damageVision;
    private ArrayList<Integer> damageDeal;
    private String basicEffect;
    private ArrayList<String>specialEffectName;
    private ArrayList<Integer>specialAmmoCost;
    private ArrayList<String>specialEffect;
    private String notes;

    public String getCardName(){

        return cardName;
    }
    public void setCardName(String cardName){

        this.cardName = cardName;
    }

    public ArrayList<Integer> getBasicammoCost(){

        return basicammoCost;
    }
    public void setBasicammoCost(ArrayList<Integer> basicammoCost){

        this.basicammoCost = basicammoCost;
    }

    public Integer getDamageVision(){

        return damageVision;
    }
    public void setDamageVision(Integer damageVision){

        this.damageVision = damageVision;
    }

    public ArrayList<Integer> getDamageDeal(){

        return damageDeal;
    }
    public void setDamageDeal(ArrayList<Integer> damageDeal){

        this.damageDeal = damageDeal;
    }

    public String getBasicEffect(){
        return basicEffect;
    }
    public void setBasicEffect(String basicEffect){

        this.basicEffect = basicEffect;
    }

    public java.util.ArrayList<String> getSpecialEffectName(){

        return specialEffectName;
    }
    public void setSpecialEffectName(ArrayList<String> specialEffectName){

        this.specialEffectName = specialEffectName;
    }

    public ArrayList<Integer> getSpecialAmmoCost(){

        return specialAmmoCost;
    }
    public void setSpecialAmmoCost(ArrayList<Integer> specialAmmoCost){

        this.specialAmmoCost = specialAmmoCost;
    }

    public java.util.ArrayList<String> getSpecialEffect(){

        return specialEffect;
    }
    public void setSpecialEffect(ArrayList<String> specialEffect){

        this.specialEffect = specialEffect;
    }

    public String getNotes(){

        return notes;
    }
    public void setNotes(String notes){

        this.notes = notes;
    }

    @Override
    public String toString(){

        String resultString = "[";

        for(int i = 0; i < 21 ; ++i ){

            resultString += "{" + "\n";
            resultString += "cardName=" + cardName + "\n";
            resultString += "basicammoCost='" + basicammoCost + "\n";
            resultString += "damageVision='" + damageVision + "\n";
            resultString += "damageDeal='" + damageDeal + "\n";
            resultString += "basiceffect='" + basicEffect + "\n";
            resultString += "specialEffectName='" + specialEffectName + "\n";
            resultString += "specialAmmoCost='" + specialAmmoCost + "\n";
            resultString += "specialEffect='" + specialEffect + "\n";
            resultString += "notes='" + notes + "\n";
            resultString += "}\n";
        }

        resultString = "]";

        return  resultString;


    }


}



