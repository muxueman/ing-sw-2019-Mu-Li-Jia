package it.polimi.ingsw.se2019.Adrenaline.network;

/**
 *
 *
 * @author Xueman Mu
 */

import it.polimi.ingsw.se2019.Adrenaline.network.messages.ServerMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInterface extends Remote {

    public void sendError(String error) throws RemoteException;

    public void updateStatus(ServerMessage serverMessage) throws RemoteException;

    public void checkConnection() throws RemoteException;
}
