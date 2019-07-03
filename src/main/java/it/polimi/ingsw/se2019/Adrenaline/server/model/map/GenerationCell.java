package it.polimi.ingsw.se2019.Adrenaline.server.model.map;

import it.polimi.ingsw.se2019.Adrenaline.server.model.Board;
import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.AmmotileCard;
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

    private ArrayList<WeaponCard> containedWeapon;

    public GenerationCell(int cellID) {
       super(cellID);
       //generation cell type = 1
       this.type = 1;
       containedWeapon = new ArrayList<>();
    }

    public ArrayList<WeaponCard> getWeaponCard(){
        return containedWeapon;
    }
    //position = 0 represent the most left card, position = 1,represent the middle, position = 2 represent the right most
    //public WeaponCard getWeaponCard(int cardPosition) {
    //    return containedWeapon[cardPosition];
    //}
    public AmmotileCard getAmmotileCard(){ return null; }
    @Override
    public void reload(Board board) {
        String color = new String();
        switch (cellID){
            case 3: color = "blue"; break;
            case 5: color = "red"; break;
            case 12: color = "yellow"; break;
        }
        while(containedWeapon.size()<3){
        containedWeapon.add(board.extractWeaponWithColor(color));
        }
    }
    public void weaponTaken(int cardPosition){
        containedWeapon.remove(cardPosition);
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