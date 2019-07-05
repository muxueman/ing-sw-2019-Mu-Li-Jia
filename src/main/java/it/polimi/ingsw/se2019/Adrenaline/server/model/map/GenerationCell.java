package it.polimi.ingsw.se2019.Adrenaline.server.model.map;

import it.polimi.ingsw.se2019.Adrenaline.server.model.Board;
import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.AmmotileCard;
import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.WeaponCard;
import java.util.ArrayList;

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
    public AmmotileCard getAmmotileCard(){ return null; }
    @Override
    public void reload(Board board) {
        String color = "";
        switch (cellID){
            case 3: color = "blue"; break;
            case 5: color = "red"; break;
            case 12: color = "yellow"; break;
            default: break;
        }
        while(containedWeapon.size()<3){
        containedWeapon.add(board.extractWeaponWithColor(color));
        }
    }
    public void weaponTaken(int cardPosition){
        containedWeapon.remove(cardPosition);
    }

    @Override
    public String toString(){
        return "Generation Cell";
    }
}