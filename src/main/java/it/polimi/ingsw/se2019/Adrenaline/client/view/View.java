package it.polimi.ingsw.se2019.Adrenaline.client.view;
import it.polimi.ingsw.se2019.Adrenaline.client.model.ModelUpdate;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Player;
import it.polimi.ingsw.se2019.Adrenaline.server.model.PlayerBoard;

import java.util.*;
import it.polimi.ingsw.se2019.Adrenaline.utils.Observer;
import it.polimi.ingsw.se2019.Adrenaline.utils.Observable;
import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.Status;

/**
 *
 * View is an abstract class extended by every type of view used on the client.
 * It groups the methods that can be called on the view.
 *
 * @author li xuejing
 */

public abstract class View extends Observable<String> implements Observer<ModelUpdate> {




    //show the Ranking with scores
    public abstract void showMessage(String message);
//    public abstract void showScore(KillShootTrack track);
    public abstract void reportError(String error);
    //public void showStatus(Status status) {
        // doesn't need to do anything, only used by CLIView
    //}
    public void nextView(boolean next) {
        // doesn't need to do anything, only used by GUIView
    }


    public void showStatus(Status status){

    }
    public void setCancelImage() {
        // doesn't need to do anything, only used by GUIView
    }
    public void launchTimer(){

    }

    public void value(){

    }



}