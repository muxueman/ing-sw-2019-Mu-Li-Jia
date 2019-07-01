package it.polimi.ingsw.se2019.Adrenaline.client.model;

import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.ClientStatus;
import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.Status;

import java.util.ArrayList;
import java.util.List;

public class ModelUpdate {

    private String message;
    protected List<Status> statusUpdates;
    private ClientStatus clientStatus;

    public ModelUpdate() {
        message = "";
        statusUpdates = new ArrayList<Status>();
        clientStatus = null;
    }

    public ModelUpdate(ClientStatus clientStatus) {
        message = "";
        statusUpdates = new ArrayList<Status>();
        this.clientStatus = clientStatus;
    }

    public ModelUpdate(List<Status> statusUpdate, ClientStatus clientStatus) {
        message = "";
        this.statusUpdates = statusUpdate;
        this.clientStatus = clientStatus;
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

    public ClientStatus getClientStatus() {
        return clientStatus;
    }

    public void setClientStatus(ClientStatus clientStatus) {
        this.clientStatus = clientStatus;
    }

    public boolean isEmpty() {
        return statusUpdates.isEmpty() && clientStatus == null;
    }
}
