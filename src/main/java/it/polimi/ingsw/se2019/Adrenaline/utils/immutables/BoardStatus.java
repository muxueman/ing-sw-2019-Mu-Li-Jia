package it.polimi.ingsw.se2019.Adrenaline.utils.immutables;
import org.fusesource.jansi.Ansi;

public class BoardStatus implements Status {




    @Override
    public Ansi toAnsi(){
        Ansi ansi = new Ansi();
//        for (PlayerStatus player : players) {
//            ansi = ansi.a(player.toAnsi());
//        }
//        ansi= ansi.a(draftPool.toAnsi()).a(roundTrack.toAnsi());
//        for(int i = 0; i< numPublicObjectiveCards; i++){
//            ansi= ansi.a(publicObjectiveCards[i].toAnsi());
//        }
//        ansi= ansi.a(privateObjectiveCard.toAnsi());
//        for(int i = 0; i< numToolCards; i++){
//            ansi= ansi.a(toolCards[i].toAnsi());
//        }
        return ansi;
    }

}
