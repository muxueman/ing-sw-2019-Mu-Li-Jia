package it.polimi.ingsw.se2019.Adrenaline.network;

/**
 *
 * @author Xueman Mu
 */

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInterface extends Remote {

    void sendError(String error) throws RemoteException;

    void updateStatus(ServerMessage serverMessage) throws RemoteException;

    void checkConnection() throws RemoteException;
}
