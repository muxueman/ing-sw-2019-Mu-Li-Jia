package it.polimi.ingsw.se2019.Adrenaline.utils.immutables;

import org.fusesource.jansi.Ansi;

import static org.fusesource.jansi.Ansi.ansi;

public class AdditionalStatus implements Status {

    private String message;
    private Status status;

    /**
     *
     * The constructor creates an additional status with the message and the status of an entity of the game.
     * @param message is the message to elaborate.
     * @param status is the status of the entity.
     *
     */
    public AdditionalStatus(String message, Status status) {
        this.message = message;
        this.status = status;
    }

    /**
     *
     * The getStatus method is used to get the current status of the entity.
     * @return the status of the entity.
     *
     */
    public Status getStatus() {
        return status;
    }

    /**
     *
     * The toString method is used to get the string containing the message and the status information.
     * @return the string containing the message and the status information.
     *
     */
    @Override
    public String toString() {
        return message + status.toString();
    }

    /**
     *
     * The toAnsi method is used to get the ansi containing the message
     * and the status colored information.
     * @return the ansi containing the message and the status colored information.
     *
     */
    @Override
    public Ansi toAnsi() {
        return ansi().a(message).a(" ").a(status.toAnsi());
    }
}
