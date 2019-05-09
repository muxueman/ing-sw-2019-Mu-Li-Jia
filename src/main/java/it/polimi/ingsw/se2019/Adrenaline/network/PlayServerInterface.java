package it.polimi.ingsw.se2019.Adrenaline.network;

/**
 *
 * @author Xueman Mu
 */

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PlayServerInterface extends Remote {

    PlayServerInterface update(ClientMessage message, ClientInterface client) throws RemoteException;
}