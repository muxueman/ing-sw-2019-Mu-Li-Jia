package it.polimi.ingsw.se2019.Adrenaline.utils.immutables;

import it.polimi.ingsw.se2019.Adrenaline.server.model.Color;
import it.polimi.ingsw.se2019.Adrenaline.server.model.KillShootTrack;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Map;
import org.fusesource.jansi.Ansi;


import java.util.Objects;

import static org.fusesource.jansi.Ansi.ansi;

public class PlayerStatus implements Status{

    protected String username;
    protected String playerID;
    protected KillShootTrackStatus killShootTrack;
    protected Color color;
    protected Map map;
    protected int numKillShoot;
    //protected int favorTokens;

    public PlayerStatus(String playerID) {
        this.playerID = playerID;
        username = null;
        killShootTrack = null;
        map = null;
        color = null;
        //favorTokens = 0;
    }
    public PlayerStatus(String playerID, String username, Map map, int numKillShoot,  KillShootTrackStatus killShootTrack) {
        this.playerID = playerID;
        this.username = username;
        this.map = map;
        this.numKillShoot = numKillShoot;
        this.killShootTrack = killShootTrack;
    }

    public String getUsername() {
        return username;
    }

    public String getPlayerID() {
        return playerID;
    }

    public KillShootTrackStatus getKillShootTrack() {
        return killShootTrack;
    }

    public Color getColor() {
        return color;
    }

    public Map getMap() {
        return map;
    }

    public int getNumKillShoot() {
        return numKillShoot;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public void setKillShootTrack(KillShootTrackStatus killShootTrack) {
        this.killShootTrack = killShootTrack;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public void setNumKillShoot(int numKillShoot) {
        this.numKillShoot = numKillShoot;
    }

    @Override
    public String toString(){
        return "Username: " + username + "\n" + "color: " + color+ "\n" + killShootTrack.toString() + "\n";
    }

    public Ansi toAnsi(){
        return ansi().a("Username: " + username + "\n color: " + color + "\n" + killShootTrack.toAnsi().a("\n"));
    }

    public int hashCode() {
        return Objects.hash(getPlayerID());
    }
}

