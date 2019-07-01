package it.polimi.ingsw.se2019.Adrenaline.utils.immutables;

import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.WeaponCard;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Cell;
import org.fusesource.jansi.Ansi;

import java.util.ArrayList;

import static org.fusesource.jansi.Ansi.ansi;
//so far no use

public class MapStatus implements Status{

    private ArrayList<Cell> allCells;

    public MapStatus(){
    }
    public ArrayList<Cell> getAllCells(){
        return this.allCells;
    }

    public Ansi toAnsi(){
        return ansi().a("allcells:" + allCells);
    }

}
