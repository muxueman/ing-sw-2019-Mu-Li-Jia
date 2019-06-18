package it.polimi.ingsw.se2019.Adrenaline.server;

import it.polimi.ingsw.se2019.Adrenaline.network.ClientInterface;
import it.polimi.ingsw.se2019.Adrenaline.server.controller.ChooseMapState;
import it.polimi.ingsw.se2019.Adrenaline.server.controller.MatchController;
import it.polimi.ingsw.se2019.Adrenaline.server.controller.ServerController;

import java.rmi.RemoteException;
import java.util.*;
import java.util.logging.Logger;

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
        createMatch();
        timer = new Timer();
        queue = new Queue();
        queue.start();

    }
    private synchronized void remove(ClientInterface clientInterface) {
        ServerController serverController = controllers.get(clientInterface);
        clients.remove(clientInterface);
        controllers.remove(clientInterface);
        clientInterfaces.remove(serverController);
        usernames.remove(clientInterface);
    }

    private class Queue extends Thread {

        private boolean active = true;
        private boolean timerStarted = false;

        @Override
        public void run() {
            while (active) {
                if (playersNumber(3)) {
                    timerStarted = false;
                    startMatch();
                } else if (playersNumber(2)) {
                    if (!timerStarted) {
                        timerStarted = true;
                        startTimer();
                    }
                } else {
                    if (timerStarted) {
                        timerStarted = false;
                        closeTimer();
                    }
                }
            }
        }

        private boolean playersNumber(int n) {
            synchronized (Lobby.this) {
                long readyPlayers = clients.entrySet().stream().map(Map.Entry::getValue).filter(ready -> ready).count();
                return readyPlayers >= n;
            }
        }

        private void startTimer() {
            timer.scheduleAtFixedRate(new StartGameTask(seconds), 0, 1000);
        }

        private void resetTimer() {
            timerStarted = false;
        }
    }

    private class StartGameTask extends TimerTask {

        private int seconds;

        @Override
        public void run() {
            if(seconds == 0) {
                startMatch();
            }
            seconds--;
        }

        private StartGameTask(int seconds) { this.seconds = seconds; }
    }


    private synchronized void startMatch() {
        List<ClientInterface> playingClients = new ArrayList<>();
        for (Map.Entry<ClientInterface, Boolean> entry : clients.entrySet()) {
            if (entry.getValue() && currentMatch.isNotFull()) {
                ClientInterface client = entry.getKey();
                currentMatch.addClient(client, controllers.get(client));
                currentMatch.setPlayer(usernames.get(client), client);
                ServerController serverController = controllers.get(client);
                serverController.setMatch(currentMatch);
                try {
                    serverController.nextState(new ChooseMapState(serverController, client));
                } catch (RemoteException e) {
                    Logger.getGlobal().warning("There has been an error: " + e.getMessage());
                }
                playingClients.add(client);
            }
        }
        for (ClientInterface c : playingClients) {
            remove(c);
        }
        currentMatch.startWhenReady();
        createMatch();
        closeTimer();
    }

    private synchronized void createMatch() {
        String matchID = UUID.randomUUID().toString();
        currentMatch = new MatchController(matchID);
        matches.put(matchID, currentMatch);
    }


    private void closeTimer() {
        timer.cancel();
        timer = new Timer();
        queue.resetTimer();
    }
}
