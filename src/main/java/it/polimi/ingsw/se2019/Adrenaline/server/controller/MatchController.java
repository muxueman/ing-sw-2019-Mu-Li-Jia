package it.polimi.ingsw.se2019.Adrenaline.server.controller;

/**
 * The MatchController deals with a specific match
 * @author Xueman Mu
 */

import it.polimi.ingsw.se2019.Adrenaline.client.model.Model;
import it.polimi.ingsw.se2019.Adrenaline.network.ClientInterface;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.ErrorMessage;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.PlayMessage;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.ServerMessage;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.UpdateMessage;
//import it.polimi.ingsw.se2019.Adrenaline.network.updates.PlayerStatusUpdate;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.updates.*;
import it.polimi.ingsw.se2019.Adrenaline.server.controller.controllerState.NonPlayingState;
import it.polimi.ingsw.se2019.Adrenaline.server.controller.controllerState.PlayingState;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Color;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Board;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Player;
import it.polimi.ingsw.se2019.Adrenaline.server.model.TurnHandler;
import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.PowerupCard;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.MapA;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.MapB;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.MapC;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.MapD;
import it.polimi.ingsw.se2019.Adrenaline.utils.exceptions.EndGameException;
//import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Map;
import it.polimi.ingsw.se2019.Adrenaline.utils.exceptions.InvalidGrabException;
import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.BoardStatus;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.rmi.RemoteException;
import java.util.*;
import java.util.function.BiFunction;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MatchController {

    private final String matchID;

    private Player currentPlayer;
    private Map<ClientInterface, Player> players;
    private Map<Player, ServerController> controllers;
    private Map<Player, ClientInterface> clients;
    private Map<ClientInterface,Color> figures;
    private List<Color> colors = new ArrayList<>(Arrays.asList(Color.values()));


    private HashMap<Integer, Integer> selectedKills;
    private HashMap<Integer, Integer> selectedMaps;
    private it.polimi.ingsw.se2019.Adrenaline.server.model.map.Map selectedMap;
    private int selectedKill;
    private int selectedMapInt;

    private Board playBoard;

    private TurnHandler turnHandler;
    private boolean started;
    private Timer timer;
    private int numSqawnAlready;

    public MatchController(String matchID) {
        this.matchID = matchID;
        playBoard = null;
        selectedMaps = new HashMap<>();
        selectedKills = new HashMap<>();
        selectedMapInt = 0;
        selectedKill = 5;
        colors.remove(Color.RED);
        currentPlayer = null;
        players = new HashMap<>();
        controllers = new HashMap<>();
        clients = new HashMap<>();
        figures = new HashMap<>();
        started = false;
        //roundTrack = new RoundTack();
        started = false;
        turnHandler = null;
        timer = new Timer();
        numSqawnAlready = 0;
    }

    public synchronized boolean isNotFull() {
        return !(players.size() == 5 && isReady());
    }

    private synchronized boolean isReady() {
        boolean notReady = players.entrySet().stream().map(Map.Entry::getValue)
                .anyMatch(player -> !player.isReady());
        return !notReady;
    }
    public String getPlayerID(ClientInterface clientInterface){
        return players.get(clientInterface).getPlayerID();
    }

    //************************************* initial part ****************************************

    //add the player to the match
    public synchronized void addClient(ClientInterface client, ServerController controller){
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
            figures.put(client, color);
            players.get(client).setPlayerColor(color);
        }
    }

    public synchronized void setPlayer(String username, ClientInterface client) {
        boolean taken = players.entrySet().stream().map(Map.Entry::getValue).map(Player::getUsername)
                .anyMatch(un -> un != null && un.equals(username));
        if (!taken) {
            players.get(client).setUserName(username);
        }
    }

    // start match
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

    //输入用户名之后从这里开始一个match,并且update player->clientController.updateStatus(server Message)
    private synchronized void startMatch() {
        //如果开始的人数少于三人
        if (players.size() < 2) {
            endGame(true);
            return;
        }
        if (turnHandler == null) {
            started = true;
            Logger.getGlobal().log(Level.INFO,"start a match eventually");
            //client: from waiting state to next state map select
            updateAll(new ServerMessage());
            closeTimer();
            startTimer();
        }
    }

    public void chooseMap(int selectMap) throws RemoteException{
        if (selectedMaps.containsKey(selectMap)) {
            selectedMaps.put(selectMap, selectedMaps.get(selectMap) + 1);
        } else {
            selectedMaps.put(selectMap, 1);
        }
        if (numMap() == clients.size()){
            Integer maxCount = Collections.max(selectedMaps.values());

            for (Integer i : selectedMaps.keySet()) {
                if (selectedMaps.get(i) == maxCount) {
                    selectedMapInt = i;
                    switch (i){
                        case 1: selectedMap = new MapA();break;
                        case 2: selectedMap = new MapB();break;
                        case 3: selectedMap = new MapC();break;
                        case 4: selectedMap = new MapD();break;
                        default: selectedMap = new MapB();
                    }
                }
            }
            if (selectedMapInt != 0 ){
                Logger.getGlobal().log(Level.INFO,"chose the map : {0} ", selectedMapInt);
                for (ClientInterface c : clients.values()){
                    chooseMapEach(c);
                }
            }
        }
    }

    public void chooseKill(int selectKill) throws RemoteException{
        if (selectedKills.containsKey(selectKill)) {
            selectedKills.put(selectKill, selectedKills.get(selectKill) + 1);
        } else {
            selectedKills.put(selectKill, 1);
        }
        if (numKill() == clients.size()){
            Integer maxCount = Collections.max(selectedKills.values());
            for (Integer i : selectedKills.keySet()) {
                if (selectedKills.get(i) == maxCount) {
                    this.selectedKill = i;
                }
            }
            Logger.getGlobal().log(Level.INFO,"chose the kill number: {0} ", selectedKill);
            initPlayBoard();
            for (ClientInterface c : clients.values()){
                chooseKillEach(c);
                initPlayer(c);
            }
        }
    }

    //initial playBoard with map, Numkill, players
    protected void initPlayBoard(){
        playBoard = new Board(selectedMap, selectedKill,selectedMapInt);
        addPlayersToBoard(playBoard);
        Logger.getGlobal().log(Level.INFO,"init play board success!");
    }

    // add all players on the board, including giving each player color and two powerUp card
    public void addPlayersToBoard(Board playBoard){
        for(ClientInterface key : players.keySet()){
            playBoard.addPlayers(players.get(key));
            players.get(key).setPlayBoard(playBoard);
            try{
                //每次都只取一个卡牌
                players.get(key).addPowerupCard(playBoard.extractPowerupcard());
                players.get(key).addPowerupCard(playBoard.extractPowerupcard());
            }
            catch (InvalidGrabException e){
                Logger.getGlobal().warning("Invalid Powerup Grab ");
            };
        }
        //Collections.shuffle(playBoard.getAllPlayers());//打乱allplayer 顺序 这是个arrayList
        //TurnHandler turnHandler = new TurnHandler(playBoard);// here set the current player
    }

    //初始化玩家并更新client-side status //update
    synchronized void initPlayer(ClientInterface client) throws RemoteException{
        Player player = players.get(client);
        Logger.getGlobal().log(Level.INFO,"init a player{0}",player.getPlayerID());
        String reconnectionToken = matchID + "_" + player.getPlayerID();
        Logger.getGlobal().log(Level.INFO,"reconnectionToken: {0}",reconnectionToken);
        ServerMessage serverMessageID = new ServerMessage(false,"ID", getPlayerID(client));
        client.updateStatus(serverMessageID);
        ServerMessage serverMessage = new ServerMessage(false, "Already start the match!");
        serverMessage.addStatusUpdate(new BoardUpdate(playBoard));
        serverMessage.addStatusUpdate(new MapUpdate(playBoard.getMap()));
        serverMessage.addStatusUpdate(new TokenUpdate(reconnectionToken));
        updateClient(client,serverMessage);
        Logger.getGlobal().log(Level.INFO,"finish init of {0}",player.getPlayerID());
        List<Player> readyPlayers = new ArrayList<>();
        for (Player pl: players.values()){
            players.values().forEach(p -> {
                if (p.isReady() && !p.equals(pl)) {
                    readyPlayers.add(p);
                }
            });
        }
        if (!readyPlayers.isEmpty()) {
            String messageO = "ALl players in this match: ";
            for (Player p : readyPlayers) {
                messageO += p.getUserName() + "  ";
            }
            Logger.getGlobal().log(Level.INFO,"ready players ", messageO);
            ServerMessage opponentsMessage = new ServerMessage(false, "OPPONENTS", messageO);
            updateClient(client, opponentsMessage);
        }

    }

    //send map result ot each client
    protected void chooseMapEach(ClientInterface client) throws RemoteException{
        ServerMessage serverMessage = new ServerMessage(false, "CHOOSEMAP",selectedMapInt);
        client.updateStatus(serverMessage);
    }

    //send kill result to each client
    protected void chooseKillEach(ClientInterface client) throws RemoteException {
        ServerMessage serverMessage = new ServerMessage(false, "CHOOSEKILL",selectedKill);
        client.updateStatus(serverMessage);
    }

    //calculate the number of values(client) that have already selected
    protected Integer numMap(){
        int num = 0;
        for(int m : selectedMaps.keySet()){
            num += selectedMaps.get(m);
        }
        return num;
    }
    protected Integer numKill(){
        int num = 0;
        for(int k : selectedKills.keySet()){
            num += selectedKills.get(k);
        }
        return num;
    }

    //drop a powerupcard in a SpawnLocation State
    public void spawnLocationDrop(String powerupName,ClientInterface client) throws RemoteException{
        players.get(client).dropPowerupAndGoNewCell(powerupName);
        numSqawnAlready += 1;
        Logger.getGlobal().log(Level.INFO,"dropPowerupAndGoNewCell {0} successful! ", powerupName);
        if (numSqawnAlready == players.size()){
            ServerMessage message = new ServerMessage(false, "SpawnLocation");
            message.addStatusUpdate(new SpawnLocationUpdate(playBoard,playBoard.getMap()));
            for (Player p: players.values()){
                System.out.println(p.getUserName());
                System.out.println(p.getCurrentCell().getCellID());
                System.out.println(playBoard.getMap().getAllCells().get(3).getCellPlayers().size());
                System.out.println(playBoard.getMap().getAllCells().get(5).getCellPlayers().size());
                System.out.println(playBoard.getMap().getAllCells().get(12).getCellPlayers().size());
            }
            updateAll(message);
            readyPlayers();
        }
    }

    //update all players to ready
    public void readyPlayers(){
        List<Player> definitivePlayers = new ArrayList<>();
        players.forEach((client, player) -> definitivePlayers.add(player));
        turnHandler = new TurnHandler(definitivePlayers);
        currentPlayer = turnHandler.getCurrentPlayer();
        refreshControllerStates();
    }

    //change state between playing state and non playing state
    private synchronized void refreshControllerStates() {
        Logger.getGlobal().log(Level.INFO,"start refresh controller state....");
        controllers.forEach((player, controller) -> {
            if (player.equals(currentPlayer)) {
                Logger.getGlobal().log(Level.INFO,"current player next state: playing");
                controller.nextState(new PlayingState(this));
            } else {
                controller.nextState(new NonPlayingState());
                Logger.getGlobal().log(Level.INFO,"not current player next state: nonplaying");
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
    private void updateAll(ServerMessage serverMessage, BiFunction<Player, ClientInterface, Boolean> conditions) {
        for (Map.Entry<Player, ClientInterface> entry : clients.entrySet()) {
            Player player = entry.getKey();
            ClientInterface client = entry.getValue();
            if (conditions.apply(player, client)) {
                updateClient(client, serverMessage);
            }
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
                    + players.get(client).getUsername());
            e.printStackTrace();
        }
    }
    private void updateCurrentPlayer(ServerMessage serverMessage) {
        Logger.getGlobal().warning("current player {0} "+ currentPlayer.getUserName());
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

    public synchronized void nextTurn() {
        try {
            ServerMessage serverMessage = new PlayMessage();
            UpdateMessage updateMessage = new UpdateMessage(currentPlayer.getUsername() + " passed the turn.");
//            Die chosenDie = currentPlayer.resetMoves();
//            draftPool.returnDie(chosenDie);
//            if (chosenDie != null) {
//                serverMessage.addStatusUpdate(new AdditionalStatusUpdate(null));
//                serverMessage.addStatusUpdate(new DraftPoolUpdate(draftPool.getStatus()));
//                serverMessage.setMessage("You put back the chosen die.");
//                updateMessage.addStatusUpdate(new DraftPoolUpdate(draftPool.getStatus()));
//            }
            updateCurrentPlayer(serverMessage);
            updateAll(updateMessage, (p, c) -> !p.equals(currentPlayer));
            nextPlayer();
            boolean found = false;
            while (!found) {
                if (controllers.get(currentPlayer).isActive()) {
                    found = true;
                } else {
                    nextPlayer();
                }
            }
            refreshControllerStates();
            updateCurrentPlayer(new PlayMessage());
            closeTimer();
            startTimer();
        } catch (EndGameException e) {
            endGame(false);
        }
    }

    //reconnect, 更新player的新的接口和控制器
    public boolean reconnect(String id, ServerController serverController, ClientInterface client) throws RemoteException{
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
            if (playersNumber() == 1) {
                endGame(true);
            } else if (playersNumber() == 0) {
                endMatch();
            } else if (currentController.equals(serverController)) {
                nextTurn();
            }
        } else {
            Player player = null;
            for (Player p : players.values()) {
                ServerController controller = controllers.get(p);
                if (serverController.equals(controller)) {
                    player = p;
                    break;
                }
            }
            ClientInterface client = clients.get(player);
            controllers.remove(player);
            clients.remove(player);
            players.remove(client);
//            distributedWindowPatternCards.remove(client);
//            colors.add(distributedPrivateObjectiveCards.get(client));
//            distributedPrivateObjectiveCards.remove(client);      从
        }
    }

    //number of players that are active
    private int playersNumber() {
        final int[] number = {0};
        controllers.forEach((p, c) -> {
            if (c.isActive()) {
                number[0]++;
            }
        });
        return number[0];
    }


    public List<PowerupCard> getChoosablePowerupCards(ClientInterface client) {
        List<List<PowerupCard>> temp = new ArrayList<>();
        List<PowerupCard> choosableWindowPatternCards = new ArrayList<>();
        // todo: get the initial powerup cards
        return choosableWindowPatternCards;
    }
}