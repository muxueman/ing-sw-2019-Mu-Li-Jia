package it.polimi.ingsw.se2019.Adrenaline.server.controller;

import it.polimi.ingsw.se2019.Adrenaline.network.ClientInterface;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.ErrorMessage;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.PlayMessage;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.ServerMessage;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.UpdateMessage;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Color;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Player;
import it.polimi.ingsw.se2019.Adrenaline.server.model.TurnHandler;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.MapA;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.MapB;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.MapC;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.MapD;
import it.polimi.ingsw.se2019.Adrenaline.utils.exceptions.EndGameException;
//import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.rmi.RemoteException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class MatchController {

    private final String matchID;

    private Player currentPlayer;
    private Map<ClientInterface, Player> players;
    private Map<Player, ServerController> controllers;
    private Map<Player, ClientInterface> clients;
    private List<Color> colors = new ArrayList<>(Arrays.asList(Color.values()));

    private TurnHandler turnHandler;
    private boolean started;
    private Timer timer;

    public MatchController(String matchID) {
        this.matchID = matchID;
        colors.remove(Color.RED);
        currentPlayer = null;
        players = new HashMap<>();
        controllers = new HashMap<>();
        clients = new HashMap<>();
        started = false;
        //roundTrack = new RoundTack();
        started = false;
        turnHandler = null;
        timer = new Timer();
    }

    public synchronized boolean isNotFull() {
        return !(players.size() == 5 && isReady());
    }

    private synchronized boolean isReady() {
        boolean notReady = players.entrySet().stream().map(Map.Entry::getValue)
                .anyMatch(player -> !player.isReady());
        return !notReady;
    }

    //add the player to the match
    public synchronized void addClient(ClientInterface client, ServerController controller) {
        if (isNotFull()) {
            //shuffle the colors
            Collections.shuffle(colors);
            Color color = colors.get(0);
            colors.remove(0);
            UUID uuid = UUID.randomUUID();
            Player player = new Player(uuid.toString());
            players.put(client, player);
            controllers.put(player, controller);
            clients.put(player, client);
        }
    }

    public synchronized void setPlayer(String username, ClientInterface client) {
        boolean taken = players.entrySet().stream().map(Map.Entry::getValue).map(Player::getUserName)
                .anyMatch(un -> un != null && un.equals(username));
        if (!taken) {
            players.get(client).setUserName(username);
            Logger.getGlobal().log(Level.INFO,"New player: {0}", username);
        }
    }

    synchronized List<it.polimi.ingsw.se2019.Adrenaline.server.model.map.Map> getChoosableMap(ClientInterface client) {
        List<it.polimi.ingsw.se2019.Adrenaline.server.model.map.Map> choosableMap = new ArrayList<>();
        choosableMap.add(new MapA());
        choosableMap.add(new MapB());
        choosableMap.add(new MapC());
        choosableMap.add(new MapD());
        int i = 0;
        if(clients.size() == 3){
            choosableMap.remove(3);
        }
        else if(clients.size() == 5){
            choosableMap.remove(0);
        }
        return choosableMap;
    }

    public void startWhenReady() {
        (new MatchStarter()).start();
    }

    private class MatchStarter extends Thread {

        private boolean active = true;

        @Override
        public void run() {
            while (active) {
                if (isReady()) {
                    startMatch();
                    active = false;
                }
            }
        }
    }


    synchronized void initPlayer(ClientInterface client) {
        Player player = players.get(client);
        String reconnectionToken = matchID + "_" + player.getPlayerID();
        ServerMessage serverMessage = new ServerMessage(false, "This is your board:");
//        serverMessage.addStatusUpdate(new PlayerStatusUpdate(player.getStatus()));
//        serverMessage.addStatusUpdate(new PrivateObjectiveUpdate(player.getPrivateObjectiveCard()));
//        serverMessage.addStatusUpdate(new PublicObjectiveUpdate(publicObjectiveCards));
//        List<ToolCardStatus> toolCardStatuses = new ArrayList<>();
//        for (ToolCard toolCard : toolCards) {
//            toolCardStatuses.add(toolCard.getStatus());
//        }
//        serverMessage.addStatusUpdate(new ToolCardsUpdate(toolCardStatuses));
//        serverMessage.addStatusUpdate(new DraftPoolUpdate(draftPool.getStatus()));
//        serverMessage.addStatusUpdate(new RoundTrackUpdate(roundTrack.getStatus()));
//        serverMessage.addStatusUpdate(new TokenUpdate(reconnectionToken));
        updateClient(client, serverMessage);
        List<Player> readyPlayers = new ArrayList<>();
        players.values().forEach(p -> {
            if (p.isReady() && !p.equals(player)) {
                readyPlayers.add(p);
            }
        });
        if (!readyPlayers.isEmpty()) {
            ServerMessage opponentsMessage = new ServerMessage(false, "These are your current opponents:");
            for (Player p : readyPlayers) {
//                opponentsMessage.addStatusUpdate(new PlayerStatusUpdate(p));
            }
            updateClient(client, opponentsMessage);
        }
    }
    private synchronized void startMatch() {
        //如果开始的人数少于三人
        if (players.size() < 3) {
            endGame(true);
            return;
        }
        if (turnHandler == null) {
            started = true;
            List<Player> definitivePlayers = new ArrayList<>();
            players.forEach((client, player) -> definitivePlayers.add(player));
            turnHandler = new TurnHandler(definitivePlayers);
//            refreshDraftPool();
//            currentPlayer = turnHandler.getCurrentPlayer();
//            refreshControllerStates();
//            updateAll(new PlayMessage());
//            updateCurrentPlayer(new PlayMessage());
            closeTimer();
            startTimer();
        }
    }

    // put the remaining dice in the round track
//    private synchronized void refreshDraftPool() {
//        if (roundTrack.getCurrentRound() != 0) {
//            roundTrack.insertDice(draftPool.getDice(), roundTrack.getCurrentRound() - 1);
//        }
//        draftPool.setDraftPool(diceBag.roundDraw(players.size()));
//        UpdateMessage updateMessage = new UpdateMessage("This is the draft pool right now:");
//        updateMessage.addStatusUpdate(new DraftPoolUpdate(draftPool.getStatus()));
//        updateMessage.addStatusUpdate(new RoundTrackUpdate(roundTrack.getStatus()));
//        updateAll(updateMessage);
//    }

    //change state between playing state and non playing state
    private synchronized void refreshControllerStates() {
        controllers.forEach((player, controller) -> {
            if (player.equals(currentPlayer)) {
                controller.nextState(new PlayingState(this));
            } else {
                controller.nextState(new NonPlayingState());
            }
        });
    }

   //pass to next player
    private synchronized void nextPlayer() throws EndGameException {
        //currentPlayer = turnHandler.nextTurn();
        //if (turnHandler.isNewRound()) {
            //refreshDraftPool();
        //}
    }


    private void endGame(boolean lessPlayer) {
        ErrorMessage endGameMessage = new ErrorMessage("ENDGAME");
        updateAll(endGameMessage);
        if (lessPlayer) {
            Player activePlayer = null;
            for (Player player : players.values()) {
                ServerController serverController = controllers.get(player);
                if (serverController.isActive()) {
                    activePlayer = player;
                }
            }
//            if (activePlayer != null) {
//                Map<Player, Score> ranking = new HashMap<>();
//                ranking.put(activePlayer, countPoints(activePlayer));
//                UpdateMessage updateMessage = new UpdateMessage("You won because everyone left!");
//                updateMessage.addStatusUpdate(new RankingUpdate(new Ranking(ranking)));
//                updateClient(clients.get(activePlayer), updateMessage);
//            }
//        } else {
//            List<Player> playerList = turnHandler.getPlayers();
//            Map<Player, Score> ranking = new HashMap<>();
//            for (Player player : playerList) {
//                ranking.put(player, countPoints(player));
//            }
            UpdateMessage updateMessage = new UpdateMessage("");
//            updateMessage.addStatusUpdate(new RankingUpdate(new Ranking(ranking)));
            updateAll(updateMessage);
        }
        endMatch();
    }


    private void endMatch() {
        closeTimer();
    }


    public void startTimer() {
        try (Scanner input = new Scanner(MatchController.class.getResourceAsStream("/config.json"))){
            //Read the content of the file
            StringBuilder jsonIn = new StringBuilder();
            while(input.hasNextLine()) {
                jsonIn.append(input.nextLine());
            }
            JSONParser parser = new JSONParser();
            JSONObject root = (JSONObject) parser.parse(jsonIn.toString());
            JSONArray jsonArray = (JSONArray) root.get("timer");
            JSONObject cell = (JSONObject) jsonArray.get(0);
            final int seconds = Integer.parseInt((String) cell.get("seconds")) + 30;
            timer.scheduleAtFixedRate(new GameTimerTask(seconds),0,1000);
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getGlobal().warning(e.getMessage());
        }
    }
    private void closeTimer() {
        timer.cancel();
        timer = new Timer();
    }
    private void updateAll(ServerMessage serverMessage) {
        for (ClientInterface client : clients.values()) {
            updateClient(client, serverMessage);
        }
    }


    //update client from the server part
    private void updateClient(ClientInterface client, ServerMessage serverMessage) {
        try {
            ServerController serverController = controllers.get(players.get(client));
            if (serverController.isActive()) {
                client.updateStatus(serverMessage);
            }
        } catch (RemoteException e) {
            Logger.getGlobal().warning("There has been a connection error from player "
                    + players.get(client).getUserName());
        }
    }
    private void updateCurrentPlayer(ServerMessage serverMessage) {
        ClientInterface client = clients.get(currentPlayer);
        updateClient(client, serverMessage);
    }
    private class GameTimerTask extends TimerTask {
        private int seconds;

        @Override
        public void run() {
            if(seconds == 0) {
                ErrorMessage errorMessage = new ErrorMessage("TIMER");
                updateCurrentPlayer(errorMessage);
                nextTurn();
            }
            seconds--;
        }
        private GameTimerTask(int seconds) {
            this.seconds = seconds;
        }

    }

    synchronized void nextTurn() {
//        try {
//            ServerMessage serverMessage = new PlayMessage();
//            UpdateMessage updateMessage = new UpdateMessage(currentPlayer.getUsername() + " passed the turn.");
//            Die chosenDie = currentPlayer.resetMoves();
//            draftPool.returnDie(chosenDie);
//            if (chosenDie != null) {
//                serverMessage.addStatusUpdate(new AdditionalStatusUpdate(null));
//                serverMessage.addStatusUpdate(new DraftPoolUpdate(draftPool.getStatus()));
//                serverMessage.setMessage("You put back the chosen die.");
//                updateMessage.addStatusUpdate(new DraftPoolUpdate(draftPool.getStatus()));
//            }
//            updateCurrentPlayer(serverMessage);
//            updateAll(updateMessage, (p, c) -> !p.equals(currentPlayer));
//            nextPlayer();
//            boolean found = false;
//            while (!found) {
//                if (controllers.get(currentPlayer).isActive()) {
//                    found = true;
//                } else {
//                    nextPlayer();
//                }
//            }
//            refreshControllerStates();
//            updateCurrentPlayer(new PlayMessage());
//            closeTimer();
//            startTimer();
//        } catch (EndGameException e) {
//            endGame(false);
//        }
//    }
    }

    //reconnect, 更新player的新的接口和控制器
    public boolean reconnect(String id, ServerController serverController, ClientInterface client) {
        Player player = null;
        ClientInterface clientInterface;
        for (Player p : players.values()) {
            if (p.getPlayerID().equals(id)) {
                clientInterface = clients.get(p);
                player = p;
                players.remove(clientInterface);
                players.put(client, player);
                clients.replace(p, client);
                controllers.replace(p, serverController);
                break;
            }
        }
        if (player != null) {
            PlayMessage playMessage = new PlayMessage();
            playMessage.setMessage("RECONNECT");
            updateClient(client, playMessage);
            initPlayer(client);
            serverController.setMatch(this);
            return true;
        }
        return false;
    }

    public void disconnect(ServerController serverController) {
        ServerController currentController = controllers.get(currentPlayer);
        if (started) {
//            if (playersNumber() == 1) {
//                endGame(true);
//            } else if (playersNumber() == 0) {
//                endMatch();
//            } else if (currentController.equals(serverController)) {
//                nextTurn();
//            }
//        } else {
//            Player player = null;
//            for (Player p : players.values()) {
//                ServerController controller = controllers.get(p);
//                if (serverController.equals(controller)) {
//                    player = p;
//                    break;
//                }
//            }
//            ClientInterface client = clients.get(player);
//            controllers.remove(player);
//            clients.remove(player);
//            players.remove(client);
//            windowPatternCards.addAll(distributedWindowPatternCards.get(client));
//            distributedWindowPatternCards.remove(client);
//            colors.add(distributedPrivateObjectiveCards.get(client));
//            distributedPrivateObjectiveCards.remove(client);
        }
    }




}
