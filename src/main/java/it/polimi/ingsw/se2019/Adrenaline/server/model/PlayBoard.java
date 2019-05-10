package it.polimi.ingsw.se2019.Adrenaline.server.model;

import java.util.ArrayList;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Map;
import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.WeaponCard;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.MapA;

import java.util.*;


public class PlayBoard {

    private ArrayList<Player> allPlayers;
    //private int playBoardMode;
    private int[] numDamageOnSkullBoard;
    private Color[] colorDamageOnSkullBoard; //与num一一对应
    private Player currentPlayer;
    private Map map;
    private WeaponCard[][][] weaponCardsOnBoard;
    private int numKillShoot; //记录射杀的总数 即玩家设定的 一局死多少人后结束
    private int killTurn;// 记录本场第几次的射杀
    private int firstPlayer; //
    private boolean firenzyTriggerd;

    // constructor
    public PlayBoard(Map map, ArrayList<Player> allPlayers) {
        this.map = map;
        this.allPlayers = allPlayers;
        this.numKillShoot = 5; //默认玩五局 玩家可再修改 选择 5-8 局
        numDamageOnSkullBoard = new int[numKillShoot];
        colorDamageOnSkullBoard = new Color[numKillShoot];
        weaponCardsOnBoard = new WeaponCard[][][]{};
        killTurn = 0;
        setCurrentPlayer();
        firenzyTriggerd = false;
        for(Player player : this.allPlayers){
            player.setPlayBoard(this);
        }
    }
    //overload only for test
    public PlayBoard(int numKillShoot) {
        map = new MapA();
        map.initialMap();
        this.allPlayers = new ArrayList<>();
        this.numKillShoot = numKillShoot;
        numDamageOnSkullBoard = new int[numKillShoot];
        colorDamageOnSkullBoard = new Color[numKillShoot];
        weaponCardsOnBoard = new WeaponCard[][][]{};
        killTurn = 0;
        firenzyTriggerd = false;
    }


    public void addPlayers(Player player){
        allPlayers.add(player);
    }

    public Player findPlayerByColor (Color playerColor){
        int i = 0;
        while(allPlayers.get(i).playerColor != playerColor){
            i++;
            if(i == allPlayers.size()){
                //not exists this player
                break;
            }
        }
        return allPlayers.get(i);
    }
    //生成0到玩家数量-1的随机数， 只决定第一个玩家
    public Player setCurrentPlayer(){
        this.firstPlayer = (int)(System.currentTimeMillis()%(allPlayers.size()));
        currentPlayer = allPlayers.get(firstPlayer);
        return currentPlayer;
    }
    public Player setCurrentPlayer(Player player){
        currentPlayer = player;
        return currentPlayer;
    }
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
                if(!allPlayers.get(index).alive){
                    killTurn++;
                    numDamageOnSkullBoard[killTurn-1] = allPlayers.get(index).getKillShootTrack().getBeKilled();
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
    public void setNumKillShoot(int numKillShoot) {
        this.numKillShoot = numKillShoot;
        //当玩家设定的局数不在5-8内 提示重新设置

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

    public WeaponCard[][][] getWeaponCardsOnBoard() {
        return weaponCardsOnBoard;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

}