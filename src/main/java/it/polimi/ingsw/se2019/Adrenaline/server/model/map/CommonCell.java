package it.polimi.ingsw.se2019.Adrenaline.server.model.map;

import it.polimi.ingsw.se2019.Adrenaline.server.model.Board;
import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.AmmotileCard;
import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.WeaponCard;
import java.util.ArrayList;

/**
 *subclass common cell
 *@author Xueman Mu
 */

public class CommonCell extends Cell {

    private AmmotileCard ammotileInCell;
    public CommonCell(int cellID) {
        super(cellID);
        this.type = 0;
        ammotileInCell = new AmmotileCard();
    }

    public void setAmmotileInCell(AmmotileCard ammotileInCell) { this.ammotileInCell = ammotileInCell;}
    public AmmotileCard getAmmotileCard(){
        return ammotileInCell;
    }
    public ArrayList<WeaponCard> getWeaponCard(){
        return null;
    }
    public void removeAmmotileCard(){
        ammotileInCell = null;
    }
    public int[] getAmmoColor(){
        return ammotileInCell.getAmmoColor();
    }

    @Override
    public void reload(Board board){
        ammotileInCell = board.extractAmmotile();
    }

    @Override
    public String toString(){
        return "cell players:" + cellPlayers.toString() ;
    }
}

