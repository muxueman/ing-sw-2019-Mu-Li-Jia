package it.polimi.ingsw.se2019.Adrenaline.utils.immutables;

import it.polimi.ingsw.se2019.Adrenaline.server.model.Color;
import org.fusesource.jansi.Ansi;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.fusesource.jansi.Ansi.ansi;

public class PlayerBoardStatus implements Status {

    protected PlayerStatus playerStatus;
    protected ArrayList<Color> damageColorOnTrack;
    protected ArrayList<Color> markColorOnTrack;
    protected Map<Color, Integer> playerScore;
    protected int numKillShoot; // 被射杀的次数
    protected int beKilled; // 0 没有被杀， 1 被杀， 2 被超杀

    public PlayerBoardStatus(PlayerStatus playerStatus) {
        this.playerStatus = playerStatus;
        this.damageColorOnTrack = new ArrayList<Color>();
        this.markColorOnTrack = new ArrayList<Color>();
        playerScore = new HashMap<>();
        beKilled = 0;
    }
    public PlayerBoardStatus(){}

    public int getNumKillShoot() {
        return numKillShoot;
    }

    public ArrayList<Color> getMarkColorOnTrack() {
        return markColorOnTrack;
    }

    public ArrayList<Color> getDamageColorOnTrack() {
        return damageColorOnTrack;
    }

    public int getBeKilled() {
        return beKilled;
    }

    public Map<Color, Integer> getPlayerScore() {
        return playerScore;
    }

    public PlayerStatus getPlayerStatus() {
        return playerStatus;
    }

    @Override
    public String toString(){
        return "damage on you: " + damageColorOnTrack.toString() + "\n" + "mark on you: " + markColorOnTrack.toString()+ "\n";
    }
    public Ansi toAnsi(){
        return ansi().a("damage on you:" + damageColorOnTrack);
    }


}
