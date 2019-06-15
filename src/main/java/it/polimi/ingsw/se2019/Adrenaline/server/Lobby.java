package it.polimi.ingsw.se2019.Adrenaline.server;

import it.polimi.ingsw.se2019.Adrenaline.network.ClientInterface;
import it.polimi.ingsw.se2019.Adrenaline.server.controller.MatchController;
import it.polimi.ingsw.se2019.Adrenaline.server.controller.ServerController;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Timer;

public class Lobby {

    private Map<ClientInterface, Boolean> clients;
    private Map<ClientInterface, ServerController> controllers;
    private Map<ServerController, ClientInterface> clientInterfaces;
    private Map<ClientInterface, String> usernames;
    private MatchController currentMatch;
    private Map<String, MatchController> matches;
    private Timer timer;
    private int seconds;
    private Queue queue;


    Lobby() {
        clients = new HashMap<>();
        controllers = new HashMap<>();
        clientInterfaces = new HashMap<>();
        usernames = new HashMap<>();
        matches = new HashMap<>();
        //createMatch();
        timer = new Timer();
        //queue = new Queue();
        //queue.start();

    }
    /*
    private synchronized void createMatch() {
        String matchID = UUID.randomUUID().toString();
        currentMatch = new MatchController(matchID);
        matches.put(matchID, currentMatch);
    }
    */
}
