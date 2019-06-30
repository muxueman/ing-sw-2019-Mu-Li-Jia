package it.polimi.ingsw.se2019.Adrenaline.utils.immutables;

import it.polimi.ingsw.se2019.Adrenaline.server.model.Color;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Board;
import it.polimi.ingsw.se2019.Adrenaline.server.model.PlayerBoard;
import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.PowerupCard;
import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.WeaponCard;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Cell;
import org.fusesource.jansi.Ansi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.fusesource.jansi.Ansi.ansi;

public class PlayerStatus implements Status{

    protected String username;
    protected String playerID;
    protected PlayerBoardStatus killShootTrack;
    protected Color playerColor;
    protected int[] ammoOwned;
    protected Map<WeaponCard, Boolean> weaponsOwned;
    protected ArrayList<PowerupCard> powerupsOwned;
    protected int myScore;
    protected CellStatus currentCell;
    protected int actionMode;
    protected boolean alive;
    protected BoardStatus board;
    protected int favorTokens;

    //constructor
    public PlayerStatus(String playerID) {
        this.playerID = playerID;//改动
        this.username = null;
        this.killShootTrack = new PlayerBoard(this);
        ammoOwned = new int[] {3,3,3};//RED, BLUE, YELLOW
        weaponsOwned = new HashMap<>();
        powerupsOwned = new ArrayList<>();
        actionMode = 0;
        myScore = 0;
        alive = true;
        favorTokens = 0;
        //初始化所有的玩家,放入allPlayer
    }

    public PlayerStatus(String playerID, String username, int favorTokens) {
        this.playerID = playerID;
        this.username = username;
        this.favorTokens = favorTokens;
        this.killShootTrack = new PlayerBoard(this);
        ammoOwned = new int[] {3,3,3};//RED, BLUE, YELLOW
        weaponsOwned = new HashMap<>();
        powerupsOwned = new ArrayList<>();
        actionMode = 0;
        myScore = 0;
        alive = true;
        //初始化所有的玩家,放入allPlayer
    }


    //***************************************** getter and setter *****************************

    public int getMyScore() {
        return myScore;
    }

    public BoardStatus getBoard() { return board; }

    public int getFavorTokens() { return favorTokens; }

    public ArrayList<PowerupCard> getPowerupsOwned() { return powerupsOwned; }

    public Map<WeaponCard, Boolean> getWeaponsOwned() {
        return weaponsOwned;
    }

    public CellStatus getCurrentCellStatus() {
        return currentCell;
    }

    public int[] getAmmoOwned() {
        return ammoOwned;
    }

    public String getUsername() {
        return username;
    }

    public String getPlayerID() {
        return playerID;
    }

    public int getActionMode() {
        return actionMode;
    }

    public boolean isAlive() {
        return alive;
    }

    public Color getPlayerColor() {
        return playerColor;
    }

    public boolean isReady() {
        return username != null;
    }

    public CellStatus getCurrentCell() {
        return currentCell;
    }

    public PlayerBoardStatus getKillShootTrack(){
        return killShootTrack;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void setActionMode(int actionMode) {
        this.actionMode = actionMode;
    }

    public void setMyScore(int myScore) {
        this.myScore = myScore;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public void setKillShootTrack(PlayerBoardStatus killShootTrack) {
        this.killShootTrack = killShootTrack;
    }

    public void setPlayerColor(Color playerColor) {
        this.playerColor = playerColor;
    }

    public void setAmmoOwned(int[] ammoOwned) {
        this.ammoOwned = ammoOwned;
    }

    public void setWeaponsOwned(Map<WeaponCard, Boolean> weaponsOwned) {
        this.weaponsOwned = weaponsOwned;
    }

    public void setPowerupsOwned(ArrayList<PowerupCard> powerupsOwned) {
        this.powerupsOwned = powerupsOwned;
    }

    public void setCurrentCell(Cell currentCell) {
        this.currentCell = currentCell;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setFavorTokens(int favorTokens) {
        this.favorTokens = favorTokens;
    }

    @Override
    public String toString(){
        return "Username: " + username + "\n" + "color: " + playerColor+ "\n" + killShootTrack.toString() + "\n"
                + "Your current Cell:" + currentCell + "\n" + "Ammo you owned: " + ammoOwned + "\n" + "weapon you have:"
                + weaponsOwned + "\n" + "powerup you have:" + "\n";
    }

    public Ansi toAnsi(){
        return ansi().a("Username: " + username + "\n color: " + playerColor + "\n" + killShootTrack.toAnsi().a("\n"));
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerStatus playerStatus = (PlayerStatus) o;
        return getPlayerID().equals(playerStatus.getPlayerID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPlayerID());
    }
}

