package it.polimi.ingsw.se2019.Adrenaline.server;

/**
 *  It behaves like a real view with respect to Model and controller:
 *  It is an observer with respect to the Model
 *  It is an observable towards the controller
 *  @author Xueman Mu
 */

import it.polimi.ingsw.se2019.Adrenaline.network.MVEvent;
import it.polimi.ingsw.se2019.Adrenaline.network.VCEvent;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Player;
import it.polimi.ingsw.se2019.Adrenaline.utils.Observable;
import it.polimi.ingsw.se2019.Adrenaline.utils.Observer;

import java.net.Socket;
import java.util.ArrayList;


public abstract class VirtualView extends Observable<VCEvent> implements Observer<MVEvent> {

    public abstract void startServer();

    public abstract void chooseKillShoot();

    public abstract int chooseMap();

    public abstract int scegliFamiliareDaGiocare();

    public abstract int daiNumeroServitoriDaSacrificare();

    public abstract int chiediSeAttivareCartaLeader(String nome);

    public abstract void PrintMap(String stringa);

    public abstract void stampaSlotNonDisponibile();

    public abstract void stampaFamiliareGiaGiocato();

    public abstract ArrayList<Player> getplayers();

    public abstract void setGiocatoreCorrente(Player player);

    public abstract void startGame();

    public abstract void VisualizzaOrdineTurno(ArrayList<String> ordineTurno);

    public abstract int chooseCardToTake(ArrayList<Integer> slotsDisponibili);

    public abstract void rifareMossa();

    public abstract void fineMossaGiocatore();

    public abstract void startTurn(int periodo);

    public abstract void setClients(ArrayList<Socket> clients);


}
