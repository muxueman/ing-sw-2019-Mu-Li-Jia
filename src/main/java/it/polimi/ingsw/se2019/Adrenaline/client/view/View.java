package it.polimi.ingsw.se2019.Adrenaline.client.view;
import it.polimi.ingsw.se2019.Adrenaline.server.model.KillShootTrack;

import java.util.*;



/**
 *
 * View is an abstract class extended by every type of view used on the client.
 * It groups the methods that can be called on the view.
 *
 * @author Riccardo Nembrini
 *
 */

public abstract class View extends Observable implements Observer{

    /**
     *
     * The showMessage method is used to show a message on the view, either on
     * a CLI or on a GUI, depending on the implementation.
     *
     * @param message the String message to show.
     *
     */

    public abstract void showMessage(String message);

    /**
     *
     * The reportError method is used to report an error to the user on the view.
     *
     * @param error the String error message to be reported.
     *
     */

    public abstract void reportError(String error);


    /**
     *
     * The nextView method is used to pass to the next view.
     *
     */

    public void nextView(boolean wpc) {
        // doesn't need to do anything, only used by GUIView
    }

    /**
     *
     * The play method is used to send a playing message to the client.
     * @param playing true or false corresponding to play or not.
     *
     */

    public void play(boolean playing) {
        // doesn't need to do anything, only used by GUIView
    }

    /**
     *
     * The play method is used to send a playing message to the client and
     * then active a zone on the gui.
     *
     * @param playing true or false corresponding to play or not.
     * @param diePlaced true or false corresponding in a placed die or not.
     * @param toolCardUsed true or false corresponding in a tool card used or not.
     *
     */

    public void play(boolean playing, boolean diePlaced, boolean toolCardUsed) {
        // doesn't need to do anything, only used by GUIView
    }

    /**
     *
     * The draft method is used to active the draft pool on the gui.
     * @param toolCard a boolean used to active or not.
     *
     */

    public void draft(boolean toolCard) {
        // doesn't need to do anything, only used by GUIView
    }

    /**
     *
     * The window method is used to active the windowFrame on the gui.
     * @param toolCard a boolean used to active or not.
     */

    public void window(boolean toolCard) {
        // doesn't need to do anything, only used by GUIView
    }

    /**
     *
     * The tool method is used to active the toolCard block on the gui.
     * @param toolCard a boolean used to active or not.
     *
     */

    public void tool(boolean toolCard) {
        // doesn't need to do anything, only used by GUIView
    }

    /**
     *
     * The launchTimer method is used to launch the timer on the gui.
     *
     */

    public void launchTimer() {
        // doesn't need to do anything, only used by GUIView
    }

    /**
     *
     * The value method is used to get the value needed for a toolcard.
     *
     */

    public void value() {
        // doesn't need to do anything, only used by GUIView
    }

    /**
     *
     * The number method is used to get the number needed for a toolcard.
     *
     */

    public void number() {
        // doesn't need to do anything, only used by GUIView
    }

    /**
     *
     * The choice method is used to get a choice needed for a toolcard.
     *
     */

    public void choice() {
        // doesn't need to do anything, only used by GUIView
    }

    /**
     *
     * The roundTrack method is used active the DiceStorege.
     *
     */

    public void roundTrack() {
        // doesn't need to do anything, only used by GUIView
    }

    /**
     *
     * The setCancelImage method is used to disable or not the cancel image.
     *
     */

    public void setCancelImage() {
        // doesn't need to do anything, only used by GUIView
    }
}
