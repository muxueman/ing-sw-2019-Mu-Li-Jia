package it.polimi.ingsw.se2019.Adrenaline.client.view.GUI;

import it.polimi.ingsw.se2019.Adrenaline.client.model.ModelUpdate;
import it.polimi.ingsw.se2019.Adrenaline.client.view.View;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Player;

/**
 *
 * The GUIView Class represent the Gui View and it
 * extends the View Class.
 *
 *
 * @author Li xuejing
 */

public class GUIView extends View {

    private GUIController guiController = null;

    /**
     * Constructs a GuiView passing a guiController.
     *
     * @param guiController a new guiController.
     */
    public GUIView(GUIController guiController) {
        setGuiController(guiController);
    }

    /**
     *
     * The notify method is used to notify a gui client.
     *
     * @param message a message needed to be notified.
     *
     */

    @Override
    protected void notify(String message) {
        super.notify(message);
    }

    /**
     *
     * The launchTimer method is used to launch the timer on the gui.
     *
     */

    public void launchTimer() { guiController.guiLaunchTimer(); }

    /**
     *
     * The play method is used to send a playing message to the client.
     * @param playing true or false corresponding to play or not.
     *
     */

    public void play(boolean playing) {
        guiController.guiPlay(playing);
    }

    /**
     *
     * The showMessage method is used to show a message on the view, either on
     * a CLI or on a GUI, depending on the implementation.
     *
     * @param message the String message to show.
     *
     */
    @Override
    public void showMessage(String message) {
        guiController.showMessage(message);

    }

    /**
     *
     * The reportError method is used to report an error to the user on the view.
     *
     * @param error the String error message to be reported.
     *
     */

    @Override
    public void reportError(String error) {
        guiController.reportError(error);

    }

    /**
     *
     * The update method is used to update a part of the model.
     *
     * @param message part of the model needed to be updated.
     *
     */

    @Override
    public void update(ModelUpdate message) {
        guiController.update(message);
    }

    /**
     *
     * The setGuiController method is used to set the gui controller.
     *
     * @param guiController the actual guiController.
     *
     */

    public void setGuiController(GUIController guiController) {
        this.guiController = guiController;
        guiController.setGuiView(this);
    }

    /**
     *
     * The nextView method is used to pass to the next view.
     *
     */

    @Override
    public void nextView(boolean next){
        guiController.nextView(next);
    }

    /**
     *
     * The setCancelImage method is used to disable or not the cancel image.
     *
     */

    @Override
    public void setCancelImage(){
        guiController.guiSetCancelImage();
    }


    public GUIController getGuiController() {
        return guiController;
    }

    /**
     *
     * The value method is used to get the value needed for a toolcard.
     *
     */

    @Override
    public void value(){
        guiController.guiValue();
    }

    /**
     *
     * The showRanking method is used to show the ranking.
     *
     * @param myscore the ranking needed to be show.
     *
     */

    public void showRanking(Player myscore) {
        guiController.showRanking(myscore);
    }


}
