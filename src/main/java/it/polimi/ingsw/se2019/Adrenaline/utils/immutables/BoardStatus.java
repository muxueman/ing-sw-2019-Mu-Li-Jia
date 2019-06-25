package it.polimi.ingsw.se2019.Adrenaline.utils.immutables;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Color;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Player;
import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.PowerupCard;
import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.PowerupCardDeck;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Map;
import org.fusesource.jansi.Ansi;

import java.util.ArrayList;
import java.util.List;

public class BoardStatus implements Status {

    protected int[] numDamageOnSkullBoard;
    protected Color[] colorDamageOnSkullBoard; //与num一一对应
    protected Player currentPlayer;
    protected PlayerStatus thisPlayer;
    protected static int numKillShoot;
    protected int killTurn;
    protected boolean firenzyTriggerd;
    protected ArrayList<Player> allPlayers;
    protected Map map;
    private List<PlayerStatus> players;
    protected PowerupCardDeck powerupCardDeck;
    //记录第几次的射杀

    private boolean started;

    /**
     * The constructor initializes all the attributes.
     */
    public BoardStatus(int skull){
        currentPlayer = null;
        numKillShoot = skull;
        killTurn = 0;
        firenzyTriggerd = false;
        powerupCardDeck = new PowerupCardDeck();
    }

    //construct a board with all players
    public BoardStatus(ArrayList<Player> allPlayers){
        this.allPlayers = allPlayers;
        numKillShoot = 5;//default value
        killTurn = 0;
        firenzyTriggerd = false;
    }

    public BoardStatus(Map map){
        allPlayers = new ArrayList<>();
        this.map = map;
        map.initialMap();
        currentPlayer = null;
        killTurn = 0;
        firenzyTriggerd = false;
        players = new ArrayList<>();
        powerupCardDeck = new PowerupCardDeck();
    }


    public void setPlayers(ArrayList<Player> allPlayers) {
        for(Player player: allPlayers){
            players.add((PlayerStatus)player);
        }
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void setNumKillShoot(int numKillShoot) {
        BoardStatus.numKillShoot = numKillShoot;
    }
    public PlayerStatus getPlayer(String playerID) {
        for (PlayerStatus p : players) {
            if (p.getPlayerID().equals(playerID)) {
                return p;
            }
        }
        return null;
    }
    public boolean updatePlayer(PlayerStatus newPlayerStatus) {
        PlayerStatus player = getPlayer(newPlayerStatus.getPlayerID());
        if (player == null) {
            players.add(newPlayerStatus);
            if (thisPlayer == null) {
                thisPlayer = newPlayerStatus;
            }
        } else {
            int playerIndex = players.indexOf(player);
            players.remove(playerIndex);
            players.add(playerIndex, newPlayerStatus);
            if (newPlayerStatus.equals(thisPlayer)) {
                thisPlayer = newPlayerStatus;
            }
        }
        return true;
    }
    @Override
    public String toString(){
        return "map: " + map.getMapInfo() + map + "\n" + "numkill: " + numKillShoot+ "\n" + "firenzy:" + firenzyTriggerd
                + "\n" + "allplayer:" + players.get(0).toString();
    }
}
