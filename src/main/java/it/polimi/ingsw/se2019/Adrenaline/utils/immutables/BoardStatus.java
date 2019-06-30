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

    private List<PowerupCard> optionalPowerupCards;
    private List<WeaponCard> optionalWeaponCards;

    protected PlayerStatus currentPlayer;
    protected static int numKillShoot;
    protected int killTurn;
    protected boolean firenzyTriggerd;

    private List<PlayerStatus> players;

    protected MapStatus map;
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
        players = new ArrayList<>();
    }
    //constructor
    public BoardStatus(){
        currentPlayer = null;
        additional = null;
        numKillShoot = 5;
        killTurn = 0;
        firenzyTriggerd = false;
        reconnectionToken = "";
        this.map = map;
        players = null;
    }
    public void setMap(Map map){
        this.map = map;
    }
    public void setNumKillShoot(int numKillShoot) {

        BoardStatus.numKillShoot = numKillShoot;
        numDamageOnSkullBoard = new int[numKillShoot];
        colorDamageOnSkullBoard = new Color[numKillShoot];
    }

    public void setPlayers(List<PlayerStatus> players) {
        for(PlayerStatus player: players){
            players.add((PlayerStatus)player);
        }
    }
    //不懂
    public void updatePlayerStatus(){
        int i = 0;
        for(PlayerStatus player: players){
            players.remove(i);
            players.add((PlayerStatus) player);
        }
    }

    public void setOptionalPowerupCards(List<PowerupCard> optionalPowerupCards) {
        this.optionalPowerupCards = optionalPowerupCards;
    }
    public void setOptionalWeaponCards(List<WeaponCard> optionalWeaponCards) {
        this.optionalWeaponCards = optionalWeaponCards;
    }

    public void setReconnectionToken(String token) {
        reconnectionToken = token;
    }

    public String getReconnectionToken() { return reconnectionToken;}

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public PlayerStatus getCurrentPlayer() {
        return currentPlayer;
    }

    public void updateAdditional(AdditionalStatus additionalStatus) {
        additional = additionalStatus;
    }



    public List<PlayerStatus> getPlayerList() {
        return players;
    }

    //return the status of a player
    public PlayerStatus getPlayerStatus(String playerID) {
        for (PlayerStatus p : players) {
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
            if (currentPlayer == null) {
                currentPlayer = newPlayerStatus;
            }
        } else {
            int playerIndex = players.indexOf(player);
            players.remove(playerIndex);
            players.add(playerIndex, newPlayerStatus);
            if (newPlayerStatus.equals(currentPlayer)) {
                currentPlayer = newPlayerStatus;
            }
        }
        return true;
    }
    @Override
    public String toString(){
//        return "map: " + map.getMapInfo() + map + "\n" + "numkill: " + numKillShoot+ "\n" + "firenzy:" + firenzyTriggerd;
//                + "\n" + "allplayer:" + players.get(0).toString();
        return "board";
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
