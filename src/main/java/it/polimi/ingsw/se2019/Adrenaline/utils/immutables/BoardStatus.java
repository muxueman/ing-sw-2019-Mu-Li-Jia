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

    //与num一一对应
    protected int[] numDamageOnSkullBoard;
    protected Color[] colorDamageOnSkullBoard;


    protected Player currentPlayer; //希望用turnhandler.getCurrentPlauyer
    protected PlayerStatus thisPlayer;
    protected static int numKillShoot;
    protected int killTurn;
    protected boolean firenzyTriggerd;

    protected ArrayList<Player> allPlayers;
    private List<PlayerStatus> players;

    protected Map map;
    protected int mapInt;

    private AdditionalStatus additional;
    private String reconnectionToken;
//    protected TurnHandler turnHandler;
    //记录第几次的射杀

    private boolean started;

    //constructor
    public BoardStatus(Map map,int skull){
        currentPlayer = null;
        additional = null;
        numKillShoot = skull;
        killTurn = 0;
        firenzyTriggerd = false;
        reconnectionToken = "";
        this.map = map;
        allPlayers = new ArrayList<>();

    }

    public void setPlayers(ArrayList<Player> allPlayers) {
        for(Player player: allPlayers){
            players.add((PlayerStatus)player);
        }
    }
    //不懂
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

    public void updateAdditional(AdditionalStatus additionalStatus) {
        additional = additionalStatus;
    }

    public void setNumKillShoot(int numKillShoot) {

        BoardStatus.numKillShoot = numKillShoot;
        numDamageOnSkullBoard = new int[numKillShoot];
        colorDamageOnSkullBoard = new Color[numKillShoot];
    }

    public ArrayList<Player> getAllPlayers() {
        return allPlayers;
    }

    //return the status of a player
    public PlayerStatus getPlayerStatus(String playerID) {
        for (Player p : allPlayers) {
            if (p.getPlayerID().equals(playerID)) {
                return (PlayerStatus) p;
            }
        }
        return null;
    }


    //permits to update the status of a player.
    public boolean updatePlayer(PlayerStatus newPlayerStatus) {
        PlayerStatus player = getPlayerStatus(newPlayerStatus.getPlayerID());
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
