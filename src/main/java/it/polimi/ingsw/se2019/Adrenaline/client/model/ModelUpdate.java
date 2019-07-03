package it.polimi.ingsw.se2019.Adrenaline.client.model;

import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.BoardStatus;
import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.Status;

import java.util.ArrayList;
import java.util.List;

public class ModelUpdate {

    private String message;
    private List<Status> statusUpdates;
    private BoardStatus boardStatus;


    public ModelUpdate() {
        message = "";
        statusUpdates = new ArrayList<>();
        boardStatus = null;
    }


    public ModelUpdate(BoardStatus boardStatus) {
        message = "";
        statusUpdates = new ArrayList<>();
        this.boardStatus = boardStatus;
    }


    public ModelUpdate(List<Status> statusUpdate, BoardStatus boardStatus) {
        message = "";
        this.statusUpdates = statusUpdate;
        this.boardStatus = boardStatus;
    }

    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }


    public List<Status> getStatusUpdates() {
        return statusUpdates;
    }

    public void addStatusUpdate(Status status) {
        statusUpdates.add(status);
    }

    public BoardStatus getBoardStatus() {
        return boardStatus;
    }


    public void setBoardStatus(BoardStatus boardStatus) {
        this.boardStatus = boardStatus;
    }


    public boolean isEmpty() {
        return statusUpdates.isEmpty() && boardStatus == null;
    }
}


