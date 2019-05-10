package it.polimi.ingsw.se2019.Adrenaline.server.model;

import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 *
 */
public class KillShootTrack implements Cloneable {

    private Player player;
    private int turn;
    private ArrayList<Color> damageColorOnTrack;
    private ArrayList<Color> markColorOnTrack;
    protected Map<Color, Integer> playerScore;
    private static int[] scoreTable = {8,6,4,2,2,1};
    private static int maxiDamageOnTrack = 12;
    protected int numKillShoot; // 被射杀的次数
    private int beKilled; // 0 没有被杀， 1 被杀， 2 被超杀

    public  KillShootTrack(Player player) {

        this.player = player;
        turn = 0;
        this.damageColorOnTrack = new ArrayList<Color>();
        this.markColorOnTrack = new ArrayList<Color>();
        playerScore = new HashMap<>();
        beKilled = 0;
    }
    //overload
    public  KillShootTrack() {
        turn = 0;
        this.damageColorOnTrack = new ArrayList<Color>();
        this.markColorOnTrack = new ArrayList<Color>();
        playerScore = new HashMap<>();
        beKilled = 0;
    }

    public int getNumKillShoot() {
        return numKillShoot;
    }

    public int getBeKilled() {
        return beKilled;
    }

    //检查是否已有该玩家的Mark
    public void addMarkToDamage(Player shooter){
        int index = 0;
        while(index < markColorOnTrack.size()){
            if(shooter.playerColor == markColorOnTrack.get((index))){
                markColorOnTrack.remove(index);
                damageColorOnTrack.add(shooter.playerColor);
                index--;
            }
            index++;
        }
    }

    //先增加原有的mark到damage， 增加新的damage， 增加新的Mark， 检查是否已经被打死，若打死的话 先清除多余的damage，检查是否被overkill, 超杀给shooter加mark
    public int beAttacked (Player shooter, int damageNum, int markNum ) {
        addMarkToDamage(shooter);
        while(damageNum >0) {
            this.damageColorOnTrack.add(shooter.playerColor);
            damageNum--;
        }

        while(markNum >0) {
            this.markColorOnTrack.add(shooter.playerColor);
            markNum--;
        }
        if(damageColorOnTrack.size() >= 12) {//如果被超杀死，先清除多余的
            int i = maxiDamageOnTrack;
            while (damageColorOnTrack.size() - maxiDamageOnTrack != 0) {
                damageColorOnTrack.remove(damageColorOnTrack.size()-1);
            }
            /*
            while (damageColorOnTrack.get(i) == shooter.getPlayerColor()) {
                damageColorOnTrack.remove(shooter.getPlayerColor());
            }
            */
            player.alive = false;
            killShoot();
            overkillMark(shooter);
            numKillShoot++;
            beKilled = 2;
            clearKillShootTrack();

        }
        else if(damageColorOnTrack.size() == 11) {
            player.alive = false;
            killShoot();
            numKillShoot++;
            beKilled = 1;
        }
        else{
            beKilled = 0;// 未被杀死
        }
        return beKilled;
    }

    public void overkillMark(Player shooter) {
        shooter.getKillShootTrack().markColorOnTrack.add(player.playerColor);
    }
    //count damage num of each color on the track, then add score.
    private Map countDamageOnTrack() {
        Map<Color, Integer> damageCount = new HashMap<Color, Integer>();
        for(Color color : damageColorOnTrack){
            if(damageCount.containsKey(color)) {
                Integer times = damageCount.get(color);
                times++; // 不会每次都创建新对象了
                damageCount.put(color, times);
            }
            else {
                damageCount.put(color, new Integer(1));
            }
        }
        return damageCount;
    }

    //用于给各玩家damage num排序
    private Map valueOfMapDownSort(Map inputMap) {
        List<Map.Entry<String, Integer>> infoIds = new ArrayList<Map.Entry<String, Integer>>(inputMap.entrySet());
        Collections.sort(infoIds, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return (-(o1.getValue() - o2.getValue()));
                //return (o1.getKey()).toString().compareTo(o2.getKey());
            }
        });
        return inputMap;
    }


    public ArrayList<Integer> checkHighestScore(){
        ArrayList<Integer> scoreArray = new ArrayList<Integer>();
        int index = 0;
        while((index + turn) < 6){
            scoreArray.add(scoreTable[index +turn]);
            index++;
        }
        return scoreArray;

    }

    public Map countPlayerScore() {
        Map damageCount = new HashMap(valueOfMapDownSort(countDamageOnTrack()));
        Map playerScore = new HashMap<Color, Integer>();
        int i = 0;
        ArrayList<Integer> scoreArray = checkHighestScore();
        for (Object key : damageCount.keySet()){
            playerScore.put(key, scoreArray.get(i));
            i++;
        }
        return playerScore;
    }

    public Map scoreForfirstShooter(Map playerScore){
        int sum = (int)(playerScore.get(this.damageColorOnTrack.get(0)));
        sum++;
        playerScore.put(this.damageColorOnTrack.get(0),sum);
        return playerScore;
    }
    /*
    public void addScoreToPlayer(ArrayList<Player> players){
        Map playerScore = new HashMap(scoreForfirstShooter(countPlayerScore()));
        int index = 0;
        while(index < players.size()){
            players.get(index).playerColor =
        }
    }
    */
    //被射杀，计算damage数，排序，查看最高分数（第几次被射杀），增加玩家的分数，第一射手得得分加1, 清理板子
    public void killShoot(){
        this.playerScore = countPlayerScore();
        this.playerScore = scoreForfirstShooter(playerScore);
        System.out.println(playerScore.toString());
        turn++;
    }

    public void recover(){
        clearKillShootTrack();
        turn++;
        playerScore.clear();
    }
    //计算板子上各玩家该得分数后 放入this.playerscore， 给每个玩家加分的步骤  由存有所有玩家的类来调用
    public void addPlayerScore(Player player){
        for (Color key : playerScore.keySet()){
            if(player.playerColor == key){
                player.myScore += playerScore.get(key);
                break;
            }
        }
    }

    public void setNumKillShoot(int numKillShoot) { this.numKillShoot = numKillShoot; }

    public void setBeKilled(int beKilled) { this.beKilled = beKilled; }

    public void setDamageColorOnTrack(ArrayList<Color> damageColorOnTrack) { this.damageColorOnTrack = damageColorOnTrack; }

    public void setMarkColorOnTrack(ArrayList<Color> markColorOnTrack) { this.markColorOnTrack = markColorOnTrack; }

    public void setPlayer(Player player) { this.player = player; }

    public void setPlayerScore(Map<Color, Integer> playerScore) { this.playerScore = playerScore; }

    public static void setScoreTable(int[] scoreTable) { KillShootTrack.scoreTable = scoreTable; }

    public void setTurn(int turn) { this.turn = turn; }

    public ArrayList<Color> getDamageColorOnTrack(){
        return damageColorOnTrack;
    }

    public ArrayList<Color> getMarkColorOnTrack() {
        return markColorOnTrack;
    }

    public void clearKillShootTrack(){
        damageColorOnTrack.clear();
    }


    /*
    public Object clone() {
        Object o = null;
        try {
            o = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return o;
    }
    */
    public Player getPlayer() {
        return this.player;
    }

    public Map<Color, Integer> getPlayerScore() { return playerScore; }

    @Override
    public String toString() {
        String resultDamgeOnTrack = "damageColorOnTrack: ";
        for(Color color : damageColorOnTrack){
            resultDamgeOnTrack += color.toString();
        }
        String resultMarkOnTrack = "markColorOnTrack: ";
        for(Color color : markColorOnTrack){
            resultMarkOnTrack += color.toString();
        }

        return "KillShootTrackColor: " + getPlayer().getPlayerColor() + "\n"
                + resultDamgeOnTrack
                + resultMarkOnTrack;
    }
}