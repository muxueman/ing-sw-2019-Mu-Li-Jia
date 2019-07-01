package it.polimi.ingsw.se2019.Adrenaline.server.model;

import java.util.ArrayList;

import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.*;
import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.WeaponCard;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Cell;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Map;
import it.polimi.ingsw.se2019.Adrenaline.utils.exceptions.InvalidNameException;
import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.Status;
import org.fusesource.jansi.Ansi;

import java.util.*;

import static org.fusesource.jansi.Ansi.ansi;

public class Board implements Status {

    private Player currentPlayer;
    private Map map;
    private int firstPlayer;
    private ArrayList<Cell> pickedCell;
    private PowerupCardDeck powerupCardDeck;
    private WeaponCardDeck weaponCardDeck;
    private AmmotileCardDeck ammotileCardDeck;
    private static int numKillShoot;
    private int killTurn;
    private boolean firenzyTriggerd;
    private ArrayList<Player> allPlayers;
    //与num一一对应
    private int[] numDamageOnSkullBoard;
    private Color[] colorDamageOnSkullBoard;
    private String reconnectionToken;

    // constructor
    // this is used in controller
    public Board(Map map, int skull){
        numKillShoot = skull;
        killTurn = 0;
        firenzyTriggerd = false;
        reconnectionToken = "";
        this.map = map;
        numDamageOnSkullBoard = new int[numKillShoot];
        colorDamageOnSkullBoard = new Color[numKillShoot];
        pickedCell = new ArrayList<>();
        firstPlayer = 0;
        powerupCardDeck = new PowerupCardDeck();
        ammotileCardDeck = new AmmotileCardDeck();
        weaponCardDeck = new WeaponCardDeck();
        allPlayers = new ArrayList<>();
        initialCardsOnBoard();
    }
    public void setMap(Map map) {
        this.map = map;
        map.initialMap();
    }

    public void setNumKillShoot(int numKillShoot) {
        numKillShoot = numKillShoot;
        numDamageOnSkullBoard = new int[numKillShoot];
        colorDamageOnSkullBoard = new Color[numKillShoot];
    }

    public void setAllPlayerColor(){
        ArrayList<Color> colorArrayList = new ArrayList<>();
        colorArrayList.add(Color.YELLOW);
        colorArrayList.add(Color.RED);
        colorArrayList.add(Color.BLUE);
        colorArrayList.add(Color.GREEN);
        colorArrayList.add(Color.PINK);
        colorArrayList.add(Color.WHITE);
//        Collections.shuffle(colorArrayList);
        int i = 0;
        while(i < allPlayers.size()) {
            allPlayers.get(i).setPlayerColor(colorArrayList.get(i));
            i++;
        }
    }

    public void addPlayers(Player player){
        allPlayers.add(player);
        player.setPlayBoard(this);
    }

    public ArrayList<Player> getAllPlayers() {return this.allPlayers;}
    public Player findPlayerByColor (Color playerColor){
        int i = 0;
        while(allPlayers.get(i).getPlayerColor() != playerColor){
            i++;
            if(i == allPlayers.size()){
                return null;
            }
        }
        return allPlayers.get(i);
    }
    //生成0到玩家数量-1的随机数， 只决定第一个玩家

//    public void setCurrentPlayer(Player player){
//        currentPlayer = player;
//    }

    //返回下一个玩家 不改变 当前玩家
    public Player nextPlayer(Player currentPlayer){
        int playerTurn = allPlayers.indexOf(currentPlayer);
        if(playerTurn == allPlayers.size()-1) return allPlayers.get(0);
        else return allPlayers.get(playerTurn+1);
    }

    //在玩家每次进行shoot后 检查是否有人死
    public boolean checkIfAnyPlayerDie(){
        int index = 0;
        boolean playerDie = false;
        while(index < allPlayers.size()){
            if(index != allPlayers.indexOf(currentPlayer)){
                if(!allPlayers.get(index).isAlive()){
                    killTurn++;
                    numDamageOnSkullBoard[killTurn-1] =  allPlayers.get(index).getKillShootTrack().getBeKilled();                                                                                                                           allPlayers.get(index).getKillShootTrack().getBeKilled();
                    colorDamageOnSkullBoard[killTurn-1] = currentPlayer.getPlayerColor();
                    playerDie = true;
                    addScoreFromKST(allPlayers.get(index));
                    System.out.println(allPlayers.get(index).getKillShootTrack().getPlayerScore());
                    allPlayers.get(index).recover();
                    break;
                }
                else index++;
            }
            else index++;
        }
        return playerDie;
    }
    //重载
    public boolean checkIfAnyPlayerDie(Player shooter){
        boolean playerDie = false;
        for(Player player : allPlayers){
            if(player.isAlive()) continue;
            else {
                killTurn++;
                numDamageOnSkullBoard[killTurn-1] = player.getKillShootTrack().getBeKilled();
                colorDamageOnSkullBoard[killTurn-1] = player.getPlayerColor();
                playerDie = true;
            }
        }
        return playerDie;
    }
    //在开枪后检查是否有人死， 如果有人死，算分。
    //杀死人后 检查是否trigger firenzy
    public boolean triggerFirenzy(){
        if(killTurn == numKillShoot) firenzyTriggerd = true;
        return firenzyTriggerd;
    }

    // 从当前玩家开始 mode 变为 3 可进行两次3选一 从第一玩家到之后 mode 变为4 可进行一次2选1
    public void changefirenzyMode(){
        int index = allPlayers.indexOf(currentPlayer);
        if(index < firstPlayer){
            int count = 0;
            while(count < allPlayers.size()) {
                if(firstPlayer - index > 0){
                    allPlayers.get(index).setActionMode(3);
                    index++;
                    count++;
                }
                else{
                    allPlayers.get(index).setActionMode(4);
                    index++;
                    if(index == allPlayers.size()) index = 0;
                    count++;
                }
            }
        }
        else if(index > firstPlayer) {
            int count = 0;
            int i = firstPlayer;
            while (count < allPlayers.size()) {
                if (i >= firstPlayer && i < index) {
                    allPlayers.get(i).setActionMode(4);
                    count++;
                    i++;
                } else if (i >= index) {
                    allPlayers.get(i).setActionMode(3);
                    count++;
                    i++;
                    if (i == allPlayers.size()) i = 0;
                } else if (i >= 0 && i < firstPlayer) {
                    allPlayers.get(i).setActionMode(3);
                    i++;
                    count++;
                }

            }
        }
        else if(index == firstPlayer){
            int i = 0;
            while(i < allPlayers.size()){
                allPlayers.get(i).setActionMode(3);
                i++;
            }
        }

    }
    // 某玩家被杀后 其余玩家给自己增加计分板上的得分
    public void addScoreFromKST(Player diedPlayer){
        for(Color playerColor: diedPlayer.getKillShootTrack().getPlayerScore().keySet()){
            findPlayerByColor(playerColor).countMyScore(diedPlayer);
        }
    }
    //游戏结束后 所有玩家给自己增加得分
    public void addScoreFromPB(){
        int index = 0;
        for (Player player : allPlayers){
            while(index < colorDamageOnSkullBoard.length){
                if(player.getPlayerColor() != colorDamageOnSkullBoard[index]) index++;
                else {
                    player.addToMyScore(numDamageOnSkullBoard[index]);
                    break;
                }
            }
        }
    }
    public void initialCardsOnBoard(){
        pickedCell.addAll(map.getAllCells());
        reloadCardOnBoard();
    }

    public void setPlayers(ArrayList<Player> players) {
        for(Player player: players){
            players.add((Player)player);
        }
    }
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void reloadCardOnBoard(){
        int i = 0;
        while(i < pickedCell.size()){
            pickedCell.get(i).reload(this);
            i++;
        }
        pickedCell.clear();
    }

    public PowerupCard extractPowerupcard(){
        if(powerupCardDeck.ppCards.size() == 0){
            powerupCardDeck = new PowerupCardDeck();
        }
        PowerupCard powerupCard = powerupCardDeck.ppCards.get(0);
        powerupCardDeck.ppCards.remove(0);
        return powerupCard;
    }
    public WeaponCard extractWeapon(){
        if(weaponCardDeck.weaponCards.size() == 0){
            weaponCardDeck = new WeaponCardDeck();
        }
        WeaponCard weaponCard = weaponCardDeck.weaponCards.get(0);
        weaponCardDeck.weaponCards.remove(0);
        return weaponCard;
    }
    public AmmotileCard extractAmmotile(){
        if(ammotileCardDeck.atCards.size() == 0){
            ammotileCardDeck = new AmmotileCardDeck();
        }
        AmmotileCard ammotileCard = ammotileCardDeck.atCards.get(0);
        ammotileCardDeck.atCards.remove(0);
        return ammotileCard;
    }

    public Player getNameFromPlayer(String playerName) throws InvalidNameException {
        for(Player p : allPlayers){
            if(p.getUsername() == playerName){
                return p;
            }
        }
        throw new InvalidNameException();
    }

    public int[] getNumDamageOnSkullBoard() {
        return numDamageOnSkullBoard;
    }

    public Color[] getColorDamageOnSkullBoard() {
        return colorDamageOnSkullBoard;
    }

    public void setKillTurn(int killTurn) { this.killTurn = killTurn; }

    public int getKillTurn() { return killTurn; }

    public int getFirstPlayer() { return firstPlayer; }

    public Map getMap() { return map; }

    //public void turnNextPlayer(){
     //   setCurrentPlayer(nextPlayer(currentPlayer));
    //}


    public int getNumKillShoot() { return numKillShoot; }


    public ArrayList<Cell> getPickedCell() {
        return pickedCell;
    }

    @Override
    public Ansi toAnsi(){
        return ansi().a("map: " + map + "\n" + "numkill: " + numKillShoot+ "\n" + "firenzy:" + firenzyTriggerd);
    }
    @Override
    public String toString(){
        return "map: " + map + "\n" + "numkill: " + numKillShoot+ "\n" + "firenzy:" + firenzyTriggerd ;
    }
}