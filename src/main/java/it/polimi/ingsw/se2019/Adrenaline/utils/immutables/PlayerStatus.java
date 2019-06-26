package it.polimi.ingsw.se2019.Adrenaline.utils.immutables;

import it.polimi.ingsw.se2019.Adrenaline.server.model.Color;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Board;
import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.PowerupCard;
import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.WeaponCard;
import org.fusesource.jansi.Ansi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
    protected Board board;



    public PlayerStatus(String username) {
        playerID = username;
        this.username = username;
        this.killShootTrack = new PlayerBoardStatus(this);
        ammoOwned = new int[] {3,3,3};//RED, BLUE, YELLOW
        weaponsOwned = new HashMap<>();
        powerupsOwned = new ArrayList<>();
        actionMode = 0;
        myScore = 0;
        alive = true;
        //初始化所有的玩家,放入allPlayer
    }

    //不知道用法
    public Ansi toAnsi(){
        return ansi().a("Username: " + username + "\n color: " + playerColor + "\n" + killShootTrack.toAnsi().a("\n"));
    }

    public int getMyScore() {
        return myScore;
    }

    public ArrayList<PowerupCard> getPowerupsOwned() {
        return powerupsOwned;
    }

    public Map<WeaponCard, Boolean> getWeaponsOwned() {
        return weaponsOwned;
    }

    public CellStatus getCurrentCell() {
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

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void setActionMode(int actionMode) {
        this.actionMode = actionMode;
    }

    public void setMyScore(int myScore) {
        this.myScore = myScore;
    }

    @Override
    public String toString(){
        return "Username: " + username + "\n" + "color: " + playerColor+ "\n" + killShootTrack.toString() + "\n"
                + "Your current Cell:" + currentCell + "\n" + "Ammo you owned: " + ammoOwned + "\n" + "weapon you have:"
                + weaponsOwned + "\n" + "powerup you have:" + "\n";
    }

}

