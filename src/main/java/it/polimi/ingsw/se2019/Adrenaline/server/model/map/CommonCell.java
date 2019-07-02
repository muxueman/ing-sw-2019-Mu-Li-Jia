package it.polimi.ingsw.se2019.Adrenaline.server.model.map;

import com.sun.org.apache.xerces.internal.util.Status;
import com.sun.org.glassfish.gmbal.AMXMetadata;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Board;
import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.AmmotileCard;
import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.WeaponCard;
import org.fusesource.jansi.Ansi;

import java.util.ArrayList;

import static org.fusesource.jansi.Ansi.ansi;

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
    public Ansi toAnsi(){
        return ansi().a("cell players:" + cellPlayers.toString());
    }
    @Override
    public String toString(){
        return "cell players:" + cellPlayers.toString() ;
    }
}

