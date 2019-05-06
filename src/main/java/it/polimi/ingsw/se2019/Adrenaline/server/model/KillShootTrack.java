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
    private int numKillShoot;
    private ArrayList<Color> damageColorOnTrack;
    private ArrayList<Color> markColorOnTrack;
    private int turn;
    protected Map<Color, Integer> playerScore;
    private static int[] scoreTable = {8,6,4,2,2,1};
    private static int maxiDamageOnTrack = 12;

    public  KillShootTrack() {
        this.numKillShoot = 0;
        turn = 0;
        this.damageColorOnTrack = new ArrayList<Color>();
        this.markColorOnTrack = new ArrayList<Color>();
        playerScore = new HashMap<>();
        //this.skull = new boolean[6];
    }
    //检查是否已有该玩家的Mark
    public void addMarkToDamage(Player shooter){
        int index = 0;
        int markNum = 0;
        while(index < markColorOnTrack.size()){
            if(shooter.playerColor == markColorOnTrack.get((index))){
                markNum++;
                markColorOnTrack.remove(index);
                damageColorOnTrack.add(shooter.playerColor);
                index--;
            }
            index++;
        }
    }
    //先增加原有的mark到damage， 增加新的damage， 增加新的Mark， 检查是否已经被打死，若打死的话 先清除多余的damage，检查是否被overkill, 超杀给shooter加mark
    public void beAttacked (Player shooter, int damageNum, int markNum ) {
        addMarkToDamage(shooter);
        while(damageNum >0) {
            this.damageColorOnTrack.add(shooter.playerColor);
            damageNum--;
        }
        while(markNum >0) {
            this.markColorOnTrack.add(shooter.playerColor);
            damageNum--;
        }
        if(damageColorOnTrack.size() >= 12) {//如果被超杀死，先清除多余的
            int i = maxiDamageOnTrack;
            while (damageColorOnTrack.get(i) != null) {
                damageColorOnTrack.remove(i);
                i++;
            }
            killShoot();
            overkillMark(shooter);
        }
        else if(damageColorOnTrack.size() == 11) {
            killShoot();
        }
        else{
            System.out.println("still alive");
        }
    }
    public void overkillMark(Player shooter){
        KillShootTrack shooterKillShootTrack = shooter.getKillShootTrack();
        shooterKillShootTrack.markColorOnTrack.add(shooter.playerColor);
    }
    //count damage num of each color on the track, then add score.
    public Map countDamageOnTrack() {
        Map damageCount = new HashMap<Color, Integer>();
        ArrayList<Color> copyColorOnTrack = (ArrayList<Color>)this.damageColorOnTrack.clone();
        for(int i = 0; i < copyColorOnTrack.size()-1; i++){
            Color currentColor = copyColorOnTrack.get(i);
            int sum = 0;
            for(int j = i+1; j < copyColorOnTrack.size(); j++){
                Color comparedColor = copyColorOnTrack.get(j);
                if(currentColor == comparedColor){
                    sum++;
                    damageCount.put(currentColor, sum);
                    copyColorOnTrack.remove(j);
                    j--;
                }
                else continue;
            }
        }
        return damageCount;
    }
    public Map valueOfMapDownSort(Map inputMap) {
        List<Map.Entry<String, Integer>> infoIds = new ArrayList<Map.Entry<String, Integer>>(inputMap.entrySet());
        Collections.sort(infoIds, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return (o2.getValue() - o1.getValue());
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
        Map damageCount = new HashMap(countDamageOnTrack());
        damageCount = valueOfMapDownSort(damageCount);
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
        this.playerScore = scoreForfirstShooter(countPlayerScore());
        clearkillShootTrack();
        turn++;
    }
    public int getPlayerScore(Player player){
        return player.getMyscore();
    }


    public ArrayList<Color> getDamageColorOnTrack(){
        return damageColorOnTrack;
    }

    public void clearkillShootTrack(){
        damageColorOnTrack.clear();
    }
    public Object clone() {
        Object o = null;
        try {
            o = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return o;
    }

}