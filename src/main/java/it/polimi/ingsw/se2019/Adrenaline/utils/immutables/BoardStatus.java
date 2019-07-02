package it.polimi.ingsw.se2019.Adrenaline.utils.immutables;
/**
 *Board Status from client-side point of view, contains all data needed for view (client)
 *@author Xueman Mu
 */
import it.polimi.ingsw.se2019.Adrenaline.server.model.Board;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Color;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Player;
import it.polimi.ingsw.se2019.Adrenaline.server.model.PlayerBoard;
import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.*;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Cell;

import org.fusesource.jansi.Ansi;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

import static org.fusesource.jansi.Ansi.ansi;

public class BoardStatus implements Status {

    //与num一一对应
    private int[] numDamageOnSkullBoard; //update board,skull specially
    private Color[] colorDamageOnSkullBoard;//update board,skull specially

    private static int mapID;
    private static int numKillShoot;

    private boolean firenzyTriggerd;

    private PlayerStatus currentPlayer; //update player
    private List<PlayerStatus> allPlayers; //update board/player
    private Map<String, PlayerStatus> players; //<id, playerStatus> //update board/player
    private Map<String, String> usernames; //<id, username> update board/player

    private Map<Integer, Cell> allCells; // update map
    private Map<Integer, AmmotileCard> ammotilesInCell;// update map
    private Map<Integer, WeaponCard[]> weaponsInCell;// update map
    //private Map<Integer, List<PlayerStatus>> playersInCell; //update map
    private Map<PlayerStatus, Integer> positions; //update map

    private AdditionalStatus additional;
    private String reconnectionToken;
    private boolean started;

    //constructor
       public BoardStatus(Board board) {
        this.numDamageOnSkullBoard = board.getNumDamageOnSkullBoard();
        this.colorDamageOnSkullBoard = board.getColorDamageOnSkullBoard();
        this.mapID = board.getMapInt();
        this.numKillShoot = board.getNumKillShoot();
        this.firenzyTriggerd = board.triggerFirenzy();
        this.currentPlayer = null;
        this.allPlayers = new ArrayList<>();
        this.players = new HashMap<>();
        this.usernames = new HashMap<>();
        this.allCells = new HashMap<>();
        this.ammotilesInCell = new HashMap<>();
        this.weaponsInCell = new HashMap<>();
        //this.playersInCell = new HashMap<>();
        this.positions = new HashMap<>();
        this.additional = null;
        this.reconnectionToken = "";
        this.started = false;
    }

    //constructor from controller
    public BoardStatus(int mapID, int numKillShoot) {
        this.numDamageOnSkullBoard = null;
        this.colorDamageOnSkullBoard = null;
        this.mapID = mapID;
        this.numKillShoot = numKillShoot;
        this.firenzyTriggerd = false;
        this.currentPlayer = null;
        this.allPlayers = new ArrayList<>();
        this.usernames = new HashMap<>();
        this.allCells = new HashMap<>();
        this.ammotilesInCell = new HashMap<>();
        this.weaponsInCell = new HashMap<>();
        this.players = new HashMap<>();
        //this.playersInCell = new HashMap<>();
        this.positions = new HashMap<>();
        this.additional = null;
        this.reconnectionToken = "";
        this.started = false;

    }

    public int[] getNumDamageOnSkullBoard() {
        return numDamageOnSkullBoard;
    }
    public Color[] getColorDamageOnSkullBoard() {
        return colorDamageOnSkullBoard;
    }
    public PlayerStatus getCurrentPlayer() { return currentPlayer; }
    public List<PlayerStatus> getAllPlayers() { return allPlayers; }
    public Map<Integer, Cell> getAllCells() { return allCells; }
    public Map<Integer, AmmotileCard> getAmmotilesInCell() { return ammotilesInCell; }
    public Map<Integer, WeaponCard[]> getWeaponsInCell() { return weaponsInCell; }
    public Map<PlayerStatus, Integer> getPositions() { return positions; }
    public String getReconnectionToken() { return reconnectionToken;}

    public void setNumDamageOnSkullBoard(int[] numDamageOnSkullBoard) {
        this.numDamageOnSkullBoard = numDamageOnSkullBoard;
    }

    public void setColorDamageOnSkullBoard(Color[] colorDamageOnSkullBoard) {
        this.colorDamageOnSkullBoard = colorDamageOnSkullBoard;
    }

    public static void setMapID(int mapID) {
        BoardStatus.mapID = mapID;
    }

    public static void setNumKillShoot(int numKillShoot) {
        BoardStatus.numKillShoot = numKillShoot;
    }

    public void setFirenzyTriggerd(boolean firenzyTriggerd) {
        this.firenzyTriggerd = firenzyTriggerd;
    }

    public void setCurrentPlayer(PlayerStatus currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void setAllPlayers(List<PlayerStatus> allPlayers) {
        this.allPlayers = allPlayers;
    }

    public void setPlayers(Map<String, PlayerStatus> players) {
        this.players = players;
    }

    public void setUsernames(Map<String, String> usernames) {
        this.usernames = usernames;
    }

    public void setAllCells(Map<Integer, Cell> allCells) {
        this.allCells = allCells;
    }

    public void setAmmotilesInCell(Map<Integer, AmmotileCard> ammotilesInCell) {
        this.ammotilesInCell = ammotilesInCell;
    }

    public void setWeaponsInCell(Map<Integer, WeaponCard[]> weaponsInCell) {
        this.weaponsInCell = weaponsInCell;
    }

    public void setPositions(Map<PlayerStatus, Integer> positions) {
        this.positions = positions;
    }

    public void setAdditional(AdditionalStatus additional) {
        this.additional = additional;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public static int getMapID() {
        return mapID;
    }

    public static int getNumKillShoot() {
        return numKillShoot;
    }

    public boolean isFirenzyTriggerd() {
        return firenzyTriggerd;
    }

    public Map<String, PlayerStatus> getPlayers() {
        return players;
    }

    public Map<String, String> getUsernames() {
        return usernames;
    }

    public AdditionalStatus getAdditional() {
        return additional;
    }

    public boolean isStarted() {
        return started;
    }

    //更新map里面包含的内容
    public boolean updateMap(it.polimi.ingsw.se2019.Adrenaline.server.model.map.Map map){
           ArrayList<Cell> cells = map.getAllCells();
           for (Cell c: cells){
               if (c.getCellID() != 0){
                   allCells.put(c.getCellID(),c);
                   if (c.getAmmotileCard() != null){
                       ammotilesInCell.put(c.getCellID(),c.getAmmotileCard());
                   }
                   if (c.getWeaponCard() != null){
                       weaponsInCell.put(c.getCellID(),c.getWeaponCard());
                   }
                   if (c.getCellPlayers() != null){
                       ArrayList<Player> celllplayers = c.getCellPlayers();
                       for (Player p: celllplayers){
                           PlayerStatus pp = getPlayer(p.getPlayerID());
                           positions.put(pp,c.getCellID());
                       }
                   }
               }
           }
           return true;
    }

    //找到player类在这里的映射PlayerStatus
    public PlayerStatus getPlayer(String playerID) {
        for (PlayerStatus p : allPlayers) {
            if (p.getPlayerID().equals(playerID)) {
                return p;
            }
        }
        return null;
    }

    //更新一个player
    public boolean updatePlayer(Player newPlayerStatus){
        PlayerStatus newPlayer = new PlayerStatus(newPlayerStatus);
        PlayerStatus player = getPlayer(newPlayer.getPlayerID());
        if (player == null) {
            allPlayers.add(newPlayer);
            if (currentPlayer == null) {
                currentPlayer = newPlayer;
            }
        }else{
            int playerIndex = allPlayers.indexOf(player);
            allPlayers.remove(playerIndex);
            allPlayers.add(playerIndex, newPlayer);
            if (newPlayer.getPlayerID().equals(currentPlayer.getPlayerID())) {
                currentPlayer = newPlayer;
            }
        }
        usernames.put(newPlayer.getPlayerID(),newPlayer.getUsername());
        players.put(newPlayer.getPlayerID(),newPlayer);
        return true;
    }

    //更新所有的玩家，在游戏开始时,默认
    public boolean updatePlayers(Board board){
        ArrayList<Player> players = board.getAllPlayers();
        for(Player player : players){
            updatePlayer(player);
        }
        return true;
    }


    public boolean updateDamageSkullBoard(Board board){
        this.numDamageOnSkullBoard = board.getNumDamageOnSkullBoard();
        this.colorDamageOnSkullBoard = board.getColorDamageOnSkullBoard();
        return true;
    }

    public boolean setFirenzyTrigger(boolean Firenzy){
           this.firenzyTriggerd = Firenzy;
           return true;
    }

    public void updateAdditional(AdditionalStatus additionalStatus) {
        additional = additionalStatus;
    }
    public void setReconnectionToken(String token) {
        reconnectionToken = token;
    }

    @Override
    public String toString(){
        return "BoardStatus:\n " + "map id: " + mapID + "\n" + "numkill: " + numKillShoot+ "\n" +
                "firenzy:" + firenzyTriggerd + "\n" + "allplayer:" +allPlayers + "\n" + "current player:" +currentPlayer;
    }

    @Override
    public Ansi toAnsi(){
        return ansi().a("BoardStatus:\n " + "map id: " + mapID + "\n" + "numkill: " + numKillShoot+ "\n" +
                "firenzy:" + firenzyTriggerd + "\n" + "allplayer:" +allPlayers + "\n" + "current player:" +currentPlayer);
    }
}
