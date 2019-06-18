package it.polimi.ingsw.se2019.Adrenaline.utils.immutables;

import it.polimi.ingsw.se2019.Adrenaline.server.model.Color;
import it.polimi.ingsw.se2019.Adrenaline.server.model.KillShootTrack;
import org.fusesource.jansi.Ansi;

import static org.fusesource.jansi.Ansi.ansi;

public class PlayerStatus implements Status{

    protected String username;
    protected KillShootTrackStatus killShootTrack;
    protected Color playerColor;




    @Override
    public String toString(){
        return "Username: " + username + "\n" + "color: " + playerColor+ "\n" + killShootTrack.toString() + "\n";
    }

    public Ansi toAnsi(){
        return ansi().a("Username: " + username + "\n color: " + playerColor + "\n" + killShootTrack.toAnsi().a("\n"));
    }
}
