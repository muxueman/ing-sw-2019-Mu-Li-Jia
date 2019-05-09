package it.polimi.ingsw.se2019.Adrenaline.server.model.effect;
import java.util.Scanner;


public class EffectInNanoTracerMode {
    public EffectInNanoTracerMode() {
    }
    public void dd() {
        double rate1 = 0.1, rate2 = 0.075, rate3 = 0.05, rate4 = 0.03, rate5 = 0.014, rate6 = 0.01;
        long reward = 0;
        System.out.println("请输入利润：");
        Scanner sc = new Scanner(System.in);
        long money = sc.nextLong();
        if (money >= 0 && money <= 100000) {
            reward = (long) (money * rate1);
        } else if (money <= 200000) {
            reward = (long) (100000 * rate1 + (money - 100000) * rate2);
        } else if (money <= 400000) {
            reward = (long) (100000 * rate1 + 100000 * rate2 + (money - 200000) * rate3);
        } else if (money <= 600000) {
            reward = (long) (10000 * rate1 + 100000 * rate2 + 200000 * rate3
                    + (money - 400000) * rate4);
        } else if (money <= 1000000) {
            reward = (long) (10000 * rate1 + 100000 * rate2 + 200000 * rate3 + 200000
                    * rate4 + (money - 6000000) * rate5);
        } else {
            reward = (long) (10000 * rate1 + 100000 * rate2 + 200000 * rate3 + 200000
                    * rate4 + 400000 * rate5 + (money - 1000000) * rate6);
        }
        System.out.println("奖金为："+reward);
    }
}
