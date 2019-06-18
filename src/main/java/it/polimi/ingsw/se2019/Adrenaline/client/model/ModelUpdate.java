package it.polimi.ingsw.se2019.Adrenaline.client.model;

import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.BoardStatus;
import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.Status;

import java.util.ArrayList;
import java.util.List;

public class ModelUpdate {

    private String message;
    private List<Status> statusUpdates;
    private BoardStatus boardStatus;

    /**
     *
     * The constructor creates an empty update.
     *
     */
    public ModelUpdate() {
        message = "";
        statusUpdates = new ArrayList<Status>();
        boardStatus = null;
    }

    /**
     *
     * The constructor creates an update containing the new status of the board.
     * @param boardStatus is the new status of the board.
     *
     */
    public ModelUpdate(BoardStatus boardStatus) {
        message = "";
        statusUpdates = new ArrayList<Status>();
        this.boardStatus = boardStatus;
    }

    /**
     *
     * The constructor creates an update containing the new status of the board and others update.
     * @param statusUpdate is a list of new status.
     * @param boardStatus is the new status of the board.
     *
     */
    public ModelUpdate(List<Status> statusUpdate, BoardStatus boardStatus) {
        message = "";
        this.statusUpdates = statusUpdate;
        this.boardStatus = boardStatus;
    }

    /**
     *
     * The getMessage method is used to get the message of the update.
     * @return the string message.
     *
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * The setMessage method is used to set the message associated to the update.
     * @param message is the string message to be set.
     *
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     *
     * The getStatusUpdates method is used to get the list of updates of some entity.
     * @return the list of updates.
     *
     */
    public List<Status> getStatusUpdates() {
        return statusUpdates;
    }

    /**
     *
     * The addStatusUpdate method is used to add an update of an entity to the list
     * containing the updates.
     * @param status is the update to be added.
     *
     */
    public void addStatusUpdate(Status status) {
        statusUpdates.add(status);
    }

    /**
     *
     * The getBoardStatus method is used to get the update of the board.
     * @return the status of the board.
     *
     */
    public BoardStatus getBoardStatus() {
        return boardStatus;
    }

    /**
     *
     * The setBoardStatus method is used to set the new status of the board.
     * @param boardStatus is the new status of the board.
     *
     */
    public void setBoardStatus(BoardStatus boardStatus) {
        this.boardStatus = boardStatus;
    }

    /**
     *
     * The isEmpty method is used to check if the list of updates is empty and
     * the new status of the board is the same of the previous status.
     * @return true if the list of updates is empty and nothing in the board is changed,
     * false if not.
     *
     */
    public boolean isEmpty() {
        return statusUpdates.isEmpty() && boardStatus == null;
    }
}
