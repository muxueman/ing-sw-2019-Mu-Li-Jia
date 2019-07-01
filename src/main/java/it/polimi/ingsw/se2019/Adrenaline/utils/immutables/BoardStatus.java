package it.polimi.ingsw.se2019.Adrenaline.utils.immutables;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Board;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Color;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Player;
import it.polimi.ingsw.se2019.Adrenaline.server.model.TurnHandler;
import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.*;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Cell;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.MapA;
import org.fusesource.jansi.Ansi;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

import static org.fusesource.jansi.Ansi.ansi;

public class BoardStatus implements Status {

    //与num一一对应
    private int[] numDamageOnSkullBoard;
    private Color[] colorDamageOnSkullBoard;

    private int mapID;
    private int numKillShoot;
    private boolean firenzyTriggerd;

    private PlayerStatus currentPlayer; //update player
    private List<PlayerStatus> allPlayers;
    private Map<String, String> usernames; //id, username

    private Map<Integer, Cell> allCells; // update map
    private Map<Integer, AmmotileCard> ammotilesInCell;// update map
    private Map<Integer, WeaponCard[]> weaponsInCell;// update map
    private Map<Integer, List<PlayerStatus>> playersInCell;
    private Map<PlayerStatus, Integer> positions;

    private AdditionalStatus additional;
    private String reconnectionToken;
    private boolean started;

    //constructor
       public BoardStatus() {
        this.numDamageOnSkullBoard = null;
        this.colorDamageOnSkullBoard = null;
        this.mapID = 1;
        this.numKillShoot = 5;
        this.firenzyTriggerd = false;
        this.currentPlayer = null;
        this.allPlayers = new ArrayList<>();
        this.usernames = new HashMap<>();
        this.allCells = new HashMap<>();
        this.ammotilesInCell = new HashMap<>();
        this.weaponsInCell = new HashMap<>();
        this.playersInCell = new HashMap<>();
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
        this.playersInCell = new HashMap<>();
        this.positions = new HashMap<>();
        this.additional = null;
        this.reconnectionToken = "";
        this.started = false;
    }

    public BoardStatus(Board board,int mapID, int numKillShoot) {
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
        this.playersInCell = new HashMap<>();
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
        return true;
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
