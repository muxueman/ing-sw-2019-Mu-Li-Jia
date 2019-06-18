package it.polimi.ingsw.se2019.Adrenaline.utils.immutables;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Map;
import org.fusesource.jansi.Ansi;

import java.util.ArrayList;
import java.util.List;

public class BoardStatus implements Status {

    private List<PlayerStatus> players;
    private PlayerStatus thisPlayer;
    private Map map;
    protected static int numKillShoot;
    protected int thisKillShoot;

    private boolean started;

    /**
     * The constructor initializes all the attributes.
     * @param skull is the number of turn they choose, map is the map they choose
     */
    public BoardStatus(int skull) {
        players = new ArrayList<>();
        thisPlayer = null;
        numKillShoot = skull;
        thisKillShoot = 0;

    }




}
