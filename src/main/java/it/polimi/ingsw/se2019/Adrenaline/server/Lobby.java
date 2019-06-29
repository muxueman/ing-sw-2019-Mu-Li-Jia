package it.polimi.ingsw.se2019.Adrenaline.server;

import it.polimi.ingsw.se2019.Adrenaline.network.ClientInterface;
import it.polimi.ingsw.se2019.Adrenaline.server.controller.ChooseMapState;
import it.polimi.ingsw.se2019.Adrenaline.server.controller.MatchController;
import it.polimi.ingsw.se2019.Adrenaline.server.controller.ServerController;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.rmi.RemoteException;
import java.util.*;
import java.util.logging.Level;
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

        try (Scanner input = new Scanner(MatchController.class.getResourceAsStream("/file/config.json"))){
            StringBuilder jsonIn = new StringBuilder();
            while(input.hasNextLine()) {
                jsonIn.append(input.nextLine());
            }
            JSONParser parser = new JSONParser();
            JSONObject root = (JSONObject) parser.parse(jsonIn.toString());
            JSONArray jsonArray = (JSONArray) root.get("timer");
            JSONObject cell = (JSONObject) jsonArray.get(0);
            seconds = Integer.parseInt((String) cell.get("seconds"));
        } catch (Exception e) {
            Logger.getGlobal().warning(e.getMessage());
            seconds = 90;
        }

    }

    public synchronized void addClient(ClientInterface client, ServerController controller) {
        clients.put(client, false);
        controllers.put(client, controller);
        clientInterfaces.put(controller, client);
    }

    //connect client with username/token to lobby
    public boolean connect(ServerController serverController, ClientInterface client, String token) throws RemoteException {
        if (token.length() == 73) {
            String[] ids = token.split("_");
            MatchController match = matches.get(ids[0]);
            if (match != null && ids.length == 2) {
                if (!match.reconnect(ids[1], serverController, client)) {
                    client.sendError("You can't reconnect to this match! Insert username.");
                }
            } else {
                client.sendError("There is no match for this token! Insert username.");
            }
        } else {
            if (usernames.values().contains(token)) {
                client.sendError("This username is already taken!");
            } else {
                usernames.put(client, token);
                clients.replace(client, true);
                return true;
            }
        }
        return false;
    }

    public void disconnect(ServerController serverController, MatchController matchController) {
        if (matchController != null) {
            matchController.disconnect(serverController);
        } else {
            ClientInterface clientInterface = clientInterfaces.get(serverController);
            if (clientInterface != null) {
                remove(clientInterface);
            }
        }
    }
    private synchronized void remove(ClientInterface clientInterface) {
        ServerController serverController = controllers.get(clientInterface);
        clients.remove(clientInterface);
        controllers.remove(clientInterface);
        clientInterfaces.remove(serverController);
        usernames.remove(clientInterface);
    }

    //***************************************** start match time part ***********************************

    //另一个线程，等待用户连接游戏，3人游戏开始，否则 2 人 ，需要改进
    private class Queue extends Thread {

        private boolean active = true;
        private boolean timerStarted = false;

        @Override
        public void run() {
            while (active) {
                if (playersNumber(2)) {
                    timerStarted = false;
                    startMatch();
                } else if (playersNumber(1)) {
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

        //判断游戏人数是否大于n人
        private boolean playersNumber(int n) {
            synchronized (Lobby.this) {
                long readyPlayers = clients.entrySet().stream().map(Map.Entry::getValue).filter(ready -> ready).count();
                return readyPlayers >= n;
            }
        }

        //计时1000秒,等待1000 否则就直接开始start match
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

    private void closeTimer() {
        timer.cancel();
        timer = new Timer();
        queue.resetTimer();
    }

    //***************************************** start match time part end ***********************************

    //entry set 返回此映射中包含的映射关系的 Set 视图。
    private synchronized void startMatch() {
        List<ClientInterface> playingClients = new ArrayList<>();
        for (Map.Entry<ClientInterface, Boolean> entry : clients.entrySet()) {
            // when a client is adding to this match, we have this procedure
            if (entry.getValue() && currentMatch.isNotFull()) {
                ClientInterface client = entry.getKey();
                currentMatch.addClient(client, controllers.get(client));
                currentMatch.setPlayer(usernames.get(client), client);
                ServerController serverController = controllers.get(client);
                serverController.setMatch(currentMatch);
                try {
                    serverController.nextState(new ChooseMapState(serverController, client));
                    Logger.getGlobal().log(Level.INFO,"next state choose map state ");
                } catch (RemoteException e) {
                    Logger.getGlobal().warning("There has been an error: " + e.getMessage());
                }
                playingClients.add(client);
            }
        }
        //如果已经成功加入到一个match，则从这里删除以便重新计算 queue
        for (ClientInterface c : playingClients) {
            remove(c);
        }
        currentMatch.startWhenReady();

        //为下一个桌面做准备
        createMatch();
        closeTimer();
    }

    private synchronized void createMatch() {
        String matchID = UUID.randomUUID().toString();
        currentMatch = new MatchController(matchID);
        matches.put(matchID, currentMatch);
    }



}