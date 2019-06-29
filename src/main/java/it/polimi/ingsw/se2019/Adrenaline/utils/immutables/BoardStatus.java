package it.polimi.ingsw.se2019.Adrenaline.utils.immutables;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Color;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Player;
import it.polimi.ingsw.se2019.Adrenaline.server.model.TurnHandler;
import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.*;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Map;
import org.fusesource.jansi.Ansi;

import java.util.ArrayList;
import java.util.List;

import static org.fusesource.jansi.Ansi.ansi;

public class BoardStatus implements Status {

    protected int[] numDamageOnSkullBoard;
    protected Color[] colorDamageOnSkullBoard; //与num一一对应
    protected Player currentPlayer; //希望用turnhandler.getCurrentPlauyer
    protected PlayerStatus thisPlayer; //目前没用
    protected static int numKillShoot;
    protected int killTurn;
    protected boolean firenzyTriggerd;
    protected ArrayList<Player> allPlayers;
    protected Map map;
    private List<PlayerStatus> players;  //目前没用

    private String reconnectionToken;

//    protected TurnHandler turnHandler;
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
        reconnectionToken = "";

    }

    //construct a board with all players
    public BoardStatus(ArrayList<Player> allPlayers){
        this.allPlayers = allPlayers;
        numKillShoot = 5;//default value
        killTurn = 0;
        firenzyTriggerd = false;
        currentPlayer = allPlayers.get(0);
        reconnectionToken = "";
//        turnHandler = new TurnHandler(this);
    }

    public BoardStatus(Map map){
        this.map = map;
        map.initialMap();
        allPlayers = new ArrayList<>();
        currentPlayer = null;
        killTurn = 0;
        firenzyTriggerd = false;
        players = new ArrayList<>();

    }


    public void setPlayers(ArrayList<Player> allPlayers) {
        for(Player player: allPlayers){
            players.add((PlayerStatus)player);
        }
    }
    public void updatePlayerStatus(){
        int i = 0;
        for(Player player: allPlayers){
            players.remove(i);
            players.add((PlayerStatus) player);
        }
    }

    public void setReconnectionToken(String token) {
        reconnectionToken = token;
    }

    public String getReconnectionToken() { return reconnectionToken;}

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }


    public void setNumKillShoot(int numKillShoot) {

        BoardStatus.numKillShoot = numKillShoot;
        numDamageOnSkullBoard = new int[numKillShoot];
        colorDamageOnSkullBoard = new Color[numKillShoot];
    }

    public ArrayList<Player> getAllPlayers() {
        return allPlayers;
    }

    public PlayerStatus getPlayer(String playerID) {
        for (PlayerStatus p : players) {
            if (p.getPlayerID().equals(playerID)) {
                return p;
            }
        }
        return null;
    }

    public PlayerStatus getPlayerStatus(String playerID) {
        for (Player p : allPlayers) {
            if (p.getPlayerID().equals(playerID)) {
                return (PlayerStatus) p;
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
    @Override
    public Ansi toAnsi(){
        return ansi().a("map: " + map.getMapInfo() + map + "\n" + "numkill: " + numKillShoot+ "\n" + "firenzy:" + firenzyTriggerd);
    }
    public int[] getNumDamageOnSkullBoard() {
        return numDamageOnSkullBoard;
    }

    public Color[] getColorDamageOnSkullBoard() {
        return colorDamageOnSkullBoard;
    }
}
