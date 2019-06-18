package it.polimi.ingsw.se2019.Adrenaline.utils.immutables;

import it.polimi.ingsw.se2019.Adrenaline.server.model.Color;
import it.polimi.ingsw.se2019.Adrenaline.server.model.KillShootTrack;
import org.fusesource.jansi.Ansi;
import java.util.ArrayList;

import static org.fusesource.jansi.Ansi.ansi;

public class KillShootTrackStatus implements Status {

    private ArrayList<Color> damageColorOnTrack;
    private ArrayList<Color> markColorOnTrack;

    @Override
    public String toString(){
        return "damage on you: " + damageColorOnTrack.toString() + "\n" + "mark on you: " + markColorOnTrack.toString()+ "\n";
    }
    public Ansi toAnsi(){
        return ansi().a("damage on you:" + damageColorOnTrack);
    }


}
