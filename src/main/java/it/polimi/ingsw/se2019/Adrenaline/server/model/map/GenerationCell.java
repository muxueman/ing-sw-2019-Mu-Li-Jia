package it.polimi.ingsw.se2019.Adrenaline.server.model.map;

import it.polimi.ingsw.se2019.Adrenaline.server.model.Board;
import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.WeaponCard;
import org.fusesource.jansi.Ansi;

import java.util.ArrayList;
import java.util.*;

import static org.fusesource.jansi.Ansi.ansi;

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
                i++;
            }
            else i++;
        }
    }
    public void weaponTaken(int cardPosition){
        containedWeapon[cardPosition] = null;
    }
    @Override
    public Ansi toAnsi(){
        return ansi().a("cell players:" + cellPlayers.toString());
    }
    @Override
    public String toString(){
        return "cell players:";
//                + cellPlayers.toString() ;
    }
}