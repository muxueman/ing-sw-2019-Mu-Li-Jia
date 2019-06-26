package it.polimi.ingsw.se2019.Adrenaline.server.model;

import java.util.ArrayList;

import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.PowerupCard;
import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.PowerupCardDeck;
import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.WeaponCardDeck;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Cell;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Map;
import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.WeaponCard;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.MapA;
import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.BoardStatus;
import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.PlayerStatus;

import java.util.*;


public class Board extends BoardStatus{

//    private Player currentPlayer;
    private Map map;
    private int firstPlayer;
    private ArrayList<Cell> pickedCell;
    // constructor

    //overload only for test
    public Board(int numKillShoot) {
        super(numKillShoot);
        allPlayers = new ArrayList<>();
        numDamageOnSkullBoard = new int[numKillShoot];
        colorDamageOnSkullBoard = new Color[numKillShoot];
        pickedCell = new ArrayList<>();
        firstPlayer = 0;
    }
    public Board(ArrayList<Player> allPlayers){
        super(allPlayers);
        numDamageOnSkullBoard = new int[numKillShoot];
        colorDamageOnSkullBoard = new Color[numKillShoot];
        pickedCell = new ArrayList<>();
        firstPlayer = 0;
    }
    public Board(Map map){
        super(map);
        numDamageOnSkullBoard = new int[numKillShoot];
        colorDamageOnSkullBoard = new Color[numKillShoot];
        pickedCell = new ArrayList<>();
        firstPlayer = 0;
    }
    public void setMap(Map map) {
        this.map = map;
        map.initialMap();
    }

    public void addPlayers(Player player){
        allPlayers.add(player);
    }
    public void addAllPlayers(ArrayList<Player> players) {allPlayers.addAll(players);}

    public Player findPlayerByColor (Color playerColor){
        int i = 0;
        while(allPlayers.get(i).getPlayerColor() != playerColor){
            i++;
            if(i == allPlayers.size()){
                //not exists this player
                break;
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
                    numDamageOnSkullBoard[killTurn-1] =                                                                                                                             allPlayers.get(index).getKillShootTrack().getBeKilled();
                    colorDamageOnSkullBoard[killTurn-1] = currentPlayer.getPlayerColor();
                    playerDie = true;
                    addScoreFromKST(allPlayers.get(index));
                    System.out.println(allPlayers.get(index).getKillShootTrack().playerScore);
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
        for(Color playerColor: diedPlayer.getKillShootTrack().playerScore.keySet()){
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


    public void reloadCardOnBoard(){
        int i = 0;
        while(i < pickedCell.size()){
            pickedCell.get(i).reload();
            i++;
        }
        pickedCell.clear();
    }
    public ArrayList<PowerupCard> extractTwoPowerup(){
        ArrayList<PowerupCard> twoPowerupCard = new ArrayList<>();
        if(powerupCardDeck.ppCards.size()<1){
            powerupCardDeck = new PowerupCardDeck();
        }
        twoPowerupCard.add(powerupCardDeck.ppCards.get(0));
        powerupCardDeck.ppCards.remove(0);
        twoPowerupCard.add(powerupCardDeck.ppCards.get(0));
        powerupCardDeck.ppCards.remove(0);
        return twoPowerupCard;
    }
    public PowerupCard extractOnePowerupcard(){
        if(powerupCardDeck.ppCards.size() == 0){
            powerupCardDeck = new PowerupCardDeck();
        }
        PowerupCard powerupCard =  powerupCardDeck.ppCards.get(0);
        powerupCardDeck.ppCards.remove(0);
        return powerupCard;
    }


    public void setKillTurn(int killTurn) { this.killTurn = killTurn; }

    public int getKillTurn() { return killTurn; }

    public int getFirstPlayer() { return firstPlayer; }

    public Map getMap() { return map; }


    public int getNumKillShoot() { return numKillShoot; }

    public ArrayList<Player> getAllPlayers() {
        return allPlayers;
    }

    public int[] getNumDamageOnSkullBoard() {
        return numDamageOnSkullBoard;
    }

    public Color[] getColorDamageOnSkullBoard() {
        return colorDamageOnSkullBoard;
    }



    public ArrayList<Cell> getPickedCell() {
        return pickedCell;
    }
}