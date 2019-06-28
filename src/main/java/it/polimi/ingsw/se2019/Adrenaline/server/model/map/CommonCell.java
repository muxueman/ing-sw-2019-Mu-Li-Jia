package it.polimi.ingsw.se2019.Adrenaline.server.model.map;

import com.sun.org.glassfish.gmbal.AMXMetadata;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Board;
import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.AmmotileCard;
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
    @Override
    public AmmotileCard getAmmotileCard(){
        return ammotileInCell;
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
}

