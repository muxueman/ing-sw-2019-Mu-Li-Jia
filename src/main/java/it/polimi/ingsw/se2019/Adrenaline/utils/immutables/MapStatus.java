package it.polimi.ingsw.se2019.Adrenaline.utils.immutables;

import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Cell;
import org.fusesource.jansi.Ansi;

import java.util.ArrayList;

import static org.fusesource.jansi.Ansi.ansi;

public class MapStatus implements Status {
    protected ArrayList<Cell> allCells;

    public MapStatus(){
    }
    public Ansi toAnsi(){
        return ansi().a("allcells:" + allCells);
    }

}
