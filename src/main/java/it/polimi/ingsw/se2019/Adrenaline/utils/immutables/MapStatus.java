package it.polimi.ingsw.se2019.Adrenaline.utils.immutables;

import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.WeaponCard;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Cell;
import org.fusesource.jansi.Ansi;

import java.util.ArrayList;

import static org.fusesource.jansi.Ansi.ansi;
//so far no use

public class MapStatus implements Status {

    protected ArrayList<Cell> allCells;
    protected String mapInfo;

    public MapStatus(){
    }
    public ArrayList<Cell> getAllCells(){
        return this.allCells;
    }

    //get map info
    public String getMapInfo(){
        return this.mapInfo;
    }

    public Ansi toAnsi(){
        return ansi().a("allcells:" + allCells);
    }

}
