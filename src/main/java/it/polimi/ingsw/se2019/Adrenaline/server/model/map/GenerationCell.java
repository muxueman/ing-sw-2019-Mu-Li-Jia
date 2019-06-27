package it.polimi.ingsw.se2019.Adrenaline.server.model.map;

import it.polimi.ingsw.se2019.Adrenaline.server.model.Board;
import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.WeaponCard;
import java.util.ArrayList;
import java.util.*;

/**
 *subclass common cell
 *@author Xueman Mu
 */

public class GenerationCell extends Cell {

    //private ArrayList<WeaponCard> containedWeaponCard;
    private WeaponCard[] containedWeapon;

    public GenerationCell(int cellID) {
       super(cellID);
       //generation cell type = 1
       this.type = 1;
       containedWeapon = new WeaponCard[3];
       initialWeapon();

    }
    public void initialWeapon(){
        WeaponCard leftCard = new WeaponCard();
        WeaponCard middelCard = new WeaponCard();
        WeaponCard rightCard = new WeaponCard();
        containedWeapon[0] = leftCard;
        containedWeapon[1] = middelCard;
        containedWeapon[2] = rightCard;
    }

    public WeaponCard[] getWeaponCard(){
        return containedWeapon;
    }
    //position = 0 represent the most left card, position = 1,represent the middle, position = 2 represent the right most
    public WeaponCard getWeaponCard(int cardPosition) {
        return containedWeapon[cardPosition];
    }

    @Override
    public void reload(Board board) {
        int i = 0;
        while(i<3){
            if(containedWeapon[i] == null){
                containedWeapon[i] = board.extractWeapon();
                break;
            }
            else i++;
        }
    }

    public void weaponTaken(int cardPosition){
        containedWeapon[cardPosition] = null;
    }

}